package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.bean.RespSelectBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Elijah on 6/7/2017.
 */
public class WaimaiBootstrap extends BaseAbstractBootstrap {
    static org.slf4j.Logger logger = LoggerFactory.getLogger(WaimaiBootstrap.class);

    final static String[] shopArea = {"中山路/轮渡", "火车站", "厦门大学", "环岛路沿线", "仙岳路沿线", "曾厝垵", "禾祥西路", "莲坂", "嘉禾路", "莲花", "莲前大道", "白鹭洲公园", "前埔/软件园", "瑞景", "市政府", "松柏", "黄厝", "体育路", "SM/江头", "仙岳路沿线", "湖里万达", "SM广场", "湖里公园", "枋湖", "东渡", "五缘湾乐都汇", "蔡塘广场", "塘边", "殿前", "高崎", "金尚小区", "马垅", "泰和花园", "金山小区", "五缘湾建发湾悦城", "新景国际外滩", "悦享中心", "集美学村", "杏林", "集美万达广场", "孙厝新华都", "厦门北站", "旺角", "灌口", "园博苑", "海沧商业圈", "阿罗海城市广场", "东孚", "新垵", "海裕路", "汽车站/城南路", "城西/祥平街道", "城东/岳口", "梧侣/康亭", "同安老城区/钟楼", "同安乐海广场", "后田海鲜船坞", "乌涂商业街", "新店", "马巷", "大嶝岛", "鼓浪屿风景区"};
    final static String[] cityNames = {"厦门"};
    final static String[] cityPys = {"xiamen"};
    final static String[] cityIds = {"350200"};

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
            for (int i = 0; i < shopArea.length; i++) {
                st += i + ":" + shopArea[i] + "  ";
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
//        bootstrap.invokeShopListPost();
//        bootstrap.invokeShopPost();
    }


    private void crawl(int start, int end) {

        int city_index = 0;
        int area_index = 0;
        String city_id = cityIds[city_index];
        String cityName = cityNames[city_index];
        String cityPy = cityPys[city_index];
        String keywords;

        UrlService service = new UrlServiceImpl();

        RespBean respCookieBean = service.getCityWmCookies(cityPy, city_id);
        System.out.println(JSON.toJSONString(respCookieBean));

        for (int i = start; i <= end; i++) {
            keywords = shopArea[i];

            System.setProperty("area", i+"");
            ((org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false)).reconfigure();


            Date stime = new Date();
            logger.info("main starting...start:{}，end:{}", shopArea[start], shopArea[end]);

            data4Area(cityName, keywords, service, respCookieBean);

            Date etime = new Date();
            logger.info("main end...start:{}，end:{} , it took : {}s", shopArea[start], shopArea[end], (etime.getTime() - stime.getTime()) / 1000);
        }

    }

    private void data4Area(String cityName, String keywords, UrlService service, RespBean respCookieBean) {
        List<RespSelectBean> respSelectBeans = service.searchText(cityName, keywords, respCookieBean);
//        System.out.println(JSON.toJSONString(respSelectBeans));

        RespSelectBean respSelectBean = respSelectBeans.get(0);
        String location = respSelectBean.getLocation().replace(".", "");     //  118.102555,24.436341
        String[] split = location.split(",");
        location = split[1] + "," + split[0];

        String name = respSelectBean.getName();             //  厦门大学思明校区

        String adname = respSelectBean.getAdname();         //  思明区
        String address = respSelectBean.getAddress();        //  思明南路422号
        String pname = respSelectBean.getPname();          //  福建省
        String cityname = respSelectBean.getCityname();//  厦门市

        respCookieBean.getCookies().put("w_latlng", location);
        respCookieBean.getCookies().put("w_addr", name);
        String cookie = getCookie(respCookieBean.getCookies());
        String w_addr = name; // 厦门大学思明校区  具体的一个poi
        String uuid = respCookieBean.getCookies().get("w_uuid");
        int pageIndex = 107;
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
//            if (pageIndex > 0) {
//                break;
//            }
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
        if (null == obj) return defaultValue;
        else return obj.toString();
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
            map.put("uuid", "XqTYccd4UrffVcesf-EstGlKsVeSUnjRt1YCNeYyjkB13uZj5lX-NJ2IeNHM7QXJ");
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
            cookies.append("webp=1; w_addr=厦门大学思明校区; utm_source=0; w_cid=350200; w_cpy_cn=\"%E5%8E%A6%E9%97%A8\"; w_cpy=xiamen; w_latlng=24436341,118102555; terminal=i; w_utmz=\"utm_campaign=(direct)&utm_source=5000&utm_medium=(none)&utm_content=(none)&utm_term=(none)\"; w_visitid=8d702c40-5c0b-48d7-925c-a5eeecf8fa40; w_uuid=XqTYccd4UrffVcesf-EstGlKsVeSUnjRt1YCNeYyjkB13uZj5lX-NJ2IeNHM7QXJ; JSESSIONID=1293cotcnyroo1daadt3aysvl1; _lx_utm=");
            Map<String, String> reqHeader = new HashMap<>();
            reqHeader.put("Cookie", cookies.toString());
            reqHeader.put("User-Agent", "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");

            // it works  true
//            RespBean post = UrlServiceImpl.post(url.toString(), map, encoding, reqHeader);
//            if (StringUtils.isEmpty(post.getContent())) {
//                break;
//            }
//            System.out.println(page_index + "-->" + post.getContent());
//            page_index++;
//            if (41 == page_index) {
//                break;
//            }
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
