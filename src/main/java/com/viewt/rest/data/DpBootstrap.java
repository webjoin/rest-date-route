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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elijah on 18/7/2017.
 */
public class DpBootstrap {

    static String categoryId = "102;103;104;106;107;110;111;112,-3613;112,1831;112,1832;112,1833;112,1834;112,1835;112,1836;112,1837;112,1838;112,1839;112,1840;112,8085;112,8373;112,8374;112,8375;112,15652;112,27090;112,28549;112,28887;112,1847;112,1846;112,1830;112,15651;112,1842;112,8087;112,15650;112,27464;112,1843;112,65208;112,70185;112,27090;112,70208;112,15649;112,27054;112,1845;112,1844;112,70523;112,70175;112,106;112,107;112,108;112,109;114;115;116;117,-3613;117,104;117,105;117,106;117,107;117,108;117,109;118,-3613;118,104;118,105;118,106;118,107;118,108;118,109;132;219;224;247;249;250;251;508;733;1338;1783;1817";
    static Logger logger = LoggerFactory.getLogger(DpBootstrap.class);

    public static void main(String[] args) {
        DpBootstrap bootstrap = new DpBootstrap();
//        bootstrap.crawl1(args);
        bootstrap.crawl();

    }

    private void crawl1(String[] args) {
        if (args.length == 0) {
            System.out.println("参数不能为空");
            System.exit(1);
        }

        String cityId;
        cityId = WaimaiBootstrap.dp_cityIds[0];  //

        String regionId = "0";
        String categoryid = "10";
        if (args.length >= 1) {
            categoryid = args[0];
        }
        if (args.length >= 2) {
            regionId = args[1];
        }

        String timestamp = System.currentTimeMillis() + "";
        int start = 0;
        while (true) {
            TimeUtil.sleep(1);
            RespBean respBean = urlService.searchshop4Dp(start, cityId, regionId, categoryid, timestamp);
            String content = respBean.getContent();
            if (StringUtils.isNotEmpty(content)) {
                try {
                    int i1 = content.indexOf("{");
                    int i2 = content.lastIndexOf("}");
                    content = content.substring(i1, i2 + 1);
                    JSONObject jsonObject = JSONUtil.parseObject(content);
                    String isEnd = jsonObject.getString("isEnd");
                    logger.info(content);
                    if ("true".equals(isEnd)) {
                        break;
                    }
                } catch (Exception e) {
                    logger.error(" start:{},cityId:{},regionId:{},categoryid:{} some exception,{} ", start, cityId, regionId, categoryid, e.getMessage());
                }

            } else {
                logger.info(" start:{},cityId:{},regionId:{},categoryid:{} has not data pls check", start, cityId, regionId, categoryid);
            }
            start = start + 25;
        }
    }

    UrlService urlService = new UrlServiceImpl();
    JSONArray regionNavs;
    JSONArray categoryNavs;

    void crawl() {


        String userDir = System.getProperty("user.dir");
        String dpJonsFilePath = userDir + "/mate/dp-xm.json";
               dpJonsFilePath = userDir + "/mate/dp-sh.json";
//        dpJonsFilePath = "/Users/Elijah/Desktop/self/rest-date-route/src/main/resources/dp-sh.json";
        List<String> list = FileUtil.readFile(dpJonsFilePath);
        String dpJsonText = StringUtils.join(list.toArray(new String[list.size()]), "");
        JSONObject jsonObject = JSON.parseObject(dpJsonText);
        String cityId = WaimaiBootstrap.dp_cityIds[0];
        cityId = WaimaiBootstrap.dp_cityIds[1];  //
        regionNavs = jsonObject.getJSONArray("regionNavs");
        categoryNavs = jsonObject.getJSONArray("categoryNavs");
        Iterator<Object> iterator = regionNavs.iterator();
        Iterator<Object> category = categoryNavs.iterator();
        int i = 0;
        List<JSONObject> regionItems = new ArrayList<>();
        List<JSONObject> categoryItems = new ArrayList<>();
//        while (iterator.hasNext()) {
//            JSONObject regionBeanJSON = (JSONObject) iterator.next();
//            int parentId = regionBeanJSON.getIntValue("parentId");
//            int id = regionBeanJSON.getIntValue("id");
//            String name = regionBeanJSON.getString("name");
//            if (id == 0 || id == -10000 || parentId == -10000 || parentId == 0 || id == parentId) continue;
//            i++;
//            System.out.println(i + "-->>" + name);
//            regionItems.add(regionBeanJSON);
//        }
//        countByRegion(cityId, regionItems);
        while (category.hasNext()) {
            JSONObject categoryBeanJSON = (JSONObject) category.next();
            int id = categoryBeanJSON.getIntValue("id");
            int parentId = categoryBeanJSON.getIntValue("parentId");
            String name = categoryBeanJSON.getString("name");
            if (id == 10 || id == 0 || parentId == 0 || id == parentId) continue;
            if (parentId != 10) continue;
            System.out.println(++i + " ,id:" + id + ",name:" + name);
            categoryItems.add(categoryBeanJSON);
        }
        countByXX(cityId, categoryItems, false);
    }

