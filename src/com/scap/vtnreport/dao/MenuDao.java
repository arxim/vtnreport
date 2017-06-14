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
		final String SQL = " SELECT  UG.USER_GROUP,ACTION_TYPE,MM.MENU_CODE,M.MENU_NAME, M.LINK_PAGE,M.STATUS"
				+ "  FROM USER_GROUP UG "
				+ " JOIN STP_MENU_REPORT_MAPPING MM ON UG.USER_GROUP = MM.USER_GROUP_CODE"
				+ " JOIN STP_MENU_REPORT M ON  MM.MENU_CODE = M.MENU_CODE"
				+ " WHERE UG.USER_GROUP = ? AND M.STATUS = 'Y' ";
		 String value = "";
		//System.out.println(SQL);
		DbConnector DBConnector = new DbConnector();
		try (Connection conn = DBConnector.getDBConnection()) {
			ps = conn.prepareStatement(SQL);
			ps.setString(1, userRole);
			lstMenuRole = DBConnector.convertArrayListHashMap(ps.executeQuery());
			for (int i = 0; i < lstMenuRole.size(); i++) {

//				String parentCode = lstMenuRole.get(i).get("MENU_PARENT_CODE");
////				String menuCode = lstMenuRole.get(i).get("MENU_CODE");
////				String idChk = parentCode.concat(menuCode);
//				String menuNameEN = lstMenuRole.get(i).get("MENU_NAME_EN");
//				String chk = lstMenuRole.get(i).get("CHK").equals("") ? "" : "checked";
//				String chkSubMenu = lstMenuRole.get(i).get("MENU_PARENT_CODE").equals("") ? "" : "submenu";
				
 
				switch (lstMenuRole.get(i).get("MENU_CODE")) {

				case "RE001":
					value +="<a href=\"#\" class=\"btn btn-default\" style=\"color:#19067f\" onclick=\"getPaymnet()\">"
							+ " <span class=\"btn-lg glyphicon glyphicon-list-alt\"></span>"
							+ "</a>&nbsp;";
					break;
				case "RE002":
					value +="<a href=\"#\" class=\"btn btn-default\" style=\"color:#19067f\" onclick=\"getTax()\">"
							+ " <span class=\"btn-lg glyphicon glyphicon-usd\"></span>"
							+ " </a>&nbsp;";
					break;
				case "RE003":
					value +="<a href=\"#\" class=\"btn btn-default\" style=\"color:#19067f\" onclick=\"getEmail()\">"
							+ " <span class=\"btn-lg glyphicon glyphicon-send\"></span>"
						    + " </a>&nbsp;";
					break;
				}
				 
				
//				if (lstMenuRole.get(i).get("MENU_PARENT").equals("Y")) {
//					value += "<div class=\"checkbox col-xs-offset-2\">" + "<label>" + "<input type=\"checkbox\" id=\""
//							+ menuCode + "\" value=\"" + menuCode + "\" " + chk + " class=\"menuMap " + parentCode
//							+ "headMenu\">" + "<strong> " + menuNameEN + "</strong>" + "</label>" + "</div>";
//				} else if (chkSubMenu.equals("submenu")) {
//					value += "<div class=\"checkbox col-xs-offset-3\">" + "<label>" + "<input type=\"checkbox\" id=\""
//							+ idChk + "\" value=\"" + menuCode + "\" " + chk + " class=\"menuMap subMenu " + parentCode
//							+ "sub\">" + "<strong> " + menuNameEN + "</strong>" + "</label>" + "</div>";
//				} else {
//
//				}
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
