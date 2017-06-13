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

}
