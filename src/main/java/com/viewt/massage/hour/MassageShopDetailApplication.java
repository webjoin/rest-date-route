package com.viewt.massage.hour;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.Cons;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tangyu
 * @since 2018-01-09 09:58
 * area=massage-shop-detail
 */
public class MassageShopDetailApplication extends BaseBootstrap {

    public static void main(String[] args) {
        MassageShopDetailApplication bootstrap = new MassageShopDetailApplication();
        try {
            long stime = System.currentTimeMillis();
            bootstrap.logger.error("{} , start...", bootstrap.dc_index);
            bootstrap.start(args);
            long etime = System.currentTimeMillis();
            bootstrap.logger.error("{} , end...it tooks {}s", bootstrap.dc_index, (etime - stime) / 1000);
        } catch (Exception e) {
            bootstrap.logger.error(e.getMessage(), e);
            bootstrap.logger.error("end...");
        }
    }

    UrlService urlService;
    int dc_index = -1;
    AtomicInteger atomicInteger = new AtomicInteger(10);
    @Override
    protected void crawl(String[] args) {
        String filepath = Cons.USER_DIR + "/logs/other/day-massage-shop-data.log";
        List<String> ids = MassageActivityDetailApplication.getIds(filepath);

        String cookie = "_lxsdk_cuid=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _lxsdk=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _hc.v=\"\\\"f21d88bb-1c97-40ec-8046-549b1af9070b.1514345590\\\"\"; PHOENIX_ID=0a45d1c9-160d6383ebc-181e705; cy=1; cye=shanghai; _tr.u=36Gnj6oGAqP4s3Na; JSESSIONID=C5FF9D30D4F84C0D75DB21BF2FCD01C6; cityid=1; default_ab=shop%3AA%3A1%7CshopList%3AA%3A1; source=m_browser_test_22; pvhistory=6L+U5ZuePjo8L3NzbmV3P2tleXdvcmQ9JUU1JUJBJUI3JUU5JTgxJTkzJUU2JThDJTg5JUU2JTkxJUE5Jl89MTUxNTQyMzYwODM0OSZjYWxsYmFjaz1aZXB0bzE1MTU0MjMyNzg4NDk+OjwxNTE1NDIzNDY4Nzc4XV9b; m_flash2=1; Hm_lvt_185e211f4a5af52aaffe6d9c1a2737f4=1515423625; Hm_lpvt_185e211f4a5af52aaffe6d9c1a2737f4=1515423625; tg_list_scroll=0";
        cookie = "_lxsdk_cuid=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _lxsdk=1609609b3abc8-0ec349de52e4e68-49566d-13c680-1609609b3abc8; _hc.v=\"\\\"f21d88bb-1c97-40ec-8046-549b1af9070b.1514345590\\\"\"; cy=1; cye=shanghai; _tr.u=36Gnj6oGAqP4s3Na; cityid=1; default_ab=shop%3AA%3A1%7Cindex%3AA%3A1%7CshopList%3AA%3A1; m_flash2=1; Hm_lvt_185e211f4a5af52aaffe6d9c1a2737f4=1515423625; switchcityflashtoast=1; s_ViewType=10; PHOENIX_ID=0a48af8e-160defe4f9e-18b3720; JSESSIONID=57A65B893657890F3507CF66B749B81E; tg_list_scroll=61; _lxsdk_s=160df3cc99f-f21-df2-054%7C%7C4";
        reqHeader.put(Cons.COOKIE, cookie);
        reqHeader.put(Cons.USER_AGENT, Cons.MOBILE_USER_AGENT);
        int i = 0;
        urlService = new UrlServiceImpl();
        logger.info("{},{},{}", "商店ID", "已购买人次", "序号", "时间");
        for (String id : ids) {
            String agent = MessageFormat.format(Cons.PC_USER_AGENT_1, atomicInteger.addAndGet(1) + "");
//            System.out.println(agent);
            reqHeader.put(Cons.COOKIE, MessageFormat.format(cookie, Instant.now().getEpochSecond() + ""));
            reqHeader.put(Cons.USER_AGENT, agent);


            String url = MessageFormat.format(Cons.Dianping.MASSAGE_DISTRICTS_M_BASE, id, Instant.now().toEpochMilli() + "");
            RespBean contentBean = urlService.getContentByUrl(url, reqHeader);
            String content = contentBean.getContent();
            int soldNum = fetchNum(content);
            logger.info("{},{},{},{}", id, soldNum, i++, MassageActivityDetailApplication.getTime());
        }
    }


    private int fetchNum(String content) {
        int index = content.indexOf("(");
        int lastIndex = content.lastIndexOf(")");
        String substring = content.substring(index + 1, lastIndex);
        JSONObject jsonObject = JSON.parseObject(substring);
        String bookNum = jsonObject.getString("bookNum");
        int rs = 0;
        if (Objects.isNull(bookNum)) {
            return rs;
        }
        String s = bookNum.replaceAll("[^0-9]+", "");
        if (StringUtils.isNumeric(s)) {
            rs = Integer.parseInt(s);
        }
        return rs;
    }

}
