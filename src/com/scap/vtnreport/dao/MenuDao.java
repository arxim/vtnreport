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
				+ " WHERE UG.USER_GROUP = ? AND M.STATUS = 'Y' ORDER BY MENU_CODE";
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
				
				case "RE000":
					value +="<a href=\"#\" id=\"btn-sum-payment\" class=\"btn btn-default clsBtnHover\" onclick=\"getPaymnetAll()\">"
							+ " <span><img src=\"resources/images/bar-chart.png\" class=\"img-responsive\" alt=\"icon payment all\" width=\"45\" height=\"45\"></span>"
							+ "</a>&nbsp;";
					break;
				
				case "RE001":
					value +="<a href=\"#\" id=\"btn-payment\" class=\"btn btn-default clsBtnHover\" onclick=\"getPaymnet()\">"
							+ " <span><img src=\"resources/images/icon-payment.png\" class=\"img-responsive\" alt=\"icon payment\" width=\"45\" height=\"45\"></span>"
							+ "</a>&nbsp;";
					break;
				case "RE002":
					value +="<a href=\"#\" id=\"btn-tax\" class=\"btn btn-default clsBtnHover\" onclick=\"getTax()\">"
							+ " <span><img src=\"resources/images/icon-tax.png\" class=\"img-responsive\" alt=\"icon tax\" width=\"45\" height=\"45\"></span>"
							+ " </a>&nbsp;";
					break;
				case "RE003":
					value +="<a href=\"#\" id=\"btn-email\" class=\"btn btn-default clsBtnHover\" onclick=\"getEmail()\">"
							+ " <span><img src=\"resources/images/icon-send-email.png\" class=\"img-responsive\" alt=\"icon send email\" width=\"45\" height=\"45\"></span>"
						    + " </a>&nbsp;";
					break;
				case "RE004":
					value +="<a href=\"#\" id=\"btn-manual\" class=\"btn btn-default clsBtnHover\" onclick=\"getManual()\">"
							+ " <span><img src=\"resources/images/icon-manual.png\" class=\"img-responsive\" alt=\"icon Manual\" width=\"45\" height=\"45\"></span>"
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