    private void countByXX(String cityId, List<JSONObject> regionItems, boolean isRegion) {
        int i = 0;
        List<JSONObject> ls = new ArrayList<>();
        List<JSONObject> ls1 = new ArrayList<>();
        for (JSONObject regionItem : regionItems) {
            i++;
            JSONObject jsonObject = searchshop4Dp(i, cityId, regionItem, isRegion);
            ls.add(jsonObject);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
//        String jsonArrStr = "[{\"recordCount\":\"23524\",\"name\":\"小吃快餐\",\"id\":\"112\"},{\"recordCount\":\"1689\",\"name\":\"海鲜\",\"id\":\"251\"},{\"recordCount\":\"1581\",\"name\":\"咖啡厅\",\"id\":\"132\"},{\"recordCount\":\"292\",\"name\":\"自助餐\",\"id\":\"111\"},{\"recordCount\":\"6335\",\"name\":\"面包甜点\",\"id\":\"117\"},{\"recordCount\":\"529\",\"name\":\"日本料理\",\"id\":\"224\"},{\"recordCount\":\"1579\",\"name\":\"西餐\",\"id\":\"116\"},{\"recordCount\":\"1021\",\"name\":\"闽菜\",\"id\":\"249\"},{\"recordCount\":\"1362\",\"name\":\"火锅\",\"id\":\"110\"},{\"recordCount\":\"1305\",\"name\":\"烧烤\",\"id\":\"508\"},{\"recordCount\":\"277\",\"name\":\"韩国料理\",\"id\":\"114\"},{\"recordCount\":\"421\",\"name\":\"粤菜/潮州菜\",\"id\":\"103\"},{\"recordCount\":\"1639\",\"name\":\"川菜\",\"id\":\"102\"},{\"recordCount\":\"109\",\"name\":\"东南亚菜\",\"id\":\"115\"},{\"recordCount\":\"301\",\"name\":\"私房菜\",\"id\":\"1338\"},{\"recordCount\":\"427\",\"name\":\"湘菜\",\"id\":\"104\"},{\"recordCount\":\"317\",\"name\":\"东北菜\",\"id\":\"106\"},{\"recordCount\":\"306\",\"name\":\"客家菜\",\"id\":\"733\"},{\"recordCount\":\"283\",\"name\":\"江西菜\",\"id\":\"247\"},{\"recordCount\":\"2322\",\"name\":\"粉面馆\",\"id\":\"1817\"},{\"recordCount\":\"156\",\"name\":\"台湾菜\",\"id\":\"107\"},{\"recordCount\":\"258\",\"name\":\"小龙虾\",\"id\":\"219\"},{\"recordCount\":\"93\",\"name\":\"创意菜\",\"id\":\"250\"},{\"recordCount\":\"460\",\"name\":\"家常菜\",\"id\":\"1783\"},{\"recordCount\":\"5416\",\"name\":\"其他\",\"id\":\"118\"}]";
//        JSONArray jsonArray = JSON.parseArray(jsonArrStr);
//        Iterator<Object> iterator = jsonArray.iterator();
//        while (iterator.hasNext()) {
//            ls.add((JSONObject) iterator.next());
//        }
        int parentRegionId = 0;
        for (JSONObject jsonObject : ls) {
            int recordCount = jsonObject.getIntValue("recordCount");
            if (recordCount > 5000) {
                List<JSONObject> jsonObjects = querySubCount(cityId, jsonObject, parentRegionId);
                ls1.addAll(jsonObjects);
            }
        }
        ls.addAll(ls1);
        ls1.clear();
        for (JSONObject json : ls) {
            int region4Count = json.getIntValue(parentRegionId + "region4Count");
            int parentRegionId1 = json.getIntValue(parentRegionId + "regionId");
            String parentRegionName = json.getString(parentRegionId + "regionName");
            if (region4Count > 5000) {
                List<JSONObject> jsonObjects = querySubCount(cityId, json, parentRegionId1);
                ls1.addAll(jsonObjects);
            }
        }
        ls.addAll(ls1);
        for (JSONObject json : ls) {
            logger.info(json.toString());
        }

    }

    private List<JSONObject> querySubCount(String cityId, JSONObject jsonObject, int parentRegionId) {
        String categoryid = jsonObject.getString("id");
        List<JSONObject> rsJsonArr = new ArrayList<>();
        Iterator<Object> iterator = regionNavs.iterator();
        while (iterator.hasNext()) {

            JSONObject regionBeanJSON = (JSONObject) iterator.next();
            int parentId = regionBeanJSON.getIntValue("parentId");
            int regionId1 = regionBeanJSON.getIntValue("id");
            String name = regionBeanJSON.getString("name");
            if (parentId != parentRegionId) continue;
            if (regionId1 == -10000) continue;
            if (parentRegionId != 0)
                if (regionId1 == parentId) continue;
            String regionId = regionId1 + "";
            String timestamp = System.currentTimeMillis() + "";
            RespBean respBean = urlService.searchshop4Dp(0, cityId, regionId, categoryid, timestamp);
            String content = respBean.getContent();
            String count = "0";
            if (StringUtils.isNotEmpty(content)) {
                int i1 = content.indexOf("{");
                int i2 = content.lastIndexOf("}");
                content = content.substring(i1, i2 + 1);
                JSONObject contentJSON = JSON.parseObject(content);
                count = contentJSON.getString("recordCount");
            }
            logger.info("--||--{},{},{},{}", categoryid, regionId, name, count);

            JSONObject regionCountJson = new JSONObject();
            regionCountJson.putAll(jsonObject);

            regionCountJson.put(parentRegionId + "regionId", regionId);
            regionCountJson.put(parentRegionId + "regionName", name);
            regionCountJson.put(parentRegionId + "region4Count", count);
            rsJsonArr.add(regionCountJson);
        }
        return rsJsonArr;
    }


    JSONObject searchshop4Dp(int i, String cityId, JSONObject regionItem, boolean isRegion) {
        String regionId;
        String categoryid;

        String name = regionItem.getString("name");
        String id = regionItem.getString("id");

        String timestamp = System.currentTimeMillis() + "";
        if (isRegion) {
            categoryid = "10";
            regionId = id;
        } else {
            regionId = "0";
            categoryid = id;
        }
        RespBean respBean = urlService.searchshop4Dp(0, cityId, regionId, categoryid, timestamp);

        String content = respBean.getContent();

        String recordCount = "-1";
        if (StringUtils.isNotEmpty(content)) {
            int i1 = content.indexOf("{");
            int i2 = content.lastIndexOf("}");
            content = content.substring(i1, i2 + 1);
            JSONObject jsonObject = JSON.parseObject(content);

            recordCount = jsonObject.getString("recordCount");
        }
        logger.info("region->{},{},{},{}", i, id, name, recordCount);
        JSONObject rsJson = new JSONObject();
        rsJson.put("id", id);
        rsJson.put("name", name);
        rsJson.put("recordCount", recordCount);
        return rsJson;
    }


}
