package com.viewt.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.data.bean.MeituanFoodShopListReq;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.util.CodeUtil;
import com.viewt.rest.data.util.Cons;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author tangyu
 * @since 2017-10-31 18:17
 */
public class MeituanBootstrap extends BaseBootstrap {

    public static void main(String[] args) {
        MeituanBootstrap bootstrap = new MeituanBootstrap();
        bootstrap.start(args);
//        String[] meituanH5CityIds = Cons.Meituan.H5_CITY_IDS;
//        String[] meituanCityNames = Cons.Meituan.CITY_NAMES;
//        System.out.println(meituanCityNames.length);
//        System.out.println(meituanH5CityIds.length);
    }

    @Override
    protected void crawl(String[] args) {
//        this.reqHeader
//        预订
//        _lxsdk=15dea3737bfc8-0d0fb2c1fa69ca-14386d57-13c680-15dea3737bfc8; rvct=10; _lxsdk_cuid=15ea7def078c8-0fd0f88c6a650f-31617e02-13c680-15ea7def079c8; __mta=150729609.1506054963715.1506054963715.1506055071447.2; rvd=47020499; uuid=c8bd1a645af956b7ac66.1506054957.0.0.0; oc=oEy-BaOhCN2Pk6yEIUloNmaEsvFBqfv3h3KtEwhTaKYQmnt7oNb7WKbD6TX_NcnYc-1OVEPSkdGdJD42EKKDCnMkBPjOLrqOL2dyOR0fnNrEDu5wOhtCPdzJM3OaSjgXpQBOe9ztc7R5SCcjoWa-ms-GQs1W_P6rjSrEYMF-vqA; IJSESSIONID=18bebx7qw2kpr14c515tdvlcha; iuuid=32EF812F603A67948E238EA967029B5054F1F68DF405B483CBE3956C1D76E6AB; _hc.v=f03ed46c-4ce6-7340-d7cc-276935e06910.1509429378; ci3=1; c_6NCia=1; idau=1; latlng=; __utma=74597006.1354234526.1509429379.1509446202.1509463154.3; __utmb=74597006.3.9.1509463158274; __utmc=74597006; __utmz=74597006.1509446202.2.2.utmcsr=meishi.meituan.com|utmccn=(referral)|utmcmd=referral|utmcct=/i/; i_extend=C_b1Gimthomepagecategory11H__a; _lx_utm=utm_source=meishi.meituan.com&utm_medium=referral&utm_content=/i/; ci=73; cityname="郑州"; __mta=150729609.1506054963715.1506055071447.1509463159357.3; client-id=f820f39e-14cb-42e2-8035-50e93003a304; _lxsdk_s=64f827c8beef2f60f459c18ea2a7||10
//        买单
//        _lxsdk=15dea3737bfc8-0d0fb2c1fa69ca-14386d57-13c680-15dea3737bfc8; rvct=10; _lxsdk_cuid=15ea7def078c8-0fd0f88c6a650f-31617e02-13c680-15ea7def079c8; __mta=150729609.1506054963715.1506054963715.1506055071447.2; rvd=47020499; uuid=c8bd1a645af956b7ac66.1506054957.0.0.0; oc=oEy-BaOhCN2Pk6yEIUloNmaEsvFBqfv3h3KtEwhTaKYQmnt7oNb7WKbD6TX_NcnYc-1OVEPSkdGdJD42EKKDCnMkBPjOLrqOL2dyOR0fnNrEDu5wOhtCPdzJM3OaSjgXpQBOe9ztc7R5SCcjoWa-ms-GQs1W_P6rjSrEYMF-vqA; IJSESSIONID=18bebx7qw2kpr14c515tdvlcha; iuuid=32EF812F603A67948E238EA967029B5054F1F68DF405B483CBE3956C1D76E6AB; _hc.v=f03ed46c-4ce6-7340-d7cc-276935e06910.1509429378; ci3=1; c_6NCia=1; idau=1; latlng=; __utma=74597006.1354234526.1509429379.1509446202.1509463154.3;                                    __utmc=74597006; __utmz=74597006.1509446202.2.2.utmcsr=meishi.meituan.com|utmccn=(referral)|utmcmd=referral|utmcct=/i/; i_extend=C_b1Gimthomepagecategory11H__a; _lx_utm=utm_source=meishi.meituan.com&utm_medium=referral&utm_content=/i/; ci=73; cityname="郑州"; __mta=150729609.1506054963715.1506055071447.1509463159357.3; client-id=f820f39e-14cb-42e2-8035-50e93003a304; _lxsdk_s=64f827c8beef2f60f459c18ea2a7||13

//        排队
//        _lxsdk=15dea3737bfc8-0d0fb2c1fa69ca-14386d57-13c680-15dea3737bfc8; rvct=10; _lxsdk_cuid=15ea7def078c8-0fd0f88c6a650f-31617e02-13c680-15ea7def079c8; __mta=150729609.1506054963715.1506054963715.1506055071447.2; rvd=47020499; uuid=c8bd1a645af956b7ac66.1506054957.0.0.0; oc=oEy-BaOhCN2Pk6yEIUloNmaEsvFBqfv3h3KtEwhTaKYQmnt7oNb7WKbD6TX_NcnYc-1OVEPSkdGdJD42EKKDCnMkBPjOLrqOL2dyOR0fnNrEDu5wOhtCPdzJM3OaSjgXpQBOe9ztc7R5SCcjoWa-ms-GQs1W_P6rjSrEYMF-vqA; IJSESSIONID=18bebx7qw2kpr14c515tdvlcha; iuuid=32EF812F603A67948E238EA967029B5054F1F68DF405B483CBE3956C1D76E6AB; _hc.v=f03ed46c-4ce6-7340-d7cc-276935e06910.1509429378; ci3=1; c_6NCia=1; idau=1; latlng=; __utma=74597006.1354234526.1509429379.1509446202.1509463154.3; __utmb=74597006.3.9.1509463158274; __utmc=74597006; __utmz=74597006.1509446202.2.2.utmcsr=meishi.meituan.com|utmccn=(referral)|utmcmd=referral|utmcct=/i/; i_extend=C_b1Gimthomepagecategory11H__a; _lx_utm=utm_source=meishi.meituan.com&utm_medium=referral&utm_content=/i/; ci=73; cityname="郑州"; __mta=150729609.1506054963715.1506055071447.1509463159357.3; client-id=f820f39e-14cb-42e2-8035-50e93003a304; _lxsdk_s=64f827c8beef2f60f459c18ea2a7||7
//        上海排队
//        _lxsdk=15dea3737bfc8-0d0fb2c1fa69ca-14386d57-13c680-15dea3737bfc8; rvct=10; _lxsdk_cuid=15ea7def078c8-0fd0f88c6a650f-31617e02-13c680-15ea7def079c8; __mta=150729609.1506054963715.1506054963715.1506055071447.2; rvd=47020499; uuid=c8bd1a645af956b7ac66.1506054957.0.0.0; oc=oEy-BaOhCN2Pk6yEIUloNmaEsvFBqfv3h3KtEwhTaKYQmnt7oNb7WKbD6TX_NcnYc-1OVEPSkdGdJD42EKKDCnMkBPjOLrqOL2dyOR0fnNrEDu5wOhtCPdzJM3OaSjgXpQBOe9ztc7R5SCcjoWa-ms-GQs1W_P6rjSrEYMF-vqA; IJSESSIONID=18bebx7qw2kpr14c515tdvlcha; iuuid=32EF812F603A67948E238EA967029B5054F1F68DF405B483CBE3956C1D76E6AB; _hc.v=f03ed46c-4ce6-7340-d7cc-276935e06910.1509429378; ci3=1; c_6NCia=1; idau=1; latlng=; __utma=74597006.1354234526.1509429379.1509463154.1509465208.4; __utmb=74597006.6.9.1509465214883; __utmc=74597006; __utmz=74597006.1509465208.4.3.utmcsr=meishi.meituan.com|utmccn=(referral)|utmcmd=referral|utmcct=/i/; i_extend=C_b1Gimthomepagecategory11H__a; _lx_utm=utm_source=meishi.meituan.com&utm_medium=referral&utm_content=/i/; ci=10; cityname="上海"; __mta=150729609.1506054963715.1506055071447.1509465215854.3; client-id=f820f39e-14cb-42e2-8035-50e93003a304; _lxsdk_s=64f827c8beef2f60f459c18ea2a7||33
//        IJSESSIONID=55cozwqmkyd6swbpo19zsy6n; iuuid=0D7331974CF26E0361035AEA0E7E705453AF636D203106D16F3B7F2197B5C76B; ci3=1; latlng=; __utma=74597006.819102204.1509467614.1509467614.1509467614.1; __utmb=74597006.40.9.1509470103944; __utmc=74597006; __utmz=74597006.1509467614.1.1.utmcsr=i.waimai.meituan.com|utmccn=(referral)|utmcmd=referral|utmcct=/mti/home; i_extend=C_b1Gimthomepagecategory11H__a; uuid=9d73884b-526b-448f-8dbd-d7f743dcc6c0; _lx_utm=utm_source%3Dmeishi.meituan.com%26utm_medium%3Dreferral%26utm_content%3D%2Fi%2F; _hc.v=ce06d15a-0eed-1660-dde2-af11592ec3e5.1509470105; ci=62; cityname="%E5%8E%A6%E9%97%A8"; __mta=150729609.1506054963715.1509466155381.1509470104971.4; client-id=f820f39e-14cb-42e2-8035-50e93003a304; _lxsdk_s=bc5909d05c33a1883285013a5dc4%7C%7C69


//        _lxsdk=15dea3737bfc8-0d0fb2c1fa69ca-14386d57-13c680-15dea3737bfc8; rvct=10; _lxsdk_cuid=15ea7def078c8-0fd0f88c6a650f-31617e02-13c680-15ea7def079c8; __mta=150729609.1506054963715.1506054963715.1506055071447.2; rvd=47020499; uuid=c8bd1a645af956b7ac66.1506054957.0.0.0; oc=oEy-BaOhCN2Pk6yEIUloNmaEsvFBqfv3h3KtEwhTaKYQmnt7oNb7WKbD6TX_NcnYc-1OVEPSkdGdJD42EKKDCnMkBPjOLrqOL2dyOR0fnNrEDu5wOhtCPdzJM3OaSjgXpQBOe9ztc7R5SCcjoWa-ms-GQs1W_P6rjSrEYMF-vqA; IJSESSIONID=18bebx7qw2kpr14c515tdvlcha; iuuid=32EF812F603A67948E238EA967029B5054F1F68DF405B483CBE3956C1D76E6AB; _hc.v=f03ed46c-4ce6-7340-d7cc-276935e06910.1509429378; ci3=1; c_6NCia=1; idau=1; latlng=; __utma=74597006.1354234526.1509429379.1509463154.1509465208.4; __utmb=74597006.6.9.1509465214883; __utmc=74597006; __utmz=74597006.1509465208.4.3.utmcsr=meishi.meituan.com|utmccn=(referral)|utmcmd=referral|utmcct=/i/; i_extend=C_b1Gimthomepagecategory11H__a; _lx_utm=utm_source=meishi.meituan.com&utm_medium=referral&utm_content=/i/; ci=10; cityname="上海"; __mta=150729609.1506054963715.1506055071447.1509465215854.3; _lxsdk_s=64f827c8beef2f60f459c18ea2a7||33; client-id=f820f39e-14cb-42e2-8035-50e93003a304


        //郑州排队
        String[] cityNames = Cons.Meituan.CITY_NAMES;
        String[] cityIds = Cons.Meituan.H5_CITY_IDS;
        for (int i = 0; i < cityNames.length; i++) {
            String cityName = cityNames[i];
            String cityId = cityIds[i];
            if (Objects.equals(cityName, "南昌")) {
                doCrawl(cityName, cityId);
            }
//            break;
        }

//        cookie =        "_lxsdk=15dea3737bfc8-0d0fb2c1fa69ca-14386d57-13c680-15dea3737bfc8; rvct=10; _lxsdk_cuid=15ea7def078c8-0fd0f88c6a650f-31617e02-13c680-15ea7def079c8; __mta=150729609.1506054963715.1506054963715.1506055071447.2; rvd=47020499; uuid=c8bd1a645af956b7ac66.1506054957.0.0.0; oc=oEy-BaOhCN2Pk6yEIUloNmaEsvFBqfv3h3KtEwhTaKYQmnt7oNb7WKbD6TX_NcnYc-1OVEPSkdGdJD42EKKDCnMkBPjOLrqOL2dyOR0fnNrEDu5wOhtCPdzJM3OaSjgXpQBOe9ztc7R5SCcjoWa-ms-GQs1W_P6rjSrEYMF-vqA; IJSESSIONID=18bebx7qw2kpr14c515tdvlcha; iuuid=32EF812F603A67948E238EA967029B5054F1F68DF405B483CBE3956C1D76E6AB; _hc.v=f03ed46c-4ce6-7340-d7cc-276935e06910.1509429378; ci3=1; c_6NCia=1; idau=1; latlng=; __utma=74597006.1354234526.1509429379.1509463154.1509465208.4; __utmb=74597006.18.9.1509466154311; __utmc=74597006; __utmz=74597006.1509465208.4.3.utmcsr=meishi.meituan.com|utmccn=(referral)|utmcmd=referral|utmcct=/i/; i_extend=C_b1Gimthomepagecategory11H__a; _lx_utm=utm_source%3Dmeishi.meituan.com%26utm_medium%3Dreferral%26utm_content%3D%2Fi%2F; ci=10; cityname=\"%E4%B8%8A%E6%B5%B7\"; __mta=150729609.1506054963715.1506055071447.1509466155381.3; client-id=f820f39e-14cb-42e2-8035-50e93003a304; _lxsdk_s=64f827c8beef2f60f459c18ea2a7%7C%7C63";


    }

