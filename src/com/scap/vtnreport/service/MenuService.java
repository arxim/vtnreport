package com.scap.vtnreport.service;

import com.scap.vtnreport.dao.MenuDao;
 

public class MenuService {
	public String getMenuMappingDetail(String userRole) throws Exception {
		MenuDao menuDao = new MenuDao();
		return menuDao.getMenuMappingDetail(userRole);
	}

}
