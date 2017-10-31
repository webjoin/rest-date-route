package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.bean.RespCategoryBean;
import com.viewt.rest.data.bean.RespElemeSelectBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.JSONUtil;
import com.viewt.rest.data.util.Log4jUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by Elijah on 13/7/2017.
 */
public class ElemeBootstrap {
    static org.slf4j.Logger logger = LoggerFactory.getLogger(ElemeBootstrap.class);

    String[][] citys = {
            {"xiamen", "厦门", "24.479509", "118.089478"},
            {"shanghai", "上海", "31.23037", "121.473701"}
    };


    public static void main(String[] args) {
        int start = 0;
        int end = 0;
        if (args.length == 0) {
            String st = "";
            for (int i = 0; i < WaimaiBootstrap.shopArea1.length; i++) {
                st += i + ":" + WaimaiBootstrap.shopArea1[i] + "  ";
            }
            logger.error("{}", st);
            logger.error("请输入前面的编号哦。pls!");
            System.exit(1);
        } else {
            start = Integer.parseInt(args[0]);
            if (args.length == 1) {
                end = start;
            } else {
                end = Integer.parseInt(args[1]);
            }
        }
        ElemeBootstrap bootstrap = new ElemeBootstrap();
        try {
            long stime = System.currentTimeMillis();
            logger.error("{} , start...",bootstrap.dc_index);
            bootstrap.crawl(start, end);
            long etime = System.currentTimeMillis();
            logger.error("{} , end...it tooks {}s", bootstrap.dc_index,(etime - stime) / 1000);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            logger.error("end...");
        }
    }

    UrlService urlService;
    int qty = 0;
    int dc_index = -1;

    private void crawl(int start, int end) {

        urlService = new UrlServiceImpl();
        //https://mainsite-restapi.ele.me/bgs/poi/search_poi_nearby?keyword=%E7%81%AB%E8%BD%A6%E7%AB%99&offset=0&limit=20&longitude=118.089478&latitude=24.479509
//        https://mainsite-restapi.ele.me/shopping/v2/restaurant/category?latitude=31.20525&longitude=121.60323
        //https://mainsite-restapi.ele.me/shopping/restaurants?latitude=24.46806&longitude=118.11632&keyword=&offset=20&limit=20&extras[]=activities&restaurant_category_ids[]=209&restaurant_category_ids[]=211&restaurant_category_ids[]=212&restaurant_category_ids[]=213&restaurant_category_ids[]=214&restaurant_category_ids[]=215&restaurant_category_ids[]=216&restaurant_category_ids[]=217&restaurant_category_ids[]=218&restaurant_category_ids[]=219&restaurant_category_ids[]=221&restaurant_category_ids[]=222&restaurant_category_ids[]=223&restaurant_category_ids[]=224&restaurant_category_ids[]=225&restaurant_category_ids[]=226&restaurant_category_ids[]=227&restaurant_category_ids[]=228&restaurant_category_ids[]=229&restaurant_category_ids[]=230&restaurant_category_ids[]=231&restaurant_category_ids[]=232&restaurant_category_ids[]=234&restaurant_category_ids[]=235&restaurant_category_ids[]=236&restaurant_category_ids[]=237&restaurant_category_ids[]=238&restaurant_category_ids[]=263&restaurant_category_ids[]=264&restaurant_category_ids[]=265&restaurant_category_ids[]=266&restaurant_category_ids[]=267&restaurant_category_ids[]=268&restaurant_category_ids[]=269

        String longitude = citys[0][3];
        String latitude = citys[0][2];
        for (int i = start; i <= end; i++) {
            dc_index = i;
            String keyword = WaimaiBootstrap.shopArea1[i];
            Log4jUtil.reconfigure(String.valueOf(i));
            RespBean respPoiBean = urlService.searchPoiNearby(keyword, longitude, latitude);
            List<RespElemeSelectBean> selectBeans = getSelectBeans(keyword, respPoiBean);
            if (CollectionUtils.isEmpty(selectBeans)) {
//                logger.error("keyword：{} selectBeans.list is empty", keyword);
                continue;
            }
            RespElemeSelectBean respElemeSelectBean = selectBeans.get(0);
            respElemeSelectBean.setKeyWord(keyword);
            Double latitude1 = respElemeSelectBean.getLatitude();
            Double longitude1 = respElemeSelectBean.getLongitude();
            RespBean categoryBean = urlService.getCategory(latitude1, longitude1);  //获取分类
            List<RespCategoryBean> beans = getCategory(latitude1, longitude1, categoryBean);
            if (CollectionUtils.isEmpty(beans)) {
                continue;
            }
            Map<Integer, Integer> qtyCategoryIds = getQtyCategoryIds(latitude1, longitude1, beans);
//            logger.info(categoryBean.getContent());
            Set<Map.Entry<Integer, Integer>> entries = qtyCategoryIds.entrySet();
            for (Map.Entry<Integer, Integer> entry : entries) {
                logger.error("{} , 分类ID:" + entry.getKey() + "----------分类店：" + entry.getValue(),dc_index);
                restaurants(respElemeSelectBean, entry.getKey() + "", entry.getValue());
            }
        }
    }

