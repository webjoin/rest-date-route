package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.bean.RespSelectBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.Cons;
import com.viewt.rest.data.util.Log4jUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Elijah on 6/7/2017.
 */
public class WaimaiBootstrap extends BaseAbstractBootstrap {
    static org.slf4j.Logger logger = LoggerFactory.getLogger(WaimaiBootstrap.class);

    //                                      0:中山路/轮渡  1:火车站  2:厦门大学  3:环岛路沿线  4:仙岳路沿线  5:曾厝垵  6:禾祥西路  7:莲坂  8:嘉禾路  9:莲花  10:莲前大道  11:白鹭洲公园  12:前埔/软件园  13:瑞景  14:市政府  15:松柏  16:黄厝  17:体育路  18:SM/江头  19:仙岳路沿线  20:湖里万达  21:SM广场  22:湖里公园  23:枋湖  24:东渡  25:五缘湾乐都汇  26:蔡塘广场  27:塘边  28:殿前  29:高崎  30:金尚小区  31:马垅  32:泰和花园  33:金山小区  34:五缘湾建发湾悦城  35:新景国际外滩  36:悦享中心  37:集美学村  38:杏林  39:集美万达广场  40:孙厝新华都  41:厦门北站  42:旺角  43:灌口  44:园博苑  45:海沧商业圈  46:阿罗海城市广场  47:东孚  48:新垵  49:海裕路  50:汽车站/城南路  51:城西/祥平街道  52:城东/岳口  53:梧侣/康亭  54:同安老城区/钟楼  55:同安乐海广场  56:后田海鲜船坞  57:乌涂商业街  58:新店  59:马巷  60:大嶝岛  61:鼓浪屿风景区
//    public final static String[] shopArea = {"中山路/轮渡", "火车站", "厦门大学", "环岛路沿线", "仙岳路沿线", "曾厝垵", "禾祥西路", "莲坂", "嘉禾路", "莲花", "莲前大道", "白鹭洲公园", "前埔/软件园", "瑞景", "市政府", "松柏", "黄厝", "体育路", "SM/江头", "仙岳路沿线", "湖里万达", "SM广场", "湖里公园", "枋湖", "东渡", "五缘湾乐都汇", "蔡塘广场", "塘边", "殿前", "高崎", "金尚小区", "马垅", "泰和花园", "金山小区", "五缘湾建发湾悦城", "新景国际外滩", "悦享中心", "集美学村", "杏林", "集美万达广场", "孙厝新华都", "厦门北站", "旺角", "灌口", "园博苑", "海沧商业圈", "阿罗海城市广场", "东孚", "新垵", "海裕路", "汽车站/城南路", "城西/祥平街道", "城东/岳口", "梧侣/康亭", "同安老城区/钟楼", "同安乐海广场", "后田海鲜船坞", "乌涂商业街", "新店", "马巷", "大嶝岛", "鼓浪屿风景区"};
    /**
     * 加入地标
     */
    public final static String[] shopArea1 = {"中山路/轮渡", "火车站", "厦门大学", "环岛路沿线", "仙岳路沿线", "曾厝垵", "禾祥西路", "莲坂", "嘉禾路", "莲花", "莲前大道", "白鹭洲公园", "前埔/软件园", "瑞景", "市政府", "松柏", "黄厝", "体育路", "SM/江头", "仙岳路沿线", "湖里万达", "SM广场", "湖里公园", "枋湖", "东渡", "五缘湾乐都汇", "蔡塘广场", "塘边", "殿前", "高崎", "金尚小区", "马垅", "泰和花园", "金山小区", "五缘湾建发湾悦城", "新景国际外滩", "悦享中心", "集美学村", "杏林", "集美万达广场", "孙厝新华都", "厦门北站", "旺角", "灌口", "园博苑", "海沧商业圈", "阿罗海城市广场", "东孚", "新垵", "海裕路", "汽车站/城南路", "城西/祥平街道", "城东/岳口", "梧侣/康亭", "同安老城区/钟楼", "同安乐海广场", "后田海鲜船坞", "乌涂商业街", "新店", "马巷", "大嶝岛", "鼓浪屿风景区", "体育中心", "世贸商城", "罗宾森广场", "瑞景商业广场", "加州商业广场", "国贸广场", "明发商业广场", "巴黎春天中山路店", "名汇广场", "香港广场", "老虎城", "厦门大学正门", "富万邦商业广场", "白鹭洲", "磐基中心", "国际会展中心", "国际邮轮中心码头", "SM城市广场", "东方巴黎广场", "SM新生活广场", "万达广场", "嘉庚体育馆", "财经学院"};



//    public final static String[] dp_cityIds = { "15"/*,"1"*/};//, "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "16", "17", "18", "19", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "55", "56", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "78", "79", "80", "83", "84", "86", "88", "90", "92", "93", "94", "95", "96", "97", "98", "99", "100", "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168", "169", "170", "171", "172", "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "189", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "205", "206", "207", "208", "209", "210", "211", "212", "213", "214", "215", "216", "217", "218", "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "238", "239", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "252", "253", "254", "255", "258", "260", "261", "267", "268", "269", "270", "276", "279", "291", "292", "293", "294", "295", "296", "297", "299", "301", "303", "307", "308", "310", "313", "321", "324", "325", "328", "341", "342", "344", "345", "358", "398", "1368", "2335", "2341" };

