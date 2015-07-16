package com.mzy.moban.util;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.util.Date;


/**
 * 格式化工具类
 * @author zhuwei
 *
 */
public class FormatUtils {
	
	public static final String DATE_TIME_FORMAT  = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
	 * 返回日期的 yyyy-MM-dd HH:mm:ss 串
	 * @param date
	 * @return
	 */
	public static String formatDateTime(Date date) {
		return DateFormatUtils.format(date, DATE_TIME_FORMAT);
	}
	
	
	/**
	 * 
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date parseDateTime(String dateStr) throws ParseException {
		return DateUtils.parseDate(dateStr, DATE_TIME_FORMAT);
	}


    public static Date parseDate(String dateStr) throws ParseException {
        return DateUtils.parseDate(dateStr, DATE_FORMAT);
    }

    /**
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String formatDateTime(Date date,String format) {
		return DateFormatUtils.format(date, format);
	}
    private FormatUtils() {
		
	}
}
