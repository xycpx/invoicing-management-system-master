package com.px.admin.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类
 */
public class DateUtil {

	/**
	 * 把日期对象根据传入format生成指定格式的字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDate(Date date,String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
	
	/**
	 * 把日期字符串解析成指定格式的日期对象
	 * @param str
	 * @param format
	 * @return
	 * @throws Exception
	 */
	public static Date formatString(String str,String format) throws Exception{
		if(StringUtils.isEmpty(str)){
			return null;
		}
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	/**
	 * 生成当前年月日字符串
	 * @return
	 * @throws Exception
	 */
	public static String getCurrentDateStr()throws Exception{
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		return sdf.format(date);
	}
	
	/**
	 * 获取指定范围内的日期集合
	 * @param before
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<String> getRangeDates(String before,String end){
		List<String> datas= null;
		try {
			datas = new ArrayList<String>();
			Calendar cb = Calendar.getInstance();
			Calendar ce = Calendar.getInstance();
			cb.setTime(formatString(before,"yyyy-MM-dd"));
			ce.setTime(formatString(end,"yyyy-MM-dd"));
			datas.add(formatDate(cb.getTime(),"yyyy-MM-dd"));
			while(cb.before(ce)){
				cb.add(Calendar.DAY_OF_MONTH, 1);
				datas.add(formatDate(cb.getTime(),"yyyy-MM-dd"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return datas;
	}
	
	/**
	 * 获取指定范围内的月份集合
	 * @param before
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static List<String> getRangeMonth(String before,String end){
		List<String> months= null;
		try {
			months = new ArrayList<String>();
			Calendar cb = Calendar.getInstance();
			Calendar ce = Calendar.getInstance();
			cb.setTime(formatString(before,"yyyy-MM"));
			ce.setTime(formatString(end,"yyyy-MM"));
			months.add(formatDate(cb.getTime(),"yyyy-MM"));
			while(cb.before(ce)){
				cb.add(Calendar.MONTH, 1);
				months.add(formatDate(cb.getTime(),"yyyy-MM"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return months;
	}
	
	
	public static void main(String[] args) throws Exception{
		/*List<String> datas=getRangeDatas("2017-10-28","2017-11-02");
		for(String data:datas){
			System.out.println(data);
		}*/
		List<String> months=getRangeMonth("2017-09","2018-12");
		for(String month:months){
			System.out.println(month);
		}
	}
}
