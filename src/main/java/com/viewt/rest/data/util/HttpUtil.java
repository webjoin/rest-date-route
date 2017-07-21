package com.viewt.rest.data.util;
import com.alibaba.fastjson.JSON;
import com.viewt.rest.data.bean.RespBean;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.*;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * http请求工具类(可发起get或post请求)
 */
public class HttpUtil {

    private static Logger logger = LogManager.getLogger(HttpUtil.class);

    private static int TIME_OUT = 10000;//默认10秒超时

//    private static JsonUtil jsonUtil = new FastJsonUtil();

    private HttpUtil() {
        //工具类无需对象实例化
    }

    private static String[] userAgents = {
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/601.5.17 (KHTML, like Gecko) Version/9.1 Safari/601.5.17",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:46.0) Gecko/20100101 Firefox/46.0",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36",
            "Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2618.8 Safari/537.36",
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_4) AppleWebKit/601.5.17 (KHTML, like Gecko) Version/9.1 Safari/601.5.17",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.11; rv:53.0) Gecko/20100101 Firefox/53.0",
            "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1",
            "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Mobile Safari/537.36",
            "Mozilla/5.0 (iPad; CPU OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1"
    };
    private static Random random = new Random();

    private static boolean setProxy(String proxyHost, Integer proxyPort, HttpRequestBase httpRequestBase) {
        return setProxy(proxyHost, proxyPort, httpRequestBase, TIME_OUT);
    }

    private static boolean setProxy(String proxyHost, Integer proxyPort, HttpRequestBase httpRequestBase, int timeout) {
        boolean isViaProxy = false;
        if (!StringUtils.isEmpty(proxyHost) && proxyHost != null) {
            HttpHost proxy = new HttpHost(proxyHost, proxyPort);
//            HttpClientParams.setCookiePolicy(httpClient.getParams(), CookiePolicy.BROWSER_COMPATIBILITY);
            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            httpRequestBase.setConfig(config);
            isViaProxy = true;
        } else {
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .setCookieSpec(CookieSpecs.IGNORE_COOKIES)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            httpRequestBase.setConfig(config);
        }
        return isViaProxy;
    }

    public static String get(String url, String encoding, String proxyHost, Integer proxyPort) {
        return get(url, encoding, proxyHost, proxyPort, TIME_OUT);
    }

    public static String get(String url, String encoding, String proxyHost, Integer proxyPort, int timeoutMiniSeconds) {
        return get(url, encoding, proxyHost, proxyPort, TIME_OUT, null);
    }

    public static String get(String url, String encoding, String proxyHost, Integer proxyPort, int timeoutMiniSeconds, String cookies) {
        String response = null;
        RequestConfig customizedRequestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
        CloseableHttpClient client = HttpClients.custom().setDefaultRequestConfig(customizedRequestConfig).build();
        HttpGet httpGet = new HttpGet(url);
        if (StringUtils.isNotEmpty(cookies))
            httpGet.addHeader("Cookie", cookies);
        httpGet.addHeader("Cookie", DpThreadContext.getThreadCookie());
        httpGet.addHeader("User-Agent", userAgents[random.nextInt(userAgents.length)]);
        boolean isViaProxy = setProxy(proxyHost, proxyPort, httpGet, timeoutMiniSeconds);
        try {
            CloseableHttpResponse httpResponse = client.execute(httpGet);
            try {
                logger.debug(httpResponse.getStatusLine() + " , " + url + (isViaProxy ? " , via:" + proxyHost + ":" + proxyPort : ""));
                response = EntityUtils.toString(httpResponse.getEntity(), encoding);
                setCookies(url, httpResponse);
                logger.debug(response);
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            exceptionHandle(e, url, timeoutMiniSeconds);
        } finally {
            try {
//            		httpGet.releaseConnection();
                client.close();
            } catch (Exception e) {
                exceptionHandle(e, url, timeoutMiniSeconds);
            }
        }
        return response;
    }

    private static void setCookies(String url, CloseableHttpResponse httpResponse) {
        if (DpCons.BASE_URL.equals(url)) {
            Header[] allHeaders = httpResponse.getAllHeaders();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < allHeaders.length; i++) {
//                        System.out.println("response headers " + allHeaders[i].getName() + ":" + allHeaders[i].getValue());
                if ("Set-Cookie".equals(allHeaders[i].getName())) {
                    String value = allHeaders[i].getValue();
                    int i1 = value.indexOf("=");
                    if (i1 <= -1) continue;
                    int i2 = value.indexOf(";");
                    String key = value.substring(0, i1);
                    switch (key) {
                        case "JSESSIONID":
                        case "cy":
                        case "cye":
                            String val = value.substring(i1 + 1, i2);
                            if (sb.length() > 0) {
                                sb.append(" ");
                            }
                            sb.append(key).append("=").append(val).append(";");
                            break;
                        default:
                            break;
                    }

                }
            }
            if (sb.length() > 0) {
                DpThreadContext.setThreadCookie(sb.toString());
            }
        }
    }

    /**
     * 以get方式请求指定url,并返回headers数组
     *
     * @param url
     * @param proxyHost
     * @param proxyPort
     * @return
     */
    public static Header[] get(String url, String proxyHost, Integer proxyPort) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        setProxy(proxyHost, proxyPort, httpGet);
        try {
            CloseableHttpResponse httpResponse = client.execute(httpGet);

            try {
                Header[] headers = httpResponse.getAllHeaders();
                return headers;
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            exceptionHandle(e, url, TIME_OUT);
        } finally {
            try {
                client.close();
            } catch (Exception e) {
                exceptionHandle(e, url, TIME_OUT);
            }
        }
        return new Header[0];
    }

    /**
     * 以get方式请求指定url,并返回headers数组
     *
     * @param url
     * @return
     */



    public static String get(String url, int timeoutMiniSeconds) {
        return get(url, "utf-8", null, null, timeoutMiniSeconds);
    }



    public static String post(String url, String postData, String mediaType, String encoding,
                              Header[] headers, String proxyHost, Integer proxyPort) {
        return post(url, postData, mediaType, encoding,
                headers, proxyHost, proxyPort, TIME_OUT);
    }

    public static String post(String url, String postData, String mediaType, String encoding,
                              Header[] headers, String proxyHost, Integer proxyPort, int timeoutMiniSeconds) {
        String response = null;
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        boolean isViaProxy = setProxy(proxyHost, proxyPort, httpPost, timeoutMiniSeconds);
        try {
            StringEntity entity = new StringEntity(postData, encoding);
            entity.setContentType(mediaType);
            httpPost.setEntity(entity);
            if (headers != null && headers.length > 0) {
                httpPost.setHeaders(headers);
            }
            CloseableHttpResponse httpResponse = client.execute(httpPost);
            try {
                logger.debug(httpResponse.getStatusLine().getStatusCode() + " " + url + (isViaProxy ? " via:" + proxyHost + ":" + proxyPort : ""));
                byte[] bytes = EntityUtils.toByteArray(httpResponse.getEntity());
                response = new String(bytes, encoding);
                logger.debug(response);
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
//            exceptionHandle(e, url, postData, timeoutMiniSeconds);
        } finally {
            try {
                client.close();
            } catch (Exception e) {
//                exceptionHandle(e, url, postData, timeoutMiniSeconds);
            }
        }
        return response;

    }

    public static RespBean sGetAuth(String url, String cookie, List<NameValuePair> nvps) {
        return doHttps(url, cookie, nvps, "GET");
    }

    public static RespBean sPostAuth(String url, String cookie, List<NameValuePair> nvps) {
        return doHttps(url, cookie, nvps, "POST");
    }

    public static String sGet(String url, String cookie, List<NameValuePair> nvps) {
        return doHttps(url, cookie, nvps, "GET").getContent();
    }

    public static String sPost(String url, String cookie, List<NameValuePair> nvps) {
        return doHttps(url, cookie, nvps, "POST").getContent();
    }

    public static RespBean doHttps(String url, String cookie, List<NameValuePair> nvps, String httpMethod) {
        RequestConfig config = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD_STRICT)
                .setExpectContinueEnabled(true)
                .setTargetPreferredAuthSchemes(Arrays.asList(AuthSchemes.NTLM, AuthSchemes.DIGEST))
                .setProxyPreferredAuthSchemes(Arrays.asList(AuthSchemes.BASIC))
                .build();
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", sslConnectionSocketFactory)
                .build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config)
                .build();
        String response;