    public final static String[] dp_cityIds = {
            /*"15","1", "2", "3", "4", "5", "6","7", "8", "9", "10", "11", "12", "13", "14", "16", "17", "18", "19", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31",
        "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44",
        "45",
        "46", "47", "48", "49", "50", "51", "52", "55", "56", "58", "59", "60", "61", "62", "63", "64", "65", "66", "67", "68", "69", "70", "71", "72", "73", "74", "75", "76", "78", "79", "80", "83", "84", "86", "88", "90", "92", "93", "94", "95", "96", "97", "98", "99", "100",*/ "101", "102", "103", "104", "105", "106", "107", "108", "109", "110", "111", "112", "113", "114", "115", "116", "117", "118", "119", "120", "121", "122", "123", "124", "125", "126", "127", "128", "129", "130", "131", "132", "133", "134", "135", "136", "137", "139", "140", "141", "142", "143", "144", "145", "146", "147", "148", "149", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "160", "161", "162", "163", "164", "165", "166", "167", "168",
        "169",
        "170", "171",
        "172",
        "173", "174", "175", "176", "177", "178", "179", "180", "181", "182", "183", "184", "185", "186", "187", "189", "191", "192", "193", "194", "195", "196", "197", "198", "199", "200", "201", "202", "203", "205", "206", "207", "208", "209", "210",
        "211", "212", "213", "214", "215", "216", "217", "218",
        "219", "220", "221", "222", "223", "224", "225", "226", "227", "228", "229", "230", "231", "232", "233", "234", "238", "239", "240", "241", "242", "243", "244", "245", "246", "247", "248", "249", "250", "252", "253", "254", "255", "258", "260", "261", "267", "268", "269", "270", "276", "279", "291", "292", "293", "294", "295", "296", "297", "299", "301", "303", "307", "308", "310", "313", "321", "324", "325", "328", "341", "342", "344", "345", "358", "398", "1368", "2335", "2341"
    };

//        http://i.waimai.meituan.com/xiamen?city_id=350200
//    https://restapi.amap.com/v3/place/text?s=rsv3&children=&key=3f3868abdb36336114bde5ab6eecdb68&types=%E5%95%86%E5%8A%A1%E4%BD%8F%E5%AE%85%7C%E5%AD%A6%E6%A0%A1%E4%BF%A1%E6%81%AF%7C%E7%94%9F%E6%B4%BB%E6%9C%8D%E5%8A%A1%7C%E5%85%AC%E5%8F%B8%E4%BC%81%E4%B8%9A%7C%E9%A4%90%E9%A5%AE%E6%9C%8D%E5%8A%A1%7C%E8%B4%AD%E7%89%A9%E6%9C%8D%E5%8A%A1%7C%E4%BD%8F%E5%AE%BF%E6%9C%8D%E5%8A%A1%7C%E4%BA%A4%E9%80%9A%E8%AE%BE%E6%96%BD%E6%9C%8D%E5%8A%A1%7C%E5%A8%B1%E4%B9%90%E5%9C%BA%E6%89%80%7C%E5%8C%BB%E9%99%A2%E7%B1%BB%E5%9E%8B%7C%E9%93%B6%E8%A1%8C%E7%B1%BB%E5%9E%8B%7C%E9%A3%8E%E6%99%AF%E5%90%8D%E8%83%9C%7C%E7%A7%91%E6%95%99%E6%96%87%E5%8C%96%E6%9C%8D%E5%8A%A1%7C%E6%B1%BD%E8%BD%A6%E6%9C%8D%E5%8A%A1&offset=10&city=%E5%8E%A6%E9%97%A8&page=1&language=zh_cn&callback=jsonp_429636_&platform=JS&logversion=2.0&sdkversion=1.3&appname=http%3A%2F%2Fi.waimai.meituan.com%2Fxiamen%3Fcity_id%3D350200&csid=87B02F6A-2BF8-4B34-B45E-823A81B9A030&keywords=%E4%B8%AD%E5%B1%B1%E8%B7%AF%2F%E8%BD%AE%E6%B8%A1
//    key=3f3868abdb36336114bde5ab6eecdb68; guid=05a1-7b1e-5c80-7a0f
//    118.074142,24.455004
//    http://i.waimai.meituan.com/home?lat=24.455004&lng=118.074142


//    https://restapi.amap.com/v3/place/text?s=rsv3&children=&key=3f3868abdb36336114bde5ab6eecdb68&types=%E5%95%86%E5%8A%A1%E4%BD%8F%E5%AE%85%7C%E5%AD%A6%E6%A0%A1%E4%BF%A1%E6%81%AF%7C%E7%94%9F%E6%B4%BB%E6%9C%8D%E5%8A%A1%7C%E5%85%AC%E5%8F%B8%E4%BC%81%E4%B8%9A%7C%E9%A4%90%E9%A5%AE%E6%9C%8D%E5%8A%A1%7C%E8%B4%AD%E7%89%A9%E6%9C%8D%E5%8A%A1%7C%E4%BD%8F%E5%AE%BF%E6%9C%8D%E5%8A%A1%7C%E4%BA%A4%E9%80%9A%E8%AE%BE%E6%96%BD%E6%9C%8D%E5%8A%A1%7C%E5%A8%B1%E4%B9%90%E5%9C%BA%E6%89%80%7C%E5%8C%BB%E9%99%A2%E7%B1%BB%E5%9E%8B%7C%E9%93%B6%E8%A1%8C%E7%B1%BB%E5%9E%8B%7C%E9%A3%8E%E6%99%AF%E5%90%8D%E8%83%9C%7C%E7%A7%91%E6%95%99%E6%96%87%E5%8C%96%E6%9C%8D%E5%8A%A1%7C%E6%B1%BD%E8%BD%A6%E6%9C%8D%E5%8A%A1&offset=10&city=%E5%8E%A6%E9%97%A8&page=1&language=zh_cn&callback=jsonp_877843_&platform=JS&logversion=2.0&sdkversion=1.3&appname=http%3A%2F%2Fi.waimai.meituan.com%2Fxiamen%3Fcity_id%3D350200&csid=82500C5C-F2A6-4B9F-BEDF-BD5CACF34650&keywords=%E7%81%AB%E8%BD%A6%E7%AB%99
//    guid=abd3-e06c-a4e8-8d6f; key=3f3868abdb36336114bde5ab6eecdb68


