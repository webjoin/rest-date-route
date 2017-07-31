package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.data.util.FetchDocumentBootstrap;
import com.viewt.rest.data.util.FileUtil;

import java.io.File;
import java.util.*;

/**
 * Created by Elijah on 24/7/2017.
 * 将点评店数据入库
 */
public class DpShopBootstap2Db extends BaseBootstrap {
    public static void main(String[] args) {
        DpShopBootstap2Db bootstap2Db = new DpShopBootstap2Db();
        bootstap2Db.start(args);

    }

    @Override
    protected void crawl(String[] args) {
        save2Db(args);
    }

    void save2Db(String[] args) {
        String shop_infos = "/Users/Elijah/Downloads/baidu-company-crawler-data/other-dp-shops";
        String shop_list_infos = "/Users/Elijah/Downloads/baidu-company-crawler-data/dp-xiamen-shop-list-2017-0719";

        String[] args1 = {shop_infos, shop_infos + "/dest.log"};
        FetchDocumentBootstrap.main(args1);
        args1[0] = shop_list_infos;
        args1[1] = shop_list_infos + "/dest.log";
        FetchDocumentBootstrap.main(args1);
        Map<String, JSONObject> shopListMap = new HashMap<>();
        Map<String, JSONObject> shopMap = new HashMap<>();
        File shop_list = new File(shop_list_infos + "/dest.log");
        File shop = new File(shop_infos + "/dest.log");
        if (shop_list.isFile()) {  //店list
            List<String> list = FileUtil.readFile(shop_list.getAbsolutePath());
            for (String item : list) {
                JSONObject jsonObject = JSON.parseObject(item);
                JSONArray list1 = jsonObject.getJSONArray("list");
                int startIndex = jsonObject.getIntValue("startIndex");
                int recordCount = jsonObject.getIntValue("recordCount");

                Iterator<Object> iterator = list1.iterator();
                while (iterator.hasNext()) {
                    JSONObject shopItem = (JSONObject) iterator.next(); // sho
                    String id = shopItem.getString("id");
                    shopItem.put("startIndex", startIndex);
                    shopItem.put("recordCount", recordCount);
                    shopItem.remove("recommendReasonData");
                    shopItem.remove("shopStateInformation");
                    shopItem.remove("tagList");
                    shopItem.remove("shopPositionInfo");
                    shopListMap.put(id, shopItem);
                }
            }
        }
        if (shop.isFile()) { //店
            List<String> list = FileUtil.readFile(shop.getAbsolutePath());
            for (String item : list) {
                JSONObject shopItem = JSON.parseObject(item);
                String id = shopItem.getString("id");
                shopMap.put(id, shopItem);
            }
        }

        int shopSize = shopMap.size();
        int shopListSize = shopListMap.size();

        if (shopSize >= shopListSize) {
            for (Map.Entry<String, JSONObject> entry : shopMap.entrySet()) {
                String key = entry.getKey();

                JSONObject value = entry.getValue();

                JSONObject jsonObject = shopListMap.get(key);

                value.putAll(jsonObject);

                shopMap.put(key, value);
            }
        } else {
            for (Map.Entry<String, JSONObject> entry : shopListMap.entrySet()) {
                String key = entry.getKey();

                JSONObject value = entry.getValue();

                JSONObject jsonObject = shopMap.get(key);

                if (null != jsonObject)
                    value.putAll(jsonObject);

                shopListMap.put(key, value);
            }
            Set<Map.Entry<String, JSONObject>> entries = shopListMap.entrySet();
            for (Map.Entry<String, JSONObject> entry : entries) {
                JSONObject value = entry.getValue();
                value.remove("shopDealInfos");
                value.remove("originalUrlKey");
                value.remove("defaultPic");
                value.remove("extraJson");
                value.remove("priceText");
                value.remove("altName");
                value.remove("hotelBookable");
                value.remove("sourceFileName");
                value.remove("matchText");
                value.remove("orderDish");
                value.remove("scoreText");
                logger.info(JSON.toJSONString(value));
            }
        }


    }
}
