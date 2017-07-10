package com.viewt.rest.data.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static String getNextDay(int days){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,days);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		String dateString = formatter.format(date);
		return dateString;
	}
	public static String getNextDay2(int days){
		Date date=new Date();//取时间
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,days);//把日期往后增加一天.整数往后推,负数往前移动
		date=calendar.getTime(); //这个时间就是日期往后推一天的结果
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return dateString;
	}
	public static void main(String[] args) {
		
		System.out.println(getNextDay2(1));
	}
}
