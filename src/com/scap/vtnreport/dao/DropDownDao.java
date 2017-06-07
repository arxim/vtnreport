package com.scap.vtnreport.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.scap.vtnreport.utils.DbConnector;
import com.scap.vtnreport.utils.ReadProperties;

public class DropDownDao {
	public String getYYYY() {
		String dropdown = "";
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		String sql = "SELECT  CAST(YYYY-9 AS INT) AS CODE,CAST(YYYY-9 AS INT)  AS NAME FROM BATCH WHERE CLOSE_DATE = ''";
		DbConnector db = new DbConnector();
		db.doConnect();
		data = db.getData(sql);
		db.doDisconnect();

		int year = Integer.parseInt(data.get(0).get("CODE"));
		for (int i = 0; i < 13; i++) {
			dropdown += "<option value=\"" + (year + i) + "\"> " + (year + i) + " </option>";
		}
		return dropdown;

	}

	public String getHospital() {
		String dropdown = "";
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		ReadProperties prop = new ReadProperties();
		data = prop.getDataObjReadPropertiesFile("hospital.properties");

		for (int i = 0; i < data.size(); i++) {
			dropdown += "<option value=\"" + data.get(i).get("CODE") + "\"> " + data.get(i).get("NAME") + " </option>";
		}
		return dropdown;

	}
}
