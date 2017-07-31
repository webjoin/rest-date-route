package com.viewt.rest.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.viewt.rest.data.bean.RespBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elijah on 9/7/2017.
 */
public class AbstractService {
    protected org.slf4j.Logger logger = LoggerFactory.getLogger(UrlServiceImpl.class);

    protected static String encoding = "utf-8";
    protected static String userAgent = "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1";
    // 代理服务器
    final static String proxyHost = "proxy.abuyun.com";
    final static Integer proxyPort = 9020;
    // 代理隧道验证信息
    final static String proxyUser = "HJ9W1Y72W50MHNID";
    final static String proxyPass = "08E1EA3749F6003F";

    // IP切换协议头
    final static String switchIpHeaderKey = "Proxy-Switch-Ip";
    final static String switchIpHeaderVal = "yes";

    public RespBean get(String url,
                        String encoding,
                        Map<String, String> reqHeader) {
        return getRespBean("get", url, null, reqHeader);
    }

//    private static PoolingHttpClientConnectionManager cm = null;
//    private static HttpHost proxy = null;

    //    private static CredentialsProvider credsProvider = null;
//    private static RequestConfig reqConfig = null;
    protected static int timeout = 5 * 1000;

//    static {
//        ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
//        LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
//
//        Registry registry = RegistryBuilder.create()
//                .register("http", plainsf)
//                .register("https", sslsf)
//                .build();
//
//        cm = new PoolingHttpClientConnectionManager(registry);
//        cm.setMaxTotal(20);
//        cm.setDefaultMaxPerRoute(5);
//
//        proxy = new HttpHost(proxyHost, proxyPort, "http");
//
//        credsProvider = new BasicCredentialsProvider();
//        credsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(proxyUser, proxyPass));
//
//        reqConfig = RequestConfig.custom()
//                .setConnectionRequestTimeout(timeout)
//                .setConnectTimeout(timeout)
//                .setSocketTimeout(timeout)
//                .setExpectContinueEnabled(false)
//                .setProxy(new HttpHost(proxyHost, proxyPort))
//                .build();
//    }

//    private RespBean doRequest(HttpRequestBase httpReq, String cookie) {
//        CloseableHttpClient httpClient = null;
//        CloseableHttpResponse httpResp = null;
//        RespBean respBean = new RespBean();
//        try {
//            httpReq.setHeader("Accept-Encoding", null);
//            httpReq.setHeader(switchIpHeaderKey, switchIpHeaderVal);
//            if (StringUtils.isNotEmpty(cookie))
//                httpReq.setHeader("Cookie", cookie);
//            httpReq.setHeader("User-Agent", userAgent);
//
//
//            httpReq.setConfig(reqConfig);
//
//            httpClient = HttpClients.custom()
//                    .setConnectionManager(cm)
//                    .setDefaultCredentialsProvider(credsProvider)
//                    .build();
//
//            AuthCache authCache = new BasicAuthCache();
//            authCache.put(proxy, new BasicScheme());
//
//            HttpClientContext localContext = HttpClientContext.create();
//            localContext.setAuthCache(authCache);
//
//            httpResp = httpClient.execute(httpReq, localContext);
//
////            int statusCode = httpResp.getStatusLine().getStatusCode();
////            System.out.println(statusCode);
//            parseHttpResponse(respBean, httpResp);
//        } catch (Exception e) {
//            exceptionHandle(e, httpReq.getURI().getPath(), timeout);
//        } finally {
//            try {
//                if (httpResp != null) {
//                    httpResp.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            try {
//                if (httpClient != null) {
//                    httpClient.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return respBean;
//    }


//    protected RespBean doPostRequest(String url, Map<String, String> reqParamMap, String cookie) throws Exception {
//        HttpPost httpPost;
//        try {
//            // 要访问的目标页面
//            httpPost = new HttpPost(url);
//
//            // 设置表单参数
//            List<BasicNameValuePair> params = new ArrayList();
//            Set<Map.Entry<String, String>> entries = reqParamMap.entrySet();
//            for (Map.Entry<String, String> entry : entries) {
//                params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
//            }
//            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, encoding);
//            httpPost.setEntity(entity);
//            return doRequest(httpPost, cookie);
//        } catch (Exception e) {
//            throw e;
//        }
//    }

//    protected RespBean doGetRequest(String url, String cookie) {
//        // 要访问的目标页面
//        try {
//            HttpGet httpGet = new HttpGet(url);
//            return doRequest(httpGet, cookie);
//        } catch (Exception e) {
//            throw e;
//        }
//    }

    private RespBean get(String url,
                         Map<String, String> reqHeader) {
        RespBean respBean = new RespBean();
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .setCookieSpec(CookieSpecs.STANDARD)
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
            exceptionHandle(e, url, timeout);
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
            logger.error("testing ... : {}", httpResponse.getStatusLine().getStatusCode());
        } finally {
            httpResponse.close();
        }
    }


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
            logger.error("--->>-->>>>" + stastic + "/" + val + "     " + (((double) stastic) / val) + "/1s");
        }

    }

    public RespBean post(String url,
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
        String resp = "";
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
                        handle403ForCookie(url);
                        throw new RuntimeException("403 Forbidden");
                    } else if (resp.contains("该页面暂时无法访问，请稍后再试")) {
                        sleep(timeout);
                        throw new RuntimeException("该页面暂时无法访问，请稍后再试  应该是被美团封IP了 请切换IP");
                    } else if (resp.contains("Operation timed out")) {
                        throw new RuntimeException("Operation timed out");
                    } else if (resp.contains("操作太频繁啦")) {
                        sleep(1000);
                        throw new RuntimeException("操作太频繁啦");
                    } else {
                        hasException = false;
                    }
                } else {
                    logger.error("req {} is empty", url);
                    resp = "";
                }
                break;//正常情况下直接退出
            } catch (Exception e) {
                hasException = true;
                if (StringUtils.isNotEmpty(resp)) {
                    String val;
                    if (resp.length() > 10) val = resp.substring(0, 10);
                    else {
                        val = resp;
                    }
                    logger.error("seq {} url:[{}] break - {} - {}", flag--, url, e.getMessage(), val);
                } else {
                    logger.error("seq {} url:[{}] break - {}", flag--, url, e.getMessage());
                }
            } finally {
                if (!hasException) {
                    String val;
                    if (resp.length() > 10) val = resp.substring(0, 10);
                    else {
                        val = resp;
                    }
                    logger.error("url:{} ctx:{}", url, val);
                }
            }
        } while (flag >= 0);
        if (null == resp) resp = "";
        return respBean;
    }

    private void handle403ForCookie(String url) {
        if (url.contains("dianping.com")){
//            try {
//                logger.info("sleeping 10s");
//                TimeUnit.SECONDS.sleep(10);
//                logger.info("sleeping 10s pass");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            this.get(DpCons.BASE_URL);
        }
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
            exceptionHandle(e, url, timeout);
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
