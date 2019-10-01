package com.scap.vtnreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;

import com.scap.vtnreport.utils.DbConnector;

public class AutoCompleteDao {

	public JSONArray lookupDoctor(String voDoctorSearch, String hospitalCode) throws Exception {
		JSONArray jsonArray = null;
		PreparedStatement ps = null;
		DbConnector con = new DbConnector();
	
		final String SQL =" "
		           + "SELECT CODE, "
		           + "       NAME_THAI, "
		           + "       DESCRIPTION "
		           + "FROM "
		           + "( "
		           + "    SELECT ROW_NUMBER() OVER(PARTITION BY HOSPITAL_CODE ORDER BY HOSPITAL_CODE) 'SEQ', "
		           + "           CODE, "
		           + "           NAME_THAI, "
		           + "           CODE+' : '+NAME_THAI DESCRIPTION "
		           + "    FROM DOCTOR "
		           + "    WHERE HOSPITAL_CODE = ? "
		           + "          AND CODE + NAME_THAI LIKE ? "
		           + "          OR CODE + NAME_THAI LIKE ? "
		           + ") T1 "
		           + "WHERE SEQ <= 200;";
		
		try (Connection conn = con.getConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, hospitalCode);
			ps.setString(2, "%" + voDoctorSearch.toLowerCase() + "%");
			ps.setString(3, "%" + voDoctorSearch.toUpperCase() + "%");
			jsonArray = DbConnector.getJsonAutoComplete(ps.executeQuery());

			System.out.println(hospitalCode + " : " + voDoctorSearch);
			System.out.println(jsonArray);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		return jsonArray;
	}

	public JSONArray lookupCounty(String voCountySearch, String hospitalCode) throws Exception {
		JSONArray jsonArray = null;
		PreparedStatement ps = null;
		DbConnector con = new DbConnector();
	
		final String SQL ="SELECT COUNTY_CODE,DESP,DESPS  FROM ( SELECT ROW_NUMBER() OVER(PARTITION BY COUNTY_CODE ORDER BY COUNTY_CODE) 'SEQ', " + 
				"COUNTY_CODE, COUNTY_DESCPTION_EN+' : '+COUNTY_DESCPTION_TH AS DESP,COUNTY_DESCPTION_EN+' : '+COUNTY_DESCPTION_TH AS DESPS FROM COUNTY  " + 
				" WHERE  COUNTY_DESCPTION_EN + COUNTY_DESCPTION_TH LIKE ? " + 
				"OR COUNTY_DESCPTION_TH + COUNTY_DESCPTION_EN LIKE ? ) T1 WHERE SEQ <= 200;";
		
		try (Connection conn = con.getConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, "%" + voCountySearch.toLowerCase() + "%");
			ps.setString(2, "%" + voCountySearch.toUpperCase() + "%");
			jsonArray = DbConnector.getJsonAutoComplete(ps.executeQuery());

			System.out.println(hospitalCode + " : " + voCountySearch);
			System.out.println(jsonArray);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		return jsonArray;
	}

	
}
