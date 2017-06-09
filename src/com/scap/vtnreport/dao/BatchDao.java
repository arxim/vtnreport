package com.scap.vtnreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import org.json.JSONArray;

import com.scap.vtnreport.utils.DbConnector;

public class BatchDao {
	public JSONArray getBatch() throws Exception {
		JSONArray jsonArray = null;
		PreparedStatement ps = null;
		String SQL = "SELECT YYYY,MM FROM BATCH WHERE CLOSE_DATE = ''";
		try (Connection conn = DbConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			jsonArray = DbConnector.queryJsonObj(ps.executeQuery());
		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}

		return jsonArray;
	}
}
