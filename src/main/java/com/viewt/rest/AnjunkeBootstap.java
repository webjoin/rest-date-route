package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.AnjukeDataBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.CodeUtil;
import com.viewt.rest.data.util.JSONUtil;
import com.viewt.rest.data.util.TimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Elijah on 20/7/2017.
 */
public class AnjunkeBootstap {

    static Logger logger = LoggerFactory.getLogger(AnjunkeBootstap.class);

//    https://xm.anjuke.com/community/xiangana/
//    https://xm.anjuke.com/community/siming/
//    https://xm.anjuke.com/community/huli/
//    https://xm.anjuke.com/community/tongana/
//    https://xm.anjuke.com/community/jimei/
//    https://xm.anjuke.com/community/haicang/
//    https://xm.anjuke.com/community/qitabw/  3070
//          https://xm.anjuke.com/community/jiaomeiz/   33
//          https://xm.anjuke.com/community/longyan/  0
//          https://xm.anjuke.com/community/longchi/  20
//          https://xm.anjuke.com/community/quanzhou/   1937
//          https://xm.anjuke.com/community/zhangzhoua/  1080

    String baseUrl = "https://xm.anjuke.com/community";
    String[][] urls = {
            {"/xiangana/p${page}/", "0"},
            {"/siming/p${page}/", "0"},
            {"/huli/p${page}/", "0"},
            {"/tongana/p${page}/", "0"},
            {"/jimei/p${page}/", "0"},
            {"/haicang/p${page}/", "0"},
            {"/jiaomeiz/p${page}/", "0"},
            {"/longyan/p${page}/", "0"},
            {"/longchi/p${page}/", "0"},
            {"/quanzhou/p${page}/", "0"},
            {"/zhangzhoua/p${page}/", "0"},
    };

    String shopDetailUrl = "https://xm.anjuke.com/community/view/${id}?from=Filter_1&hfilter=filterlist";
    //    e.g. https://xm.anjuke.com/community/view/608809?from=Filter_1&hfilter=filterlist
    String avgPriceUrl = "https://xm.anjuke.com/community_ajax/${version}/price/?tk=${tk}&et=${et}&cis=${id}&ib=1";
    String numsUrl = "https://xm.anjuke.com/v3/ajax/communityext/?commid=${id}&useflg=onlyForAjax";

    //    https://xm.anjuke.com/community_ajax/913/price/?tk=f72e0eb89e7d0556e69ad7c5f82dbc44&et=49010f33a4ebc9ccc765f14309b1f3c5&cis=608809&ib=1
//    https://xm.anjuke.com/community_ajax/913/price/?tk=238ee917be8303e44b6657d56b71a6e9&et=f8cb22995188ff784531d9e23db666ec1362c50c&cis=352894&ib=1
    public static void main(String[] args) {
        AnjunkeBootstap bootstap = new AnjunkeBootstap();
        bootstap.crawl();
//        bootstap.getAvg();
    }

    private UrlService urlService = new UrlServiceImpl();

    // 获取每一个店的Item
//    private Pattern listTotalPagePatten = Pattern.compile("<div\\s+_soj=\"xqlb\"[\\s\\S]+?</div>\\s*</div>");  //focus item
    private Pattern listTotalHoursePatten = Pattern.compile("<em>(\\d+)</em>");  //    <span class="tit">为您找到 <em>翔安</em> 小区 <em>72</em> 个</span>
    private Pattern itemPatten = Pattern.compile("<div\\s+_soj=\"xqlb\"[\\s\\S]+?</div>\\s*</div>");  //focus item
    private Pattern item_idPatten = Pattern.compile("/community/view/(\\d+)/"); //获取id
    private Pattern item_namePatten = Pattern.compile("title=\"([^\"]+)\""); //获取name
    private Pattern item_addrPatten = Pattern.compile("<address>([^<]+)</address>");  //focus item
    private Pattern item_endDatePatten = Pattern.compile("<p class=\"date\">([^<]+)<");  //focus item
    private Pattern item_lngPatten = Pattern.compile("/map/sale/#l1=([\\d.]+)&l2=([\\d.]+)"); //获取经纬度
    private Pattern item_avgPricePatten = Pattern.compile("<strong>(\\d+)</strong>\\s*元/平米"); //均价