    public static void main(String[] args) {
        int start = -1;
        int end = -1;
        if (args.length == 0) {
            String st = "";
            for (int i = 0; i < shopArea1.length; i++) {
                st += i + ":" + shopArea1[i] + "  ";
            }
            logger.error("{}", st);
            logger.error("请输入前面的编号哦。pls!");
            System.exit(1);
        } else {
            start = Integer.parseInt(args[0]);
            if (args.length == 1) {
                end = start;
            } else {
                end = Integer.parseInt(args[1]);
            }

        }

        WaimaiBootstrap bootstrap = new WaimaiBootstrap();
        bootstrap.crawl(start, end);
//        bootstrap.testAbuyun();
//        bootstrap.invokeShopListPost();
//        bootstrap.invokeShopPost();
    }

    private void testAbuyun() {
        UrlService service = new UrlServiceImpl();
        service.testAbuyun();
    }


    private void crawl(int start, int end) {

        int city_index = 0;
        String city_id = Cons.Meituan.PC_CITY_IDS[city_index];
        String cityName =  Cons.Meituan.CITY_NAMES[city_index];
        String cityPy =  Cons.Meituan.CITY_PYS[city_index];
        String keywords;

        UrlService service = new UrlServiceImpl();

        RespBean respCookieBean = service.getCityWmCookies(cityPy, city_id);
        System.out.println(JSON.toJSONString(respCookieBean));

        for (int i = start; i <= end; i++) {
            keywords = shopArea1[i];

            Log4jUtil.reconfigure(String.valueOf(i));

            Date stime = new Date();
            logger.info("main starting...start:{}，end:{}", shopArea1[start], shopArea1[end]);

            data4Area(cityName, keywords, service, respCookieBean);

            Date etime = new Date();
            logger.info("main end...start:{}，end:{} , it took : {}s", shopArea1[start], shopArea1[end], (etime.getTime() - stime.getTime()) / 1000);
        }
    }

    String getLong(String lon) {
        if (StringUtils.isNotEmpty(lon)) {
            if (lon.length() < 9) {
                for (int i = lon.length(); i < 9; i++) {
                    lon += "0";
                }
            }
        }
        return lon;
    }

    private String getEncode(String name) {
        try {
            return URLEncoder.encode(name, "utf-8");
        } catch (Exception e) {
            return name;
        }
    }

    private void data4Area(String cityName, String keywords, UrlService service, RespBean respCookieBean) {
        List<RespSelectBean> respSelectBeans = service.searchText(cityName, keywords, respCookieBean);
//        System.out.println(JSON.toJSONString(respSelectBeans));

        RespSelectBean respSelectBean = respSelectBeans.get(0);
        String location = respSelectBean.getLocation().replace(".", "");     //  118.102555,24.436341
        String[] split = location.split(",");
        location = split[1] + "," + getLong(split[0]);

        String name = respSelectBean.getName();             //  厦门大学思明校区

        String adname = respSelectBean.getAdname();         //  思明区
        String address = respSelectBean.getAddress();        //  思明南路422号
        String pname = respSelectBean.getPname();          //  福建省
        String cityname = respSelectBean.getCityname();//  厦门市

        respCookieBean.getCookies().put("w_latlng", location);
        respCookieBean.getCookies().put("w_addr", getEncode(name));
        String cookie = getCookie(respCookieBean.getCookies());
        String w_addr = name; // 厦门大学思明校区  具体的一个poi
        String uuid = respCookieBean.getCookies().get("w_uuid");
        int pageIndex = 0;
        System.out.println("cookies-->>" + cookie);
        while (true) {
            sleep(timeout);
            logger.info("doing name:{} page:{}", w_addr, pageIndex);
            JSONObject shopList = service.getShopList(w_addr, cookie, uuid, pageIndex++ + "");
            shopList.put("w_addr", name);     //厦门大学思明校区
            shopList.put("w_latlng", location);  //118.102555,24.436341
            shopList.put("pname", pname);
            shopList.put("cityname", cityname);
            shopList.put("adname", adname);
            shopList.put("address", address);
            logger.info(shopList.toString());
            String poi_has_next_page = shopList.getString("poi_has_next_page");
            if ("false".equals(poi_has_next_page)) {
                logger.info("{}:{}", w_addr, ",抓取完毕");
                break;
            }
        }
    }

