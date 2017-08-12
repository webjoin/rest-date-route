package com.viewt.rest.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.WaimaiBootstrap;
import com.viewt.rest.data.bean.DpDataBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Elijah on 18/7/2017.
 * 点评明细数据
 * -Darea=dp-shop-ix
 * 42512 42512   dp-shop-list-4-id
 */
public class DpShopBootstrap extends BaseBootstrap {

    static Logger logger = LoggerFactory.getLogger(DpShopBootstrap.class);


    public static void main(String[] args) {
        DpShopBootstrap bootstrap = new DpShopBootstrap();
        bootstrap.start(args);
//        List<String> ls = new ArrayList<>();
//        ls.add("aaa");
//        ls.add("bb");
//        ls.add("cc");
//        List<String> list = ls.subList(1, 3);
//        String[] strings = list.toArray(new String[list.size()]);
//        list.clear();
//        list = null;
//        ls.clear();
//        for (String string : strings) {
//            System.out.println(string);
//        }
//        System.out.println(ls.size());
    }

    @Override
    public void crawl(String[] args) {
        int start = 1;
        int end = 0;
        String log = "";
        if (args.length >= 1) {
            start = Integer.parseInt(args[0]);
        }
        if (args.length >= 2) {
            end = Integer.parseInt(args[1]);
        }
        if (args.length >= 3) {
            log = args[2];
        }
        String filePath = Cons.USER_DIR + "/logs/other/other-" + log + ".log";
        List<String> list = FileUtil.readFile(filePath);
        if (end == 0 || end > list.size()) {
            end = list.size();
        }
        if (start > list.size()) {
            logger.error("start:{} is great than size:{}", start, list.size());
            return;
        }
        List<String> sublist = list.subList(start, end);
        String[] strings = sublist.toArray(new String[sublist.size()]);
        sublist.clear();
        list.clear();
        list = null;
        sublist = null;
        int i = 0;
        logger.error("开始{}，结束{}", start, end);
        for (String item : strings) {
            i++;
            logger.error("doing... {}", start + i); //21:34 started
            if (StringUtils.isEmpty(item)) continue;
            getItemData(item);
            logger.error("end doing... {}", i); //21:34 started
        }
//        getItemData("3471487");
    }