    private Pattern detail_typePatten = Pattern.compile("<dt>物业类型：</dt><dd>([^<]+)</dd>"); //物业类型
    private Pattern detail_feePatten = Pattern.compile("<dt class=\"other-dt\">物业费：</dt><dd class=\"other-dd\">([^<]+)</dd>"); //物业类型
    private Pattern detail_areaPatten = Pattern.compile("<dt>总建面积：</dt><dd>([^<]+)</dd>");
    private Pattern detail_houseQtyPatten = Pattern.compile("<dt class=\"other-dt\">总户数：</dt><dd class=\"other-dd\">([^<]+)</dd>");
    private Pattern detail_buildTimePatten = Pattern.compile("<dt>建造年代：</dt><dd>([^<]+)</dd>");
    private Pattern detail_parkingNumPatten = Pattern.compile("<dt class=\"other-dt\">停车位：</dt><dd class=\"other-dd\">([^<]+)</dd>");
    private Pattern detail_volumePatten = Pattern.compile("<dt>容&nbsp;&nbsp;积&nbsp;&nbsp;率：</dt><dd>([^<]+)</dd>");
    private Pattern detail_greenPatten = Pattern.compile("<dt class=\"other-dt\">绿化率：</dt><dd class=\"other-dd\">([^<]+)</dd>");
    private Pattern detail_developerPatten = Pattern.compile("<dt>开&nbsp;&nbsp;发&nbsp;&nbsp;商：</dt><dd class=\"dd-column\">([^<]+)</dd>");
    private Pattern detail_wyCompanyPatten = Pattern.compile("<dt>物业公司：</dt><dd class=\"dd-column\">([^<]+)</dd>");

//    private Pattern item_Page_tokenPatten = Pattern.compile("token:'([^']+)'"); //token
//    private Pattern item_Page_versionPatten = Pattern.compile("version:'(\\d+)'"); //version

    String[] header = {"user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36"};

    private void crawl() {
        int i = 0;
        Map<String, String> reqHeader = new HashMap<>();
        reqHeader.put(header[0], header[1]);
        for (String[] url : urls) {
            if (i++ == 0) continue;
            logger.info("正在执行{}地址", url[0]);
            int page = 1;
            int htotalPage = 0;
            int htotal = 0;
            while (true) {
                TimeUtil.sleep(1);
                String reqUrl = baseUrl + url[0].replace("${page}", page + "");
                RespBean listRespBean = urlService.getContentByUrl(reqUrl, reqHeader);
                String listPageContent = listRespBean.getContent();
                if (StringUtils.isEmpty(listPageContent)) {
                    logger.error("list fetching is fail  , the url {}", reqUrl);
                    continue;
                }
                if (page == 1) {
                    Matcher listTotalHouseMatch = listTotalHoursePatten.matcher(listPageContent);
                    if (listTotalHouseMatch.find()) {
                        String group = listTotalHouseMatch.group(1);
                        htotal = Integer.parseInt(group);
                    }
                    if (htotal == 0) break;
                }
                if (htotalPage == 0) {
                    htotalPage = (int) Math.ceil((double) htotal / 30);
                    if (htotalPage >= 50) {
                        htotalPage = 50;
                    }
                }
                List<AnjukeDataBean> anjukeDataBeans = dealListPage(listPageContent, page, htotal, htotalPage);
                for (AnjukeDataBean anjukeDataBean : anjukeDataBeans) {
                    TimeUtil.sleep(1);
                    fillDetailBean(anjukeDataBean, reqHeader);
                    setNums(anjukeDataBean, reqHeader);
                    logger.info(JSON.toJSONString(anjukeDataBean));
                }
                page++;
                if (page > htotalPage) {
                    break;
                }
            }
            logger.info("正在执行{}地址  总共{} ,end..", url[0], htotal);

//
//
//            RespBean numbersRespBean = urlService.getContentByUrl(numsUrl.replace("${id}", id), reqHeader);
//            JSONObject jsonObject = JSON.parseObject(numbersRespBean.getContent());
//            JSONObject comm_propnum = jsonObject.getJSONObject("comm_propnum");
//            int saleNum = comm_propnum.getIntValue("saleNum");//二手房房源数
//            int rentNum = comm_propnum.getIntValue("rentNum");//租房源数
//            System.out.println(saleNum);
//            System.out.println(rentNum);

//            Matcher matcher1 = item_Page_tokenPatten.matcher(detailContent);
//            String token = "";
//            String version = "";
//            if (matcher1.find()) {
//                token = matcher1.group(1);
//                System.out.println(token);
//            }
//            Matcher matcher2 = item_Page_versionPatten.matcher(detailContent);
//            if (matcher2.find()) {
//                System.out.println(version = matcher2.group(1));
//            }
//            String et = getet(token, Integer.parseInt(version));
//            double k = Math.floor(Integer.parseInt(version)/ 3);
//            String cookie = "lps=${lps}; ctid=46; sessid=${sessid}; als=0; 58tj_uuid=${uuid}; new_session=0; init_refer=; new_uv=1; aQQ_ajkguid=${aQQ_ajkguid}; twe=2";
            //cookie:                                                  lps=http%3A%2F%2Fxm.anjuke.com%2Fcommunity%2Fview%2F608809%3Ffrom%3DFilter_1%26hfilter%3Dfilterlist%7C; ctid=46; sessid=C2ADF1CF-4969-27A6-536C-0B0E7DC303BF; als=0; 58tj_uuid=9cf2e2a2-2c0f-4b71-b886-d78b768ff64e; new_session=0; init_refer=; new_uv=1; aQQ_ajkguid=8B2D3FA3-089F-2306-3A92-CF275A465C51; twe=2
            //cookie:aQQ_ajkguid=1AA6A6E1-3F1A-764D-5097-9118746B42CC; lps=http%3A%2F%2Fxm.anjuke.com%2Fcommunity%2Fview%2F608809%3Ffrom%3DFilter_1%26hfilter%3Dfilterlist%7C; ctid=46; twe=2; sessid=A9309865-D132-3C8F-510B-980884FBD394
//            cookie = cookie.replace("${lps}", CodeUtil.urlEecode(detailUrl));
//            cookie = cookie.replace("${sessid}", sessid == null ? "" : sessid);
//            cookie = cookie.replace("${uuid}", "");
//            cookie = cookie.replace("${aQQ_ajkguid}", aQQ_ajkguid);
//            reqHeader.put("cookie", cookie);
//            RespBean avgRespBean = urlService.getContentByUrl(
//                    avgPriceUrl.replace("${tk}", token)
//                            .replace("${et}", et)
//                            .replace("${version}", ((int)k)+"")
//                            .replace("${id}", id)
//                    , reqHeader);
//
//            System.out.println(avgRespBean.getContent());
        }
    }