    String getCookie(Map<String, String> cookies) {

        StringBuffer cookBuf = new StringBuffer();
        String w_visitid = cookies.get("w_visitid");//=f1c12021-a899-4ddf-9727-4d5e4e7697c2;
//        String _lxsdk    = cookies.get("_lxsdk=");//15d21d610d0c8-0bcac6f9ee848d-574e6e46-2c600-15d21d610d0c8;
        String webp = ifNull(cookies.get("webp"), "1");//=1;
        String w_addr = cookies.get("w_addr");//=%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6%E6%80%9D%E6%98%8E%E6%A0%A1%E5%8C%BA; //厦门大学思明校区;
        String utm_source = ifNull(cookies.get("utm_source"), "0");//=0;
        String w_cid = cookies.get("w_cid");//=350200;
        String w_cpy_cn = cookies.get("w_cpy_cn");//="%E5%8E%A6%E9%97%A8";   //厦门
        String w_cpy = ifNull(cookies.get("w_cpy"), "xiaomen");//=xiamen;
        String w_latlng = cookies.get("w_latlng");//=24436341,118102555;
//        String __mta     = ifNull(cookies.get("__mta"),"");//=238336959.1499511263657.1499511263657.1499511768737.2;
//        String _lxsdk_s  = cookies.get("_lxsdk_s");//=4f41ac88d56664346baa5f1b18e9%7C%7C4;
        String terminal = ifNull(cookies.get("terminal"), "1");//=i;
        String w_utmz = cookies.get("w_utmz");//="utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)";
        String w_uuid = cookies.get("w_uuid");//=cLNWhvhfNDYFUALODeP75fmq_yjx6cAjBfIFOu0wgnLvBYRrg75yBm-p1LH6IUoJ;
        String JSESSIONID = cookies.get("JSESSIONID");//=15ec98ijx4iuv1lecqfdbi00tf;
        String _lx_utm = ifNull(cookies.get("_lx_utm"), "");//=
//            cookies.append("_lxsdk=15d208f0beec8-0f0e3f9c61d94f-574e6e46-2c600-15d208f0beec8;
// webp=1;
// w_addr=%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6%E6%80%9D%E6%98%8E%E6%A0%A1%E5%8C%BA;
// utm_source=0; w_cid=350200;
// w_cpy_cn=\"%E5%8E%A6%E9%97%A8\";
// w_cpy=xiamen; w_latlng=24436341,118102555;
// __mta=171218591.1499489832127.1499490436555.1499493646454.5;
// _lxsdk_s=4f41ac88d56664346baa5f1b18e9%7C%7C2;
// terminal=i;
// w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\";
// w_visitid=6e221202-2e46-4493-9a1e-436d2ba423fe;
// w_uuid=UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3OnbvKOrYS;
// JSESSIONID=uie0dm259hb1an0vw7e5ikzs;
// _lx_utm=");


//        cookBuf.append("; _lxsdk").append("=").append(_lxsdk);
        cookBuf.append("webp").append("=").append(webp);
        cookBuf.append("; w_addr").append("=").append(w_addr);
        cookBuf.append("; utm_source").append("=").append(utm_source);
//        cookBuf.append("; gw_ciduid").append("=").append(gw_ciduid);
        cookBuf.append("; w_cid").append("=").append(w_cid);
        cookBuf.append("; w_cpy_cn").append("=").append(w_cpy_cn);
        cookBuf.append("; w_cpy").append("=").append(w_cpy);
        cookBuf.append("; w_latlng").append("=").append(w_latlng);
//        cookBuf.append("; __mta").append("=").append(__mta);
//        cookBuf.append("; _lxsdk_s").append("=").append(_lxsdk_s);
        cookBuf.append("; terminal").append("=").append(terminal);
        cookBuf.append("; w_utmz").append("=").append(w_utmz);
        cookBuf.append("; w_visitid").append("=").append(w_visitid);
        cookBuf.append("; w_uuid").append("=").append(w_uuid);
        cookBuf.append("; JSESSIONID").append("=").append(JSESSIONID);
        cookBuf.append("; _lx_utm").append("=").append(_lx_utm);

        return cookBuf.toString();
    }

    private String ifNull(String obj, String defaultValue) {
        if (null == obj) {
            return defaultValue;
        } else {
            return obj.toString();
        }
    }