    //主站拿基本信息
//    String dpShopUrl = "http://www.dianping.com/shop/69996989";
    String dpShopUrl = "http://www.dianping.com/shop/{id}";
    //拿团购信息
//    String dpMShopUrl = "https://m.dianping.com/shop/69996989?from=shoplist&shoplistqueryid=8fd6a59a-24b5-4c32-9fb1-486ee5f89c16";
    String dpMShopUrl = "https://m.dianping.com/shop/{id}?from=shoplist&shoplistqueryid=8fd6a59a-24b5-4c32-9fb1-486ee5f89c16";
    //评论数量
//    String reviewAndStar = "http://www.dianping.com/ajax/json/shopDynamic/reviewAndStar?shopId=69996989&cityId=1&mainCategoryId=252";
//    String reviewAndStar = "http://www.dianping.com/ajax/json/shopDynamic/reviewAndStar?shopId={id}&cityId={cityId}&mainCategoryId={categoryId}";
    //推荐菜
//    String shopTabs = "http://www.dianping.com/ajax/json/shopDynamic/shopTabs?shopId=69996989&cityId=1&shopName=%E5%B1%B1%E4%BA%BA%E7%91%B6&power=5&mainCategoryId=252&shopType=10&shopCityId=1";
    String shopTabs = "http://www.dianping.com/ajax/json/shopDynamic/shopTabs?shopId={id}&cityId={cityId}&shopName={shopName}&power=5&mainCategoryId={categoryId}&shopType=10&shopCityId={cityId}&_token=eJxVjFtPgzAAhf9Ln5tRSjs63ia6BXRGLuOi4QEYDtRyazPIjP%2FdLvLi03fy5ZzzDUbnBCwdIUR0CKRQmSKMN5hSTDCBoPzvqKFqxRjdA%2BtNJ4hCg9HsZnwllCEIbhDK4F9kpplB9YLJreOoCqil7C1Nm6ZpdWrytm%2Fa86rsuCbqrtfWzNBNQhmAAKgFD9VC8XNhvlAuFM25BRao3DkMBBHDu38Q4RFFpXy%2BHuZr2MxuWLLLthPp%2FrGLqFu1O8nzoY5aeleSaY7t6sGOeRrPXcHdwYtTk1Ypx6M57l8aZCRF0qxf2VNw2QVTfvzinv3hO7a3HWmQTODnF63GXYE%3D";
//    String shopTabs1= "http://www.dianping.com/ajax/json/shopDynamic/shopTabs?shopId={}&cityId={}        &shopName={}        &power=5&mainCategoryId=210         &shopType=10&shopCityId=1058    &_token=eJxVi9tOg0AURf%2FlPE%2FKMMzAlMQHbWkdpDVyi9b0gV4oiFBgiBSM%2F%2B409sXk5Kydlb2%2FoRUHsHWMMdURdFJlhgkx1bOMKUaw%2F%2B%2BYodyujedgv%2BsUM2Rwtr0aXwllKEZTjLfoL3LL2iJC1V07QlUg67ra1rS%2B7yeHPKnqvDpN9udSk9m51jjWqWUSDghALcpQLRSLG5MbuxtlfqrAhqN7CQNJZZP6KxlGeOXFLwM2VuPj%2BtlzPtfjwGe5X2yWzSlm7rFadGXSZHHFHvZUDMWCvy3krF%2FPHTUR%2FUfqjY6hp5uasKEyrWT66rWE7QSRy0xsIqeJBA%2Fu3eDJ%2FaJ38PMLm4lcqQ%3D%3D";
    //大家认为  包含了 评论数量
//    String allReview = "http://www.dianping.com/ajax/json/shopDynamic/allReview?shopId=69996989&cityId=1&categoryURLName=food&power=5&cityEnName=shanghai&shopType=10";
    String allReview = "http://www.dianping.com/ajax/json/shopDynamic/allReview?shopId={id}&cityId={cityId}&categoryURLName=food&power={power}&cityEnName={cityEnName}&shopType={shopType}&_token=eJxVjFtPgzAAhf9Ln5tRSjs63ia6BXRGLuOi4QEYDtRyazPIjP%2FdLvLi03fy5ZzzDUbnBCwdIUR0CKRQmSKMN5hSTDCBoPzvqKFqxRjdA%2BtNJ4hCg9HsZnwllCEIbhDK4F9kpplB9YLJreOoCqil7C1Nm6ZpdWrytm%2Fa86rsuCbqrtfWzNBNQhmAAKgFD9VC8XNhvlAuFM25BRao3DkMBBHDu38Q4RFFpXy%2BHuZr2MxuWLLLthPp%2FrGLqFu1O8nzoY5aeleSaY7t6sGOeRrPXcHdwYtTk1Ypx6M57l8aZCRF0qxf2VNw2QVTfvzinv3hO7a3HWmQTODnF63GXYE%3D";

    private Map<String, String> reqHeader = new HashMap<>();

