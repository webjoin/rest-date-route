package com.viewt.rest.data.util;

/**
 * Created by Elijah on 22/7/2017.
 */
public interface Cons {

     String DP_BASE_URL = "http://www.dianping.com/";

     String USER_DIR = System.getProperty("user.dir");
     String USER_AGENT = "User-Agent";
     String COOKIE = "Cookie";
     String MOBILE_USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
//     String PC_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
     String PC_USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";

    interface Meituan{

        String[] CITY_NAMES = {"厦门","福州", "广州", "深圳", "郑州",               "大连", "沈阳", "哈尔滨", "济南", "青岛", "天津", "北京", "南昌", "重庆", "成都",                        "西安", "武汉", "长沙", "温州", "宁波", "杭州", "上海", "合肥", "常州", "苏州", "无锡", "南京"};
        String[] CITY_PYS = {"xiamen","fuzhou","guangzhou","shenzhen","zhengzhou","dalian","shenyang","haerbin","jinan","qingdao","tianjin","beijing","nanchang","chongqing","chengdu","xian","wuhan","changsha","wenzhou","ningbo","hangzhou","shanghai","hefei","changzhou","suzhou","wuxi","nanjing"};
        String[] H5_CITY_IDS = {"62",     "44",    "20",     "30",       "73",     "65",       "66",      "105",    "96",    "60",      "40"    ,"1",    "83",      "45",        "59",  "42"   ,"57",   "70",     "112",     "51",    "50",          "10",  "56",     "89",      "80",  "52" , "55"};

        String[] PC_CITY_IDS = {"350200"};
        /**
         *  美团美食商户列表
         *  又发现他们都是基于cookie来做的
         */
        String FOOD_SHOP_LIST_API = "https://meishi.meituan.com/i/api/channel/deal/list";



     }

}
