package com.viewt.rest.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.viewt.rest.data.bean.RespBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elijah on 9/7/2017.
 */
public class AbstractService {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

    protected String encoding = "utf-8";
    protected String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";

    public RespBean get(String url,
                        String encoding,
                        Map<String, String> reqHeader) {
        return getRespBean("get", url, null, reqHeader);
    }

    private RespBean get(String url,
                         Map<String, String> reqHeader) {
        RespBean respBean = new RespBean();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .setCookieSpec(CookieSpecs.DEFAULT)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            httpGet.setConfig(config);

            if (null != reqHeader) {
                Set<Map.Entry<String, String>> entries = reqHeader.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    httpGet.setHeader(entry.getKey(), entry.getValue());
                }
            }
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            parseHttpResponse(respBean, httpResponse);
        } catch (Exception e) {
            exceptionHandle(e,url,timeout);
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return respBean;
    }
    private static void exceptionHandle(Exception e, String url, int timeOut) {
        throw new RuntimeException("invoke fail server address : " + url + ", timeout : " + timeOut + " , type : " + e.getClass() + " , cause : " + e.getMessage());
    }
    private void parseHttpResponse(RespBean respBean, CloseableHttpResponse httpResponse) throws IOException {
        String response;
        try {
            Map<String, String> cookies = new HashMap<>();
            Header[] allHeaders = httpResponse.getAllHeaders();
            for (int i = 0; i < allHeaders.length; i++) {
                if ("Set-Cookie".equals(allHeaders[i].getName())) {
                    String value = allHeaders[i].getValue();
                    int i1 = value.indexOf("=");
                    int i2 = value.indexOf(";");
                    String key = value.substring(0, i1);
                    String val = value.substring(i1 + 1, i2);
                    cookies.put(key, val);
                }
            }
            response = EntityUtils.toString(httpResponse.getEntity(), encoding);
            respBean.setContent(response);
            respBean.setCookies(cookies);
        } finally {
            httpResponse.close();
        }
    }
    protected int timeout = 5 * 1000;
    protected long sleep = 500;

    protected void sleep(long sleep1) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleep1 > 0 ? sleep1 : sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static int stastic = 0;
    static long sStastic = 0;

    private void sleep() {
        sleep(sleep);
    }

    private void plus() {
        if (stastic == 0) sStastic = System.currentTimeMillis();
        stastic++;


        long val = (System.currentTimeMillis() - sStastic) / 1000;  //s
        if (val > 1) {
            logger.info("--->>-->>>>" + stastic + "/" + val + "     " + (((double) stastic) / val) + "/1s");
        }

    }

    protected RespBean post(String url,
                            Map<String, String> formMap,
                            String encoding,
                            Map<String, String> reqHeader) {

        return getRespBean("post", url, formMap, reqHeader);
    }

    private RespBean getRespBean(String method, String url,
                                 Map<String, String> formMap, /* if get , is null */
                                 Map<String, String> reqHeader) {
        plus();
        int flag = 10;
        String resp = null;
        boolean hasException = false;
        RespBean respBean = null;
        do {
            try {
                sleep();
                if (hasException) { //没有异常则需要睡眠
                    sleep(1 * 1000);
                }
                if ("post".equals(method)) {
                    respBean = post(url, formMap, reqHeader);    //httpclient
                } else {
                    respBean = get(url, reqHeader);    //httpclient
                }
                resp = respBean.getContent();
                if (StringUtils.isNotEmpty(resp)) {
                    if (resp.contains("502 Bad Gateway")) {
                        throw new RuntimeException("502 Bad Gateway");
                    } else if (resp.contains("您使用的IP访问网站过于频繁")) {
                        flag++;
                        throw new RuntimeException("your ip is frequence ");
                    } else if (resp.contains("暂无此类团购")) {
                        throw new RuntimeException("the kind is no here");
                    } else if (resp.contains("#5e6265;\">关闭</span>")) {
                        throw new RuntimeException("#5e6265;\">close</span>");
                    } else if (resp.contains("继续访问触屏版")) {
                        flag = 10;
                        throw new RuntimeException("continue to visit plat screen");
                    } else if (resp.contains("forbidden")) {
                        sleep(1 * 1000);
                        throw new RuntimeException("forbidden");
                    } else if (resp.contains("403 Forbidden")) {
//                        handle403ForCookie();
                        throw new RuntimeException("403 Forbidden");
                    }else if (resp.contains("该页面暂时无法访问，请稍后再试")){
                        sleep(timeout);
                        throw new RuntimeException("该页面暂时无法访问，请稍后再试  应该是被美团封IP了 请切换IP");
                    }else if(resp.contains("Operation timed out")){
                        throw new RuntimeException("Operation timed out");
                    }else {
                        hasException = false;
                    }
                }
                break;//正常情况下直接退出
            } catch (Exception e) {
                hasException = true;
                if (StringUtils.isNotEmpty(resp))
                    logger.error("seq {} url:[{}] break - {} - {}", flag--, url, e.getMessage(), resp.substring(0, 10));
                else {
                    logger.error("seq {} url:[{}] break - {}", flag--, url, e.getMessage());
                }
            } finally {
                if (!hasException) {
                    logger.error("url:{} ctx:{}", url, resp.substring(0, 10));
                }
            }
        } while (flag >= 0);
        if (null == resp) resp = "";
        return respBean;
    }

    private RespBean post(String url,
                          Map<String, String> formMap,
//                                String encoding,
                          Map<String, String> reqHeader) {
        RespBean respBean = new RespBean();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        try {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .setCookieSpec(CookieSpecs.DEFAULT)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            httpPost.setConfig(config);
            List<NameValuePair> list = new ArrayList<>();
            Iterator iterator = formMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, encoding);
                httpPost.setEntity(entity);
            }
            if (null != reqHeader) {
                Set<Map.Entry<String, String>> entries = reqHeader.entrySet();
                for (Map.Entry<String, String> entry : entries) {
                    httpPost.setHeader(entry.getKey(), entry.getValue());
                }
            }
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            parseHttpResponse(respBean, httpResponse);
        } catch (Exception e) {
            exceptionHandle(e,url,timeout);
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return respBean;
    }
}