    public void getWaimaiShopList() {
        String url = "http://i.waimai.meituan.com/ajax/v6/poi/filter?category_type=910&category_text=%E7%BE%8E%E9%A3%9F&_token=eJx9jl9rgzAUxb9LwL4oajQziSClZRYsow+ta7uNMVIXNNT/ZmoZ++5LQZC97HLhd+7hcDnfoI0+gQ9tNdQAslMaUepCgrDn2sgAyV8PUWyAS3t8BP4bgsjArvd+N/bqno1ZOUjtPRGpAMikrH3LEubARMGEWXAhv1hpJlVhJRkrS54vEyZ5WrW3D3mreUChvZgdPspAC7G2DjUSaiHVVq5GN8D493FWFXyZMxk4yEQPLvHwIi/TAEJi2pjahADVr4hVP8XrRDZRTuxEWgIf8O0YH8QYxaEOd8f9UyzGbZyQvqmG/sDsqjs7eqtbOerzKK2fN82FnbL0tYl257E4X+tmf3LW12plCfSyGYIA/PwCYfJyug==";
        String formData = "uuid=-TaH9zi9QKDioSQnX_EE_4nZ_niLUjsgtf1OYGTdZ54Wiob8ggcEJQDwWD4lHoom&platform=3&partner=4&page_index=0&apage=1";
        String s = this.post(url, formData);
        System.out.println(s);
    }

    public void getWaimaiShopList1() {
        String url = "http://i.waimai.meituan.com/channel?category_type=910&category_text=%E7%BE%8E%E9%A3%9F";
        String formData = "uuid=-TaH9zi9QKDioSQnX_EE_4nZ_niLUjsgtf1OYGTdZ54Wiob8ggcEJQDwWD4lHoom&platform=3&partner=4&page_index=0&apage=1";
        String s = this.post(url, formData);
        System.out.println(s);
    }


//    http://i.waimai.meituan.com/ajax/v6/poi/filter?category_type=910&category_text=%E7%BE%8E%E9%A3%9F&_token=eJx9jl9rgzAUxb9LwL4oajQziSClZRYsow+ta7uNMVIXNNT/ZmoZ++5LQZC97HLhd+7hcDnfoI0+gQ9tNdQAslMaUepCgrDn2sgAyV8PUWyAS3t8BP4bgsjArvd+N/bqno1ZOUjtPRGpAMikrH3LEubARMGEWXAhv1hpJlVhJRkrS54vEyZ5WrW3D3mreUChvZgdPspAC7G2DjUSaiHVVq5GN8D493FWFXyZMxk4yEQPLvHwIi/TAEJi2pjahADVr4hVP8XrRDZRTuxEWgIf8O0YH8QYxaEOd8f9UyzGbZyQvqmG/sDsqjs7eqtbOerzKK2fN82FnbL0tYl257E4X+tmf3LW12plCfSyGYIA/PwCYfJyug==


