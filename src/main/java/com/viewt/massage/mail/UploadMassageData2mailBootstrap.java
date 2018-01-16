package com.viewt.massage.mail;

import com.sun.mail.util.MailSSLSocketFactory;
import com.viewt.massage.MassageBootstrap;
import com.viewt.rest.data.bean.MassageActivityBean;
import com.viewt.rest.data.bean.MassageDataBean;
import com.viewt.rest.data.bean.MassageDataMailBean;
import com.viewt.rest.data.bean.MassageShopBean;
import com.viewt.rest.data.util.Cons;
import com.viewt.rest.data.util.DateUtil;
import com.viewt.rest.data.util.FileUtil;
import com.viewt.rest.data.util.Massage;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author tangyu
 * @since 2018-01-15 19:43
 */
public class UploadMassageData2mailBootstrap {
    private static Logger logger = LoggerFactory.getLogger(UploadMassageData2mailBootstrap.class);

    public static void main(String[] args) {

        String logsDirPattern = Cons.USER_DIR + "/logs/{0}/{1}/";//20180114/hour-massage-activety-detail-2018011401-1.log.gz
        String pattern = "yyyyMMdd";
        String gzSuffix = ".log.gz";
        String excelSuffix = ".xlsx";

        Properties prop = MassageBootstrap.loadConf(MassageBootstrap.getConfFile());
        String specialDate = prop.getProperty(Cons.Job.JOB_MASSAGE_DATA_DATE);

        String yesterday = Optional.ofNullable(specialDate).orElseGet(() -> LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern(pattern)));


