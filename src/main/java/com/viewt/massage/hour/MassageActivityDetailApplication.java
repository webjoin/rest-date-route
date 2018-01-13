package com.viewt.massage.hour;

import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.data.bean.MassageActivityBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.Cons;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangyu
 * @since 2018-01-09 09:58
 * area=massage-activety-detail
 */
public class MassageActivityDetailApplication extends BaseBootstrap {
    private static Logger logger = LoggerFactory.getLogger(MassageActivityDetailApplication.class);

    public static void main(String[] args) {
        MassageActivityDetailApplication bootstrap = new MassageActivityDetailApplication();
        try {
            long stime = System.currentTimeMillis();
            logger.error("{} , start...", bootstrap.dc_index);
            bootstrap.start(args);
            long etime = System.currentTimeMillis();
            logger.error("{} , end...it tooks {}s", bootstrap.dc_index, (etime - stime) / 1000);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("end...");
        }
    }

    UrlService urlService;
    int qty = 0;
    int dc_index = -1;

    @Override
    protected void crawl(String[] args) {
        String filepath = Cons.USER_DIR + "/logs/other/day-massage-activety-data.log";
        List<String> ids = getIds(filepath);

        String cookie = "_lxsdk_cuid=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _lxsdk=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _hc.v=\"\\\"f21d88bb-1c97-40ec-8046-549b1af9070b.1514345590\\\"\"; PHOENIX_ID=0a45d1c9-160d6383ebc-181e705; cy=1; cye=shanghai; _tr.u=36Gnj6oGAqP4s3Na; JSESSIONID=C5FF9D30D4F84C0D75DB21BF2FCD01C6; cityid=1; default_ab=shop%3AA%3A1%7CshopList%3AA%3A1; source=m_browser_test_22; pvhistory=6L+U5ZuePjo8L3NzbmV3P2tleXdvcmQ9JUU1JUJBJUI3JUU5JTgxJTkzJUU2JThDJTg5JUU2JTkxJUE5Jl89MTUxNTQyMzYwODM0OSZjYWxsYmFjaz1aZXB0bzE1MTU0MjMyNzg4NDk+OjwxNTE1NDIzNDY4Nzc4XV9b; m_flash2=1; Hm_lvt_185e211f4a5af52aaffe6d9c1a2737f4=1515423625; Hm_lpvt_185e211f4a5af52aaffe6d9c1a2737f4=1515423625; tg_list_scroll=0";
        cookie = "_lxsdk_cuid=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _lxsdk=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _hc.v=\"\\\"f21d88bb-1c97-40ec-8046-549b1af9070b.1514345590\\\"\"; cy=1; cye=shanghai; _tr.u=36Gnj6oGAqP4s3Na; cityid=1; default_ab=shop%3AA%3A1%7Cindex%3AA%3A1%7CshopList%3AA%3A1; m_flash2=1; Hm_lvt_185e211f4a5af52aaffe6d9c1a2737f4=1515423625; switchcityflashtoast=1; s_ViewType=10; PHOENIX_ID=0a48af8e-160defe4f9e-18b3720; JSESSIONID=57A65B893657890F3507CF66B749B81E; tg_list_scroll=61; _lxsdk_s=160df3cc99f-f21-df2-054%7C%7C4";
        reqHeader.put(Cons.COOKIE, cookie);
        reqHeader.put(Cons.USER_AGENT, Cons.PC_USER_AGENT);
        int i = 0;
        urlService = new UrlServiceImpl();
        logger.info("{},{},{},{}", "团购ID", "已购买人次", "序号", "时间");
        for (String id : ids) {
            String url = Cons.Dianping.MASSAGE_DISTRICTS_BASE + "/deal/" + id;
            RespBean contentBean = urlService.getContentByUrl(url, reqHeader);
            String content = contentBean.getContent();
            int soldNum = fetchNum(content);
            logger.info("{},{},{},{}", id, soldNum, i++, getTime());
        }
    }

    static synchronized Object getTime() {
        String pattern = "yyyy-MM-dd HH:mm";
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(dtf);

    }

    /**
     * 团购的ID列表
     */
    static List<String> getIds(String filepath) {

        List<String> strings = null;
        try {
            strings = Files.readAllLines(Paths.get(filepath));
        } catch (IOException e) {
            logger.error("{} 文件找那个号不到", filepath);

        }
        List<String> ids = new ArrayList<>();
        int i = 0;
        for (String string : strings) {
            if (i == 0) {
                i++;
                continue;
            }
            String[] split = string.split(",");
            ids.add(split[0]);

        }
        return ids;
    }

    private void saveData(List<MassageActivityBean> datas) {
        datas.forEach(x -> logger.info("{}", x));
    }

    String soldReg = "action-info\">\\s*<span>\\s*已售\\s*<[^>]+>(\\d+)";
    Pattern soldReqPattern = Pattern.compile(soldReg);

    String soldNumOtherReg = "<span class=\"num\">(\\d+)";
    Pattern soldNumOtherPattern = Pattern.compile(soldNumOtherReg);


    private int fetchNum(String content) {
        int val = 0;
        Matcher soldRegMatcher = soldReqPattern.matcher(content);
        if (soldRegMatcher.find()) {
            String outRegGroup = soldRegMatcher.group(1);
            if (StringUtils.isNumeric(outRegGroup)) {
                val += Integer.parseInt(outRegGroup);
            }
        }

        Matcher soldNumOtherRegMatcher = soldNumOtherPattern.matcher(content);
        while (soldNumOtherRegMatcher.find()) {
            String soldNumGroup = soldNumOtherRegMatcher.group(1);
            if (StringUtils.isNumeric(soldNumGroup)) {
                val += Integer.parseInt(soldNumGroup);
            }
        }
        return val;
    }

    @Override
    protected void start(String[] args) {
        super.start(args);
    }

    @Override
    protected void initSqlSessionFactory(String env) {
        super.initSqlSessionFactory(env);
    }

    @Override
    protected int insert(String statement, Object parameter) {
        return super.insert(statement, parameter);
    }
}
