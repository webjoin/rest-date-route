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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Elijah on 18/7/2017.
 * 将外卖的Id写到文件 中
 * 参数 -Darea=dp_wm_ids
 * -Dlist=dp-shop-list-4
 * -Darea=dp-shop-list-4-id
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
        } else {
            System.out.println("-Darea ： dp_ids is Dp-shop-id  , dp_waimai_ids dp-waimai-id  exit do nothing ");
            System.exit(1);
        }
        DpBootstrapIdsFile bootstrapIdsFile = new DpBootstrapIdsFile();
        bootstrapIdsFile.fetchDpIds(isDpWaimai);  // 生成的 是DP的id列表            DP
    }

    private void fetchDpIds(Boolean isDpWaimai) {
        String path = "/Users/Elijah/Desktop/self/sites/dp/dp-shop-list";
        path = "/Users/Elijah/Downloads/baidu-company-crawler-data/dp-xiamen-shop-list-2017-0719";
//        path = Cons.USER_DIR + "/logs/other/other-" + System.getProperty("list") + ".log";
        File file = new File(path);
        File[] files = new File[1];
        if (file.isDirectory()) {
            files = file.listFiles();
        } else if (file.isFile()) {
            files[0] = file;
        }
        List<String> dpIds = new ArrayList<>();
        for (File file1 : files) {
            if (!file1.isFile()) continue;
            System.out.println(file1.getAbsoluteFile());
            List<String> list = FileUtil.readFile(file1.getAbsolutePath());
            for (String s : list) {
                if (StringUtils.isEmpty(s)) continue;
                JSONObject shopItem = JSON.parseObject(s);

                String hasTakeaway = shopItem.getString("hasTakeaway");
                String dpId = shopItem.getString("id");
                if (dpIds.contains(dpId)) continue;
                else dpIds.add(dpId);

//                if (null == isDpWaimai) {
//                    logger.info(shopItem.toString());
//                } else {
                if (isDpWaimai) {
                    if (!"true".equals(hasTakeaway)) continue;  //非外卖
                }
                logger.info("{}", dpId);
//                }
            }
        }
    }
}