    private void fillDetailBean(AnjukeDataBean bean, Map<String, String> reqHeader) {
        String detailUrl = shopDetailUrl.replace("${id}", bean.getId() + "");
        bean.setReqDetailUrl(detailUrl);
        RespBean detailResBean = urlService.getContentByUrl(detailUrl, reqHeader);
        String detailContent = detailResBean.getContent();
        if (StringUtils.isEmpty(detailContent)) {
            logger.info("detail fetching is fail  , the url {}", detailUrl);
            return;
        }

        Matcher typePatten = detail_typePatten.matcher(detailContent);
        Matcher feePatten = detail_feePatten.matcher(detailContent);
        Matcher areaPatten = detail_areaPatten.matcher(detailContent);
        Matcher houseQtyPatten = detail_houseQtyPatten.matcher(detailContent);
        Matcher buildTimePatten = detail_buildTimePatten.matcher(detailContent);
        Matcher parkingNumPatten = detail_parkingNumPatten.matcher(detailContent);
        Matcher volumePatten = detail_volumePatten.matcher(detailContent);
        Matcher greenPatten = detail_greenPatten.matcher(detailContent);
        Matcher developerPatten = detail_developerPatten.matcher(detailContent);
        Matcher wyCompanyPatten = detail_wyCompanyPatten.matcher(detailContent);


        if (typePatten.find()) {
            String typePattenValue = typePatten.group(1);
            bean.setWyType(typePattenValue);
        }
        if (feePatten.find()) {
            String feePattenValue = feePatten.group(1);
            bean.setWyFee(feePattenValue);
        }
        if (areaPatten.find()) {
            String areaPattenValue = areaPatten.group(1);
            bean.setWyArea(areaPattenValue);
        }
        if (houseQtyPatten.find()) {
            String houseQty = houseQtyPatten.group(1);
            bean.setHouseQty(houseQty);
        }
        if (buildTimePatten.find()) {
            String buildTimePattenValue = buildTimePatten.group(1);
            bean.setWyBuildTime(buildTimePattenValue);
        }
        if (parkingNumPatten.find()) {
            String parkingNumPattenValue = parkingNumPatten.group(1);
//            System.out.println(parkingNumPattenValue);
            bean.setParkingNum(parkingNumPattenValue);
        }
        if (volumePatten.find()) {
            String volumePattenValue = volumePatten.group(1);
//            System.out.println(volumePattenValue);
            bean.setVolume(volumePattenValue);
        }
        if (greenPatten.find()) {
            String greenPattenValue = greenPatten.group(1);
//            System.out.println(greenPattenValue);
            bean.setGreen(greenPattenValue);
        }
        if (developerPatten.find()) {
            String developerPattenValue = developerPatten.group(1);
//            System.out.println(developerPattenValue);
            bean.setDeveloper(developerPattenValue);
        }
        if (wyCompanyPatten.find()) {
            String wyCompanyPattenValue = wyCompanyPatten.group(1);
//            System.out.println(wyCompanyPattenValue);
            bean.setWyCompany(wyCompanyPattenValue);
        }
    }

