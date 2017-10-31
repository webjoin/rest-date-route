package com.viewt.rest;

import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.util.Cons;
import com.viewt.rest.data.util.FileUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Elijah on 22/7/2017.
 * 从点评入口的美团外卖数据
 * 1、-Darea=dp-wm
 * 2、1 10
 */
public class DpMeituanWaimaiBootstrap extends BaseBootstrap {


    public static void main(String[] args) {
        DpMeituanWaimaiBootstrap bootstrap = new DpMeituanWaimaiBootstrap();
        bootstrap.start(args);
    }


    @Override
    public void crawl(String[] args) {
        int start = 1;
        int end = 0;
        if (args.length >= 1) {
            start = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) {
            end = Integer.parseInt(args[1]);
        }
        String filePath = Cons.USER_DIR + "/mate/other-dp_wm_ids.log";
        List<String> list = FileUtil.readFile(filePath);
        if (end == 0) {
            end = list.size();
        }

        int i = 0;
        for (String item : list) {
            i++;
            if (i < start || i > end) {
                continue;
            }
            if (StringUtils.isEmpty(item)) {
                continue;
            }
            String[] split = item.split(",");
            String dpId;
            if (split.length >= 2) {
                dpId = split[1];
            } else {  // it don't
                dpId = split[0];
            }
            getItemData(dpId);
            logger.info("start ... {}", start);
        }
    }


    private void getItemData(String dpId) {
        String dpWaimaiUrl = "https://takeaway.dianping.com/waimai/ajax/newm/menu?actualLat=&actualLng=&initialLat=&initialLng=&geoType=2&mtWmPoiId=-1&dpShopId=${id}&source=&_token=eJyVjl1rwjAUhv9L&_=" + System.currentTimeMillis();
        dpWaimaiUrl = dpWaimaiUrl.replace("${id}", dpId);

        String cookie = "_hc.v=\"\\\"6bb3b1a7-53f2-41fe-be7f-77d22ad6284d.1500378610\\\"\"; _lxsdk_cuid=15d5f465072c8-01f6f4877f321d-3067780b-13c680-15d5f465073c8; _lxsdk=15d5f465072c8-01f6f4877f321d-3067780b-13c680-15d5f465073c8; aburl=1; cy=15; cye=xiamen; __mta=51756822.1500542032306.1500542032306.1500542132542.2; PHOENIX_ID=0a03112c-15d65f7b066-13bce04; switchcityflashtoast=1; m_cookie_issues_uuid=ks3QPf5zPq; cityid=15; download_banner=on; chwlsource=default; source=m_browser_test_33; __mta=51756822.1500542032306.1500542132542.1500655599561.3; msource=default; default_ab=citylist%3AA%3A1%7Cshop%3AA%3A1%7Cindex%3AA%3A1%7CshopList%3AA%3A1; _lxsdk_s=15d65fa2056-056-e5c-5bd%7C%7C99; _lx_utm=; isUuidUnion=false; dpUserId=\"\"; mtUserId=\"\"; waimai_cityname=%E5%9F%8E%E5%B8%82%E5%90%8D%E5%B7%B2%E5%88%A0%E9%99%A4; pvhistory=\"6L+U5ZuePjo8L3N0YXRpY3Rlc3QvbG9nZXZlbnQ/bmFtZT1XaGVyZUFtSUZhaWwmaW5mbz1odG1sLSU1QiU3QiUyMmNvZGUlMjIlM0ExJTJDJTIybWVzc2FnZSUyMiUzQSUyMlVzZXIlMjBkZW5pZWQlMjBHZW9sb2NhdGlvbiUyMiU3RCU1RCZjYWxsYmFjaz1XaGVyZUFtSTExNTAwNjU1ODc4Nzg5Pjo8MTUwMDY1NTcxOTQ4Ml1fWw==\"; m_flash2=1";

        Map<String, String> reqHeader = new HashMap<>();
        reqHeader.put("Cookie", cookie);
        reqHeader.put("User-Agent", Cons.MOBILE_USER_AGENT);
        reqHeader.put("Accept", "application/json");
        reqHeader.put("Host", "takeaway.dianping.com");

        RespBean contentByUrl = urlService.getContentByUrl(dpWaimaiUrl, reqHeader);
        logger.info(contentByUrl.getContent());
    }
}