    private void getItemData(String dpId) {

        DpDataBean dpDataBean = new DpDataBean();
        dpDataBean.setId(Long.parseLong(dpId));
        String cookie = "_hc.v=50fdc104-3464-64f6-2867-8506bfb03f7f.1502264668; cityid=1; default_ab=shop%3AA%3A1; _lxsdk_cuid=15dc61dac51c8-0f3075106ef6b5-3067780b-13c680-15dc61dac52c8; _lxsdk=15dc61dac51c8-0f3075106ef6b5-3067780b-13c680-15dc61dac52c8; s_ViewType=10; JSESSIONID=73ACF16622538BC6BC4E2BAEDE35CE9A; __mta=253147702.1502292209388.1502292209388.1502292229508.2; aburl=1; cy=15; cye=xiamen; _lxsdk_s=15dc797ec93-0c3-bc-f12%7C%7C38";
        reqHeader.put("Cookie", cookie);

        reqHeader.put("User-Agent", Cons.PC_USER_AGENT);
        RespBean shopRespBean = urlService.getContentByUrl(dpShopUrl.replace("{id}", dpId), reqHeader);
        parseShopRespBean(shopRespBean, dpDataBean);
        dpDataBean.setId(Long.parseLong(dpId));

//        if ("44282963".equals(dpId)){
//            System.out.println("abc");
//        }
        if (StringUtils.isEmpty(dpDataBean.getFullName())) {
            logger.error(dpId + " this id has some problem ... 貌似这个id有问题哈。。。。");
            return;
        }
        reqHeader.put("User-Agent", Cons.MOBILE_USER_AGENT);
        String mShopUrl = dpMShopUrl.replace("{id}", dpId);
        RespBean dpMShopRespBean = urlService.getContentByUrl(mShopUrl, reqHeader);
        parseMShopRespBean(dpMShopRespBean, dpDataBean);
        reqHeader.put("User-Agent", Cons.MOBILE_USER_AGENT);
//        String shopTabs = "http://www.dianping.com/ajax/json/shopDynamic/shopTabs?shopId={id}&cityId={cityId}&shopName={shopName}&power={power}&mainCategoryId={categoryId}&shopType=10&shopCityId={shopCityId}";




        reqHeader.put("Cookie","_hc.v=50fdc104-3464-64f6-2867-8506bfb03f7f.1502264668; cityid=1; default_ab=shop%3AA%3A1; _lxsdk_cuid=15dc61dac51c8-0f3075106ef6b5-3067780b-13c680-15dc61dac52c8; _lxsdk=15dc61dac51c8-0f3075106ef6b5-3067780b-13c680-15dc61dac52c8; s_ViewType=10; JSESSIONID=73ACF16622538BC6BC4E2BAEDE35CE9A; __mta=253147702.1502292209388.1502292209388.1502292229508.2; aburl=1; cy=15; cye=xiamen; _lxsdk_s=15dc797ec93-0c3-bc-f12%7C%7C38");

        String shopTabsUrl =
                shopTabs.replace("{id}", dpId)
                        .replace("{cityId}", dpDataBean.getCityId() + "")
                        .replace("{shopName}", CodeUtil.urlEecode(dpDataBean.getFullName()))
                        .replace("{power}", dpDataBean.getPower() + "")
                        .replace("{categoryId}", dpDataBean.getMainCategoryId() + "")
                        .replace("{shopCityId}", dpDataBean.getShopCityId() + "");

        RespBean shopTabsRespBean = urlService.getContentByUrl(shopTabsUrl, reqHeader);
        parseShopTabsRespBean(shopTabsRespBean, dpDataBean);

        reqHeader.put("User-Agent", Cons.MOBILE_USER_AGENT);
//        String allReview = "http://www.dianping.com/ajax/json/shopDynamic/allReview?shopId={id}&cityId={cityId}&categoryURLName={categoryURLName}&power={power}&cityEnName={cityEnName}&shopType={shopType}";
        String allReviewUrl =
                allReview.replace("{id}", dpId)
                        .replace("{cityId}", dpDataBean.getCityId() + "")
                        .replace("{categoryURLName}", dpDataBean.getCategoryURLName())
                        .replace("{power}", dpDataBean.getPower() + "")
                        .replace("{cityEnName}", dpDataBean.getCityEnName())
                        .replace("{shopType}", dpDataBean.getShopType() + "");

        RespBean allReviewRespBean = urlService.getContentByUrl(allReviewUrl, reqHeader);
        parseAllReviewRespBean(allReviewRespBean, dpDataBean);
        saveBean(dpDataBean);
    }

    private void saveBean(DpDataBean dpDataBean) {
        // save to log file
        logger.info(JSON.toJSONString(dpDataBean));
    }

    private Pattern telPattern = Pattern.compile("itemprop=\"tel\">([^<]+)</span>");
    private Pattern branchQtyPattern = Pattern.compile("<a class=\"branch J-branch\">其它(\\d+)家分店");
    private Pattern shopPattern = Pattern.compile("window.shop_config=\\s*\\{([\\s\\S]+)}\\s*</script>");
    private Pattern shop_pricePattern = Pattern.compile("<span id=\"avgPriceTitle\" class=\"item\">人均：(\\d+)元</span>");
    private Pattern shop_smellPattern = Pattern.compile("<span class=\"item\">口味：([\\d.]+)</span>");
    private Pattern shop_envPattern = Pattern.compile("<span class=\"item\">口味：([\\d.]+)</span>");
    private Pattern shop_servicePattern = Pattern.compile("<span class=\"item\">口味：([\\d.]+)</span>");

