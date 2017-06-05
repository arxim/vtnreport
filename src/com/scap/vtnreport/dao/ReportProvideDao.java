package com.scap.vtnreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.scap.vtnreport.utils.DbConnector;

public class ReportProvideDao {
	
	public List<Map<String, String>> getReportData(String vaSql) throws Exception{
		List<Map<String,String>> voReportDataList = null;
		PreparedStatement ps = null;
		DbConnector con = new DbConnector();
		try	(Connection conn = con.getConnection()){
			ps = conn.prepareStatement(vaSql);
			voReportDataList = DbConnector.getData(ps.executeQuery());
		}catch (Exception e){
			throw e;
		}finally {
			if (ps != null) ps.close();
		}
		return voReportDataList;
	}

}