    private void doCrawl(String cityName, String cityId) {
        MeituanFoodShopListReq shopListReq = new MeituanFoodShopListReq();
        shopListReq.setAreaId(0);
        shopListReq.setCateId(1);
        shopListReq.setDealAttr23("");
        shopListReq.setDealAttr24("");
        shopListReq.setDealAttr25("");
        shopListReq.setLimit(15);
        shopListReq.setLineId(0);
        shopListReq.setOffset(0);
        shopListReq.setPoiAttr20033(20065);
        shopListReq.setPoiAttr20043("");
        shopListReq.setSort("default");

        String cookiePatten = "_lxsdk=15dea3737bfc8-0d0fb2c1fa69ca-14386d57-13c680-15dea3737bfc8; rvct=10; _lxsdk_cuid=15ea7def078c8-0fd0f88c6a650f-31617e02-13c680-15ea7def079c8; __mta=150729609.1506054963715.1506054963715.1506055071447.2; rvd=47020499; uuid=c8bd1a645af956b7ac66.1506054957.0.0.0; oc=oEy-BaOhCN2Pk6yEIUloNmaEsvFBqfv3h3KtEwhTaKYQmnt7oNb7WKbD6TX_NcnYc-1OVEPSkdGdJD42EKKDCnMkBPjOLrqOL2dyOR0fnNrEDu5wOhtCPdzJM3OaSjgXpQBOe9ztc7R5SCcjoWa-ms-GQs1W_P6rjSrEYMF-vqA; IJSESSIONID=18bebx7qw2kpr14c515tdvlcha; iuuid=32EF812F603A67948E238EA967029B5054F1F68DF405B483CBE3956C1D76E6AB; _hc.v=f03ed46c-4ce6-7340-d7cc-276935e06910.1509429378; ci3=1; c_6NCia=1; idau=1; latlng=; __utma=74597006.1354234526.1509429379.1509463154.1509465208.4; __utmb=74597006.12.9.1509465499040; __utmc=74597006; __utmz=74597006.1509465208.4.3.utmcsr=meishi.meituan.com|utmccn=(referral)|utmcmd=referral|utmcct=/i/; i_extend=C_b1Gimthomepagecategory11H__a; _lx_utm=utm_source%3Dmeishi.meituan.com%26utm_medium%3Dreferral%26utm_content%3D%2Fi%2F; ci={0}; cityname=\"{1}\"; __mta=150729609.1506054963715.1506055071447.1509465508711.3; client-id=f820f39e-14cb-42e2-8035-50e93003a304; _lxsdk_s=64f827c8beef2f60f459c18ea2a7%7C%7C50";
        String cookie = MessageFormat.format(cookiePatten, cityId, CodeUtil.urlEecode(cityName));
        reqHeader.put(Cons.COOKIE, cookie);
        reqHeader.put(Cons.USER_AGENT, Cons.PC_USER_AGENT);

        int i = 0;
        int totalCount = 0;
        int limit = 15;
        while (true) {
            //大于了 总数则退出
            if (totalCount != 0) {
                if (shopListReq.getOffset() >= totalCount) {
                    break;
                }
            }
            logger.info("正在处理：city name:{}, city id:{}, page:{} started...", cityName, cityId, i++);
            String reqStr = JSON.toJSONString(shopListReq);
            RespBean post = urlService.executePostJson(Cons.Meituan.FOOD_SHOP_LIST_API, reqStr, reqHeader);
            String rsContent = post.getContent();
            //如果不足15条则继续拉取
            JSONObject jsonObject = JSON.parseObject(rsContent);
            if (Objects.nonNull(jsonObject)) {
                jsonObject.put("cityName", cityName);
                jsonObject.put("cityId", cityId);
                jsonObject.put("page", i);
                //写文件
                logger.info(jsonObject.toJSONString());
                int rsStatus = jsonObject.getIntValue("status");
                if (0 == rsStatus) {
                    JSONObject rsData = jsonObject.getJSONObject("data");
                    if (Objects.nonNull(rsData)) {
                        JSONObject rsPoiListObj = rsData.getJSONObject("poiList");
                        if (Objects.nonNull(rsPoiListObj)) {
                            if (0 == totalCount) {
                                totalCount = rsPoiListObj.getIntValue("totalCount");
                            }

                            JSONArray rsPoiInfos = rsPoiListObj.getJSONArray("poiInfos");
                            if (Objects.nonNull(rsPoiInfos)) {
                                int size = rsPoiInfos.size();
                                if (limit > size) {
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    logger.info("{},{}, page:{} get an error", cityName, cityId, i++);
                }
            }
            shopListReq.setOffset(shopListReq.getOffset() + limit);
        }


    }


}