    private void invokeShopListPost() {
        int page_index = 0;
        while (true) {
            System.out.println(page_index);
//            String url = "http://i.waimai.meituan.com/ajax/v6/poi/filter?category_type=910&category_text=%E7%BE%8E%E9%A3%9F&_token=eJx9jm1rgzAUhf9LwH5RNNHojCClY3ZYRmHWtW5jjNQFzXzXzFrK/vtSKPhtlwvn3IfD5VxAH34BD0E5RANikB4TgonlYAdCRwPpzDCU0EUaOPb7B+C9WybUbMf9uIJI3jOYnYnlXhOhDIBciNYzDK6fKK8o1yvGxQ+t9bSpjDSndc3KZUoFy5r+/CnOLfMJgouZsEn4SnCn3AeKGygBUVaWQtZA+/dx3lRsWVLhm1jHlmNhtCjrzEfI1RE0bdsGsl8Vy35Si5vSm4qbDjyrgQfYZop3fArjQEXbffQU82kTp+7YNadxR2EzJKbaq0aJxzLM2pd1d6SHPHvrwm0yVUnRdtHBnIpmZXw/vz5mvg9+/wBN2nLd";
//            url = "http://i.waimai.meituan.com/ajax/v6/poi/filter?category_type=910&category_text=%E7%BE%8E%E9%A3%9F&_token=eJx9j19rgzAUxb9LwL5UNInRGUFKxyyzjD5Y17qNMVIXNKsa/2StZey7L4WCb7tc+J17uFzO/QF9/AkCBHVRE6hBa0IpoY5HPAg9E+ST5zqeA7FvgkO/ewDBm4Oh6Xr++9VI9DwZk8JE93Uj1gugVKoNbFtYZyZqJqyaC/XNGiuXtZ2XrGl4tciZ4oXsLx/q0vKQIjibHD6q0IjujPvI8CMjosbSMegKmP8eLmXNFxVTISYW0S8QNKuaIkTItxDErusCna9OdT7N443sRnXjIIoGBICvx3QrxjiN5mizS55SMa7T3D918nzaMiiHDM/7uV2RUxUX7fOqO7B9Wbx28SYb6+zYdsket0e5tL+Kl8dlGILfP0rEcsQ=";
//            url = "http://i.waimai.meituan.com/ajax/v6/poi/filter?category_type=910&category_text=%E7%BE%8E%E9%A3%9F&_token=eJx9jl9rgzAUxb9LwL4oamK0RpDSMgXL6EPrWrcxRuqCBv9r1lrKvvtSENzTLhfOuT8Ol3MHffQFPGjKIRoQg/SYEEzspeMQF2kg/cuWxMKSnfvjE/DeLWRqtuN+PMBe3jOYHcJyH4lIBkAuROsZBtevlFeU6xXj4pvWetpURprTumblKqWCZU1/+xS3lvkEmouZsFH4SrBUNoHiBkpAlLWlkBBo/z7Om4qtSip8hHVsORaGi7LOfAhdHZrItm0g+1Wx7Ce1mJROKiYdeFYDD7DtGB/4GMWBCnfH/XPMx22cupeuuV4O1GyGBKm9apT4UkZZ+xJ2Z3rKs7cu2iVjlRRttz+hTdGsDY5fw6vvg59fbhJysg==";

            StringBuffer url = new StringBuffer("http://i.waimai.meituan.com/ajax/v6/poi/filter");
            url.append("?");
            url.append("ategory_type").append("=").append("910");
            url.append("&category_text").append("=").append("%E7%BE%8E%E9%A3%9F");
//            url.append("&_token").append("=").append("eJx9jl9rgzAUxb9LwL5U1MTEGkFKyxQsow+ta7uNMVIXNNT/Zmop++5LoSB72eXC79zD4XJuoI2+gActNVQHslMaU4qpQ1xnQZAOkr8eJVgH5/bwBLx3G1k6cdyPu7FT92RMCmG190SkAiCTsvZMUxgDEwUTRsGF/GalkVSFmWSsLHm+TJjkadVeP+W15j6F1mxy+Ch9LVho60BzAy2g2srWaAj0fx9nVcGXOZM+wga2HRvDWV6mPoSuAS1ECAGqXxGrfoqXB9mD8sFOpCXwAN+M8V6MURzM4fawe47FuIkTt2+qod8zq+pOaN7OzRz3eZTWL2FzZscsfWui7WksTpe62R3R+lKtTIFfw8H3wc8vcGFytA==");
//              url.append("&_token").append("=").append("eJx9jl9rgzAUxb9LwL5U1MTEGk/");
            url.append("&_token").append("=").append("eJx9/");  // 这样可取值
//                url.append("&_token").append("=").append("abcdefgh/");         // 这里取值不到
//              url.append("&_token").append("=").append("eJx9jl9rgzAUxb9LwL4omsToVJDSMgXL6IN1bbcxRuqCBv9rZi1j330pCLKXXS78zj0cLucb9NEn8BCU42pADFIT17Ughja0iaWB9K/n2LYGLv3xEXhvJoaaZTvvdyOW92IsChO590QkAyAXovUMg tXyivK9Ypx8UVrPW0qI81pXbNynVLBsqa/fYhby3wXwdXisEn4SvCgbAPFCZTAVTam4oZA /dx3lRsXVLhY6IT0zYJWpV15iPk6Ahiy7KA7Fclsp9kMZPOFDMHntXAA2w3JQc RUmgov0xfkr4tEtSZ ya63igsBnOWO1VoyRjGWXtc9hd6CnPXrtof56qc9F28Qlvi2ZjcPISXn0f/PwCL/hyhQ==");         // 这里取值不到
            String encoding = "utf-8";
            Map<String, String> map = new HashMap<>();
            map.put("uuid", "UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3OnbvKOrYS");
//            map.put("uuid", "u_xE0Y1rOtPyQntY9NCVh3B9pO2t8XyR6YZbeAxxK8ui1rIxMPIxD7tu91Hqwdan");  // direct
            map.put("uuid", "DnshGVweOzcbSABUrCbm5S8gkHhc222vTbSwvIwlDumTJb8njLW9AJRM2takFMO3");// crawl
            map.put("platform", "3");
            map.put("partner", "4");
            map.put("page_index", page_index + "");
            map.put("apage", "1");


            StringBuffer cookies = new StringBuffer();
//        cookies.append("_lxsdk=15d208f0beec8-0f0e3f9c61d94f-574e6e46-2c600-15d208f0beec8; webp=1; w_addr=%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6%E6%80%9D%E6%98%8E%E6%A0%A1%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; _lxsdk_s=4f41ac88d56664346baa5f1b18e9%7C%7C2; w_visitid=a41fb021-68d9-481e-b30a-eca79aa22c38; JSESSIONID=14c8hbiaupohn1l97ewojsu2bn; _lx_utm=; __mta=171218591.1499489832127.1499490436555.1499493646454.5; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_uuid=UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3OnbvKOrYS");
//            cookies.append("_lxsdk=15d208f0beec8-0f0e3f9c61d94f-574e6e46-2c600-15d208f0beec8; webp=1; w_addr=%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6%E6%80%9D%E6%98%8E%E6%A0%A1%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; _lxsdk_s=4f41ac88d56664346baa5f1b18e9%7C%7C2; w_visitid=a41fb021-68d9-481e-b30a-eca79aa22c38; JSESSIONID=14c8hbiaupohn1l97ewojsu2bn; _lx_utm=; __mta=171218591.1499489832127.1499490436555.1499493646454.5; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_uuid=UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3Onbv");
//            cookies.append("_lxsdk=15d208f0beec8-0f0e3f9c61d94f-574e6e46-2c600-15d208f0beec8; webp=1; w_addr=%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6%E6%80%9D%E6%98%8E%E6%A0%A1%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; __mta=171218591.1499489832127.1499490436555.1499493646454.5; _lxsdk_s=4f41ac88d56664346baa5f1b18e9%7C%7C2; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=6e221202-2e46-4493-9a1e-436d2ba423fe; w_uuid=UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3OnbvKOrYS; JSESSIONID=uie0dm259hb1an0vw7e5ikzs; _lx_utm=");
//            cookies.append("webp=1; w_addr=%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6%E6%80%9D%E6%98%8E%E6%A0%A1%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; __mta=171218591.1499489832127.1499490436555.1499493646454.5; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=6e221202-2e46-4493-9a1e-436d2ba423fe; w_uuid=UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3OnbvKOrYS; JSESSIONID=uie0dm259hb1an0vw7e5ikzs; _lx_utm=");
//            cookies.append("webp=1; w_addr=厦门大学思明校区; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=6e221202-2e46-4493-9a1e-436d2ba423fe; w_uuid=UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3OnbvKOrYS; JSESSIONID=uie0dm259hb1an0vw7e5ikzs; _lx_utm=");
//          cookies.append("webp=1; w_addr=厦门大学思明校区; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8C%97%E4%BA%AC\"; w_cpy=xiamen; w_latlng=24436341,118102555; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=bc822604-9b30-4000-a1e1-b62a238b917e; w_uuid=qBiKIwfQZVNDNkmYL6zvkKcgbeVaWu9L8h6l8LkLcBTBdbIcynM2R0sm2iNCN6Yc; JSESSIONID=ivoxvzslekg7sekrki4ikkq6; _lx_utm=");
//            cookies.append("webp=1; w_addr=厦门大学思明校区; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=f2188e68-7893-4e5a-9abd-5076f58a30b1; w_uuid=vM37EGBwAB4PEOzfmOHisvFOZyzu7fhhcv9rk0lIt27PFkOLwX3epOYDp1FW_827; JSESSIONID=1kuok5sqy1j9acslmwqnvoqc2; _lx_utm=");
//            cookies.append("webp=1; w_addr=厦门大学思明校区; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=8d702c40-5c0b-48d7-925c-a5eeecf8fa40; w_uuid=XqTYccd4UrffVcesf-EstGlKsVeSUnjRt1YCNeYyjkB13uZj5lX-NJ2IeNHM7QXJ; JSESSIONID=1293cotcnyroo1daadt3aysvl1; _lx_utm=");
//            cookies.append("webp=1; _lxsdk=15d276cd620c8-0ee672c8ce9a82-574e6e46-2c600-15d276cd620b8; __mta=47601304.1499606226395.1499606226395.1499606351236.2; mtsi-real-ip=114.88.232.102; mtsi-cur-time=\"2017-07-11 00:11:37\"; wm_poi_view_id=443529370633036; poiid=443529370633036; w_visitid=f3958e88-ac78-405e-8e47-90bc5c945685; wx_channel_id=0; w_addr=%E9%87%91%E5%B0%9A%E5%B0%8F%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24493403,118150540; __mta=47601304.1499606226395.1499606351236.1499953158302.3; _lxsdk_s=b49a7d70ca5b9124ca55cfc14c14%7C%7C6; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_uuid=u_xE0Y1rOtPyQntY9NCVh3B9pO2t8XyR6YZbeAxxK8ui1rIxMPIxD7tu91Hqwdan; JSESSIONID=miplyg51woxv16qot2hlp67kk; _lx_utm=");
//            cookies.append("webp=1; w_visitid=f3958e88-ac78-405e-8e47-90bc5c945685; wx_channel_id=0; w_addr=%E9%87%91%E5%B0%9A%E5%B0%8F%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24493403,118150540; __mta=47601304.1499606226395.1499606351236.1499953158302.3; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_uuid=u_xE0Y1rOtPyQntY9NCVh3B9pO2t8XyR6YZbeAxxK8ui1rIxMPIxD7tu91Hqwdan; JSESSIONID=miplyg51woxv16qot2hlp67kk; _lx_utm=");

//            cookies.append("webp=1; _lxsdk=15d276cd620c8-0ee672c8ce9a82-574e6e46-2c600-15d276cd620b8; __mta=47601304.1499606226395.1499606226395.1499606351236.2; mtsi-real-ip=114.88.232.102; mtsi-cur-time=\"2017-07-11 00:11:37\"; wm_poi_view_id=443529370633036; poiid=443529370633036; w_visitid=552a51dc-3c84-4dce-932f-6b62dd6186b2; wx_channel_id=0; w_addr=%E5%8E%A6%E9%97%A8%E5%B8%82%E9%BC%93%E6%B5%AA%E5%B1%BF%E9%A3%8E%E6%99%AF%E5%90%8D%E8%83%9C%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24444673,118067042; __mta=47601304.1499606226395.1499606351236.1499957403243.3; _lxsdk_s=b49a7d70ca5b9124ca55cfc14c14%7C%7C13; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_uuid=u_xE0Y1rOtPyQntY9NCVh3B9pO2t8XyR6YZbeAxxK8ui1rIxMPIxD7tu91Hqwdan; JSESSIONID=1nb26464x0f3h17of0hsx7ka01; _lx_utm=");
//              cookies.append("webp=1; w_visitid=552a51dc-3c84-4dce-932f-6b62dd6186b2; wx_channel_id=0; w_addr=%E5%8E%A6%E9%97%A8%E5%B8%82%E9%BC%93%E6%B5%AA%E5%B1%BF%E9%A3%8E%E6%99%AF%E5%90%8D%E8%83%9C%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24444673,118067042; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_uuid=u_xE0Y1rOtPyQntY9NCVh3B9pO2t8XyR6YZbeAxxK8ui1rIxMPIxD7tu91Hqwdan; JSESSIONID=1nb26464x0f3h17of0hsx7ka01; _lx_utm=");
//              cookies.append("webp=1; w_addr=%E5%8E%A6%E9%97%A8%E5%B8%82%E9%BC%93%E6%B5%AA%E5%B1%BF%E9%A3%8E%E6%99%AF%E5%90%8D%E8%83%9C%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24444673,118067042; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=ca34db2a-5afb-420d-ac6f-0a8b4c1889f1; w_uuid=DnshGVweOzcbSABUrCbm5S8gkHhc222vTbSwvIwlDumTJb8njLW9AJRM2takFMO3; JSESSIONID=1n6li6a60a8zh1ksvtcmvqxhmz; _lx_utm=");
            cookies.append("webp=1; w_addr=厦门市鼓浪屿风景名胜区; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24444673,118067042; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=ca34db2a-5afb-420d-ac6f-0a8b4c1889f1; w_uuid=DnshGVweOzcbSABUrCbm5S8gkHhc222vTbSwvIwlDumTJb8njLW9AJRM2takFMO3; JSESSIONID=1n6li6a60a8zh1ksvtcmvqxhmz; _lx_utm=");
            Map<String, String> reqHeader = new HashMap<>();
            reqHeader.put("Cookie", cookies.toString());
            reqHeader.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");

            UrlService service = new UrlServiceImpl();
            // it works  true
//            service.executePostFrom()
            RespBean post = service.executePostFrom(url.toString(), map, reqHeader);
            if (StringUtils.isEmpty(post.getContent())) {
                break;
            }
            System.out.println(page_index + "-->" + post.getContent());
            page_index++;
            if (1 == page_index) {
                break;
            }
        }
    }

