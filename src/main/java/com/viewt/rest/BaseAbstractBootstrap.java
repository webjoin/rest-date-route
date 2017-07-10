package com.viewt.rest;
import com.alibaba.fastjson.JSON;
import com.viewt.rest.data.util.DpCons;
import com.viewt.rest.data.util.HttpUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

/**
 * Created by Elijah on 26/1/2017.
 */
public class BaseAbstractBootstrap extends DbBootstrap {

    protected Logger logger = LogManager.getLogger(this.getClass());
    protected int timeout = 5 * 1000;
    protected long sleep = 300;

    /**
     * 用于获取基础数据
     */

    protected int categoryId;
    protected int regionId;


    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


    public Object getCellValue(Cell cell) {
        int type = cell.getCellType();
        Object value = null;
        switch (type) {
            case Cell.CELL_TYPE_FORMULA:
                value = cell.getDateCellValue();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = cell.getDateCellValue();
                } else {
                    BigDecimal big = new BigDecimal(cell.getNumericCellValue());
                    value = big.toPlainString();
                }
                break;
            case Cell.CELL_TYPE_STRING:
                value = cell.getStringCellValue();
                break;
        }
        return value;
    }

    protected <T> List<T> readExcel2Bean(Class<T> clazz, String input) {
        FileInputStream fis = null;
        Workbook wb = null;
        List<T> rsList = new ArrayList<>();
        try {
            fis = new FileInputStream(input);
            wb = new XSSFWorkbook(fis);  //2007
            Sheet s = wb.getSheetAt(0);
            Row r;
            Cell c;
            int firstRowNum = s.getFirstRowNum();
            int lastRowNum = s.getLastRowNum();
            int rownum = firstRowNum;
            r = s.getRow(rownum);
            List<String> fields = new ArrayList<>(r.getLastCellNum() + 1);
            for (short i = r.getFirstCellNum(); i < r.getLastCellNum(); i++) {
                c = r.getCell(i);
                fields.add(c.getStringCellValue());
            }
            rownum++;
            for (; rownum < lastRowNum + 1; rownum++) {
                T bean = clazz.newInstance();
                r = s.getRow(rownum);
                for (int i = 0; i < fields.size(); i++) {
                    c = r.getCell(i);
                    Object cellValue = this.getCellValue(c);
                    String fieldName = fields.get(i);
                    if ("shopId".equals(fieldName) || "id".equals(fieldName)) {
                        cellValue = Integer.parseInt(cellValue.toString());
                    }
                    setValue(bean, fieldName, cellValue);
                }
                rsList.add(bean);
            }
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                wb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rsList;
    }

    protected <T> void toExcel(List<T> list, String output) {

        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        T t = list.get(0);
        Field[] declaredFields = t.getClass().getSuperclass().getDeclaredFields();
        Field[] fields = t.getClass().getDeclaredFields();
        Field[] fields1 = Arrays.copyOf(declaredFields, declaredFields.length + fields.length);
        for (int i = 0; i < fields.length; i++) {
            fields1[declaredFields.length + i] = fields[i];
        }
        fields = fields1;
        FileOutputStream out = null;
        Workbook wb = null;
        try {
            out = new FileOutputStream(output);
            wb = new XSSFWorkbook();  //2007
            Sheet s = wb.createSheet();
            Row r;
            Cell c;
            int rownum = 0;
            r = s.createRow(0);
            for (int i = 0; i < fields.length; i++) {
                c = r.createCell(i);  ////城市	商户名称	菜系	人均消费	总预订次数	点评次数
                c.setCellValue(new XSSFRichTextString(fields[i].getName()));
            }
            rownum++;
            for (; rownum < list.size() + 1; rownum++) {
                T bean = list.get(rownum - 1);
                r = s.createRow(rownum);
                for (int i = 0; i < fields.length; i++) {
                    c = r.createCell(i);
                    try {
                        c.setCellValue(new XSSFRichTextString(String.valueOf(getValue(bean, fields[i].getName()))));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                wb.write(out);
                wb.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    protected String getMsgByException(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter printWriter = new PrintWriter(sw, true);
        e.printStackTrace(printWriter);
        String str = sw.toString();
        try {
            printWriter.close();
            sw.close();
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return str;
    }

    protected void sleep() {
        sleep(sleep);
    }

    protected void sleep(long sleep1) {
        try {
            TimeUnit.MILLISECONDS.sleep(sleep1 > 0 ? sleep1 : sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected String decodeContent(String content) {
        try {
            return URLDecoder.decode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            return content;
        }
    }

    protected String replaceCity(String path, int city) {
        return replaceCity(path, String.valueOf(city));
    }

    protected String replaceCity(String path, String title) {
        return path.replace("{cityid}", title);
    }

    protected void reloadLog4j2Configuration(int city) {
        reloadLog4j2Configuration(city, 0);
    }

    protected void reloadLog4j2Configuration(int city, int regionId) {
        String suffix = String.valueOf(city);
        if (regionId != 0) {
            suffix = String.valueOf(city).concat("_").concat(String.valueOf(regionId));
        }
        reloadLog4j2Configuration(suffix);
    }

    protected void reloadLog4j2Configuration(String city, String regionId) {
        reloadLog4j2Configuration(city.concat("_").concat(regionId));
    }


    protected <T> void setValue(T bean, String fieldName, Object value) {
        try {
            Method method = bean.getClass().getMethod(getSetMothedName(fieldName), value.getClass());
            method.invoke(bean, value);
        } catch (NoSuchMethodException e) {
            logger.error("设置值出错了。。。NoSuchMethodException.setValue({},{},{}) cause: {}",
                    bean.toString(), fieldName, value, e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error("设置值出错了。。。IllegalAccessException.setValue({},{},{}) cause: {}",
                    bean.toString(), fieldName, value, e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error("设置值出错了。。。InvocationTargetException.setValue({},{},{}) cause: {}",
                    bean.toString(), fieldName, value, e.getMessage());
        }
    }

    protected <T> Object getValue(T bean, String fieldName) {
        try {
            Method method = bean.getClass().getMethod(
                    getGetMothedName(fieldName));
            return method.invoke(bean);
        } catch (NoSuchMethodException e) {
            logger.error("获取值出错了。。。setValue({},{}) cause: {}",
                    bean.toString(), fieldName, e.getMessage());
        } catch (IllegalAccessException e) {
            logger.error("获取值出错了。。。setValue({},{}) cause: {}",
                    bean.toString(), fieldName, e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error("获取值出错了。。。setValue({},{}) cause: {}",
                    bean.toString(), fieldName, e.getMessage());
        }
        return null;
    }

    private String getSetMothedName(String field) {
        return getMothedName(field, "set");
    }

    private String getGetMothedName(String field) {
        return getMothedName(field, "get");
    }

    private String getMothedName(String field, String type) {
        char c = Character.toLowerCase(field.charAt(0));
        return type
                + field.replaceFirst(String.valueOf(c),
                String.valueOf((char) (c - 32)));
    }


    protected void reloadLog4j2Configuration(String suffix) {
        System.setProperty("city", suffix);
        ((org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false)).reconfigure();
    }

    protected String get(String url) {
        return get(url, false);
    }

    /**
     * 把制定的文件内容一行地读进List中
     *
     * @param path
     * @return
     */
    protected List<String> readFile(String path) {
        return readFile(path, null);
    }

    protected List<String> readFile(String path, String prefix) {
        List<String> list = new ArrayList<>();
        InputStreamReader fr = null;
        BufferedReader br = null;
        try {
            fr = new InputStreamReader(new FileInputStream(path), "utf-8");
            br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (prefix == null) {
                    list.add(line);
                } else {
                    int ix = line.indexOf(prefix);
                    if (ix > -1) {
                        list.add(line.substring(ix));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    static int stastic = 0;
    static long sStastic = 0;

    private void plus() {
        if (stastic == 0) sStastic = System.currentTimeMillis();
        stastic++;


        long val = (System.currentTimeMillis() - sStastic) / 1000;  //s
        if (val > 1) {
            logger.info("--->>-->>>>" + stastic + "/" + val + "     " + (((double) stastic) / val) + "/1s");
        }

    }

    protected String get(String url, boolean isJson) {
        return req(url, isJson,1,null);
    }

    protected String post(String url, String formData) {
        return req(url, false,2,formData);
    }
    private String req(String url, boolean isJson,int method,String formData) {
        plus();
        int flag = 10;
        String resp = null;
        boolean hasException = false;
        do {
            try {
                sleep();
                if (hasException) { //没有异常则需要睡眠
                    sleep(1 * 1000);
                }
                if ( 1== method){
                    resp = HttpUtil.get(url, timeout);    //httpclient
                }else{
                    resp = HttpUtil.postForm(url,formData);
                }
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
                        handle403ForCookie();
                        throw new RuntimeException("403 Forbidden");
                    } else {
                        hasException = false;
                    }
                }
                if (isJson) {
                    try {
                        JSON.parseObject(resp);
                        break;
                    } catch (Exception e) {

                    }
                } else {
                    break;
                }
            } catch (Exception e) {
                hasException = true;
                if (StringUtils.isNotEmpty(resp))
                    logger.info("seq {} url:[{}] break - {} - {}", flag--, url, e.getMessage(), resp.substring(0, 10));
                else {
                    logger.info("seq {} url:[{}] break - {}", flag--, url, e.getMessage());
                }
                sleep();
            } finally {
                if (!hasException) {
                    logger.info("url:{} ctx:{}", url, resp.substring(0, 10));
                }
            }
        } while (flag >= 0);
        if (null == resp) resp = "";
        return resp;
    }

    private void handle403ForCookie() {
        try {
            logger.info("sleeping 10s");
            TimeUnit.SECONDS.sleep(10);
            logger.info("sleeping 10s pass");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.get(DpCons.BASE_URL);
    }

    long timestamp1 = 0;

    private void addTimestamp1() {
        if (timestamp1 == 0) {
            timestamp1 = new Date().getTime();
        }
        timestamp1++;
    }

    public long getTimestamp1() {
        if (timestamp1 == 0) {
            timestamp1 = new Date().getTime();
        }
        return timestamp1;
    }

    protected void setUptime() {

        Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r, "timer-sec");
                t.setDaemon(true);
                return t;
            }

        }).scheduleAtFixedRate(new Runnable() {
            public void run() {
                addTimestamp1();
            }
        }, 1, 1, TimeUnit.SECONDS);
    }


    /**
     * @param cityid
     * @return
     */
    protected String geSearchtUrl(int cityid) {
        return geSearchtUrl1("0", cityid + "", "10", "0");
    }

    //http://mapi.dianping.com/searchshop.json?start={start}&regionid={regionid}&categoryid={categoryid}&sortid=0&locatecityid={cityid}&cityid={cityid}&_={timestamp}&callback=Zepto{timestamp1}";
    protected String geSearchtUrl1(String start, String cityid, String categoryId, String regionId) {
        long timestamp = new Date().getTime();
        String rsurl = "url1";
        rsurl = rsurl.replace("{start}", String.valueOf(start));
        rsurl = rsurl.replace("{categoryId}", String.valueOf(categoryId));
        rsurl = rsurl.replace("{regionId}", String.valueOf(regionId));
        rsurl = rsurl.replaceAll("\\{cityid\\}", String.valueOf(cityid));
        rsurl = rsurl.replace("{timestamp}", String.valueOf(timestamp));
        rsurl = rsurl.replace("{timestamp1}", String.valueOf(getTimestamp1()));
        rsurl = rsurl.replace("{timestamp1}", String.valueOf(getTimestamp1()));
        return rsurl;
    }

    protected String getPlainText(String value) {
        if (StringUtils.isNotEmpty(value)) {
            if (value.contains("<")) {
                value = value.replaceAll("<[^<]+>", " ");
            }
            if (value.contains("&")) {
                value = value.replaceAll("&[^<]+;", " ");
            }
            if (value.contains("  ")) {
                value = value.replaceAll("\\s{16}", " ");
            }
            value = value.replaceAll("\\t", " ");
            value = value.trim();
        }
        return value;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
}
