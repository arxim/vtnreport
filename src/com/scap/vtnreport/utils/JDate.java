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
	
	static public String getYear() {
        //Date now = new Date(System.currentTimeMillis());
        Calendar rightNow = Calendar.getInstance();
        //String strYear = Integer.toString(now.getYear()+1900);
        String strYear = "";
        if (rightNow.get(Calendar.YEAR) >= 2550) { 
            strYear = Integer.toString(rightNow.get(Calendar.YEAR) - 543); 
        } else {
            strYear = Integer.toString(rightNow.get(Calendar.YEAR));
        }
        return strYear;
    }
	
	
	
	static public String getDay() {
        //Date now = new Date(System.currentTimeMillis());
        Calendar rightNow = Calendar.getInstance();
        //SystemDate
        //String strDay = Integer.toString(now.getDate());
        String strDay = Integer.toString(rightNow.get(Calendar.DAY_OF_MONTH));
        if (strDay.length() == 1) {
            strDay = "0" + strDay;
        }
        return strDay;
    }
    
    static public String getMonth() {
//        Date now = new Date(System.currentTimeMillis());
        Calendar rightNow = Calendar.getInstance();
//        String strMonth = Integer.toString(now.getMonth()+1);
        String strMonth = Integer.toString(rightNow.get(Calendar.MONTH)+1);
        if (strMonth.length() == 1) {
            strMonth = "0" + strMonth;
        }
        //System.out.println(strMonth);
        return strMonth;
    }
    
    static public String getDateDD_MM_YYY() {
        return getDay()+" "+ getMonthName()+ " " + getYear();
    }
    
    static public String getDateMM_DD_YYY() {
        return getMonthNameEng()+ " "+ getDay()+","+ Calendar.getInstance().get(Calendar.YEAR);
    }
    
    static public String getMonthName() {
    	 Calendar rightNow = Calendar.getInstance();
    	 String nameMonth = "";
//       String strMonth = Integer.toString(now.getMonth()+1);
       String strMonth = Integer.toString(rightNow.get(Calendar.MONTH)+1);
       switch(strMonth) {
       case "01" : nameMonth = "มกราคม";
        break;
       case "02" : nameMonth = "กุมภาพันธ์";
  		break;
       case "03" : nameMonth = "มีนาคม";
  		break;
       case "04" : nameMonth = "เมษายน";
  		break;
       case "05" : nameMonth = "พฤษภาคม";
  		break;
       case "06" : nameMonth = "มิถุนายน";
  		break;
       case "07" : nameMonth = "กรกฎาคม";
  		break;
       case "08" : nameMonth = "สิงหาคม";
  		break;
       case "09" : nameMonth = "กันยายน";
  		break;
       case "10" : nameMonth = "ตุลาคม";
  		break;
       case "11" : nameMonth = "พฤศจิกายน";
  		break;
       case "12" : nameMonth = "ธันวาคม";
  		break;
       }
    	return nameMonth;
    }
    
    static public String getMonthNameEng() {
   	 Calendar rightNow = Calendar.getInstance();
   	 String nameMonth = "";
//      String strMonth = Integer.toString(now.getMonth()+1);
      String strMonth = Integer.toString(rightNow.get(Calendar.MONTH)+1);
      switch(strMonth) {
      case "01" : nameMonth = "January";
       break;
      case "02" : nameMonth = "February";
 		break;
      case "03" : nameMonth = "March";
 		break;
      case "04" : nameMonth = "April";
 		break;
      case "05" : nameMonth = "May";
 		break;
      case "06" : nameMonth = "June";
 		break;
      case "07" : nameMonth = "July";
 		break;
      case "08" : nameMonth = "August";
 		break;
      case "09" : nameMonth = "September";
 		break;
      case "10" : nameMonth = "October";
 		break;
      case "11" : nameMonth = "November";
 		break;
      case "12" : nameMonth = "December";
 		break;
      }
   	return nameMonth;
   }
	
	
    
	

}