    /**
     * 饿了么 大于1000 则不显示了
     *
     * @param latitude1
     * @param longitude1
     * @param beans
     * @return
     */
    private Map<Integer, Integer> getQtyCategoryIds(Double latitude1, Double longitude1, List<RespCategoryBean> beans) {
        int shopTotal = 0; //美食所有的店
        /*
         shopQty 商户的数量大于了1000则 则根据子类的categoryId
         */
        Map<Integer, Integer> shopQtyCategoryIdMap = new HashMap<>();

        for (RespCategoryBean bean : beans) {
            String name = bean.getName();
            Integer categoryId = bean.getId();
            Integer categoryCount = bean.getCount();
            if (categoryId == null) {
                continue;
            }
            if ("美食".equals(name)) {
                JSONArray sub_categories = bean.getSub_categories();
                Iterator<Object> iterator = sub_categories.iterator();
                while (iterator.hasNext()) {
                    JSONObject jsonObject = (JSONObject) iterator.next();
                    Integer subId = jsonObject.getInteger("id");
                    String name1 = jsonObject.getString("name");
                    int subCurrCategoryShopQty = jsonObject.getIntValue("count");
                    System.out.println(subCurrCategoryShopQty + "--" + subId);
                    if (subCurrCategoryShopQty == 0) {
                        continue;
                    }
                    if ("全部".equals(name1)) {
                    } else {
                        shopQtyCategoryIdMap.put(subId, subCurrCategoryShopQty);
                    }
                }
            } else {
                shopQtyCategoryIdMap.put(categoryId, categoryCount);
            }
        }
        return shopQtyCategoryIdMap;
    }

    private List<RespCategoryBean> getCategory(Double latitude1, Double longitude1, RespBean categoryBean) {
        String content = categoryBean.getContent();
        if (StringUtils.isEmpty(content)) {
            logger.error("latitude1:{},longitude1:{} category list is empty", latitude1, longitude1);
            return null;
        }
        List<RespCategoryBean> respElemeSelectBeans = JSONUtil.parseArray(content, RespCategoryBean.class);
        return respElemeSelectBeans;
    }


    private List<RespElemeSelectBean> getSelectBeans(String keyword, RespBean respBean) {
        String content = respBean.getContent();
        if (StringUtils.isEmpty(content)) {
            logger.error("keyword:{} select list is empty", keyword);
            return null;
        }
        List<RespElemeSelectBean> respElemeSelectBeans = JSONUtil.parseArray(content, RespElemeSelectBean.class);
        return respElemeSelectBeans;
    }

    /**
     * @param categoryId 分类IDs
     */
    private void restaurants(RespElemeSelectBean respElemeSelectBean, String categoryId, int cateShopQty) {
        String latitude = String.valueOf(respElemeSelectBean.getLatitude());
        String longitude = String.valueOf(respElemeSelectBean.getLongitude());
        String name = respElemeSelectBean.getName();
        int offset = 0;
        while (true) {
            RespBean restaurants = urlService.restaurants(offset + "", longitude, latitude, categoryId);
            String content1 = restaurants.getContent();
            if (StringUtils.isNotEmpty(content1)) {
                JSONArray jsonArrayShoplist = JSONUtil.parseArray(content1);
                if (null == jsonArrayShoplist || jsonArrayShoplist.isEmpty()) {
                    logger.error("{} get restaurants.jsonArray is null ", name);
                    return;
                } else {
                    int size = jsonArrayShoplist.size();

                    logRestaurants(restaurants.getReqUrl(), jsonArrayShoplist, respElemeSelectBean);
                    if (size < 30) { // has no more data
                        int currShopQty = offset + size;
                        logger.error("{}, now break, how many shops downloaded : {}, category size is :{}",dc_index, currShopQty, cateShopQty);
                        break;
                    }
                    offset += 30;
                }
            } else {
                logger.error("{} get restaurants is null ", name);
            }
        }
    }


