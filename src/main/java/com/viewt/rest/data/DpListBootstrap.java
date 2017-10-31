package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.BaseBootstrap;
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
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elijah on 18/7/2017.
 * 点评列表数据  明细通过 另外一个类处理
 * -Darea=dp-shop-list
 * 1 198 9
 * 城市 (菜系分类)category  (商圈)region
 */
public class DpListBootstrap extends BaseBootstrap {

    static Logger logger = LoggerFactory.getLogger(DpListBootstrap.class);

    public static void main(String[] args) {
        DpListBootstrap bootstrap = new DpListBootstrap();
        bootstrap.start(args);

    }

    private void calculate(String fileName) {
        String filePath = System.getProperty("user.dir") + "/mate/" + fileName;
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IllegalArgumentException(filePath + " is not exist , pls check it ");
        }


    }

    private void calculate(String[] args) {
        if (args.length == 0) {
            System.out.println("参数不能为空");
            System.exit(1);
        }

        String cityId = args[0];
        String regionId = "0";
        String categoryid = "10";
        if (args.length >= 2) {
            categoryid = args[1];
        }
        if (args.length >= 3) {
            regionId = args[2];
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

    @Override
    public void crawl(String[] args) {


//        String userDir = System.getProperty("user.dir");
//        String dpJonsFilePath = userDir + "/mate/dp-xm.json";
//               dpJonsFilePath = userDir + "/mate/dp-sh.json";
////        dpJonsFilePath = "/Users/Elijah/Desktop/self/rest-date-route/src/main/resources/dp-sh.json";
//        List<String> list = FileUtil.readFile(dpJonsFilePath);
//        String dpJsonText = StringUtils.join(list.toArray(new String[list.size()]), "");
//        JSONObject jsonObject = JSON.parseObject(dpJsonText);
//        String cityId = WaimaiBootstrap.dp_cityIds[0];
//        cityId = WaimaiBootstrap.dp_cityIds[1];  //
//        regionNavs = jsonObject.getJSONArray("regionNavs");
//        categoryNavs = jsonObject.getJSONArray("categoryNavs");
//        Iterator<Object> iterator = regionNavs.iterator();
//        Iterator<Object> category = categoryNavs.iterator();
//        int i = 0;
//        List<JSONObject> regionItems = new ArrayList<>();
//        List<JSONObject> categoryItems = new ArrayList<>();
////        while (iterator.hasNext()) {
////            JSONObject regionBeanJSON = (JSONObject) iterator.next();
////            int parentId = regionBeanJSON.getIntValue("parentId");
////            int id = regionBeanJSON.getIntValue("id");
////            String name = regionBeanJSON.getString("name");
////            if (id == 0 || id == -10000 || parentId == -10000 || parentId == 0 || id == parentId) continue;
////            i++;
////            System.out.println(i + "-->>" + name);
////            regionItems.add(regionBeanJSON);
////        }
////        countByRegion(cityId, regionItems);
//        while (category.hasNext()) {
//            JSONObject categoryBeanJSON = (JSONObject) category.next();
//            int id = categoryBeanJSON.getIntValue("id");
//            int parentId = categoryBeanJSON.getIntValue("parentId");
//            String name = categoryBeanJSON.getString("name");
//            if (id == 10 || id == 0 || parentId == 0 || id == parentId) continue;
//            if (parentId != 10) continue;
//            System.out.println(++i + " ,id:" + id + ",name:" + name);
//            categoryItems.add(categoryBeanJSON);
//        }
//        countByXX(cityId, categoryItems, false);
        String cityId = args[0];
        String categoryId = args[1];
        String regionId = args[2];

        int start = 0;
        while (true) {
            TimeUtil.sleep(500L);
            String time = System.currentTimeMillis() + "";
            int[] search = search(start, cityId, regionId, categoryId, time);
            int isEnd = search[0];
            int nextStartIndex = search[1];
            if (isEnd == 1) {
                break;
            }
            start = nextStartIndex;
        }
    }

    int[] search(int start,
                 String cityId,
                 String regionId,
                 String categoryId,
                 String time) {
        String s = "https://mapi.dianping.com/searchshop.json?start={0}&regionid={1}&categoryid={2}&sortid=0&locatecityid={3}&maptype=0&cityid={4}&_={5}&callback=Zepto{6}";
        reqHeader.put(Cons.USER_AGENT, Cons.MOBILE_USER_AGENT);
        reqHeader.put(Cons.COOKIE, "_hc.v=5e2ef585-e032-7e64-0ac7-ce146e828a4a.1500731291; _lxsdk_cuid=15d74d982cdc8-085875325275ed-3067780b-13c680-15d74d982cdc8; _lxsdk=15d74d982cdc8-085875325275ed-3067780b-13c680-15d74d982cdc8; PHOENIX_ID=0a010918-15d74d7054f-6e92eba; aburl=1; cy=" + cityId + "; cye=shanghai; __mta=223812900.1500904004251.1500904004251.1500904004251.1; cityid=" + cityId + "; msource=default; default_ab=shopList%3AA%3A1; _lxsdk_s=15d751a2f1e-111-750-fda%7C%7C5");
        String formatUrl = MessageFormat.format(s, start + "", regionId, categoryId, cityId, cityId, time, time);
        RespBean contentByUrl = urlService.getContentByUrl(formatUrl, reqHeader);
        String content = contentByUrl.getContent();
        boolean isEnd = false;
        int nextStartIndex = start;
        if (StringUtils.isNotEmpty(content)) {
            int i1 = content.indexOf("{");
            int i2 = content.lastIndexOf("}");
            content = content.substring(i1, i2 + 1);
            JSONObject searchJson = JSON.parseObject(content);
            JSONArray list = searchJson.getJSONArray("list");
            if (list.size() > 0) {
//                logger.error(">0");
            } else {
                logger.error("<0,{}", content);
            }
            int recordCount = searchJson.getIntValue("recordCount");
            nextStartIndex = searchJson.getIntValue("nextStartIndex");
            isEnd = searchJson.getBooleanValue("isEnd");
            Iterator<Object> iterator = list.iterator();
            int ix = start;
            while (iterator.hasNext()) {
                JSONObject next = (JSONObject) iterator.next();
                removeKeys(next);
                next.put("startIndex", ix++);
                next.put("recordCount", recordCount);
                next.put("nextStartIndex", nextStartIndex);
                next.put("isEnd", isEnd);
                logger.info(next.toString());
            }
        } else {
            logger.error("is empty by url:{}", formatUrl);
            logger.error("is empty ,pls check it , by ctx:{}", content);
        }
        return new int[]{isEnd ? 1 : 0, nextStartIndex};
    }

    private void removeKeys(JSONObject next) {
        next.remove("adInfo");
        next.remove("defaultPic");
        next.remove("dishtags");
        next.remove("extraJson");
        next.remove("matchText");
        next.remove("originalUrlKey");
        next.remove("authorityIconUrl");
        next.remove("authorityLabelType");
        next.remove("headerImageBorderColor");
        next.remove("scoreText");
        next.remove("recommendReasonData");
        next.remove("recommendReason");
        next.remove("shopDealInfos");
        next.remove("shopPositionInfo");
        next.remove("shopStateInformation");
        next.remove("tagList");
        next.remove("adShop");
        next.remove("altName");
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
            if (parentId != parentRegionId) {
                continue;
            }
            if (regionId1 == -10000) {
                continue;
            }
            if (parentRegionId != 0) {
                if (regionId1 == parentId) {
                    continue;
                }
            }
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
