package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.WaimaiBootstrap;
import com.viewt.rest.data.bean.DpCategoryNavsBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.util.Cons;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Elijah on 24/7/2017.
 * -Darea=dp-mate
 *
 * 接下来是
 * @see com.viewt.rest.data.DpShopQtyCollectionBootstrap
 * 出马
 */
public class DpMateBootstrap extends BaseBootstrap {

    public static void main(String[] args) {
        DpMateBootstrap bootstrap = new DpMateBootstrap();
        bootstrap.start(args);
    }

    @Override
    protected void crawl(String[] args) {
        String s = "https://mapi.dianping.com/searchshop.json?start=0&regionid=0&categoryid=10&sortid=0&locatecityid={0}&maptype=0&cityid={1}&_=1500888002070&callback=Zepto1500887828836";
        String[] dp_cityIds = Cons.Dianping.CITY_IDS;
        reqHeader.put(Cons.USER_AGENT, Cons.MOBILE_USER_AGENT);

        for (int i = 0; i < dp_cityIds.length; i++) {
            String cityId = dp_cityIds[i];
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
//                save2db(cityId, contentJSON.getJSONArray(key1), contentJSON.getJSONArray(key2));
            }
            logger.info(rsJson.toString());
        }
    }

    private void save2db(String cityId, JSONArray categoryNavs, JSONArray regionNavs) {

        DpShopQtyCollectionBootstrap bootstrap = new DpShopQtyCollectionBootstrap();
        List<DpCategoryNavsBean> dpCategoryNavsBeans = JSON.parseArray(categoryNavs.toString(), DpCategoryNavsBean.class);
        Iterator<DpCategoryNavsBean> iterator = dpCategoryNavsBeans.iterator();
        while (iterator.hasNext()) {
            DpCategoryNavsBean region = iterator.next();
            int id = region.getId();
            int parentId = region.getParentId();
            if (id == parentId && parentId != id) {
                List<DpCategoryNavsBean> subCategories = bootstrap.getSubCategories(cityId, parentId);
                dpCategoryNavsBeans.addAll(subCategories);
            }
            insert("insertCategory", dpCategoryNavsBeans);
        }


//        Iterator<Object> iterator1 = regionNavs.iterator();
//        while (iterator1.hasNext()) {
//            JSONObject next = (JSONObject) iterator1.next();
//            System.out.println(next);
//        }

    }


}
