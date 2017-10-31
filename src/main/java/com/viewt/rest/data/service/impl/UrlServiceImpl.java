package com.viewt.rest.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.bean.RespSelectBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.util.CodeUtil;
import com.viewt.rest.data.util.JSONUtil;
import org.apache.commons.lang3.StringUtils;

import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Elijah on 7/7/2017.
 */
public class UrlServiceImpl extends AbstractService implements UrlService {
    @Override
    public void testAbuyun() {
        String url = "https://www.baidu.com/";
        url = "http://www.163.com/";
        url = "http://3g.163.com/touch/all?dataversion=A&version=v_standard";
        String cookie = "";
//        for (int i = 0; i < 50; i++) {
//            try {
//                RespBean respBean = null;//this.doGetRequest(url, cookie);
//                System.out.println(i + "--" + respBean.getContent().substring(0, 300));
//                System.out.println(i + "--" + respBean.getCookies().entrySet());
//            } catch (Exception e) {
//                System.out.println(i + "--" + e.getCause());
//            }
//
//        }

    }



    //    Logger

    @Override
    public RespBean getCityWmCookies(String city_py, String city_id) {
//        String url = "http://i.waimai.meituan.com/xiaomen?city_id=350200"; //厦门
        String url = "http://i.waimai.meituan.com/" + city_py + "?city_id=" + city_id; //厦门
        Map<String, String> reqHeader = new HashMap<>();
        reqHeader.put("User-Agent", userAgent);
        RespBean respBean = get(url, encoding, reqHeader);

        String content = respBean.getContent();
        Map<String, String> firstCookies = respBean.getCookies();
//        content.
        String regx = "https://webapi.amap.com/maps\\?v=1.3&key=(.+)\"";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()) {
            String key = matcher.group(1);
            respBean.getCookies().put("key", key);
        }
        String key = "{key}";
        url = "https://webapi.amap.com/maps?v=1.3&key=" + key;
        String keyValue = firstCookies.get("key");
        boolean existsKey = keyValue != null;
        if (!existsKey) {
            logger.error("city_py:{}，city_id{} 's key is empty pls check!", city_py, city_id);
        }
        url = url.replace(key, !existsKey ? "" : keyValue);
        respBean = get(url, encoding, reqHeader);
        firstCookies.putAll(respBean.getCookies());
        respBean.getCookies().clear();
        respBean.getCookies().putAll(firstCookies);
        respBean.setContent(null);
        return respBean;
    }


    /**
     * @param cityName
     * @param keywords 美团
     * @param respBean
     * @return
     */
    @Override
    public List<RespSelectBean> searchText(String cityName, String keywords, RespBean respBean) {
        Map<String, String> cookies = respBean.getCookies();
        String key = cookies.get("key");
        String guid = cookies.get("guid");
        StringBuffer url = new StringBuffer("https://restapi.amap.com/v3/place/text");
        url.append("?");
        url.append("s").append("=").append("rsv3");
        url.append("&children").append("=").append("");
        url.append("&key").append("=").append(key);
        url.append("&types").append("=").append(CodeUtil.urlEecode("商务住宅|学校信息|生活服务|公司企业|餐饮服务|购物服务|住宿服务|交通设施服务|娱乐场所|医院类型|银行类型|风景名胜|科教文化服务|汽车服务"));
        url.append("&offset").append("=").append("10");
        url.append("&city").append("=").append(CodeUtil.urlEecode(cityName));
        url.append("&page").append("=").append("1");
        url.append("&language").append("=").append("zh_cn");
        url.append("&callback").append("=").append("jsonp_313964_");
        url.append("&platform").append("=").append("JS");
        url.append("&logversion").append("=").append("2.0");
        url.append("&sdkversion").append("=").append("1.3");
        url.append("&appname").append("=").append(CodeUtil.urlEecode("http://i.waimai.meituan.com/xiamen?city_id=350200"));
        url.append("&csid").append("=").append("F9AD8DE6-9C65-4E91-A551-B42E4B6E4772");
        url.append("&keywords").append("=").append(CodeUtil.urlEecode(keywords));

        Map<String, String> reqHeader = new HashMap<>();

        StringBuffer cookie = new StringBuffer();
        cookie.append("key=").append(key).append(";");
        cookie.append(" guid=").append(guid).append(";");

        reqHeader.put("Cookie", cookie.toString());
//      https://restapi.amap.com/v3/place/text?s:rsv3&children:&key:3f3868abdb36336114bde5ab6eecdb68&types:%E5%95%86%E5%8A%A1%E4%BD%8F%E5%AE%85%7C%E5%AD%A6%E6%A0%A1%E4%BF%A1%E6%81%AF%7C%E7%94%9F%E6%B4%BB%E6%9C%8D%E5%8A%A1%7C%E5%85%AC%E5%8F%B8%E4%BC%81%E4%B8%9A%7C%E9%A4%90%E9%A5%AE%E6%9C%8D%E5%8A%A1%7C%E8%B4%AD%E7%89%A9%E6%9C%8D%E5%8A%A1%7C%E4%BD%8F%E5%AE%BF%E6%9C%8D%E5%8A%A1%7C%E4%BA%A4%E9%80%9A%E8%AE%BE%E6%96%BD%E6%9C%8D%E5%8A%A1%7C%E5%A8%B1%E4%B9%90%E5%9C%BA%E6%89%80%7C%E5%8C%BB%E9%99%A2%E7%B1%BB%E5%9E%8B%7C%E9%93%B6%E8%A1%8C%E7%B1%BB%E5%9E%8B%7C%E9%A3%8E%E6%99%AF%E5%90%8D%E8%83%9C%7C%E7%A7%91%E6%95%99%E6%96%87%E5%8C%96%E6%9C%8D%E5%8A%A1%7C%E6%B1%BD%E8%BD%A6%E6%9C%8D%E5%8A%A1&offset:10&city:%E5%8E%A6%E9%97%A8&page:1&language:zh_cn&callback:jsonp_313964_&platform:JS&logversion:2.0&sdkversion:1.3&appname:http%3A%2F%2Fi.waimai.meituan.com%2Fxiamen%3Fcity_id%3D350200&csid:F9AD8DE6-9C65-4E91-A551-B42E4B6E4772&keywords:%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6
        RespBean respBean1 = get(url.toString(), encoding, reqHeader);
        String content = respBean1.getContent();
        int i = content.indexOf("(");
        int ii = content.lastIndexOf(")");
        if (i > -1) {
            content = content.substring(i + 1, ii);
        }
        JSONObject jsonObject = JSONUtil.parseObject(content);
        String pois = jsonObject.getString("pois");
        respBean1 = null;
        List<RespSelectBean> respSelectBean = JSONUtil.parseArray(pois, RespSelectBean.class);
        return respSelectBean;
    }

    /**
     * @param keyword Eleme keywork
     */
    @Override
    public RespBean searchPoiNearby(String keyword, String longitude, String latitude) {
        String eleKeywordsUrl = "https://mainsite-restapi.ele.me/bgs/poi/search_poi_nearby?keyword=${keyword}&offset=0&limit=20&longitude=${longitude}&latitude=${latitude}";
        eleKeywordsUrl = eleKeywordsUrl.replace("${keyword}", keyword);
        eleKeywordsUrl = eleKeywordsUrl.replace("${longitude}", longitude);
        eleKeywordsUrl = eleKeywordsUrl.replace("${latitude}", latitude);
        logger.info("{}，{}，{}", keyword, longitude, latitude);
        RespBean respBean = this.get(eleKeywordsUrl, encoding, null);
        return respBean;
    }

    @Override
    public RespBean getContentByUrl(String url,Map<String,String> header) {
        RespBean respBean = this.get(url, encoding, header);
        return respBean;
    }

    @Override
    public RespBean executePostFrom(String url,Map<String,String> formMap,Map<String,String> header) {
        RespBean respBean = this.executePostFrom(url,formMap,encoding,header);
        return respBean;
    }

    @Override
    public RespBean executePostJson(String url, String reqBodyJson, Map<String, String> header) {
        RespBean respBean = this.executePostJson(url,reqBodyJson,encoding,header);
        return respBean;
    }

    /**
     * @param restaurant_id Eleme
     * @return
     */
    @Override
    public RespBean getEleShop(long restaurant_id) {
        String shopUrl = "https://mainsite-restapi.ele.me/shopping/v2/menu?restaurant_id=" + restaurant_id;
        RespBean respBean = this.get(shopUrl, encoding, null);
        return respBean;
    }

    /**
     * Eleme
     *
     * @param offset
     * @param shopLongitude
     * @param shopLatitude
     * @return
     */
    @Override
    public RespBean restaurants(String offset, String shopLongitude, String shopLatitude, String categoryIds) {
        String restaurantsUrl = "https://mainsite-restapi.ele.me/shopping/restaurants?latitude=${latitude}&longitude=${longitude}&keyword=&offset=${offset}&limit=30&extras[]=activities";
        restaurantsUrl = restaurantsUrl.replace("${longitude}", shopLongitude);
        restaurantsUrl = restaurantsUrl.replace("${latitude}", shopLatitude);
        restaurantsUrl = restaurantsUrl.replace("${offset}", offset);
        String[] split = categoryIds.split(",");
        for (int i = 0; i < split.length; i++) {
            restaurantsUrl += "&restaurant_category_ids[]=" + split[i];
        }
        RespBean respBean = this.get(restaurantsUrl, encoding, null);
        if (respBean != null) {
            respBean.setReqUrl(restaurantsUrl);
        }
        return respBean;
    }

    @Override
    public RespBean getCategory(Double latitude, Double longitude) {
        String restaurantsUrl = "https://mainsite-restapi.ele.me/shopping/v2/restaurant/category?latitude=${latitude}&longitude=${longitude}";
        restaurantsUrl = restaurantsUrl.replace("${longitude}", String.valueOf(longitude));
        restaurantsUrl = restaurantsUrl.replace("${latitude}", String.valueOf(latitude));
        RespBean respBean = this.get(restaurantsUrl, encoding, null);
        return respBean;
    }

    @Override
    public JSONObject getShopList(String w_addr, String cookie, String uuid, String pageIndex) {
        String _token = "eJx9/";
        StringBuffer url = new StringBuffer("http://i.waimai.meituan.com/ajax/v6/poi/filter");
        url.append("?");
        url.append("ategory_type").append("=").append("910");
        url.append("&category_text").append("=").append("%E7%BE%8E%E9%A3%9F");
        url.append("&_token").append("=").append(_token);

        Map<String, String> reqHeader = new HashMap<>();
        reqHeader.put("Cookie", cookie);
        reqHeader.put("User-Agent", userAgent);

        Map<String, String> formMap = new HashMap<>();
        formMap.put("uuid", uuid);
        formMap.put("platform", "3");
        formMap.put("partner", "4");
        formMap.put("page_index", pageIndex);
        formMap.put("apage", "1");
        RespBean respBean1 = executePostFrom(url.toString(), formMap, encoding,reqHeader);
        String content = respBean1.getContent();
        JSONObject data = null;
        if (StringUtils.isNotEmpty(content)) {
            JSONObject jsonObject = JSONUtil.parseObject(content);
//            removeOutterKeys(jsonObject);
            data = JSONUtil.getJSONObject(jsonObject, "data");
            removeOutterKeys(data);
            if (null == data) {
                logger.error("{}:pageIndex:{}.data is null", w_addr, pageIndex); //logger
            } else {
                JSONArray poilist = JSONUtil.getJSONArray(data, "poilist");
                if (null == poilist || poilist.isEmpty()) {
                    logger.error("{}:pageIndex:{}.poi_has_next_page is not false but poilist is empty", w_addr, pageIndex); //logger
                } else {
                    for (Object poi : poilist) {
                        JSONObject poiJson = (JSONObject) poi;
                        String
                                wm_poi_id = poiJson.getString("id");
                        JSONObject shopJSON = getShop(wm_poi_id, _token, uuid, cookie);
//                        System.out.println("{},page: " + pageIndex + "-->" + wm_poi_id + "-->" + (shopJSON == null),w_addr);
                        if (null == shopJSON) {
                            logger.error("{},page: " + pageIndex + "-->" + wm_poi_id + "--> the dishes of shop is null", w_addr);
                            continue;
                        }
                        Object data1 = shopJSON.get("data");
                        if (null == data1) {
                            logger.error("{}page: " + pageIndex + "-->" + wm_poi_id + "--> the dishes.data of shop is null", w_addr);
                            continue;
                        }
                        if (data1 instanceof String) {
                            logger.error("{}page: " + pageIndex + "-->" + wm_poi_id + "-->" + data1, w_addr);
                            poiJson.put("shop", data1);
                        } else {
                            JSONObject shopJSON1 = (JSONObject) data1;
                            removeShopKeys(shopJSON1);
                            poiJson.put("shop", shopJSON1);
                        }
                    }
                }
                String poi_has_next_page = data.getString("poi_has_next_page");
                if ("false".equals(poi_has_next_page)) {
                    logger.info("{}:{}", w_addr, ",抓取完毕");
                    return data;
                }
            }
        } else {
            logger.error("{}pageIndex:{}.responseText is null", w_addr, pageIndex); //logger
        }
        return data;
    }

    private void removeShopKeys(JSONObject shopJSON1) {
        if (shopJSON1 == null) {
            return;
        }
        shopJSON1.remove("shopping_cart");
        shopJSON1.remove("latest_bought");
//        shopJSON1.remove("poi_info");
        JSONObject poi_info = JSONUtil.getJSONObject(shopJSON1, "poi_info");
        if (poi_info != null) {
            poi_info.remove("story_info");
            poi_info.remove("buz_type");
            poi_info.remove("share_tip");
            poi_info.remove("delivery_type");
//                poi_info.remove("name" : "休闲排档",
            poi_info.remove("app_delivery_tip");
            poi_info.remove("delivery_time_tip");
//            poi_info.remove("pic_url");
            poi_info.remove("poi_back_pic_url");
            poi_info.remove("wm_poi_score");
            poi_info.remove("score");
            poi_info.remove("buz_code");
            poi_info.remove("shipping_fee_tip");
            poi_info.remove("status");
            poi_info.remove("is_favorite");
//                poi_info.remove("invoice_support" );
            poi_info.remove("bulletin");
//                poi_info.remove("shipping_time" : "00:00-02:00,16:30-23:59",
            poi_info.remove("head_pic_url");
            poi_info.remove("restrict_toast");
//                poi_info.remove("min_price" : 198.0,
            poi_info.remove("remind_infos");
            poi_info.remove("has_poi_env");
//                poi_info.remove("support_pay" : NumberInt(1),
//                poi_info.remove("avg_delivery_time" : NumberInt(44),
//                poi_info.remove("wm_poi_view_id" : "341115875298156",
//                poi_info.remove("id" : NumberLong(341115875298156),
//                poi_info.remove("comment_number" : NumberInt(0),
            poi_info.remove("min_price_tip");
            poi_info.remove("discount_restrict");
            poi_info.remove("shipping_fee");
            poi_info.remove("can_use_coupon");
            poi_info.remove("poi_sell_status");
//                poi_info.remove("discounts2"
            poi_info.remove("shipping_fee_type");

        }
        shopJSON1.remove("shopping_cart_entrance");
        shopJSON1.remove("container_template");
        shopJSON1.remove("is_show_rcmd_pois");
        shopJSON1.remove("poi_notifications");
        shopJSON1.remove("container_operation_source");
        shopJSON1.remove("friend_status");
//        shopJSON1.remove("food_spu_tags");
        JSONArray food_spu_tags1 = JSONUtil.getJSONArray(shopJSON1, "food_spu_tags");
        Iterator<Object> iterator2 = food_spu_tags1.iterator();
        while (iterator2.hasNext()) {
            JSONObject foodJSON = (JSONObject) iterator2.next();
            if (foodJSON != null) {
                foodJSON.remove("tag_description");
                foodJSON.remove("tag_description");
                foodJSON.remove("tags");
                foodJSON.remove("icon");
                foodJSON.remove("selected");
                foodJSON.remove("product_count");
//            foodJSON.remove("activity_tag" : "discount",
                foodJSON.remove("tag");
                foodJSON.remove("type");
                foodJSON.remove("current_page");
                foodJSON.remove("sequence");
//            foodJSON.remove("name" : "折扣",
//            foodJSON.remove("spus" : [
                JSONArray spus = JSONUtil.getJSONArray(foodJSON, "spus");
                if (spus != null) {
                    Iterator<Object> iterator = spus.iterator();
                    while (iterator.hasNext()) {
                        JSONObject spusJSON = (JSONObject) iterator.next();
                        spusJSON.remove("log_field");
//                   spusJSON.remove("tag" : "24859955",
                        spusJSON.remove("description");
//                   spusJSON.remove("praise_num" : NumberInt(0),
                        spusJSON.remove("activity_type");
                        spusJSON.remove("promotion_info");
                        spusJSON.remove("product_label_picture");
                        spusJSON.remove("friends_praise_content");
                        spusJSON.remove("status_description");
                        spusJSON.remove("product_label_picture_list");
                        spusJSON.remove("sku_label");
                        spusJSON.remove("picture");
                        spusJSON.remove("praise_content");
//                   spusJSON.remove("praise_num_new" : NumberInt(0),
//                   spusJSON.remove("name" : "麻辣小龙虾",
//                    spusJSON.remove("tread_num");
                        JSONArray skus = JSONUtil.getJSONArray(spusJSON, "skus");
                        if (skus != null) {
                            Iterator<Object> iterator1 = skus.iterator();
                            while (iterator1.hasNext()) {
                                JSONObject skusJSON = (JSONObject) iterator1.next();
                                if (skusJSON != null) {
                                    skusJSON.remove("promotion_info");
//                                skusJSON.remove("box_num" );
//                                skusJSON.remove("picture" );
//                                skusJSON.remove("box_price" );
                                    skusJSON.remove("spec");
                                    skusJSON.remove("real_stock");
                                    skusJSON.remove("status");
//                                skusJSON.remove("origin_price" );
//                                skusJSON.remove("price" );
                                    skusJSON.remove("description");
                                    skusJSON.remove("activity_stock");
                                    skusJSON.remove("min_order_count");
                                    skusJSON.remove("restrict");
//                                skusJSON.remove("id" );
//                                skusJSON.remove("stock" );
                                }
                            }
                        }
//                    spusJSON.remove("id" :NumberInt(257801413),
//                    spusJSON.remove(        "min_price" :54.4,
//                    spusJSON.remove(        "activity_tag" :"discount",
                        spusJSON.remove("unit");
                        spusJSON.remove("friends_nickname_praise_content");
                        spusJSON.remove("month_saled_content");
                        spusJSON.remove("attrs");
                        spusJSON.remove("status_remind_list");
                        spusJSON.remove("share_tip");
                        spusJSON.remove("status");
                        spusJSON.remove("sequence");
                        spusJSON.remove("activity_policy");
                    }
                }
                foodJSON.remove("buzType");
                foodJSON.remove("has_next_page");
                foodJSON.remove("description");
            }
        }

    }

    private void removeOutterKeys(JSONObject jsonObject) {
        if (null == jsonObject) {
            return;
        }
        jsonObject.remove("rank_strategy_tag");
        jsonObject.remove("remind_infos");
        jsonObject.remove("rank_strategy_version");
        JSONArray poilist = JSONUtil.getJSONArray(jsonObject, "poilist");
        if (null == poilist) {
            return;
        }
        Iterator<Object> iterator = poilist.iterator();
        while (iterator.hasNext()) {
            JSONObject jsonObject1 = (JSONObject) iterator.next();
            jsonObject1.remove("new_promotion");
            jsonObject1.remove("origin_status");

            jsonObject1.remove("is_favorite");

            jsonObject1.remove("shipping_fee_tip");
            jsonObject1.remove("charge_info");
            jsonObject1.remove("recommend_info");
            jsonObject1.remove("buz_type");

            jsonObject1.remove("sort_reason_tag");
            jsonObject1.remove("across_book_offset_days");
            jsonObject1.remove("sort_reason_tag");
            jsonObject1.remove("across_book_offset_days");
            jsonObject1.remove("invoice_min_price");
            jsonObject1.remove("shipping_fee_discount");
            jsonObject1.remove("status_desc");
            jsonObject1.remove("buz_type");
            jsonObject1.remove("brand_type");
            jsonObject1.remove("across_book_max_days");
            jsonObject1.remove("wm_poi_score");
            jsonObject1.remove("new_promotion");
            jsonObject1.remove("sort_reason_type");
//                  jsonObject1.remove(  "discounts2"                           );
            jsonObject1.remove("ad_attr");
            jsonObject1.remove("pic_url");
            jsonObject1.remove("wm_poi_opening_days");
            jsonObject1.remove("priority");
            jsonObject1.remove("shipping_time_info");
            jsonObject1.remove("min_price_tip");
            jsonObject1.remove("origin_status");
            jsonObject1.remove("delivery_type");
            jsonObject1.remove("charge_info");
            jsonObject1.remove("recommend_info");
            jsonObject1.remove("primitiveDistance");
            jsonObject1.remove("is_favorite");
            jsonObject1.remove("ad_type");
            jsonObject1.remove("poi_type_icon");
            jsonObject1.remove("status");
            jsonObject1.remove("mt_delivery_time");
            jsonObject1.remove("pre_book");
            jsonObject1.remove("invoice_support");
            jsonObject1.remove("support_pay");
            jsonObject1.remove("wm_poi_score");
//                    "distance"
        }
    }

    @Override
    public JSONObject getShop(String wm_poi_id, String _token, String uuid, String cookie) {
        StringBuffer url = new StringBuffer("http://i.waimai.meituan.com/ajax/v8/poi/food");
        url.append("?");
        url.append("_token").append("=").append(_token);  // 这样可取值
        String encoding = "utf-8";

        Map<String, String> reqParamMap = new HashMap<>();
        reqParamMap.put("wm_poi_id", wm_poi_id);
        reqParamMap.put("uuid", uuid);
        reqParamMap.put("platform", "3");
        reqParamMap.put("partner", "4");

        StringBuffer cookies = new StringBuffer();
        cookies.append(cookie);

        Map<String, String> reqHeader = new HashMap<>();
        reqHeader.put("Cookie", cookies.toString());
        reqHeader.put("User-Agent", userAgent);
        RespBean shopCookieInfo = executePostFrom(url.toString(), reqParamMap, encoding, reqHeader);
        JSONObject shopCookieJSON = JSONUtil.parseObject(shopCookieInfo.getContent());
        if (null == shopCookieJSON) {
            shopCookieJSON = new JSONObject();
        }
        JSONObject shopCookieDataJSON = JSONUtil.getJSONObject(shopCookieJSON, "data");
        if (shopCookieDataJSON == null) {
            shopCookieDataJSON = new JSONObject();
        }
        {
            url.delete(0, url.length());
            url.append("http://i.waimai.meituan.com/ajax/v6/poi/info");
            url.append("?");
            url.append("_token").append("=").append(_token);  // 这样可取值
            reqParamMap.put("wmpoiid", reqParamMap.get("wm_poi_id"));
            reqParamMap.remove("wm_poi_id");
            RespBean shopInfo = executePostFrom(url.toString(), reqParamMap, encoding, reqHeader);
            String content = shopInfo.getContent();
            if (StringUtils.isEmpty(content)) {
                logger.error("the content of the shop detail is empty");
            } else {
                JSONObject shopInfoJson = JSONUtil.parseObject(content);
                JSONObject shopInfoKeys = getShopInfoKeys(shopInfoJson);

                if (shopCookieDataJSON != null) {
                    shopCookieDataJSON.putAll(shopInfoKeys);
                }
            }

        }
        return shopCookieJSON;
    }


    private JSONObject getShopInfoKeys(JSONObject shopInfoJson) {
        if (shopInfoJson == null) {
            return null;
        }
        JSONObject rsJSON = new JSONObject();
        JSONObject data = JSONUtil.getJSONObject(shopInfoJson, "data");
        if (data == null) {
            return rsJSON;
        }
        String address = "address";
        String latitude = "latitude";
        String longitude = "longitude";
        String phone_list = "phone_list";
        String comment_num = "comment_num";
        String brand_type = "brand_type";
        String wm_poi_score = "wm_poi_score";

        rsJSON.put(address, data.getString(address));
        rsJSON.put(latitude, data.getLong(latitude));
        rsJSON.put(longitude, data.getLong(longitude));
        rsJSON.put(phone_list, data.getString(phone_list));
        rsJSON.put(comment_num, data.getLong(comment_num));
        rsJSON.put(brand_type, data.getLong(brand_type));
        rsJSON.put(wm_poi_score, data.getLong(wm_poi_score));
        shopInfoJson = null;
        return rsJSON;
    }


    @Override
    public RespBean searchshop4Dp(int start , String cityId, String regionId,String categoryid, String timestamp) {
        String dpUrl = "https://mapi.dianping.com/searchshop.json?start=${start}&regionid=${region}&categoryid=${categoryid}&sortid=0&locatecityid=${cityId}&maptype=0&cityid=${cityId}&_=${timestamp}&callback=Zepto${timestamp}";
//      String dpUrl = "https://mapi.dianping.com/searchshop.json?start=0&regionid=1839&categoryid=10&sortid=0&locatecityid=15&maptype=0&cityid=15&_=1500353442538&callback=Zepto1500353427712";

        dpUrl = dpUrl.replace("${region}", regionId);
        dpUrl = dpUrl.replace("${categoryid}", categoryid);
        dpUrl = dpUrl.replace("${cityId}", cityId);
        dpUrl = dpUrl.replace("${timestamp}", timestamp);
        dpUrl = dpUrl.replace("${start}", start+"");
        RespBean respBean = this.get(dpUrl, encoding, null);
        return respBean;
    }
}
