package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.WaimaiBootstrap;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.Cons;
import com.viewt.rest.data.util.FileUtil;
import com.viewt.rest.data.util.JSONUtil;
import com.viewt.rest.data.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;

/**
 * Created by Elijah on 18/7/2017.
 * 将外卖的Id写到文件 中
 * 参数 -Darea=dp_wm_ids
 * -Dlist=dp-shop-list-4 -Darea=dp-shop-list-4-id   // 将id去重后 用户 根据ID抓取
 * -Darea=dp-shop-list-items  //用于 生成一个json数据
 */
public class DpBootstrapIdsFile {

    static Logger logger = LoggerFactory.getLogger(DpBootstrapIdsFile.class);

    public static void main(String[] args) {
        String isDpWaiMai = args[0];  // 是否外卖
        Boolean isDpWaimai = false;
        if ("0".equals(isDpWaiMai)) {
            isDpWaimai = false;
        } else if ("1".equals(isDpWaiMai)) {
            isDpWaimai = true;
        } else if ("2".equals(isDpWaiMai)) {
            isDpWaimai = null;
        } else {
            System.out.println("-Darea ： dp_ids is Dp-shop-id  , dp_waimai_ids dp-waimai-id  exit do nothing ");
            System.exit(1);
        }
        DpBootstrapIdsFile bootstrapIdsFile = new DpBootstrapIdsFile();
        bootstrapIdsFile.fetchDpIds(isDpWaimai);  // 生成的 是DP的id列表            DP
    }

    public void fetchDpIds(Boolean isDpWaimai) {
        String path = "/Users/Elijah/Desktop/self/sites/dp/dp-shop-list";
        path = "/Users/Elijah/Downloads/baidu-company-crawler-data/dp-xiamen-shop-list-2017-0719";
        path = "/Users/Elijah/Downloads/baidu-company-crawler-data/other-dp-shops/dp";
        path = "/Users/Elijah/Downloads/baidu-company-crawler-data/dp-shop-list/dp";
//        path = Cons.USER_DIR + "/logs/other/other-" + System.getProperty("list") + ".log";
        if (Boolean.FALSE == isDpWaimai) {
            System.out.println("a....");
        }
        File file = new File(path);
        File[] files = new File[1];
        if (file.isDirectory()) {
            files = file.listFiles();
        } else if (file.isFile()) {
            files[0] = file;
        }
        Set<String> dpIds = new HashSet<>();
        for (File file1 : files) {
            if (!file1.isFile() || !file1.getName().startsWith("other")) continue;
            System.out.println(file1.getAbsoluteFile());
            List<String> list = FileUtil.readFile(file1.getAbsolutePath());
            for (String s : list) {
                if (StringUtils.isEmpty(s)) continue;
                s = s.trim();
                if (!s.startsWith("{")) continue;
                JSONObject shopItem;
                try {
                    shopItem = JSON.parseObject(s);
                } catch (Exception e) {
                    logger.error(s);
                    continue;
                }
//                JSONArray list1 = JSONUtil.getJSONArray(shopItem, "list");
//                Iterator<Object> iterator = list1.iterator();
//                while (iterator.hasNext()) {
//                    JSONObject next = (JSONObject) iterator.next();
//                    logItem(isDpWaimai, dpIds, next);
//                }
                logItem(isDpWaimai, dpIds, shopItem);
//                break;
            }
//            break;
        }
        if (Boolean.FALSE == isDpWaimai) {
            for (String dpId : dpIds) {
                logger.info("{}", dpId);
            }
        }
    }

    public List<JSONObject> items = new ArrayList<>();


    private void logItem(Boolean isDpWaimai, Set<String> dpIds, JSONObject jsonObject) {
        String hasTakeaway = jsonObject.getString("hasTakeaway");
        String dpId = jsonObject.getString("id");
        if (dpIds.contains(dpId)) return;
        else
            dpIds.add(dpId);
        if (null == isDpWaimai) {
            removeKeys(jsonObject);
            logger.info(jsonObject.toString());
//            items.add(jsonObject);

        } else if (isDpWaimai) {
            if (!"true".equals(hasTakeaway)) return;
        } else
            logger.info("{}", dpId);
    }

    private void removeKeys(JSONObject jsonObject) {
        jsonObject.remove("originalUrlKey");
        jsonObject.remove("shopStateInformation");
        jsonObject.remove("recommendReason");
        jsonObject.remove("recommendReasonData");
        jsonObject.remove("shopDealInfos");
        jsonObject.remove("shopPositionInfo");
        jsonObject.remove("defaultPic");
        jsonObject.remove("extraJson");
        jsonObject.remove("adShop");
        jsonObject.remove("tagList");
    }
}
