package com.scap.vtnreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import com.scap.vtnreport.utils.DbConnector;

public class GetDoctorDao {
	
	public JSONObject getDoctorPaymentDatatable(String hospitalCode,String yyyy,String mm,String limit_send_mail) throws Exception{
		
		PreparedStatement ps = null;
		
		JSONObject jsonObj = null;
		
		final String SQL ="SELECT * FROM ( SELECT T1.DOCTOR_CODE, "
		           + "       T2.NAME_THAI, "
		           + "       T1.STATUS_MODIFY,ROW_NUMBER() OVER(ORDER BY DOCTOR_CODE ASC) AS ROW "
		           + "FROM PAYMENT_MONTHLY T1 "
		           + "     LEFT JOIN DOCTOR T2 ON T1.HOSPITAL_CODE = T2.HOSPITAL_CODE "
		           + "                            AND T1.DOCTOR_CODE = T2.CODE "
		           + "WHERE T1.YYYY = ? "
		           + "      AND T1.MM = ? "
		           + "      AND (T1.STATUS_MODIFY = '' "
		           + "           OR T1.STATUS_MODIFY IS NULL) "
		           + "      AND T1.HOSPITAL_CODE = ? "
		           + "      AND DR_NET_PAID_AMT > 0 "
		           + "      AND DOCTOR_CODE IN "
		           + "( "
		           + "    SELECT CODE "
		           + "    FROM DOCTOR "
		           + "    WHERE EMAIL <> '' "
		           + "))TEMP WHERE TEMP.ROW <= ? ";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, yyyy);
			ps.setString(2, mm);
			ps.setString(3, hospitalCode);
			ps.setString(4, limit_send_mail);
			jsonObj = DbConnector.convertJsonObj(ps.executeQuery());
			System.out.println(SQL);
			System.out.println(jsonObj);
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		
		return jsonObj;
	}

public JSONObject getDoctorTax406Datatable(String hospitalCode,String yyyy,String mm,String limit_send_mail) throws Exception{
		
		PreparedStatement ps = null;
		
		JSONObject jsonObj = null;
		
		final String SQL ="SELECT * FROM (SELECT T1.DOCTOR_CODE, "
		           + "       T2.NAME_THAI, "
		           + "       T1.STATUS_MODIFY,ROW_NUMBER() OVER(ORDER BY DOCTOR_CODE ASC) AS ROW  "
		           + "FROM SUMMARY_TAX_406 T1 "
		           + "     LEFT JOIN DOCTOR T2 ON T1.HOSPITAL_CODE = T2.HOSPITAL_CODE "
		           + "                            AND T1.DOCTOR_CODE = T2.CODE "
		           + "WHERE T1.YYYY = ? "
		           + "      AND T1.MM = ? "
		           + "      AND (T1.STATUS_MODIFY = '' "
		           + "           OR T1.STATUS_MODIFY IS NULL) "
		           + "      AND T1.HOSPITAL_CODE = ? "
		           + "      AND SUM_TAX_DR_AMT > 0 "
		           + "      AND DOCTOR_CODE IN "
		           + "( "
		           + "    SELECT CODE "
		           + "    FROM DOCTOR "
		           + "    WHERE EMAIL <> '' "
		           + "))TEMP WHERE TEMP.ROW <= ?  ";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, yyyy);
			ps.setString(2, mm);
			ps.setString(3, hospitalCode);
			ps.setString(4, limit_send_mail);
			jsonObj = DbConnector.convertJsonObj(ps.executeQuery());
			System.out.println(SQL);
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
		           + "FROM PAYMENT_MONTHLY T1 "
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

	public ArrayList<HashMap<String, String>> getAllDoctorSendEmailPayment(String hospitalCode,String yyyy,String mm){
		PreparedStatement ps = null;
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		String SQL = ""
				+ "SELECT T1.HOSPITAL_CODE,T2.CODE AS DOCTOR_CODE, COALESCE(NULLIF(T2.EMAIL,''),'') EMAIL,"
				+ "COALESCE(NULLIF(T1.DR_SUM_AMT,'0.00'),'0.00') AS REVENUE_DETAIL, COALESCE(NULLIF(T1.EXDR_AMT+T1.EXCR_AMT,'0.00'),'0.00') AS EXPENSE, COALESCE(NULLIF(T1.DR_NET_PAID_AMT,'0.00'),'0.00') AS VOUCHER,"
				+ "COALESCE(NULLIF(T3.DR_AMTs,'0.00'),'0.00') AS UNPAID,"
				+ "CASE WHEN T2.LICENSE_ID = '' THEN T2.CODE ELSE T2.LICENSE_ID END AS PASS_ENCRYPT,"
				+ "T1.YYYY,T1.MM,B.BATCH_NO,T1.STATUS_MODIFY FROM PAYMENT_MONTHLY T1  "
				+ "LEFT OUTER JOIN BATCH B ON T1.HOSPITAL_CODE = B.HOSPITAL_CODE AND CLOSE_DATE = '' "
				+ "LEFT OUTER JOIN DOCTOR T2 ON T1.HOSPITAL_CODE = T2.HOSPITAL_CODE AND T1.DOCTOR_CODE = T2.CODE "
				+ "LEFT OUTER JOIN  ("
				+ "SELECT DOCTOR_CODE,SUM(DR_AMT) AS DR_AMTs,YYYY,MM AS DR_AMT,HOSPITAL_CODE FROM TRN_DAILY WHERE HOSPITAL_CODE = ? AND YYYY = ? AND MM = ? GROUP BY DOCTOR_CODE,HOSPITAL_CODE,YYYY,MM  "
				+ ") T3 ON T1.HOSPITAL_CODE = T3.HOSPITAL_CODE AND T1.DOCTOR_CODE = T3.DOCTOR_CODE " 
				+ "WHERE T1.HOSPITAL_CODE = ? AND T1.YYYY = ? AND T1.MM = ?  "
				+ " AND T2.EMAIL != ''" + "AND (T1.STATUS_MODIFY = '' OR T1.STATUS_MODIFY  is null)  AND T1.DR_NET_PAID_AMT>0  "
				+ " Order by DOCTOR_CODE ";
		
		
		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, hospitalCode);
			ps.setString(2, yyyy);
			ps.setString(3, mm);
			ps.setString(4, hospitalCode);
			ps.setString(5, yyyy);
			ps.setString(6, mm);
			data = DbConnector.convertArrayListHashMap(ps.executeQuery());
			//System.out.println(SQL);
		} catch (Exception e) {
			System.out.println(e);
		}
			
		return data;
		
	}
	
public JSONArray getDoctorSentSelfEmail(String hospitalCode,String doctor_code) {
		
		PreparedStatement ps = null;
		
		JSONArray jsonArray = null;
		
		final String SQL ="SELECT HOSPITAL_CODE,CODE,EMAIL FROM DOCTOR WHERE HOSPITAL_CODE= ? AND CODE = ? ";

		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, hospitalCode);
			ps.setString(2, doctor_code);
			jsonArray = DbConnector.getJsonAutoComplete(ps.executeQuery());
			System.out.println(SQL);
			System.out.println(jsonArray);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return jsonArray;
	}


	
}
