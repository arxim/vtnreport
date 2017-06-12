package com.scap.vtnreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.scap.vtnreport.utils.DbConnector;

public class GetDoctorDao {
	
	public JSONObject getDoctorPaymentDatatable(String hospitalCode,String yyyy,String mm) throws Exception{
		
		PreparedStatement ps = null;
		
		JSONObject jsonObj = null;
		
		final String SQL ="SELECT  T1.DOCTOR_CODE, "
		           + "		 T2.NAME_THAI, "
		           + "       T1.STATUS_MODIFY "
		           + "FROM SUMMARY_MONTHLY T1 "
		           + "     LEFT JOIN DOCTOR T2 ON T1.HOSPITAL_CODE = T2.HOSPITAL_CODE "
		           + "                            AND T1.DOCTOR_CODE = T2.CODE "
		           + "WHERE T1.YYYY = ? "
		           + "      AND T1.MM = ? "
		           + "      AND (T1.STATUS_MODIFY = '' OR T1.STATUS_MODIFY IS NULL) "
		           + "      AND T1.HOSPITAL_CODE = ?;";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, yyyy);
			ps.setString(2, mm);
			ps.setString(3, hospitalCode);
			jsonObj = DbConnector.convertJsonObj(ps.executeQuery());
			System.out.println(jsonObj);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
		return jsonObj;
	}

public JSONObject getDoctorTax406Datatable(String hospitalCode,String yyyy,String mm) throws Exception{
		
		PreparedStatement ps = null;
		
		JSONObject jsonObj = null;
		
		final String SQL ="SELECT  T1.DOCTOR_CODE, "
		           + "		 T2.NAME_THAI, "
		           + "       T1.STATUS_MODIFY "
		           + "FROM SUMMARY_TAX_406 T1 "
		           + "     LEFT JOIN DOCTOR T2 ON T1.HOSPITAL_CODE = T2.HOSPITAL_CODE "
		           + "                            AND T1.DOCTOR_CODE = T2.CODE "
		           + "WHERE T1.YYYY = ? "
		           + "      AND T1.MM = ? "
		           + "      AND (T1.STATUS_MODIFY = '' OR T1.STATUS_MODIFY IS NULL) "
		           + "      AND T1.HOSPITAL_CODE = ?;";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, yyyy);
			ps.setString(2, mm);
			ps.setString(3, hospitalCode);
			jsonObj = DbConnector.convertJsonObj(ps.executeQuery());
			System.out.println(jsonObj);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
		return jsonObj;
	}
	
	public ArrayList<HashMap<String, String>> getDoctorSendEmailPayment(String hospitalCode,String doctorCode, String yyyy, String mm) throws Exception {

		PreparedStatement ps = null;
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		
		final String SQL ="SELECT T1.HOSPITAL_CODE, "
		           + "       T2.CODE AS DOCTOR_CODE, "
		           + "       COALESCE(NULLIF(T2.EMAIL,''),'0') EMAIL, "
		           + "       CASE "
		           + "           WHEN T2.LICENSE_ID = '' "
		           + "           THEN T2.CODE "
		           + "           ELSE T2.LICENSE_ID "
		           + "       END AS PASS_ENCRYPT, "
		           + "       T1.YYYY, "
		           + "       T1.MM, "
		           + "       T1.STATUS_MODIFY "
		           + "FROM SUMMARY_MONTHLY T1 "
		           + "     LEFT JOIN DOCTOR T2 ON T1.HOSPITAL_CODE = T2.HOSPITAL_CODE "
		           + "                            AND T1.DOCTOR_CODE = T2.CODE "
		           + "WHERE T1.HOSPITAL_CODE = ? "
		           + "		AND T2.CODE = ? "
		           + "		AND T1.YYYY = ? "
		           + "      AND T1.MM = ? "
		           + "      AND (T1.STATUS_MODIFY = '' OR T1.STATUS_MODIFY IS NULL); ";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, hospitalCode);
			ps.setString(2, doctorCode);
			ps.setString(3, yyyy);
			ps.setString(4, mm);
			data = DbConnector.convertArrayListHashMap(ps.executeQuery());
			System.out.println(data);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
		return data;
	}
	
	public ArrayList<HashMap<String, String>> getDoctorSendEmailTax406(String hospitalCode,String doctorCode, String yyyy, String mm) throws Exception {

		PreparedStatement ps = null;
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		
		final String SQL ="SELECT T1.HOSPITAL_CODE, "
		           + "       T2.CODE AS DOCTOR_CODE, "
		           + "       COALESCE(NULLIF(T2.EMAIL, ''), '0') EMAIL, "
		           + "       CASE "
		           + "           WHEN T2.LICENSE_ID = '' "
		           + "           THEN T2.CODE "
		           + "           ELSE T2.LICENSE_ID "
		           + "       END AS PASS_ENCRYPT, "
		           + "       T1.YYYY, "
		           + "       T1.MM, "
		           + "       T1.STATUS_MODIFY "
		           + "FROM SUMMARY_TAX_406 T1 "
		           + "     LEFT JOIN DOCTOR T2 ON T1.HOSPITAL_CODE = T2.HOSPITAL_CODE "
		           + "                            AND T1.DOCTOR_CODE = T2.CODE "
		           + "WHERE T1.HOSPITAL_CODE = ? "
		           + "      AND T2.CODE = ? "
		           + "      AND T1.YYYY = ? "
		           + "      AND T1.MM = ? "
		           + "      AND (T1.STATUS_MODIFY = '' "
		           + "           OR T1.STATUS_MODIFY IS NULL);";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, hospitalCode);
			ps.setString(2, doctorCode);
			ps.setString(3, yyyy);
			ps.setString(4, mm);
			data = DbConnector.convertArrayListHashMap(ps.executeQuery());
			System.out.println(data);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
		return data;
	}

}