//        int timeoutMiniSeconds = 10*1000;
        RespBean bean = new RespBean();
        Map<String, String> cookies = new HashMap<>();
        HttpRequestBase httpRequestBase = null;
        if ("POST".equals(httpMethod)) {
            HttpPost httpPost = new HttpPost(url);
            if (CollectionUtils.isNotEmpty(nvps)) {
                HttpEntity entity = new UrlEncodedFormEntity(nvps, Consts.UTF_8);
                httpPost.setEntity(entity);
            }
            httpPost.setHeader("accept", "application/json");
            httpPost.setHeader("x-requested-with", "XMLHttpRequest");
            httpRequestBase = httpPost;
        } else {
            httpRequestBase = new HttpGet(url);
        }
        httpRequestBase.addHeader("User-Agent", userAgents[random.nextInt(userAgents.length)]);
        if (StringUtils.isNotEmpty(cookie)) {
            httpRequestBase.addHeader("Cookie", cookie);
        }
        try {
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequestBase);
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
//                System.out.println( allHeaders[i].getName()+":"+allHeaders[i].getValue());
//                allHeaders[i].getName()
            }
            try {
                response = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                bean.setContent(response);
                bean.setCookies(cookies);
                logger.debug(response);
            } finally {
                httpResponse.close();
            }
        } catch (Exception e) {
            exceptionHandle(e, url, TIME_OUT);
        } finally {
            try {
//                httpRequestBase.releaseConnection();
                httpClient.close();
            } catch (Exception e) {
                exceptionHandle(e, url, TIME_OUT);
            }
        }
        return bean;
    }

    private static TrustManager manager = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
