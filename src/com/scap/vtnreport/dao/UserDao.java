package com.scap.vtnreport.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.scap.vtnreport.model.UserView;
import com.scap.vtnreport.utils.DbConnector;

public class UserDao {
	public UserView getUser(String userId, String password, String hospital) throws Exception {
		final String SQL = " SELECT U.HOSPITAL_CODE, U.LOGIN_NAME,"
				+ " U.PASSWORD, U.NAME, U.USER_GROUP_CODE, U.ACTIVE "
				+ " FROM USERS U"
				+ " JOIN  USER_GROUP UG ON UG.USER_GROUP = U.USER_GROUP_CODE "
				+ " WHERE  U.HOSPITAL_CODE = ? AND U.LOGIN_NAME = ? AND U.PASSWORD = ? AND U.ACTIVE ='1'";
		PreparedStatement ps = null;
		UserView user = null;
		DbConnector dbConnection = new DbConnector();
		try (Connection conn = dbConnection.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, hospital);
			ps.setString(2, userId);
			ps.setString(3, password);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserView();
				user.setLoginName(rs.getString("LOGIN_NAME"));
				user.setName(rs.getString("NAME"));
				user.setUserGroupCode(new BigDecimal(rs.getString("USER_GROUP_CODE")));
				user.setActive(rs.getString("ACTIVE"));
				user.setHospitalCode(rs.getString("HOSPITAL_CODE"));
				

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			
		} finally {
			if (ps != null)
				ps.close();
		}
		return user;
	}
}
