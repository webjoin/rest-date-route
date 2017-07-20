package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.test.db.DbBootstrap;
import com.viewt.rest.data.util.FileUtil;
import com.viewt.rest.entity.restdata.WmMeituanPoi;
import com.viewt.rest.entity.restdata.WmMeituanShop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.*;


/**
 * Created by Elijah on 13/7/2017.
 */
public class ElemeBootstrapData extends DbBootstrap {
    static Logger logger = LoggerFactory.getLogger(ElemeBootstrapData.class);

    public static void main(String[] args) {

        ElemeBootstrapData bootstrapData;

        bootstrapData = new ElemeBootstrapData();
        bootstrapData.parseFile();
    }

    public void parseFile() {
        String mtLogDir = "/Users/Elijah/Desktop/self/rest-date-route/logs/mt";
        File dir = new File(mtLogDir);
        String[] filePaths = dir.list();
        for (int i = 0; i < filePaths.length; i++) {
            String filePath = filePaths[i];
            if (!filePath.endsWith(".log")) continue;
//            if (!"wm-1.log".equals(filePath)) continue;

            System.out.println(filePath);
            readFileContent(mtLogDir, filePath);
        }
    }

    private void readFileContent(String mtLogDir, String filePath) {
        String fileAbsPath = mtLogDir + "/" + filePath;
        List<String> list = FileUtil.readFile(fileAbsPath);
        int i = 0;
        for (String json : list) {
            if (json.startsWith("{")) {
                deal(i, json);
                i++;
            }
        }
        System.out.println("文件中有" + i + "个");
        list.clear();
    }

    //{"page_size":20,"w_latlng":"24637938,118317101","w_addr":"黄厝(公交站)","page_index":0,"address":"704路","poi_total_num":
    long poiId = 0;
    Set<Long> ids = new HashSet<>();

    private void deal(int i, String json) {
        this.initSqlSessionFactory("local");
        JSONObject jsonObject = JSON.parseObject(json);

        WmMeituanPoi wmMeituanPoi = JSON.parseObject(jsonObject.toString(), WmMeituanPoi.class);
        wmMeituanPoi.setwAddr(jsonObject.getString("w_addr"));
        wmMeituanPoi.setPoiTotalNum(jsonObject.getIntValue("poi_total_num"));
        JSONArray poilist = jsonObject.getJSONArray("poilist");
        String w_latlng = jsonObject.getString("w_latlng");
        String w_addr = jsonObject.getString("w_addr");
        String[] split = w_latlng.split(",");
        String lat = split[0];
        String lon = split[1];
        wmMeituanPoi.setLatitude(lat);
        wmMeituanPoi.setLongitude(lon);
        if (i == 0) {
            poiId = 0;
            Map<String, Object> condition = new HashMap<>();
            condition.put("longitude", lon);
            condition.put("latitude", lat);
            condition.put("w_addr", w_addr);
            Integer insertWmPoi1 = selectOne("selectWmPoiBylng", condition);
            if (insertWmPoi1 != null && insertWmPoi1 > 0) {
                poiId = insertWmPoi1;
//                return;
            } else {
                int insertWmPoi = insert("insertWmPoi", wmMeituanPoi);
                if (insertWmPoi > 0) {
                    System.out.println("ok," + (poiId = wmMeituanPoi.getId().intValue()));
                }
            }
        }
        if (poiId == 0) {
            logger.error("i:{}", i);
            return;
        }

        if (i == 56) {
            System.out.println("aaa");
        }
        List<WmMeituanShop> shops = new ArrayList<>();
        for (Object o : poilist) {
            JSONObject jsonObject1 = (JSONObject) o;
            long id = jsonObject1.getLongValue("id");
            if (ids.contains(id)) {
            } else {
                WmMeituanShop shopBean = getShopBean(jsonObject1);
                shopBean.setMtSearchPoiId(poiId);
                Integer sales = shopBean.getSales();
                shopBean.setShippingTimeX(shopBean.getShippingTimeX().substring(0, 16));
                shops.add(shopBean);
            }
            ids.add(id);
        }
        if (shops.isEmpty()) {
            System.out.println("");
        } else {
            int insertShops = insert("insertShops", shops);
            System.out.println("save qty : " + insertShops);
        }

    }

    private WmMeituanShop getShopBean(JSONObject json) {
//        id, sales, support_coupon, avg_delivery_time, wm_poi_view_id, distance, longitude,
//                mt_poi_id, month_sale_num, shipping_time_x, min_price, name, shipping_fee, phone_list,
//                address, brand_type, latitude, wm_poi_score, comment_num, comment_number, invoice_support,
//                support_pay, shipping_time, mt_search_poi_id
        WmMeituanShop shop = new WmMeituanShop();
        shop.setId(json.getLongValue("id"));
        shop.setSales(json.getInteger("sales"));
        shop.setSupportCoupon(json.getInteger("support_coupon"));
        shop.setAvgDeliveryTime(json.getInteger("avg_delivery_time"));
        shop.setWmPoiViewId(json.getString("wm_poi_view_id"));
        shop.setDistance(json.getString("distance"));
        shop.setLongitude(json.getString("longitude"));
        shop.setMtPoiId(json.getString("mt_poi_id"));
        shop.setMonthSaleNum(json.getInteger("month_sale_num"));
        shop.setShippingTimeX(json.getString("shipping_time_x"));
        shop.setMinPrice(json.getDouble("min_price"));
        shop.setName(json.getString("name"));
        shop.setShippingFee(json.getDouble("shipping_fee"));
        JSONObject poi_info = json.getJSONObject("poi_info");
        Object shop2 = json.get("shop");

        if (shop2 instanceof JSONObject) {
            JSONObject shop1 = (JSONObject) shop2;
            shop.setPhoneList(shop1.getString("phone_list"));
            shop.setAddress(shop1.getString("address"));
            shop.setBrandType(shop1.getInteger("brand_type"));
            shop.setLatitude(shop1.getString("latitude"));
            shop.setWmPoiScore(shop1.getInteger("wm_poi_score"));
            shop.setCommentNum(shop1.getInteger("comment_num"));
        } else {
            System.out.println(shop.getId() + " . shop is null");
        }
        if (poi_info != null) {
            shop.setCommentNumber(poi_info.getInteger("comment_number"));
            shop.setInvoiceSupport(poi_info.getInteger("invoice_support"));
            shop.setSupportPay(poi_info.getInteger("support_pay"));
        }


        shop.setShippingTime(json.getString("shipping_time"));
        shop.setMtSearchPoiId(json.getLong("mt_search_poi_id"));
        return shop;
    }


}