    private static List<Long> restaurant_ids = new ArrayList<>();


    /**
     * 记录日志
     *
     * @param jsonArrayShoplist   店的list
     * @param respElemeSelectBean Poi JSON
     */
    private void logRestaurants(String reqUrl, JSONArray jsonArrayShoplist, RespElemeSelectBean respElemeSelectBean) {
        Iterator<Object> iterator = jsonArrayShoplist.iterator();
        List<JSONObject> restaurant_ids1 = new ArrayList<>();
        while (iterator.hasNext()) {
            JSONObject shopJsonObj = (JSONObject) iterator.next();
            shopJsonObj.put("poi", respElemeSelectBean);
            shopJsonObj.put("req", reqUrl);
            long restaurant_id = shopJsonObj.getLongValue("id");
            if (!restaurant_ids.contains(restaurant_id)) {
                RespBean eleShop = urlService.getEleShop(restaurant_id);
                JSONArray shopJsonArray = JSONUtil.parseArray(eleShop.getContent());
                removeShopKeys(shopJsonArray);
                shopJsonObj.put("shop", shopJsonArray);
            } else {
                shopJsonObj.put("del", 1); //已经爬过了
                restaurant_ids1.add(shopJsonObj);
            }
            removeShopKeys(shopJsonObj);
            restaurant_ids.add(restaurant_id);
        }
        for (JSONObject aLong : restaurant_ids1) {
            jsonArrayShoplist.remove(aLong);
        }


        for (Object o : jsonArrayShoplist) {
            logger.info(o.toString()); //save log
            qty++;
        }

        logger.error("{} , there is {} that have hean downloaded ", dc_index, qty);
    }

    private void removeShopKeys(JSONArray shopJsonArr) {
        if (shopJsonArr == null) {
            return;
        }
        Iterator<Object> iterator = shopJsonArr.iterator();
        while (iterator.hasNext()) {
            JSONObject next = (JSONObject) iterator.next();
            next.remove("is_selected");
            next.remove("description");
            next.remove("icon_url");
            JSONArray foods = JSONUtil.getJSONArray(next, "foods");
            Iterator<Object> iterator1 = foods.iterator();
            while (iterator1.hasNext()) {
                JSONObject next1 = (JSONObject) iterator1.next();
                next1.remove("limitation");
                next1.remove("description");
                next1.remove("image_path");
                next1.remove("tips");
                JSONObject activity = JSONUtil.getJSONObject(next1, "activity");

                if (activity == null) {
                    continue;
                }
                activity.remove("icon_color");
                activity.remove("image_text_color");
                activity.remove("applicable_quantity_text");
                activity.remove("quantity_text");
                activity.remove("applicable_quantity_detail_text");
                activity.remove("image_text");
                activity.remove("icon_name");
                activity.remove("description");
                activity.remove("applicable_quantity_text_color");
                activity.remove("benefit_text");
//                next1.remove("specifications");
            }
        }

    }

    private void removeShopKeys(JSONObject shopJsonObj) {
        if (shopJsonObj == null) {
            return;
        }
        shopJsonObj.remove("supports");
        shopJsonObj.remove("recommend");
        shopJsonObj.remove("promotion_info");
        shopJsonObj.remove("image_path");
        shopJsonObj.remove("max_applied_quantity_per_order");
        shopJsonObj.remove("delivery_mode");
    }


//    "abbr": "XM",
//    "id": 13,
//    "latitude": 24.479509,
//    "longitude": 118.089478,
//    "name": "厦门",
//    "pinyin": "xiamen"
}
