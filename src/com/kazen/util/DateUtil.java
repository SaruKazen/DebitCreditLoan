//$Id$
package com.kazen.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static final String MILLI_SECOND_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

	public static Long getCurrentTime() {
		return System.currentTimeMillis();
	}

	public static String convertDate(Long dateval) {
		Date date = new Date(dateval);
		SimpleDateFormat df1 = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
		String dateText = df1.format(date);
		return dateText;
	}

	public static String getTimeAsString(long dateVal) {
		DateFormat dateFormat = new SimpleDateFormat(MILLI_SECOND_DATE_FORMAT);
		Date date = new Date(dateVal);
		String time = dateFormat.format(date);
		return time;
	}

	public static String getCurrentTimeFormatInMilliSeconds() {
		long currentTime = System.currentTimeMillis();
		return convertTime(currentTime, MILLI_SECOND_DATE_FORMAT);
	}

	private static String convertTime(Long time, String format) {
		Date date = new Date(time);
		SimpleDateFormat df1 = new SimpleDateFormat(format);
		String dateText = df1.format(date);
		return dateText;
	}

	public static String getCurrentTimeAsString() throws Exception {
		String customDateFormat = "yyyy/MM/dd HH:mm:ss.SSS";
		return getCurrentTimeAsString(customDateFormat);
	}

	public static String getCurrentTimeAsString(String customDateFormat) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat(customDateFormat);
		Date date = new Date();
		String currentTime = dateFormat.format(date);
		return currentTime;
	}

	public static boolean compareDates(String psDate) throws Exception {
		// more than one day differ will be return as true - todayDate and
		// previousDate will be same ex : 08-12-1984 - return will be false
		String customDateFormat = "yyyy/MM/dd";
		SimpleDateFormat dateFormat = new SimpleDateFormat(customDateFormat);
		String today = getCurrentTimeAsString(customDateFormat);

		Date previousDate = dateFormat.parse(psDate);
		Date todayDate = dateFormat.parse(today);

		if (todayDate.after(previousDate)) {
			return true;
		} else {
			return false;
		}
	}

	public static Date parseDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = sdf.parse(dateStr);
		return date;
	}

}