    private void setNums(AnjukeDataBean bean, Map<String, String> reqHeader) {
        String reqUrl = numsUrl.replace("${id}", bean.getId() + "");
        RespBean numbersRespBean = urlService.getContentByUrl(reqUrl, reqHeader);
        String content = numbersRespBean.getContent();
        if (StringUtils.isEmpty(content)) {
            logger.error("nums fetching is fail  , the url {}", reqUrl);
            return;
        }
        JSONObject jsonObject = JSON.parseObject(content);
        JSONObject comm_propnum = JSONUtil.getJSONObject(jsonObject, "comm_propnum");
        int saleNum = comm_propnum.getInteger("saleNum");//二手房房源数
        int rentNum = comm_propnum.getInteger("rentNum");//租房源数
        bean.setSaleNum(saleNum);
        bean.setRentNum(rentNum);
    }

    private List<AnjukeDataBean> dealListPage(String listPageContent, int page, int htotal, int htotalPage) {
        List<AnjukeDataBean> rsList = new ArrayList<>();
        Matcher matcher = itemPatten.matcher(listPageContent);

        while (matcher.find()) {   // 一页数据  一个list
            AnjukeDataBean bean = new AnjukeDataBean();
            bean.setHtotal(htotal);
            bean.setPage(page);
            bean.setTotalPage(htotalPage);

            String itemContent = matcher.group();
            Matcher idMatcher1 = item_idPatten.matcher(itemContent);
            Matcher nameMatcher2 = item_namePatten.matcher(itemContent);
            Matcher lngMatcher3 = item_lngPatten.matcher(itemContent);
            Matcher avgMatcher4 = item_avgPricePatten.matcher(itemContent);
            Matcher addrMatcher5 = item_addrPatten.matcher(itemContent);
            Matcher endDateMatch6 = item_endDatePatten.matcher(itemContent);
            if (idMatcher1.find()) {    // Id
                bean.setId(Integer.parseInt(idMatcher1.group(1)));
            }
            if (nameMatcher2.find()) {
                bean.setName(nameMatcher2.group(1));
            }
            if (lngMatcher3.find()) {
                bean.setLat(Double.parseDouble(lngMatcher3.group(1)));
                bean.setLng(Double.parseDouble(lngMatcher3.group(2)));
            }
            if (avgMatcher4.find()) {
                bean.setAvg(Double.parseDouble(avgMatcher4.group(1)));
            }
            if (addrMatcher5.find()) {
                String group = addrMatcher5.group(1);
                if (StringUtils.isNotEmpty(group)) {
                    bean.setAddr(group.trim());
                }
            }
            if (endDateMatch6.find()) {
                String group = endDateMatch6.group(1);
                if (StringUtils.isNotEmpty(group)) {
                    bean.setEndDate(group.trim());
                }
            }
            rsList.add(bean);
        }
        return rsList;
    }

