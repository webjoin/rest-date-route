package com.viewt.rest.data.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    final static String YYMMDD = "yyMMdd";
    final static String YYYY_MM_DD = "yyyy-MM-dd";
    /**
     * 2018011509
     */
    public final static String YYYYMMDDHHMM = "yyyyMMddHH";


    /**秒
     *
     * @return
     */
    public static long getTomorrowSecDate() {
        LocalDate localDate = LocalDate.now().plusDays(1);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localDate.atStartOfDay().toLocalTime());
        ZoneId zone = ZoneId.systemDefault();
        return localDateTime.atZone(zone).toInstant().getEpochSecond();
    }

    /**
     * @param date
     * @return
     */
    public static long getTimeFromDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD);
        Date parse;
        try {
            parse = formatter.parse(date);
            return parse.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getStrDate(LocalDate date) {
        System.out.println(date);
        return "";
    }

    public static void main(String[] args) {

//		System.out.println(getNextDay2(1));
//		getStrDate(LocalDate.now());
//		System.out.println(getTomorrowDate());
//		System.out.println(new Date(1515513600000L));
//		System.out.println(((1515513600000L - System.currentTimeMillis()) / (double)(60 * 60 * 1000)));

    }
}