//            return new X509Certificate[0];
            return null;
        }
    };

    static {
        SSLContext context;
        try {
            context = SSLContext.getInstance("TLS");
            context.init(null, new TrustManager[]{manager}, null);
            sslConnectionSocketFactory = new SSLConnectionSocketFactory(context, NoopHostnameVerifier.INSTANCE);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
    }

    private static SSLConnectionSocketFactory sslConnectionSocketFactory;


    public static String postJSON(String url) {
        return post(url, "", "application/json");
    }
    public static String postForm(String url,String postData) {
        return post(url, postData, "application/x-www-form-urlencoded");
    }

    public static String post(String url, String postData, int timeoutMiniSeconds) {
        return post(url, postData, "application/json", "utf-8", null, null, null, timeoutMiniSeconds);
    }

    public static String post(String url, String postData, String mediaType) {
        return post(url, postData, mediaType, "utf-8", null, null, null);
    }

    public static String post(String url, String postData, Header[] header, String mediaType) {
        return post(url, postData, mediaType, "utf-8", header, null, null);
    }

    public static String post(String url, String postData, Header[] header, String mediaType, int timeoutMiniSeconds) {
        return post(url, postData, mediaType, "utf-8", header, null, null, timeoutMiniSeconds);
    }

    private static void exceptionHandle(Exception e, String url, int timeOut) {
//        e.printStackTrace();
        throw new RuntimeException("invoke fail server address : " + url + ", timeout : " + timeOut + " , type : "
                + e.getClass() + " , cause : " + e.getMessage());
    }

//    private static void exceptionHandle(Exception e, String url, String postData, int timeOut) {
//        e.printStackTrace();
//        throw new RuntimeException("调用服务失败，服务地址：" + url + "，请求参数：" + postData + "，超时时间：" + timeOut + "，异常类型："
//                + e.getClass() + "，错误原因：" + e.getMessage());
//    }

//    private static void exceptionHandle(Exception e, String url, List<NameValuePair> pairs, int timeOut) {
//        e.printStackTrace();
//        throw new RuntimeException("调用服务失败，服务地址：" + url + "，请求参数：" + JSON.toJSONString(pairs) + "，超时时间：" + timeOut + ",异常类型："
//                + e.getClass() + "，错误原因：" + e.getMessage());
//    }

    /**
     * 模拟form提交
     *
     * @param url
     * @param urlParameters
     * @return
     * @throws IOException
     */
    public static String doFormPost(String url, List<NameValuePair> urlParameters, int timeout) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();
        post.setConfig(config);
        post.setEntity(new UrlEncodedFormEntity(urlParameters));
        StringBuffer result = new StringBuffer();
        try {
            HttpResponse response = client.execute(post);
            System.out.println("Response Code : "
                    + response.getStatusLine().getStatusCode());
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
//            exceptionHandle(e, url, urlParameters, timeout);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    logger.error("client close with error", e);
                }
            }
        }
        return result.toString();
    }

    public static String doFormPost(String url, List<NameValuePair> urlParameters) throws IOException {
        return doFormPost(url, urlParameters, TIME_OUT);
    }

    public static String doFormPost(String url, List<NameValuePair> params, String mediaType, Header[] headers) throws IOException {
        return doFormPost(url, params, mediaType, "utf-8", headers);
    }

    public static String doFormPost(String url, List<NameValuePair> params, String mediaType, String encoding, Header[] headers) throws IOException {
        return doFormPost(url, params, mediaType, encoding, headers, TIME_OUT);
    }

    public static String doFormPost(String url, List<NameValuePair> params, String mediaType, String encoding,
                                    Header[] headers, int timeout) throws IOException {
        CloseableHttpClient client = null;
        String result = "";
        try {
            client = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
            RequestConfig config = RequestConfig.custom()
                    .setConnectTimeout(timeout)
                    .setSocketTimeout(timeout)
                    .setConnectionRequestTimeout(timeout)
                    .build();
            HttpPost post = new HttpPost(url);
            post.setConfig(config);
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, encoding);
            formEntity.setContentType(mediaType);
            post.setEntity(formEntity);
            if (headers != null && headers.length > 0) {
                post.setHeaders(headers);
            }
            CloseableHttpResponse resp = client.execute(post);
            try {
                if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    result = EntityUtils.toString(resp.getEntity(), encoding);
                } else {
                    throw new RuntimeException("from remote server receive status is " + resp.getStatusLine().getStatusCode() + " , url=>" + url + ",params=>" + JSON.toJSONString(params));
                }
            } finally {
                resp.close();
            }
        } catch (Exception e) {
            exceptionHandle(e, url, timeout);
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    logger.error("client close with error", e);
                }
            }

        }
        return result;
    }

}