        String logsDir = MessageFormat.format(logsDirPattern, "other", yesterday);
        String logsDataDir = MessageFormat.format(logsDirPattern, "data", yesterday);
        File fileDir = Paths.get(logsDir).toFile();
        if (fileDir.isDirectory()) {
            File[] files = fileDir.listFiles();
            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                String name = file.getName();
                if (name.endsWith(gzSuffix)) {
                    unzipFile(file, logsDataDir);
                }
            }
            String excelFilePath = logsDataDir + yesterday + excelSuffix;
            generateExcelFile(excelFilePath, executeData(logsDataDir));
//            sendMail4Data(excelFilePath);
        }


    }

    private static void generateExcelFile(String excelFilePath, MassageDataMailBean dataBean) {
        Class<?> clazz = dataBean.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Workbook wb = new XSSFWorkbook();  //2007
        for (Field field : fields) {
            Massage annotation = field.getAnnotation(Massage.class);
            String sheetName = annotation.value();
            logger.error("-------->" + sheetName);
            Sheet sheet = wb.createSheet(sheetName);
            try {
                field.setAccessible(true);
                Object listValue = field.get(dataBean);
                if (listValue instanceof Collection) {
                    Collection listValue1 = (Collection) listValue;
                    Iterator iterator = listValue1.iterator();
                    int rowIdx = 0;
                    Row firstow = sheet.createRow(rowIdx++);
                    while (iterator.hasNext()) {  // 一行一行的数据
                        Object next = iterator.next();
                        Class<?> aClass = next.getClass();
                        Field[] fields1 = aClass.getDeclaredFields();
                        Row row = sheet.createRow(rowIdx++);
                        for (int i = 0; i < fields1.length; i++) { // 一个一个cell
                            Field field1 = fields1[i];
                            field1.setAccessible(true);
                            Massage annotation1 = field1.getAnnotation(Massage.class);
                            String titleName = annotation1.value();
                            if (firstow.getLastCellNum() < fields1.length) {
                                firstow.createCell(i).setCellValue(titleName);
                            }
                            Object value = field1.get(next);
                            row.createCell(i).setCellValue(Objects.toString(value, ""));
                        }
                    }
                }
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage(), e);
            }
        }
        try {
            wb.write(new FileOutputStream(excelFilePath));
            wb.close();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }


    /**
     * 解压文件
     *
     * @param file
     */
    private static void unzipFile(File file, String logsDataDir) {
        try {
            FileUtil.unGzip(file, logsDataDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String COMMA = ",";
    static String LINE = "-";

    /**
     * 执行数据文件
     *
     * @param logsDataDir
     */
    private static MassageDataMailBean executeData(String logsDataDir) {

        MassageDataMailBean mailBean = new MassageDataMailBean();


        String day = "day";
        String hour = "hour";
        String shopDetail = "shop-detail";
        String activityDetail = "activety-detail";
        String shopData = "shop-data";
        String activityData = "activety-data";

        File fileDir = Paths.get(logsDataDir).toFile();
        File[] files = fileDir.listFiles();
        Map<String, MassageShopBean> shopBeanMap = new HashMap<>();
        Map<String, MassageActivityBean> activityBeanMap = new HashMap<>();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String name = file.getName();
            // shop & activity  基础数据
            if (name.startsWith(day)) {
                //split.length = 7
//                点评门店id, 店名, 人均, 商圈, 是否支持预订, 是否支持团购, 时间
                if (name.contains(shopData)) {
                    fetchDayShopData(file, shopBeanMap);
                    //split.length = 4
//                  活动ID,品牌名称,采集地址,日期
                } else if (name.contains(activityData)) {
                    fetchDayActivityData(file, activityBeanMap);
                }
            }
        }
        Map<String, MassageDataBean> massageActivityDataBeanMap = new HashMap<>();
        Map<String, MassageDataBean> massageShopDataBeanMap = new HashMap<>();
        for (File file : files) {
            String name = file.getName();
            if (name.startsWith(hour)) {
                //团购ID,已购买人次,序号,时间
                if (name.contains(shopDetail)) {
                    fetchHourDetailData(file, massageShopDataBeanMap);
                } else if (name.contains(activityDetail)) {
                    fetchHourDetailData(file, massageActivityDataBeanMap);
                }
            }
        }
//        fillMailbean(shopBeanMap,massageShopDataBeanMap,activityBeanMap,massageActivityDataBeanMap);
        massageShopDataBeanMap.forEach((k, v) -> {
            MassageShopBean shopBean = shopBeanMap.get(k);
            if (Objects.nonNull(shopBean)) {
                v.setName(shopBean.getShopName());
                v.setDate(shopBean.getDate().toString());
            }
        });
        massageActivityDataBeanMap.forEach((k, v) -> {
            MassageActivityBean activityBean = activityBeanMap.get(k);
            if (Objects.nonNull(activityBean)) {
                v.setName(activityBean.getBrandName());
                v.setDate(activityBean.getDate().toString());
            }
        });


        mailBean.setMassageShopBeans(shopBeanMap.values());
        mailBean.setMassageShopDataBeans(massageShopDataBeanMap.values());

        mailBean.setMassageActivityBeans(activityBeanMap.values());
        mailBean.setMassageActivityDataBeans(massageActivityDataBeanMap.values());

        return mailBean;
    }

    /**
     * 团购ID,已购买人次,序号,时间
     *
     * @param file
     */
    private static void fetchHourDetailData(File file, Map<String, MassageDataBean> activityBeanMap) {
        List<String> strings;
        MassageDataBean bean;
        try {
            strings = Files.readAllLines(file.toPath());
            if (!strings.isEmpty()) {
                strings.remove(0);
            }
            for (String string : strings) {
                String[] split = string.split(COMMA);
                String id = split[0];
                String qty = split[1];
                bean = activityBeanMap.get(id);
                if (Objects.isNull(bean)) {
                    bean = new MassageDataBean();
                    bean.setId(id);
                    activityBeanMap.put(bean.getId(), bean);
                }
                int hour = getHour(file.getName());
                switch (hour) {
                    case 0:
                        bean.setoQty(qty);
                        break;
                    case 6:
                        bean.setSixQty(qty);
                        break;
                    case 7:
                        bean.setSevenQty(qty);
                        break;
                    case 8:
                        bean.setEightQty(qty);
                        break;
                    case 9:
                        bean.setNineQty(qty);
                        break;
                    case 10:
                        bean.setTenQty(qty);
                        break;
                    case 11:
                        bean.setElevenQty(qty);
                        break;
                    case 12:
                        bean.setTwelveQty(qty);
                        break;
                    case 13:
                        bean.setThirteenQty(qty);
                        break;
                    case 14:
                        bean.setFourteenQty(qty);
                        break;
                    case 15:
                        bean.setFifteenQty(qty);
                        break;
                    case 16:
                        bean.setSixteenQty(qty);
                        break;
                    case 17:
                        bean.setSeventeenQty(qty);
                        break;
                    case 18:
                        bean.setEighteenQty(qty);
                        break;
                    case 19:
                        bean.setNineteenQty(qty);
                        break;
                    case 20:
                        bean.setTwentyQty(qty);
                        break;
                    case 21:
                        bean.setTwentyOneQty(qty);
                        break;
                    case 22:
                        bean.setTwentyTwoQty(qty);
                        break;
                    case 23:
                        bean.setTwentyThreeQty(qty);
                        break;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * @param name hour-massage-activety-detail-2018011509-1
     * @return
     */
    private static int getHour(String name) {
        String[] split = name.split(LINE);
        String dateStr = split[4];
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(DateUtil.YYYYMMDDHHMM));
        return dateTime.getHour();
    }

    private static void fetchDayActivityData(File file, Map<String, MassageActivityBean> activityBeanMap) {
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());
            if (!strings.isEmpty()) {
                strings.remove(0);
                for (String string : strings) {
                    String[] split = string.split(COMMA);
                    String id = split[0],
                            brandName = split[1],
                            url = split[2],
                            dateStr = split[3];
                    LocalDate date = LocalDate.parse(dateStr);
                    MassageActivityBean activityBean = new MassageActivityBean(date, brandName, id, url);
                    activityBeanMap.put(activityBean.getActivityId(), activityBean);
                }

            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void fetchDayShopData(File file, Map<String, MassageShopBean> shopBeanMap) {
        List<String> strings;
        try {
            strings = Files.readAllLines(file.toPath());
            if (!strings.isEmpty()) {
                strings.remove(0);
                for (String string : strings) {
                    String[] split = string.split(COMMA);
                    String shopId = split[0],
                            shopName = split[1],
                            priceText = split[2],
                            regionName = split[3],
                            bookable = split[4],
                            dealable = split[5],
                            dateStr = split[6];
                    LocalDate date = LocalDate.parse(dateStr);
                    MassageShopBean shopBean = new MassageShopBean(date, shopId, shopName, priceText, regionName, bookable, dealable, null);
                    shopBeanMap.put(shopBean.getShopId(), shopBean);
                }
            }

        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 发送邮件
     */
    private static void sendMail4Data(String excelFilePath) {
        Properties prop = MassageBootstrap.loadConf(MassageBootstrap.getMailConfFile());
        InputStream in = null;
        Authenticator auth = null;
        Session sendMailSession;
        try {
            final String userName = prop.getProperty("mail.smtp.user");
            final String password = prop.getProperty("mail.smtp.pass");
            String fromAddr = prop.getProperty("mail.smtp.fromAddress");
            String receiver = prop.getProperty("mail.receivers");

//           String toAddr = prop.getProperty("mail.toAddress");
            String subject = prop.getProperty("mail.subject");
            String content = prop.getProperty("mail.content");

            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
            //是否需要身份验证
            if ("true".equals(prop.getProperty("mail.smtp.auth"))) {
                auth = new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                };
            }
            // 根据邮件会话属性和密码验证器构造一个发送邮件的session
            sendMailSession = Session.getDefaultInstance(prop, auth);
            // 根据session创建一个邮件消息
            Message mailMessage = new MimeMessage(sendMailSession);
            // 创建邮件发送者地址
            Address from = new InternetAddress(fromAddr);
            logger.info("发件人{}", fromAddr);
            // 设置邮件消息的发送者
            mailMessage.setFrom(from);
            //多个收件人
            String[] split = receiver.split(",");
            Address[] addresses = new Address[split.length];
            for (int i = 0; i < split.length; i++) {
                // 创建邮件的接收者地址，并设置到邮件消息中
                addresses[i] = new InternetAddress(split[i]);
                logger.info("发件人{}:{}", i + 1, split[i]);
            }
            // Message.RecipientType.TO属性表示接收者的类型为TO
            mailMessage.setRecipients(Message.RecipientType.TO, addresses);
            // 设置邮件消息的主题
            mailMessage.setSubject(subject);
            // 设置邮件消息发送的时间
            mailMessage.setSentDate(new Date());

//			Html
            // MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
            Multipart mainPart = new MimeMultipart();
            BodyPart bodyPart = new MimeBodyPart();
            // 创建一个包含HTML内容的MimeBodyPart
            BodyPart html = new MimeBodyPart();
            // 设置HTML内容
            if (StringUtils.isEmpty(content)) {
                html.setContent("hi , <br/> 数据见附件 <br/><br/><br/><br/>邮件通过定时发送", "text/html; charset=utf-8");
            } else {
                html.setContent(content, "text/html; charset=utf-8");
            }
            mainPart.addBodyPart(html);
            Path path = Paths.get(excelFilePath);
            DataSource ds = new FileDataSource(path.toFile());
            bodyPart.setDataHandler(new DataHandler(ds));
            bodyPart.setFileName(MimeUtility.encodeText(path.getFileName().toString()));
//           message.setContent(multipart);
            mainPart.addBodyPart(bodyPart);

            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            logger.info("邮件发送成功。");
        } catch (Exception e) {
            logger.error("邮件发送失败", e);
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error("邮件发送失败" + e.getMessage(), e);
            }
        }
    }


}
