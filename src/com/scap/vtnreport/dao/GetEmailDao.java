package com.scap.vtnreport.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.scap.vtnreport.utils.DbConnector;

public class GetEmailDao {
	
	public ArrayList<HashMap<String, String>> getEmail(String hospitalCode,String doctorCode,String yyyy,String mm){
		ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
		final String SQL ="SELECT T1.HOSPITAL_CODE, "
		           + "       T1.DOCTOR_CODE, "
		           + "       T1.YYYY, "
		           + "       T1.MM, "
		           + "       T2.EMAIL, "
		           + "       CASE "
		           + "           WHEN T2.LICENSE_ID = '' "
		           + "           THEN T2.CODE "
		           + "           ELSE T2.LICENSE_ID "
		           + "       END AS PASS_ENCRYPT, "
		           + "       T1.STATUS_MODIFY "
		           + "FROM SUMMARY_MONTHLY T1 "
		           + "     LEFT JOIN DOCTOR T2 ON T1.HOSPITAL_CODE = T2.HOSPITAL_CODE "
		           + "                            AND T1.DOCTOR_CODE = T2.CODE "
		           + "WHERE T1.YYYY = '"+yyyy+"' "
		           + "      AND T1.MM = '"+mm+"' AND T1.STATUS_MODIFY = '' AND T1.DOCTOR_CODE = '"+doctorCode+"' AND T1.HOSPITAL_CODE = '"+hospitalCode+"'";

		
		DbConnector db = new DbConnector();
		db.doConnect();
		data = db.getData(SQL);
		db.doDisconnect();

		return data;
	}

}
