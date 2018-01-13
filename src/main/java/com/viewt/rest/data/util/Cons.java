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
//    String PC_USER_AGENT = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
    String PC_USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:57.0) Gecko/20100101 Firefox/57.0";

    interface Dianping {
        /**
         * 望湘园
         */
        String[] DP_WANG_XIANG_YUAN_IDS = {"500892", "2048551", "2054494", "2294248", "2636710", "2748850", "3056302", "3287650", "3468463", "3580894", "3690208", "3868909", "4061470", "4100532", "4197182", "4500495", "4539457", "4681807", "5097359", "5205703", "5232539", "5297150", "5300500", "5423531", "5431121", "5435204", "5440070", "5445874", "5503929", "5669032", "5734002", "6005563", "6163325", "6169720", "6169765", "8024718", "8853733", "8861127", "13873127", "14174488", "17781509", "17815210", "18238559", "19096843", "19595754", "19628701", "20672277", "20685634", "20910251", "21217041", "22442137", "22910878", "23514868", "23592057", "24292464", "24648328", "27113116", "27292170", "38090212", "38090934", "40706798", "40869142", "40986812", "45550842", "50411142", "56570785", "62230892", "63156167", "63318054", "65944578", "67087548", "67515676", "67524179", "67660807", "68355315", "68429654", "68565316", "68655595", "68850947", "69144501", "69285954", "69769344", "73419531", "73498763", "75003568", "77348840", "77695398", "90355473", "90437000", "90475414", "90477275", "91919623", "92029549", "92064680", "92131141", "92186719", "92449845", "92497079", "92514661", "92711726", "93060464"};
        /**
         * 哥老官
         */
        String[] DP_GE_LAO_GUAN_IDS = {"15959175", "19612173", "22142004", "27137313", "27348970", "58346225", "63218225", "67440079", "67749532", "79258179", "82240466", "90279829", "91596480", "92203985", "92660505", "92674849"};
        /**
         * 0点评ID
         * 1页码
         * http://www.dianping.com/shop/80990733/review_more?pageno=2
         */
        String DP_COMMENT_URL = "http://www.dianping.com/shop/{0}/review_more?pageno={1}";
        String[] CITY_IDS = {
                "15", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "16", "17", "18", "19", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
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
        /**
         * http://t.dianping.com/list/shanghai-category_41-region_3s10823
         * 的行政区域
         */
        String MASSAGE_DISTRICTS_BASE = "http://t.dianping.com";
        String MASSAGE_DISTRICTS_M_BASE = "https://m.dianping.com/joy/massage/servicereservation?shopid={0}&cityid=1&_={1}&callback=Zepto{1}";
        String[] MASSAGE_DISTRICTS = {
                "/list/shanghai-category_41-region_42s854",
                "/list/shanghai-category_41-region_42s865",
                "/list/shanghai-category_41-region_42s860",
                "/list/shanghai-category_41-region_42s802",
                "/list/shanghai-category_41-region_42s842",
                "/list/shanghai-category_41-region_42s839",
                "/list/shanghai-category_41-region_42s812",
                "/list/shanghai-category_41-region_42s811",
                "/list/shanghai-category_41-region_42s835",
                "/list/shanghai-category_41-region_42s861",
                "/list/shanghai-category_41-region_42s849",
                "/list/shanghai-category_41-region_42s840",
                "/list/shanghai-category_41-region_42s806",
                "/list/shanghai-category_41-region_42s803",
                "/list/shanghai-category_41-region_42s838",
                "/list/shanghai-category_41-region_42s821",
                "/list/shanghai-category_41-region_42s801",
                "/list/shanghai-category_41-region_42s804",
                "/list/shanghai-category_41-region_42s808",
                "/list/shanghai-category_41-region_42s818"
        };
        /**
         * 休闲娱乐 热门商圈
         */
        String MASSAGE_SHOP_LIST = "https://mapi.dianping.com/searchshop.json?start={0}&categoryid=141&parentCategoryId=141&locatecityid=1&limit=20&sortid=0&cityid=1&regionid={1}&maptype=0&callback=jsonp_{2}_62311";
        String[][] MASSAGE_HOT_AREAS = {
            {"801","陆家嘴"},
            {"802","八佰伴"},
            {"804","上南地区"},
            {"865","徐家汇"},
            {"860","人民广场"},
            {"803","世纪公园"},
            {"835","淮海路"},
            {"812","静安寺"},
            {"842","中山公园"},
            {"846","虹桥镇"},
            {"849","莘庄"},
            {"806","金桥"},
            {"808","张江"},
            {"811","南京西路"},
            {"839","虹桥"},
            {"854","五角场/大学区"},
            {"815","长寿路"},
            {"861","南京东路"},
            {"5947","康桥/周浦"},
            {"818","梅川路"}
        };
    }

    interface Job{
        String TYPE_SHOP = "SHOP";
        String TYPE_SHOP_DETAIL = "SHOP_DETAIL";

        String JOB_MASSAGE_SHOP="massage.shop";
        String JOB_MASSAGE_SHOP_DETAIL = "massage.shop.detail";

    }

    interface Meituan {

        String[] CITY_NAMES = {"厦门", "福州", "广州", "深圳", "郑州", "大连", "沈阳", "哈尔滨", "济南", "青岛", "天津", "北京", "南昌", "重庆", "成都", "西安", "武汉", "长沙", "温州", "宁波", "杭州", "上海", "合肥", "常州", "苏州", "无锡", "南京"};
        String[] CITY_PYS = {"xiamen", "fuzhou", "guangzhou", "shenzhen", "zhengzhou", "dalian", "shenyang", "haerbin", "jinan", "qingdao", "tianjin", "beijing", "nanchang", "chongqing", "chengdu", "xian", "wuhan", "changsha", "wenzhou", "ningbo", "hangzhou", "shanghai", "hefei", "changzhou", "suzhou", "wuxi", "nanjing"};
        String[] H5_CITY_IDS = {"62", "44", "20", "30", "73", "65", "66", "105", "96", "60", "40", "1", "83", "45", "59", "42", "57", "70", "112", "51", "50", "10", "56", "89", "80", "52", "55"};

        String[] PC_CITY_IDS = {"350200"};
        /**
         * 美团美食商户列表
         * 又发现他们都是基于cookie来做的
         */
        String FOOD_SHOP_LIST_API = "https://meishi.meituan.com/i/api/channel/deal/list";


    }

}
