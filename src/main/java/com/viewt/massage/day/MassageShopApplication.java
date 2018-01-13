package com.viewt.massage.day;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.viewt.rest.BaseBootstrap;
import com.viewt.rest.data.bean.MassageActivityBean;
import com.viewt.rest.data.bean.MassageShopBean;
import com.viewt.rest.data.bean.RespBean;
import com.viewt.rest.data.service.UrlService;
import com.viewt.rest.data.service.impl.UrlServiceImpl;
import com.viewt.rest.data.util.Cons;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tangyu
 * @since 2018-01-09 09:58
 * area=massage-shop
 */
public class MassageShopApplication extends BaseBootstrap {

    public static void main(String[] args) {
        MassageShopApplication bootstrap = new MassageShopApplication();
        try {
            long stime = System.currentTimeMillis();
            bootstrap.logger.error("{} , start...", bootstrap.dc_index);
            bootstrap.start(args);
            long etime = System.currentTimeMillis();
            bootstrap.logger.error("{} , end...it tooks {}s", bootstrap.dc_index, (etime - stime) / 1000);
        } catch (Exception e) {
            bootstrap.logger.error(e.getMessage(), e);
            bootstrap.logger.error("end...");
        }
    }

    UrlService urlService;
    int dc_index = -1;

    @Override
    protected void crawl(String[] args) {
        String cookie = "m_flash2=1; cityid=1; _lxsdk_cuid=160d8ff4859c8-09e808d6583494-72236637-43ad0-160d8ff4859c8; _lxsdk=160d8ff4859c8-09e808d6583494-72236637-43ad0-160d8ff4859c8; _hc.v=6310a1ae-4364-623d-3010-27613062d231.1515469097; switchcityflashtoast=1; default_ab=shop%3AA%3A1%7Cindex%3AA%3A1%7CshopList%3AA%3A1; source=m_browser_test_33; pvhistory=\"6aaW6aG1Pjo8Lz46PDE1MTU0NzMxNjA0NjddX1s=\"; msource=default; _lxsdk_s=160d98f995d-5d0-2df-ba6%7C%7C3";
        reqHeader.put(Cons.COOKIE, cookie);
        reqHeader.put(Cons.USER_AGENT, Cons.PC_USER_AGENT);

        urlService = new UrlServiceImpl();
        String[][] massageHotAreas = Cons.Dianping.MASSAGE_HOT_AREAS;
        int end = massageHotAreas.length;

        for (int i = 0; i < end; i++) {
            String[] massageHotArea = massageHotAreas[i];
            String areaId = massageHotArea[0];
            String pattern = Cons.Dianping.MASSAGE_SHOP_LIST;

            int start = 0;
            int pageSize = 20;

            int totalPage;
            String url = MessageFormat.format(pattern, start, areaId, System.currentTimeMillis());
            RespBean contentBean = urlService.getContentByUrl(url, reqHeader);
            String content = contentBean.getContent();
            int totalRecord = getTotalRecord(content);

            totalPage = (int) Math.ceil(totalRecord / (double)pageSize);
            List<MassageShopBean> massageShopBeans = getBeans(content, url);
            saveData(massageShopBeans);
            for (int j = 1; j < totalPage; j++) {
                start = j * pageSize;
                String pageUrl = MessageFormat.format(pattern, start, areaId, System.currentTimeMillis());
                RespBean contentByUrl = urlService.getContentByUrl(pageUrl, reqHeader);
                String content1 = contentByUrl.getContent();
                List<MassageShopBean> massageShopBeans1 = getBeans(content1, pageUrl);
                saveData(massageShopBeans1);
            }
        }
    }

    private List<MassageShopBean> getBeans(String content, String url) {
        List<MassageShopBean> list = new ArrayList<>();
        int index = content.indexOf("(");
        int lastIndex = content.lastIndexOf(")");
        String jsonCtx = content.substring(index + 1, lastIndex);
        JSONObject jsonObject = JSON.parseObject(jsonCtx);
        JSONArray list1 = jsonObject.getJSONArray("list");
        list1.forEach(x -> {
            MassageShopBean bean = new MassageShopBean(url, LocalDate.now());
            JSONObject x1 = (JSONObject) x;
            String branchName = x1.getString("branchName");
            if (Objects.isNull(branchName)) {
                branchName = "";
            }
            String name = x1.getString("name") + branchName;
            bean.setShopName(name);
            bean.setRegionName(x1.getString("regionName"));
            bean.setShopId(x1.getString("id"));
            bean.setPriceText(x1.getString("priceText"));
            JSONArray shopDealInfos = x1.getJSONArray("shopDealInfos");
            setProperties(bean, shopDealInfos);
            list.add(bean);
        });
        return list;
    }

    protected void setProperties(MassageShopBean bean, JSONArray shopDealInfos) {
        if (Objects.isNull(shopDealInfos)) {
            return;
        }
        shopDealInfos.forEach(x -> {
            JSONObject x1 = (JSONObject) x;
            int dealType = x1.getIntValue("dealType");
            // 预订
            if (3 == dealType) {
                bean.setBookable("1");
            } else if (1 == dealType) {
                //团
                bean.setDealable("1");
            }
        });

    }

    private void saveData(List<MassageShopBean> datas) {
        datas.forEach(x -> logger.info("{}", x));
    }

    String recordIndexReg = "recordCount\":\\s*(\\d+)";
    Pattern recordIndexRegPattern = Pattern.compile(recordIndexReg);

    private int getTotalRecord(String content) {
        Matcher matcher = recordIndexRegPattern.matcher(content);
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 1;
    }
    @Override
    protected void start(String[] args) {
        super.start(args);
    }

    @Override
    protected void initSqlSessionFactory(String env) {
        super.initSqlSessionFactory(env);
    }

    @Override
    protected int insert(String statement, Object parameter) {
        return super.insert(statement, parameter);
    }
}
