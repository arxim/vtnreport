package com.scap.vtnreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.scap.vtnreport.model.UserView;
import com.scap.vtnreport.utils.DbConnector;

public class UserDao {
	public UserView getUser(String userId, String password, String hospital) throws Exception {
		final String SQL = " SELECT U.USER_CODE, U.FIRSTNAME, U.LASTNAME, U.EMAIL, U.USER_ROLE_CODE, UR.USER_ROLE_NAME, U.HOSPITAL_CODE, U.ACTIVE, U.ACTIVE "
				+ " FROM MST_USER U "
				+ " JOIN MST_USER_ROLE UR ON U.USER_ROLE_CODE = UR.USER_ROLE_CODE AND UR.ACTIVE = 'Y' "
				+ " WHERE U.ACTIVE = 'Y' AND U.USER_CODE = ? AND U.PASSWORD = ? AND U.HOSPITAL_CODE = ?";
		PreparedStatement ps = null;
		UserView user = null;
		DbConnector dbConnection = new DbConnector();
		try (Connection conn = dbConnection.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userId);
			ps.setString(2, password);
			ps.setString(3, hospital);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user = new UserView();
				user.setUserId(rs.getString("USER_CODE"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setRoleId(rs.getString("USER_ROLE_CODE"));
				user.setHospitalId(rs.getString("HOSPITAL_CODE"));
				// user.setUsed(rs.getString("USED"));
				user.setActive(rs.getString("ACTIVE"));
				user.setRoleName(rs.getString("USER_ROLE_NAME"));
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
