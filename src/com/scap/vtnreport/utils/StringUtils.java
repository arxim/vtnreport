package com.scap.vtnreport.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringUtils {

	public static boolean isEmpty(String... args) {
		for (String arg : args)
			if (arg == null || "".equals(arg))
				return true;
		return false;
	}

	/**
	 * Method for convert to response body controller
	 * 
	 * @param args
	 * @return
	 */
	public static String convertListMapToString(List<Map<String, String>> lstDatas) {
		if (lstDatas == null || lstDatas.isEmpty())
			return null;
		StringBuilder data = new StringBuilder();
		for (int i = 0; i < lstDatas.size(); i++) {
			data.append("<option value=\"");
			data.append(lstDatas.get(i).get("DWL_KEY"));
			data.append("\">");
			data.append(lstDatas.get(i).get("DWL_VALUE"));
			data.append(" </option>");
		}
		return data.toString();
	}

	public static boolean checkEmpty(String vaData) {
		boolean vbResult = false;
		if (vaData != null) {
			if (!vaData.equals("")) {
				vbResult = true;
			}
		}
		return vbResult;
	}

	public boolean checkLenght(String vaData, String vaLenght) {
		boolean vbResult = false;
		String vaConvertLen = vaLenght.replace("Len[", "");
		vaConvertLen = vaConvertLen.replace("]", "");

		if (vaData != null) {
			if (vaData.length() <= Integer.parseInt(vaConvertLen)) {
				vbResult = true;
			}
		}
		return vbResult;
	}

	public static String convertLang(String vafield, String valang) {
		String vafieldtemp = "";

		if (valang.equalsIgnoreCase("en")) {
			vafieldtemp = vafield + "_EN";
		} else if (valang.equalsIgnoreCase("th")) {
			vafieldtemp = vafield + "_TH";
		}

		return vafieldtemp;
	}

	public static List<Map<String, String>> convertRangeYear(String year) {
		if (year == null || year.isEmpty())
			return null;
		List<Map<String, String>> lsQueryData = new ArrayList<Map<String, String>>();

		int Year = Integer.parseInt(year) - 10;
		for (int i = 0; i <= 13; i++) {
			Map<String, String> rtnData = new HashMap<String, String>();
			rtnData.put("DWL_KEY", Integer.toString(Year));
			rtnData.put("DWL_VALUE", Integer.toString(Year));
			Year++;
			lsQueryData.add(rtnData);
		}

		return lsQueryData;
	}
	
	//à¹?à¸?à¸¥à¸?à¸—à¸¨à¸?à¸´à¸¢à¸¡ 2 à¸•à¸³à¹?à¸«à¸?à¹?à¸?  Input : 651.5176515121351 ==> Output :651.52
	public static double roundDecemalPlace(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
