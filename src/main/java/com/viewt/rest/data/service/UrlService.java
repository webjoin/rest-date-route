package com.viewt.rest.data.service;

import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.bean.RespSelectBean;

import java.util.List;
import java.util.Map;

/**
 * Created by Elijah on 7/7/2017.
 */
public interface UrlService {


    RespBean getCityWmCookies(String cityPy, String city_id);

    //    https://restapi.amap.com/v3/place/text?s=rsv3&children=&key=3f3868abdb36336114bde5ab6eecdb68&types=%E5%95%86%E5%8A%A1%E4%BD%8F%E5%AE%85%7C%E5%AD%A6%E6%A0%A1%E4%BF%A1%E6%81%AF%7C%E7%94%9F%E6%B4%BB%E6%9C%8D%E5%8A%A1%7C%E5%85%AC%E5%8F%B8%E4%BC%81%E4%B8%9A%7C%E9%A4%90%E9%A5%AE%E6%9C%8D%E5%8A%A1%7C%E8%B4%AD%E7%89%A9%E6%9C%8D%E5%8A%A1%7C%E4%BD%8F%E5%AE%BF%E6%9C%8D%E5%8A%A1%7C%E4%BA%A4%E9%80%9A%E8%AE%BE%E6%96%BD%E6%9C%8D%E5%8A%A1%7C%E5%A8%B1%E4%B9%90%E5%9C%BA%E6%89%80%7C%E5%8C%BB%E9%99%A2%E7%B1%BB%E5%9E%8B%7C%E9%93%B6%E8%A1%8C%E7%B1%BB%E5%9E%8B%7C%E9%A3%8E%E6%99%AF%E5%90%8D%E8%83%9C%7C%E7%A7%91%E6%95%99%E6%96%87%E5%8C%96%E6%9C%8D%E5%8A%A1%7C%E6%B1%BD%E8%BD%A6%E6%9C%8D%E5%8A%A1&offset=10&city=%E5%8E%A6%E9%97%A8&page=1&language=zh_cn&callback=jsonp_877843_&platform=JS&logversion=2.0&sdkversion=1.3&appname=http%3A%2F%2Fi.waimai.meituan.com%2Fxiamen%3Fcity_id%3D350200&csid=82500C5C-F2A6-4B9F-BEDF-BD5CACF34650&keywords=%E7%81%AB%E8%BD%A6%E7%AB%99
    List<RespSelectBean> searchText(String cityName, String keywords, RespBean respBean);

    JSONObject getShopList(String w_addr, String cookie, String uuid, String pageIndex);

    JSONObject getShop(String wm_poi_id, String _token, String uuid, String cookie);

    void testAbuyun();


//    RespBean executePostFrom(String url,
//                  Map<String, String> formMap,
//                  String encoding,
//                  Map<String, String> reqHeader);
//
//    RespBean executePostJson(String url,
//                         String reqBodyJson,
//                         String encoding,
//                         Map<String, String> reqHeader);


    RespBean searchPoiNearby(String keyword, String longitude, String latitude);


    RespBean executePostFrom(String url, Map<String, String> formMap, Map<String, String> header);
    RespBean executePostJson(String url, String reqBodyJson, Map<String, String> header);

    RespBean getEleShop(long restaurant_id);

    RespBean getCategory(Double latitude1, Double longitude1);

    RespBean restaurants(String offset, String longitude, String latitude, String categoryIds);

    /**
     * 点评数据
     *
     * @param regionId
     * @param timestamp
     */
    RespBean searchshop4Dp(int start, String cityId, String regionId, String categoryid, String timestamp);

    RespBean getContentByUrl(String url,Map<String,String> header);
}