    void parseShopRespBean(RespBean shopRespBean, DpDataBean bean) {
        String content = shopRespBean.getContent();
        if (StringUtils.isNotEmpty(content)) {
            Matcher matcher = shopPattern.matcher(content);
            Matcher priceMatcher = shop_pricePattern.matcher(content);
            Matcher smellMatcher = shop_smellPattern.matcher(content);
            Matcher envMatcher = shop_envPattern.matcher(content);
            Matcher serviceMatcher = shop_servicePattern.matcher(content);
            Matcher telMatcher = telPattern.matcher(content);
            Matcher branchQtyMatcher = branchQtyPattern.matcher(content);
            if (matcher.find()) {
                String group = matcher.group(1);
                if (StringUtils.isNotEmpty(group)) {
                    group = group.replace("\\", "、");
                    DpDataBean shopConfigBean = JSON.parseObject("{" + group + "}", DpDataBean.class);

                    try {
                        BeanUtils.copyProperties(bean, shopConfigBean);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            while (telMatcher.find()) {
                if (bean.getTel() != null)
                    bean.setTel(bean.getTel() + "," + telMatcher.group(1));
                else {
                    bean.setTel(telMatcher.group(1));
                }
            }
            if (branchQtyMatcher.find()) {
                bean.setBranchQty(Integer.parseInt(branchQtyMatcher.group(1)));
            }
            if (priceMatcher.find()) {
                String group = priceMatcher.group(1);
                bean.setAvgPrice(Double.parseDouble(group));
            }
            if (smellMatcher.find()) {
                String group = smellMatcher.group(1);
                bean.setSmell(Double.parseDouble(group));
            }
            if (envMatcher.find()) {
                String group = envMatcher.group(1);
                bean.setEnv(Double.parseDouble(group));
            }
            if (serviceMatcher.find()) {
                String group = serviceMatcher.group(1);
                bean.setService(Double.parseDouble(group));
            }
        }
    }


    //    https://m.dianping.com/shop/22900626?from=shoplist&shoplistqueryid=60266254-5c34-4566-a40d-5c6bdbfeb666
    private Pattern huiListPattern = Pattern.compile("<div class=\"hui-list\">[\\s\\S]+?</a>\\s*</div>");
    private Pattern huiList_itemPattern = Pattern.compile("<a\\s*[\\s\\S]+?</a>");

    private Pattern huiList_itemNewTitilePattern = Pattern.compile("<div class=\"newtitle\">([^<]+)</div>");
    private Pattern huiList_itemSoldNumPattern = Pattern.compile("<span class=\"soldNum\">([^<]+)</span>");

    private Pattern tuanListPattern = Pattern.compile("<div class=\"tuan-list\">[\\s\\S]+?</a>\\s*</div>");
    private Pattern tuanList_itemPattern = Pattern.compile("<a\\s*[\\s\\S]+?</a>");

    private Pattern tuanList_itemNamePattern = Pattern.compile("<div class=\"newtitle\">([^<]+)</div>");
    private Pattern tuanList_itemPricePattern = Pattern.compile("<span class=\"price\">([^<]+)</span>");
    private Pattern tuanList_itemOPricePattern = Pattern.compile("<span class=\"o-price\">([^<]+)</span>");
    private Pattern tuanList_itemSoldNumPattern = Pattern.compile("<span class=\"soldNum\">([^<]+)</span>");

    void parseMShopRespBean(RespBean mShopRespBean, DpDataBean dataBean) {
        String content = mShopRespBean.getContent();
        String tuan = "";  // //80,101,27;35,45,26; // 实付,应付,售卖量;
        String hui = "";  // // 9.5,2500;100-5,646;   每满100减5元已买646
        if (StringUtils.isNotEmpty(content)) {
            Matcher huilistMatcher = huiListPattern.matcher(content);
            if (huilistMatcher.find()) {
                String focusContent = huilistMatcher.group();
                if (StringUtils.isNotEmpty(focusContent)) {
                    Matcher itemMatcher = huiList_itemPattern.matcher(focusContent);
                    while (itemMatcher.find()) {
                        String itemTuan = itemMatcher.group();
                        Matcher newtitileMatcher = huiList_itemNewTitilePattern.matcher(itemTuan);
                        Matcher soldNumMatcher = huiList_itemSoldNumPattern.matcher(itemTuan);
                        if (newtitileMatcher.find()) {
                            String group = newtitileMatcher.group(1);
                            if (StringUtils.isNotEmpty(group)) {
                                group = group.trim();
//                                if (group.contains("满")) {  //满100元送10元尊享券
//                                    String str = "";
//                                    String[] split = group.split("[^\\d]+");
//                                    for (int i = 0; i < split.length; i++) {
//                                        if (split[i].isEmpty()) continue;
//                                        str += split[i] + "-";
//                                    }
//                                    if (null != str && str.endsWith("-")) {
//                                        str = str.substring(0, str.length() - 1);
//                                    }
//                                    hui += "," + str;
//                                } else if (group.contains("折")) {
                                hui += "," + group;
//                                }
                            }
                        }
                        if (soldNumMatcher.find()) {
                            String group = soldNumMatcher.group(1);
                            if (StringUtils.isNotEmpty(group)) {
                                group = group.trim();
                                hui += "," + group.replaceAll("[^\\d]+]", "");
                            }
                        }
                        hui += ";";
                    }
                }
            }


            Matcher tuanlistMatcher = tuanListPattern.matcher(content);
            if (tuanlistMatcher.find()) {
                String focusContent = tuanlistMatcher.group();
                if (StringUtils.isNotEmpty(focusContent)) {
                    Matcher itemMatcher = tuanList_itemPattern.matcher(focusContent);
                    while (itemMatcher.find()) {
                        String itemTuan = itemMatcher.group();
                        Matcher priceMatcher = tuanList_itemPricePattern.matcher(itemTuan);
                        Matcher oPriceMatcher = tuanList_itemOPricePattern.matcher(itemTuan);
                        Matcher soldNumMatcher = tuanList_itemSoldNumPattern.matcher(itemTuan);
                        Matcher nameMatcher = tuanList_itemNamePattern.matcher(itemTuan);
                        if (nameMatcher.find()) {
                            String group = nameMatcher.group(1);
                            if (StringUtils.isNotEmpty(group)) {
                                group = group.trim();
                                tuan += "," + group.replace(",", " ");
                            }
                        }
                        if (oPriceMatcher.find()) {
                            String group = oPriceMatcher.group(1);
                            if (StringUtils.isNotEmpty(group)) {
                                group = group.trim();
                                tuan += "," + group.replaceAll("[^\\d]+]", "");
                            }
                        }
                        if (priceMatcher.find()) {
                            String group = priceMatcher.group(1);
                            if (StringUtils.isNotEmpty(group)) {
                                group = group.trim();
                                tuan += "," + group.replaceAll("[^\\d]+]", "");
                            }
                        }
                        if (soldNumMatcher.find()) {
                            String group = soldNumMatcher.group(1);
                            if (StringUtils.isNotEmpty(group)) {
                                group = group.trim();
                                tuan += "," + group.replaceAll("[^\\d]+]", "");
                            }
                        }
                        tuan += ";";
                    }
                }
            }

            dataBean.setTuan(tuan);
            dataBean.setHui(hui);
        }
    }

    void parseShopTabsRespBean(RespBean shopTabsRespBean, DpDataBean dataBean) {
        String content = shopTabsRespBean.getContent();
        if (StringUtils.isNotEmpty(content)) {
            String allDishesVal = "";
            JSONObject jsonObject = JSON.parseObject(content);
            JSONArray allDishes = JSONUtil.getJSONArray(jsonObject, "allDishes");
            for (Object allDish : allDishes) {
                JSONObject object = (JSONObject) allDish;
                String val = object.getString("dishTagName") + "," +
                        object.getString("tagCount") + "," +
                        object.getString("finalPrice") + "";
                allDishesVal += val + ";";
            }
            dataBean.setAllDishes(allDishesVal);
        }
    }

    void parseAllReviewRespBean(RespBean allReviewRespBean, DpDataBean bean) {
        //  泡菜肥牛稻粉,56,35;牛肉诱惑稻粉,39,29   name,count,price
        String content = allReviewRespBean.getContent();
        if (StringUtils.isNotEmpty(content)) {
            JSONObject jsonObject = JSON.parseObject(content);
            Integer reviewCountStar1 = jsonObject.getIntValue("reviewCountStar1");
            Integer reviewCountStar2 = jsonObject.getIntValue("reviewCountStar2");
            Integer reviewCountStar3 = jsonObject.getIntValue("reviewCountStar3");
            Integer reviewCountStar4 = jsonObject.getIntValue("reviewCountStar4");
            Integer reviewCountStar5 = jsonObject.getIntValue("reviewCountStar5");

            Integer reviewCountAll = jsonObject.getIntValue("reviewCountAll");
            JSONArray reviewDataList = JSONUtil.getJSONArray(jsonObject, "reviewDataList");
            JSONArray summarys = JSONUtil.getJSONArray(jsonObject, "summarys");

//            lastTime
            String summarysVal = "";
            List<Long> times = new ArrayList<>();
            if (reviewDataList != null) {
                for (Object time : reviewDataList) {
                    JSONObject object = (JSONObject) time;
                    Long lastTime = object.getLong("lastTime");
                    times.add(lastTime);
                }
                Collections.sort(times);
                bean.setLastReviewTime(times.get(times.size() - 1));
            }

            if (summarys != null) {
                for (Object summary : summarys) {
                    JSONObject object = (JSONObject) summary;
                    String val = object.getString("summaryString") + "," +
                            object.getString("summaryCount");
                    summarysVal += val + ";";
                }
            }

            bean.setSummarys(summarysVal);

            bean.setReviewCountStar1(reviewCountStar1);
            bean.setReviewCountStar2(reviewCountStar2);
            bean.setReviewCountStar3(reviewCountStar3);
            bean.setReviewCountStar4(reviewCountStar4);
            bean.setReviewCountStar5(reviewCountStar5);
            bean.setReviewCount(reviewCountAll);

        }
    }
}
