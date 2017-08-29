/**
 * Copyright &copy; 2012-2013 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */
package com.csair.wxopen.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2013-3-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	public static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
		"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm","yyyy年MM月dd日","MM月dd日","yyyyMMdd"};
	public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}
	
	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}
	
	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}
	
 
	
	
	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}
	
	public static String getHHMMTime(Date date) {
		return formatDate(date, "HH:mm");
	}
	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null){
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	
	
	
	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", 
	 *   "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date getDateAll(String date){
		if(date==null) {
			return null;
		}
		SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return myDateFormat.parse(date);
		} catch (ParseException e) {
			LOGGER.error("试图将"+date+"转换成yyyy-MM-dd格式的日期类型，转换失败！",e);
			return null;
		}
	}

	
	/**
	 * 获取过去的天数
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime()-date.getTime();
		return t/(24*60*60*1000);
	}
	
   
	/**
	 * "yyyy年MM月dd日","MM月dd日","yyyyMMdd"
	 * @desc 2016年9月30号
	 * @author tangan
	 * @date 2016年10月1日 下午3:16:24
	 * @email tangan@foreveross.com
	 * @return
	 */
	public static String toyyyyMMdd_zh(Date date) {
		return formatDate(date,parsePatterns[6].toString());
	}
	
	/**
	 * 
	 * @desc 9月30号
	 * @author tangan
	 * @date 2016年10月1日 下午3:39:14
	 * @email tangan@foreveross.com
	 * @return
	 */
	public static String toMMdd(Date date) {
		return formatDate(date,parsePatterns[7].toString());
	}
	/**
	 * 
	 * @desc 20160930
	 * @author tangan
	 * @date 2016年10月1日 下午3:16:10
	 * @email tangan@foreveross.com
	 * @return
	 */
	public static String toyyyyMMdd(Date date) {
		return formatDate(date,parsePatterns[8].toString());
	}
	
	
	
	/**
	 * 
	 * @desc 根据日期获取星期几
	 * @author tangan
	 * @date 2016年10月1日 下午3:15:49
	 * @email tangan@foreveross.com
	 * @return
	 */
	public static String getWeekOfDate(Date date) { 
		  return formatDate(date, "E");
		} 
 
	
	
	 /**
     * 获得指定日期的前一天
     * 
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    public static String getDayBefore(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
        	LOGGER.info(" DateUtils getDayBefore error. errorMsg:{}",e);
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - 1);

        String dayBefore = new SimpleDateFormat("yyyyMMdd").format(c
                .getTime());
        return dayBefore;
    }

    /**
     * 获得指定日期的后一天
     * 
     * @param specifiedDay
     * @return
     */
    public static String getDayAfter(String specifiedDay) {
        Calendar c = Calendar.getInstance();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyyMMdd").parse(specifiedDay);
        } catch (ParseException e) {
        	LOGGER.info(" DateUtils getDayAfter error. errorMsg:{}",e);
        }
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day + 1);
        String dayAfter = new SimpleDateFormat("yyyyMMdd")
                .format(c.getTime());
        return dayAfter;
    }
    public static String getTimeByMinute(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        /*String beginTime = TimeUtil.forMatterDate(TimeUtil.getTimeByMinute(Constants.QUERY_PREVIOUS_MINUTE));
    	String endTime = TimeUtil.forMatterDate(TimeUtil.getTimeByMinute(0));
    	*/
        return formatter.format(calendar.getTime());
    }
	
	
  //格式化时间为'yyyy-MM-dd HH:m0:00'
  	public static Date forMatterDate(String TimeStr) {
  		Date date = null;
  		if(StringUtils.isEmpty(TimeStr))
  		{
  			return null;
  		}
  		try {
  			date = formatter.parse(TimeStr);
  		} catch (ParseException e) {
  			LOGGER.error("时间格式转换异常 ParseException:" + e);
  		}
  		return date;
  	}
	
  	/**
  	 * 根据传入的日期，和转换的格式转换成String数据
  	 * @param date
  	 * @param pattern
  	 * @return
  	 */
  	 public static String getDateToString(Date date,String pattern) {
  		 if(date==null)
  		 {
  			 return "";
  		 }
  		if (StringUtils.isEmpty(pattern)) 
  		{
  			pattern="yyyy-MM-dd HH:mm:ss";
  		}
  		 SimpleDateFormat formatDate= new SimpleDateFormat(pattern);
  		  return formatDate.format(date);
     }
  	
}