    String getAvg() {
        String s = "https://xm.anjuke.com/community_ajax/913/price/?tk=e918fa5fc5987d14ca718d16b3d3aed9&et=7e8bc25abb3e2ee1fd1e39dc4b72f1bc18a94f356d26f42910fee57677adaa26&cis=608809&ib=1";
        s = "https://xm.anjuke.com/community_ajax/913/price/?tk=18594513551077febc352554ccb93b0a&et=5a9570ee2bc21984c201dbf6dd37adc1&cis=608809&ib=1";
        String cookie = "lps=http%3A%2F%2Fxm.anjuke.com%2Fcommunity%2Fview%2F608809%3Ffrom%3DFilter_1%26hfilter%3Dfilterlist%7C; ctid=46; sessid=B8D9A616-0DA9-0DBC-112B-963BAED406C3; 58tj_uuid=748e91c1-3a62-43fb-b131-def3175056f5; new_session=1; init_refer=; new_uv=1; als=0; aQQ_ajkguid=EED7221E-63FA-C40A-F8F3-BAD2EB4A5CC3; twe=2";
        cookie = "lps=http%3A%2F%2Fxm.anjuke.com%2Fcommunity%2Fview%2F608809%3Ffrom%3DFilter_1%26hfilter%3Dfilterlist%7C; ctid=46; sessid=B8D9A616-0DA9-0DBC-112B-963BAED406C3; als=0; 58tj_uuid=748e91c1-3a62-43fb-b131-def3175056f5; new_session=0; init_refer=; new_uv=1; aQQ_ajkguid=EED7221E-63FA-C40A-F8F3-BAD2EB4A5CC3; twe=2";

        Map<String, String> reqHeader = new HashMap<>();
        reqHeader.put(header[0], header[1]);
        reqHeader.put("cookie", cookie);
        reqHeader.put(":authority", "xm.anjuke.com");
        reqHeader.put(":method", "GET");
        reqHeader.put(":path", ":path:/community_ajax/913/price/?tk=18594513551077febc352554ccb93b0a&et=5a9570ee2bc21984c201dbf6dd37adc1&cis=608809&ib=1");
        reqHeader.put(":scheme", "https");
        reqHeader.put("accept", "application/json, text/javascript, */*; q=0.01");
        reqHeader.put("accept-encoding", "gzip, deflate, br");

        RespBean contentByUrl = urlService.getContentByUrl(s, reqHeader);
//        System.out.println(contentByUrl.getContent());
        return "";
    }


    public static String getet(String token, int version) {
        double k = Math.floor(version / 2);
//        String[][] j ={{"\\x6D\\x64\\x35", "\\x73\\x68\\x61\\x31", "\\x73\\x68\\x61\\x32\\x35\\x36"}, {"\\x6D\\x64\\x35", "\\x73\\x68\\x61\\x32\\x35\\x36", "\\x73\\x68\\x61\\x31"}, {"\\x73\\x68\\x61\\x31", "\\x73\\x68\\x61\\x32\\x35\\x36", "\\x6D\\x64\\x35"}, {"\\x73\\x68\\x61\\x31", "\\x6D\\x64\\x35", "\\x73\\x68\\x61\\x32\\x35\\x36"}, {"\\x73\\x68\\x61\\x32\\x35\\x36", "\\x73\\x68\\x61\\x31", "\\x6D\\x64\\x35"}, {"\\x73\\x68\\x61\\x32\\x35\\x36", "\\x6D\\x64\\x35", "\\x73\\x68\\x61\\x31"}, {"\\x6D\\x64\\x35", "\\x73\\x68\\x61\\x31"}, {"\\x73\\x68\\x61\\x31", "\\x6D\\x64\\x35"}, {"\\x6D\\x64\\x35", "\\x73\\x68\\x61\\x32\\x35\\x36"}, {"\\x73\\x68\\x61\\x32\\x35\\x36", "\\x6D\\x64\\x35"}, {"\\x73\\x68\\x61\\x32\\x35\\x36", "\\x73\\x68\\x61\\x31"}, {"\\x73\\x68\\x61\\x31", "\\x73\\x68\\x61\\x32\\x35\\x36"}, {"\\u0062\\u0061\\u0073\\u0065\\u0036\\u0034\\u005f\\u0065\\u006e\\u0063\\u006f\\u0064\\u0065"}};
        String[][] j = {{"md5", "sha1", "sha256"}, {"md5", "sha256", "sha1"}, {"sha1", "sha256", "md5"}, {"sha1", "md5", "sha256"}, {"sha256", "sha1", "md5"}, {"sha256", "md5", "sha1"}, {"md5", "sha1"}, {"sha1", "md5"}, {"md5", "sha256"}, {"sha256", "md5"}, {"sha256", "sha1"}, {"sha1", "sha256"}, {"base64_encode"}};
        double m = (Integer.parseInt(token.substring(0, 3), 16) + k) % j.length;
        String[] l = j[(int) m];
        if (l == j[j.length - 1]) {
            return CodeUtil.base64Encode(token);
        } else {
            String n = token;
            for (int i = 0; i < l.length; i++) {
//                n = ajk.chart.algos[p](n)
                switch (l[i]) {
                    case "md5":
                        n = CodeUtil.md5(n);
                        break;
                    case "sha1":
                        n = CodeUtil.sha1(n);
                        break;
                    case "sha256":
                        n = CodeUtil.sha256(n);
                        break;
                }
            }

            System.out.println(n);
            return n;
        }
    }
}