    private void invokeShopPost() {
        int page_index = 0;
        StringBuffer url = new StringBuffer("http://i.waimai.meituan.com/ajax/v8/poi/food");
        url.append("?");
        url.append("&_token").append("=").append("eJx9juFLwzAQxf+Xg/jF0iZN0jWFIpurOrXDSaeIiMQats61G+2NdRP/dzMoFL8IB7+7xzve+4Z68gkRo5Qy6gA2dhdKSSpDxrkUDuR/NaGkAx/10xiiV+5TRwbh20l4tHcv9Jsv7JwcE2uAJeI28rzC3eui1IVbmgJ3unLzTenVpkG9q3WFng2mNjBgIuADX4Lz72O+1FVl1he5RrPY1Id3PGxNrBg96xXTYkySARklJExIosiQE3UFtliZ2WKWXx11R+zYFIsKIjC3bXbXnE/vw9XDKlVpk839aXa9x/GMpcdZmx5feLqeXw5vnv0RFTH8/AKwymTz");  // 这样可取值
        String encoding = "utf-8";
        Map<String, String> reqHeader = new HashMap<>();
        Map<String, String> reqParamMap = new HashMap<>();
        reqParamMap.put("wm_poi_id", "354099561463725");
        reqParamMap.put("uuid", "UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3OnbvKOrYS");
        reqParamMap.put("platform", "3");
        reqParamMap.put("partner", "4");

        StringBuffer cookies = new StringBuffer();
        cookies.append("_lxsdk=15d208f0beec8-0f0e3f9c61d94f-574e6e46-2c600-15d208f0beec8; webp=1; w_addr=%E5%8E%A6%E9%97%A8%E5%A4%A7%E5%AD%A6%E6%80%9D%E6%98%8E%E6%A0%A1%E5%8C%BA; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; __mta=171218591.1499489832127.1499490436555.1499493646454.5; _lxsdk_s=4f41ac88d56664346baa5f1b18e9%7C%7C2; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=6e221202-2e46-4493-9a1e-436d2ba423fe; w_uuid=UNFoHdPp0uQstYBvDHURRYaFHJzQIJfWcJEq9mpXuext6zZ61pPBib3OnbvKOrYS; JSESSIONID=uie0dm259hb1an0vw7e5ikzs; _lx_utm=");
        reqHeader.put("Cookie", cookies.toString());
        reqHeader.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
//        RespBean post = UrlServiceImpl.post(url.toString(), reqParamMap, encoding, reqHeader);
//        if (StringUtils.isEmpty(post.getContent())) {
//                break;
//        }
//        String content = post.getContent();
//        JSONObject jsonObject = JSON.parseObject(content);
//        JSONArray food_spu_tags = jsonObject.getJSONArray("food_spu_tags");
//        JSONObject poi_info = jsonObject.getJSONObject("poi_info");
//
//        JSONObject discounts2 = poi_info.getJSONObject("discounts2");  //优化折扣
//        System.out.println(page_index + "-->" + post.getContent());
        page_index++;
    }


}
