package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.DpMateBootstrap;
import com.viewt.rest.data.DpShopQtyCollectionBootstrap;
import com.viewt.rest.data.bean.DpCategoryNavsBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.test.db.DbBootstrap;
import com.viewt.rest.data.util.Cons;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.*;

/**
 * Created by Elijah on 25/7/2017.
 */
public class DpMatedataBootstrapDateTest extends DbBootstrap {
    public static void main(String[] args) {
        DpMatedataBootstrapDateTest bootstrapDate = new DpMatedataBootstrapDateTest();
        bootstrapDate.deal();


    }

    protected Map<String, String> reqHeader = new HashMap<>();
    protected UrlService urlService = new UrlServiceImpl();

    private void deal() {

        String s = "https://mapi.dianping.com/searchshop.json?start=0&regionid=0&categoryid=10&sortid=0&locatecityid={0}&maptype=0&cityid={1}&_=1500888002070&callback=Zepto1500887828836";
        String[] dpCityIds = Cons.Dianping.CITY_IDS;
        reqHeader.put(Cons.USER_AGENT, Cons.MOBILE_USER_AGENT);

        this.initSqlSessionFactory("local");

        for (int i = 0; i < dpCityIds.length; i++) {
            String cityId = dpCityIds[i];
            if (!"15".equals(cityId)) continue;
            String formatUrl = MessageFormat.format(s, cityId, cityId);
            RespBean contentByUrl = urlService.getContentByUrl(formatUrl, reqHeader);
            String content = contentByUrl.getContent();
            String key1 = "categoryNavs";
            String key2 = "regionNavs";
            String key3 = "recordCount";
            JSONObject rsJson = new JSONObject();
            rsJson.put("cityId", cityId);
            if (StringUtils.isNotEmpty(content)) {
                int i1 = content.indexOf("{");
                int i2 = content.lastIndexOf("}");
                content = content.substring(i1, i2 + 1);
                JSONObject contentJSON = JSON.parseObject(content);
                rsJson.put(key1, contentJSON.getJSONArray(key1));
                rsJson.put(key2, contentJSON.getJSONArray(key2));
                rsJson.put(key3, contentJSON.getInteger(key3));
                save2db(cityId, contentJSON.getJSONArray(key1), contentJSON.getJSONArray(key2));
            }
        }

    }

    private void save2db(String cityId, JSONArray categoryNavs, JSONArray regionNavs) {

        List<Integer> ids = new ArrayList<>();
        DpShopQtyCollectionBootstrap bootstrap = new DpShopQtyCollectionBootstrap();
        List<DpCategoryNavsBean> dpCategoryNavsBeans = JSON.parseArray(categoryNavs.toString(), DpCategoryNavsBean.class);
        Iterator<DpCategoryNavsBean> iterator = dpCategoryNavsBeans.iterator();
        List<DpCategoryNavsBean> ls = new ArrayList<>();
        List<DpCategoryNavsBean> ls1 = new ArrayList<>();
        while (iterator.hasNext()) {
            DpCategoryNavsBean category = iterator.next();
            int id = category.getId();
            int parentId = category.getParentId();
            if (parentId != id && (id == 10 || parentId == 10)) { //美食
                ls.add(category);
                ls.addAll(bootstrap.getSubCategories(cityId, id));
            }
        }
        for (DpCategoryNavsBean dpCategoryNavsBean : ls) {
            int id = dpCategoryNavsBean.getId();
            if (ids.contains(id)) continue;
            else {
                ids.add(id);
                ls1.add(dpCategoryNavsBean);
            }
        }
//        for (Integer id : ids) {
//            DpCategoryNavsBean bean = null;
//            for (DpCategoryNavsBean dpCategoryNavsBean : ls) {
//                int id1 = dpCategoryNavsBean.getId();
//                if (id == id1) {
//                    bean = dpCategoryNavsBean;
//                    break;
//                }
//            }
//            if (bean != null) {
//                ls.remove(bean);
//            }
//        }
        insert("insertCategory", ls1);


//        Iterator<Object> iterator1 = regionNavs.iterator();
//        while (iterator1.hasNext()) {
//            JSONObject next = (JSONObject) iterator1.next();
//            System.out.println(next);
//        }

    }


//    this.initSqlSessionFactory("local");


}
