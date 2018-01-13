package com.viewt.job.core;

import com.viewt.massage.MassageBootstrap;
import com.viewt.rest.data.util.Cons;
import jdk.nashorn.internal.scripts.JO;
import org.apache.poi.ss.formula.functions.T;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author tangyu
 * @since 2018-01-10 09:53
 */
public class SchedulerManager {

    protected final static Logger logger = LoggerFactory.getLogger(SchedulerManager.class);
    private static SchedulerManager instance;
    private Scheduler scheduler;

    private SchedulerManager() {
        try {
            scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
        } catch (SchedulerException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static SchedulerManager getInstance() {
        if ((null == instance)) {
            synchronized (SchedulerManager.class) {
                if ((null == instance)) {
                    instance = new SchedulerManager();
                    return instance;
                }
            }
        }
        return instance;
    }


    private void addJob(String jobName, String triggerName, String cron, Class<? extends Job> jobClass, Map<? extends String, ?> paramMap) throws SchedulerException, InterruptedException {

        String group = "data";

        JobKey jobKey = new JobKey(jobName, group);
        if (scheduler.checkExists(jobKey)) {
            scheduler.deleteJob(jobKey);
        }

        TimeUnit.SECONDS.sleep(1);
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, "data").build();
        if (Objects.nonNull(paramMap)) {
            JobDataMap jobDataMap = jobDetail.getJobDataMap();
            jobDataMap.putAll(paramMap);
        }
        //Cron Trigger  周期性的  每天 、每月、 每年 执行具体函数                        貌似天不能用“*”
        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(triggerName, group)
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule(cron))
//	                .withSchedule(CronScheduleBuilder.cronSchedule( "0 0 7 ? * *"     ) )
                .build();
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    public void addJob4MassageShop(String cron) {
        String jobName = Cons.Job.JOB_MASSAGE_SHOP;
        String triggerName = Cons.Job.JOB_MASSAGE_SHOP;
        Map<String, String> map = new HashMap<>(1);
        String type = "type";
        map.put(type, Cons.Job.TYPE_SHOP);
        try {
            addJob(jobName, triggerName, cron, MassageBootstrap.class, map);
        } catch (Exception e) {
            logger.error("添加任务错误。。。");
        }
    }


    public void addJob4MassageShopDetail(String cron) {
        String jobName = Cons.Job.JOB_MASSAGE_SHOP_DETAIL;
        String triggerName = Cons.Job.JOB_MASSAGE_SHOP_DETAIL;
        Map<String, String> map = new HashMap<>(1);
        String type = "type";
        map.put(type, Cons.Job.TYPE_SHOP_DETAIL);
        try {
            addJob(jobName, triggerName, cron, MassageBootstrap.class, map);
        } catch (Exception e) {
            logger.error("添加任务错误。。。");
        }
    }


}