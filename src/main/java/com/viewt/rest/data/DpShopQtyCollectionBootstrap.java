package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.WaimaiBootstrap;
import com.viewt.rest.data.bean.DpCategoryNavsBean;
import com.viewt.rest.data.bean.DpCategoryRegionBean;
import com.viewt.rest.data.bean.DpRegionNavsBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.util.Cons;
import com.viewt.rest.data.util.FileUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Elijah on 24/7/2017.
 * 点评店铺量 尽可能收集 类
 * -Darea=dp_category_qty
 */
public class DpShopQtyCollectionBootstrap extends BaseBootstrap {
    public static void main(String[] args) {
        DpShopQtyCollectionBootstrap bootstrap = new DpShopQtyCollectionBootstrap();
        bootstrap.start(args);
    }

    @Override
    protected void crawl(String[] args) {

        saveCategoryId();
//        getCategoryQty();
    }


//    private void getCategoryQty() {
//        String s = Cons.USER_DIR + "/logs/other/other-dp_category_qty.log";
//        List<String> list = FileUtil.readFile(s);
//        int qty = 0;
//        int qty1 = 0;
//        for (String s1 : list) {
//            String[] split = s1.split(",");
//            int count = Integer.parseInt(split[split.length - 1]);
//            if (count > 5000) {
//                count = 5000;
//            } else {
//                qty1 += count;
//            }
//            qty += count;
//        }
//        System.out.println("拉取最大量包含重复,qty:" + qty);
//        System.out.println("简易最大量,qty1:" + qty1);
//    }

