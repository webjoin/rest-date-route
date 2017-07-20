package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.WaimaiBootstrap;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
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
 */
public class DpBootstrapFileData {

    static String categoryId = "102;103;104;106;107;110;111;112,-3613;112,1831;112,1832;112,1833;112,1834;112,1835;112,1836;112,1837;112,1838;112,1839;112,1840;112,8085;112,8373;112,8374;112,8375;112,15652;112,27090;112,28549;112,28887;112,1847;112,1846;112,1830;112,15651;112,1842;112,8087;112,15650;112,27464;112,1843;112,65208;112,70185;112,27090;112,70208;112,15649;112,27054;112,1845;112,1844;112,70523;112,70175;112,106;112,107;112,108;112,109;114;115;116;117,-3613;117,104;117,105;117,106;117,107;117,108;117,109;118,-3613;118,104;118,105;118,106;118,107;118,108;118,109;132;219;224;247;249;250;251;508;733;1338;1783;1817";
    static Logger logger = LoggerFactory.getLogger(DpBootstrapFileData.class);

    public static void main(String[] args) {
        DpBootstrapFileData bootstrap = new DpBootstrapFileData();
        String path = "/Users/Elijah/Desktop/self/dp-shop-list";
        File file = new File(path);
        File[] files = file.listFiles();
        int ii = 0;
        List<String> dpIds = new ArrayList<>();
        for (File file1 : files) {
            System.out.println(file1.getAbsoluteFile());
            List<String> list = FileUtil.readFile(file1.getAbsolutePath());
            for (String s : list) {
                if (StringUtils.isEmpty(s)) continue;
                JSONObject jsonObject = JSON.parseObject(s);
                JSONArray list1 = JSONUtil.getJSONArray(jsonObject, "list");
                JSONObject currentCategory = jsonObject.getJSONObject("currentCategory");
                JSONObject currentRegion = jsonObject.getJSONObject("currentRegion");
                int startIndex = jsonObject.getIntValue("startIndex");
                int recordCount = jsonObject.getIntValue("recordCount");

                String categoryId;
                String categoryName;
                String regionId;
                String regionName;
                if (null != currentCategory) {
                    categoryId = currentCategory.getString("id");
                    categoryName = currentCategory.getString("name");
                } else {
                    categoryId = "";
                    categoryName = "";
                }
                if (currentRegion != null) {
                    regionId = currentRegion.getString("id");
                    regionName = currentRegion.getString("name");
                } else {
                    regionId = "";
                    regionName = "";
                }
//

                for (Object o : list1) {
                    if (null == o) {
                        logger.error("----->index:{}, count:{}, category:{}, region:{}", startIndex, recordCount, currentCategory.getString("name"), currentRegion.getString("name"));
                        continue;
                    }
                    JSONObject shopItem = (JSONObject) o;
                    String dpId = shopItem.getString("id");
                    if (dpIds.contains(dpId)) continue;
                    else dpIds.add(dpId);
                    shopItem.put("currentCategory.id", categoryId);
                    shopItem.put("currentCategory.name", categoryName);
                    shopItem.put("currentRegion.id", regionId);
                    shopItem.put("currentRegion.name", regionName);
                    shopItem.put("startIndex", startIndex);
                    shopItem.put("recordCount", recordCount);
                    shopItem.remove("recommendReasonData");
                    shopItem.remove("shopStateInformation");
                    shopItem.remove("tagList");
                    shopItem.remove("shopPositionInfo");
                    logger.info(shopItem.toString());
                    ii++;
                }

                if (startIndex + 25 >= recordCount) {
                    // 重复  56030
                    // 唯一  46304
                    System.out.println(recordCount + "-->" + ii);
                }
            }
        }
    }
}
