package com.viewt.massage;

import com.viewt.job.core.SchedulerManager;
import com.viewt.massage.day.MassageActivityApplication;
import com.viewt.massage.day.MassageActivityDataApplication;
import com.viewt.massage.day.MassageShopApplication;
import com.viewt.massage.day.MassageShopDataApplication;
import com.viewt.massage.hour.MassageActivityDetailApplication;
import com.viewt.massage.hour.MassageShopDetailApplication;
import com.viewt.massage.mail.UploadMassageData2mailBootstrap;
import com.viewt.rest.data.util.Cons;
import com.viewt.rest.data.util.Log4jUtil;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author tangyu
 * @since 2018-01-09 20:53
 * -Darea=massage
 */
public class MassageBootstrap implements Runnable, Job {
    private static Logger logger = LoggerFactory.getLogger(MassageBootstrap.class);

    public MassageBootstrap() {
    }

    public MassageBootstrap(String[] args) {
        this.args = args;
    }

    public static void main(String[] args) throws IOException {
        invoke(args);
        scheduleLoad(args);
    }

    private static void scheduleLoad(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(new MassageBootstrap(args), 20, 20, TimeUnit.SECONDS);
    }

    long lastModified;

    @Override
    public void run() {
        String confFile = getConfFile();
        File file = new File(confFile);
        long latestModified = file.lastModified();
        logger.error("正在检查文件{},时间{}", file.getAbsolutePath(), latestModified);
        if (lastModified == 0) {
            lastModified = latestModified;
        } else {
            if (lastModified != latestModified) {
                try {
                    logger.error("正在reload文件 执行invoke");
                    MassageBootstrap.invoke(args);
                    logger.error("正在reload文件 执行invoke 完成");
                    lastModified = latestModified;
                } catch (IOException e) {
                    logger.error(e.getMessage(), e.getCause());
                }
            }

        }
    }

    private static void invoke(String[] args) throws IOException {

        Properties properties = loadConf(getConfFile());
        String shopCron = properties.getProperty(Cons.Job.JOB_MASSAGE_SHOP);
        String detailCron = properties.getProperty(Cons.Job.JOB_MASSAGE_SHOP_DETAIL);
        String data2mailCron = properties.getProperty(Cons.Job.JOB_MASSAGE_DATA_TO_MAIL_TIME);

        SchedulerManager instance = SchedulerManager.getInstance();

        instance.addJob4MassageShop(shopCron);

        instance.addJob4MassageShopDetail(detailCron);

        instance.addJob4MassageData2Mail(data2mailCron);

    }

    public static String getConfFile() {
        return Cons.USER_DIR + "/conf/conf.properties";
    }
    public static String getMailConfFile() {
        return Cons.USER_DIR + "/conf/mail.properties";
    }

    public static Properties loadConf(String configFile) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(configFile));
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return properties;
    }

    private String[] args;

    /**
     * 零点调度一次
     */
    public void runShop() {

        String area;

        area = "massage-activety";
        Log4jUtil.reconfigure(area);
        MassageActivityApplication.main(args);

        area = "massage-activety-data";
        Log4jUtil.reconfigure(area);
        MassageActivityDataApplication.main(args);

        area = "massage-shop";
        Log4jUtil.reconfigure(area);
        MassageShopApplication.main(args);

        area = "massage-shop-data";
        Log4jUtil.reconfigure(area);
        MassageShopDataApplication.main(args);
    }

    /**
     * 每小时
     */
    public void runShopDetail() {

        String area;

        area = "massage-activety-detail";
        Log4jUtil.reconfigure(area);
        MassageActivityDetailApplication.main(args);

        area = "massage-shop-detail";
        Log4jUtil.reconfigure(area);
        MassageShopDetailApplication.main(args);

    }

    /**
     * 每小时
     */
    public void runData2Mail() {
        UploadMassageData2mailBootstrap.main(args);
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail = context.getJobDetail();
        JobKey key = jobDetail.getKey();
        switch (key.getName()) {
            case Cons.Job.JOB_MASSAGE_SHOP:
                logger.error("runShop 这在调度商户任务 ");
                runShop();
                logger.error("runShop 这在调度商户任务 done ");
                break;
            case Cons.Job.JOB_MASSAGE_SHOP_DETAIL:
                logger.error("runShopDetail 这在调度商户任务 ");
                runShopDetail();
                logger.error("runShopDetail 这在调度商户任务 done ");
                break;
            case Cons.Job.JOB_MASSAGE_DATA_TO_MAIL_TIME:
                logger.error("runData2Mail 这在调度商户任务 ");
                runData2Mail();
                logger.error("runData2Mail 这在调度商户任务 done ");
                break;
            default:
                break;
        }
    }
}
