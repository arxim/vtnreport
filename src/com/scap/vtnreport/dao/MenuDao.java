package com.scap.vtnreport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import com.scap.vtnreport.utils.DbConnector;
 

public class MenuDao {
	public String getMenuMappingDetail(String userRole) throws Exception {
		PreparedStatement ps = null;
		ArrayList<HashMap<String, String>> lstMenuRole = null;
		String value = "";
		final String SQL = "";
		//System.out.println(SQL);
		DbConnector DBConnector = new DbConnector();
		try (Connection conn = DBConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userRole);
			lstMenuRole = DBConnector.convertArrayListHashMap(ps.executeQuery());
			for (int i = 0; i < lstMenuRole.size(); i++) {

				String parentCode = lstMenuRole.get(i).get("MENU_PARENT_CODE");
				String menuCode = lstMenuRole.get(i).get("MENU_CODE");
				String idChk = parentCode.concat(menuCode);
				String menuNameEN = lstMenuRole.get(i).get("MENU_NAME_EN");
				String chk = lstMenuRole.get(i).get("CHK").equals("") ? "" : "checked";
				String chkSubMenu = lstMenuRole.get(i).get("MENU_PARENT_CODE").equals("") ? "" : "submenu";

				/*if (lstMenuRole.get(i).get("MENU_PARENT").equals("Y")) {
					value += "<div class=\"checkbox col-xs-offset-2\">" + "<label>" + "<input type=\"checkbox\" id=\""
							+ menuCode + "\" value=\"" + menuCode + "\" " + chk + " class=\"menuMap " + parentCode
							+ "headMenu\">" + "<strong> " + menuNameEN + "</strong>" + "</label>" + "</div>";
				} else if (chkSubMenu.equals("submenu")) {
					value += "<div class=\"checkbox col-xs-offset-3\">" + "<label>" + "<input type=\"checkbox\" id=\""
							+ idChk + "\" value=\"" + menuCode + "\" " + chk + " class=\"menuMap subMenu " + parentCode
							+ "sub\">" + "<strong> " + menuNameEN + "</strong>" + "</label>" + "</div>";
				} else {

				}*/
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (ps != null)
				ps.close();
		}
		//System.out.println(value);
		return value;
	}

}