    /**
     * 根据分类生成可采集的 入口ID
     */
    private void saveCategoryId() {
        String[] dp_cityIds = WaimaiBootstrap.dp_cityIds;
        for (int j = 0; j < dp_cityIds.length; j++) {
            String cityId = dp_cityIds[j];
//            if (!"1".equals(cityId)) continue;
            DpCategoryRegionBean dpCategoryRegionBean = excute(cityId);
            testGetSubCategory(cityId, dpCategoryRegionBean);

            DpCategoryNavsBean dpCategoryNavsBean = dpCategoryRegionBean.getDpCategoryNavsBean();
            DpRegionNavsBean dpRegionNavsBean = dpCategoryRegionBean.getDpRegionNavsBean();
            List<DpCategoryNavsBean> children = dpCategoryNavsBean.getChildren();
            String time = System.currentTimeMillis() + "";

            List<String> vals = new ArrayList<>();

            for (DpCategoryNavsBean child : children) {  //菜系 分类
                String regionId = "0";
//            String regionName = "全部美食";
                String categoryId = child.getId() + "";
                String categoryCount = getCategoryCount(regionId, categoryId, cityId, time);
                String val = "regionId," + regionId + ",categoryId," + categoryId + ",count," + categoryCount;
//            val = "regionId," + regionName + ",categoryId," + child.getName() + ",count," + categoryCount;
                vals.add(val);
                if (5000 < Integer.parseInt(categoryCount)) {//subCategoryCount
                    List<DpCategoryNavsBean> subCategorys = child.getChildren();
//                    if (subCategorys.isEmpty()) {  //没有子菜系
                    List<DpRegionNavsBean> regionNavsBeans1 = dpRegionNavsBean.getChildren(); //行政区
                    for (DpRegionNavsBean regionNavsBean : regionNavsBeans1) {
                        String subCategoryCount = getCategoryCount(regionNavsBean.getId() + "", categoryId + "", cityId, time);
                        val = "regionId," + regionId + ",categoryId," + categoryId + ",count," + categoryCount
                                + ",regionId1," + regionNavsBean.getId() + ",categoryId1," + categoryId + ",count1," + subCategoryCount
                        ;
                        vals.add(val);
//                        if (5000 < Integer.parseInt(subCategoryCount)) {
//                            List<DpRegionNavsBean> children1 = regionNavsBean.getChildren();
//                            for (DpRegionNavsBean navsBean : children1) {
//                                String subCategoryCount1 = getCategoryCount(navsBean.getId() + "", categoryId + "", cityId, time);
//                                val = "regionId," + regionId + ",categoryId," + categoryId + ",count," + categoryCount
//                                        + ",regionId1," + regionNavsBean.getId() + ",categoryId1," + categoryId + ",count1," + subCategoryCount
//                                        + ",regionId1," + navsBean.getId() + ",categoryId1," + categoryId + ",count1," + subCategoryCount1
//                                ;
//                                vals.add(val);
//                            }
//                        }
                    }
//                    } else
                    for (DpCategoryNavsBean subCategory : subCategorys) {
                        int subCategoryId = subCategory.getId();
                        String subCategoryCount = getCategoryCount(regionId, subCategoryId + "", cityId, time);
                        val = "regionId," + regionId + ",categoryId," + categoryId + ",count," + categoryCount
                                + ",regionId1," + regionId + ",categoryId1," + subCategoryId + ",count1," + subCategoryCount;

//                    val = "regionId," + regionName + ",categoryId," + child.getName() + ",count," + categoryCount
//                            + ",regionId1," + regionName + ",categoryId1," + subCategory.getName() + ",count1," + subCategoryCount;
                        vals.add(val);
                        if (5000 < Integer.parseInt(subCategoryCount) || "本帮江浙菜".equals(child.getName())) {//subCategoryCount
                            List<DpRegionNavsBean> regionNavsBeans = dpRegionNavsBean.getChildren(); //行政区
                            for (DpRegionNavsBean regionNavsBean : regionNavsBeans) {
                                int regionId1 = regionNavsBean.getId();
                                String regionCount = getCategoryCount(regionId1 + "", subCategoryId + "", cityId, time);
                                val = "regionId," + regionId + ",categoryId," + categoryId + ",count," + categoryCount
                                        + ",regionId1," + regionId + ",categoryId1," + subCategoryId + ",count1," + subCategoryCount
                                        + ",regionId2," + regionId1 + ",categoryId2," + subCategoryId + ",count2," + regionCount;
//                            val = "regionId," + regionName + ",categoryId," + child.getName() + ",count," + categoryCount
//                                    + ",regionId1," + regionName + ",categoryId1," + subCategory.getName() + ",count1," + subCategoryCount
//                                    + ",regionId2," + regionNavsBean.getName() + ",categoryId2," + subCategory.getName() + ",count2," + regionCount;
                                vals.add(val);
                                if (5000 < Integer.parseInt(regionCount)) {
                                    List<DpRegionNavsBean> children1 = regionNavsBean.getChildren();
                                    for (DpRegionNavsBean navsBean : children1) {
                                        int regionId2 = navsBean.getId();
                                        String regionCount2 = getCategoryCount(regionId2 + "", subCategoryId + "", cityId, time);
                                        val = "regionId," + regionId + ",categoryId," + categoryId + ",count," + categoryCount
                                                + ",regionId1," + regionId + ",categoryId1," + subCategoryId + ",count1," + subCategoryCount
                                                + ",regionId2," + regionId1 + ",categoryId2," + subCategoryId + ",count2," + regionCount
                                                + ",regionId3" + regionId2 + ",categoryId3," + subCategoryId + ",count3," + regionCount2;
//                                    val = "regionId," + regionName + ",categoryId," + child.getName() + ",count," + categoryCount
//                                            + ",regionId1," + regionName + ",categoryId1," + subCategory.getName() + ",count1," + subCategoryCount
//                                            + ",regionId2," + regionNavsBean.getName() + ",categoryId2," + subCategory.getName() + ",count2," + regionCount
//                                            + ",regionId3" + navsBean.getName() + ",categoryId3," + subCategory.getName() + ",count3," + regionCount2;
                                        vals.add(val);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            for (int i = 0; i < vals.size(); i++) {
                logger.info("{},{}", cityId, vals.get(i));
            }
        }

    }


    private String getCategoryCount(String regionId, String categoryId, String cityId, String time) {

        String countUrl = "https://mapi.dianping.com/searchshop.json?start=0&regionid={regionId}&categoryid={categoryId}&sortid=0&locatecityid={cityId}&maptype=0&cityid={cityId}&_={time}&callback=Zepto{time}";
        countUrl = countUrl.replace("{regionId}", regionId);
        countUrl = countUrl.replace("{categoryId}", categoryId);
        countUrl = countUrl.replace("{cityId}", cityId + "");
        countUrl = countUrl.replace("{time}", time);

        reqHeader.put("User-Agent", Cons.MOBILE_USER_AGENT);
        RespBean countRespBean = urlService.getContentByUrl(countUrl, reqHeader);
        String content = countRespBean.getContent();
        if (StringUtils.isNotEmpty(content)) {
            int i1 = content.indexOf("{");
            int i2 = content.lastIndexOf("}");
            content = content.substring(i1, i2 + 1);
            JSONObject contentJSON = JSON.parseObject(content);
            return contentJSON.getString("recordCount");
        }
        return null;
    }


    /**
     * 获取子菜系ID
     */
    private void testGetSubCategory(String city, DpCategoryRegionBean dpCategoryRegionBean) {

        DpCategoryNavsBean dpCategoryNavsBean = dpCategoryRegionBean.getDpCategoryNavsBean();
        List<DpCategoryNavsBean> children = dpCategoryNavsBean.getChildren();
        for (DpCategoryNavsBean child : children) {
            int category = child.getId(); // 小吃快餐
            List<DpCategoryNavsBean> subCategories = getSubCategories(city, category);
            child.setChildren(subCategories);
        }

    }

    DpCategoryRegionBean excute(String cityid) {
        String join = getCityCategoryJson(cityid);
        JSONObject jsonObject = JSON.parseObject(join);
        JSONArray categoryNavs = jsonObject.getJSONArray("categoryNavs");
        JSONArray regionNavs = jsonObject.getJSONArray("regionNavs");

        Iterator<Object> regIterator = regionNavs.iterator();
        List<JSONObject> regionJsonArr = new ArrayList<>();
        DpRegionNavsBean parentBean1 = new DpRegionNavsBean();

        Iterator<Object> iterator = categoryNavs.iterator();
        List<JSONObject> categoryJsonArr = new ArrayList<>();
        DpCategoryNavsBean parentBean = new DpCategoryNavsBean();
        while (iterator.hasNext()) {
            JSONObject category = (JSONObject) iterator.next();
            int id1 = category.getIntValue("id");
            int parentId = category.getIntValue("parentId");
            String name = category.getString("name");
            category.remove("distance");
            category.remove("sortId");
            category.remove("favIcon");
            categoryJsonArr.add(category);
            if (id1 == 10 && parentId == 0) {
                parentBean.setId(id1);
                parentBean.setParentId(parentId);
                parentBean.setName(name);
            }
        }
        fetchChild(parentBean, categoryJsonArr);
        System.out.println(JSON.toJSONString(parentBean));

        while (regIterator.hasNext()) {
            JSONObject region = (JSONObject) regIterator.next();
            int id1 = region.getIntValue("id");
            int parentId = region.getIntValue("parentId");
            String name = region.getString("name");
            region.remove("distance");
            region.remove("sortId");
            region.remove("favIcon");

            if (parentId != -10000 && id1 != -10000) {
                regionJsonArr.add(region);
            }
            if (id1 == 0) {
                parentBean1.setId(id1);
                parentBean1.setParentId(parentId);
                parentBean1.setName(name);
                System.out.println("-------->>");
            }
        }
        fetchChild(parentBean1, regionJsonArr);
        System.out.println(JSON.toJSONString(parentBean1));


        return new DpCategoryRegionBean(parentBean, parentBean1);
    }

    private String getCityCategoryJson(String cityid) {  //other-dp-mate.log 需要将log文件移动到 mate下
        String mateFilePath = Cons.USER_DIR + "/logs/other/other-dp-mate.log";

        List<String> list = FileUtil.readFile(mateFilePath);
        String json = "";
        for (String s : list) {
            String conKey = "\"cityId\":\"" + cityid + "\"";
            boolean contains = s.contains(conKey);
            if (contains) {
                json = s;
                break;
            }
        }
        list.clear();
        return json;
    }

    DpRegionNavsBean fetchChild(DpRegionNavsBean parentBean, List<JSONObject> regionJsonArr) {
        int id = parentBean.getId();
        Iterator<JSONObject> iterator = regionJsonArr.iterator();
        List<DpRegionNavsBean> children = new ArrayList<>();
        while (iterator.hasNext()) {
            JSONObject next = iterator.next();
            int id1 = next.getIntValue("id");
            int parentId = next.getIntValue("parentId");
            String name = next.getString("name");
            if (id == parentId && parentId != id1) {
                DpRegionNavsBean dpRegionNavsBean = new DpRegionNavsBean(name, id1, parentId);
                children.add(dpRegionNavsBean);
                fetchChild(dpRegionNavsBean, regionJsonArr);
            }
        }
        parentBean.setChildren(children);
        return parentBean;
    }


    public List<DpCategoryNavsBean> getSubCategories(String cityid, int parentCategoryId) {
        String addr = "http://www.dianping.com/search/category/{cityid}/10/g{category}";

        reqHeader.put("User-Agent", Cons.PC_USER_AGENT);
        addr = addr.replace("{cityid}", cityid + "")
                .replace("{category}", parentCategoryId + "")
        ;
        RespBean contentByUrl = urlService.getContentByUrl(addr, reqHeader);
        String s = "<div id=\"classfy-sub\"[\\s\\S]+?</div>";
        String sub = "<a href=\"/search/category/\\d+/\\d+/g(\\d+)\"[\\s\\S]+?><span>([^<]+)</span></a>";
        Pattern pattern = Pattern.compile(s);
        Pattern subPattern = Pattern.compile(sub);
        Matcher matcher = pattern.matcher(contentByUrl.getContent());
        List<DpCategoryNavsBean> list = new ArrayList<>();
        if (matcher.find()) {
            String group = matcher.group();
            Matcher subMather = subPattern.matcher(group);
            while (subMather.find()) {
                String subCategoryId = subMather.group(1);
                String subCategoryName = subMather.group(2);
                DpCategoryNavsBean bean = new DpCategoryNavsBean(Integer.parseInt(subCategoryId), subCategoryName, parentCategoryId);
                if ((parentCategoryId + "").equals(subCategoryId)) {
                } else
                    list.add(bean);
            }
        }
        return list;
    }


    DpCategoryNavsBean fetchChild(DpCategoryNavsBean parentBean, List<JSONObject> regionJsonArr) {
        int id = parentBean.getId();
        Iterator<JSONObject> iterator = regionJsonArr.iterator();
        List<DpCategoryNavsBean> children = new ArrayList<>();
        while (iterator.hasNext()) {
            JSONObject next = iterator.next();
            int id1 = next.getIntValue("id");
            int parentId = next.getIntValue("parentId");
            String name = next.getString("name");
            if (id == parentId && parentId != id1) {
                DpCategoryNavsBean dpRegionNavsBean = new DpCategoryNavsBean(id1, name, parentId);
                children.add(dpRegionNavsBean);
                fetchChild(dpRegionNavsBean, regionJsonArr);
            }
        }
        parentBean.setChildren(children);
        return parentBean;
    }
}
