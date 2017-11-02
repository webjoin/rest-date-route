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

     interface Dianping{
         String[] CITY_IDS = {
                 "15","1", "2", "3", "4", "5", "6","7", "8", "9", "10", "11", "12", "13", "14", "16", "17", "18", "19", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
                 "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44",
                 "46",
                 "47", "48", "49", "50", "51", "52", "55", "56", "58", "59", "60", "61", "62", "63", "64",
                 "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "78", "79", "80", "83", "84", "86", "88", "90", "92", "93", "94", "95", "96", "97", "98", "99",
                 "100",
                 "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125",
                 "126",
                 "127", "128", "129", "130", "131", "132",
                 "133", "134", "135", "136", "137", "139", "140",
                 "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154",
                 "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168",
                 "169",
                 "170", "171",
                 "172",
                 "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185",
                 "186", "187", "189", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "205", "206", "207", "208", "209", "210",
                 "211", "212", "213", "214", "215", "216", "217", "218",
                 "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231",
                 "232", "233", "234", "238", "239", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "252", "253", "254", "255", "258", "260", "261", "267", "268", "269", "270", "276", "279", "291", "292", "293", "294", "295", "296", "297", "299", "301", "303", "307", "308", "310", "313", "321", "324", "325", "328", "341", "342", "344", "345", "358", "398", "1368", "2335", "2341"
         };
     }
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
