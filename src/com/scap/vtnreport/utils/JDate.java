package com.scap.vtnreport.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class JDate {
	
	public static String getLastDayOfMonth(int year,int month){
		
		Calendar calendar = new GregorianCalendar();
	    calendar.set(year, month - 1, 1);
	    calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
	    Date date = calendar.getTime();
	    DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd",Locale.US);
	    return DATE_FORMAT.format(date);

	}
	static public String getTime() {
		return getHour() + "" + getMinutes() + "" + getSeconds();
	}
	
	static public String getHour() {
		// Date now = new Date(System.currentTimeMillis());
		Calendar rightNow = Calendar.getInstance();
		// String strHour = Integer.toString(now.getHours());
		String strHour = Integer.toString(rightNow.get(Calendar.HOUR_OF_DAY));
		if (strHour.length() == 1) {
			strHour = "0" + strHour;
		}
		return strHour;
	}
	
	static public String getMinutes() {
		// Date now = new Date(System.currentTimeMillis());
		Calendar rightNow = Calendar.getInstance();
		// String strMinutes = Integer.toString(now.getMinutes());
		String strMinutes = Integer.toString(rightNow.get(Calendar.MINUTE));
		if (strMinutes.length() == 1) {
			strMinutes = "0" + strMinutes;
		}
		return strMinutes;
	}
	
	static public String getSeconds() {
		// Date now = new Date(System.currentTimeMillis());
		Calendar rightNow = Calendar.getInstance();
		// String strSeconds = Integer.toString(now.getSeconds());
		String strSeconds = Integer.toString(rightNow.get(Calendar.SECOND));
		if (strSeconds.length() == 1) {
			strSeconds = "0" + strSeconds;
		}
		return strSeconds;
	}
	
	

}
