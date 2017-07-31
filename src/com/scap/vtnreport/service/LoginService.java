package com.scap.vtnreport.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.scap.vtnreport.dao.UserDao;
import com.scap.vtnreport.model.UserView;
import com.scap.vtnreport.utils.AuthenticationLDAP;
import com.scap.vtnreport.utils.MD5;
import com.scap.vtnreport.utils.ADAuthen;
import com.scap.vtnreport.utils.AesUtil;
import com.scap.vtnreport.utils.StringUtils;

public class LoginService {
	
	final static Logger logger = Logger.getLogger(LoginService.class);
	
	public UserView doLoginProcess(HttpServletRequest request) {
		String isLoginLdapPass = "FAIL";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hospitalcode = request.getParameter("hospitalcode");
		UserView isUserView = null;

		try {
			// Check External database LDAP
			ADAuthen ldap = new ADAuthen();
			String check = ldap.processADAuthen(username, password);
			
			if ("Y".equals(check)) {
				logger.debug("LDAPLOGIN");
				
				isLoginLdapPass = "LDAPLOGIN";
				UserDao userDao = new UserDao();
				UserView isUser = userDao.getUserByUserCodeLdap(username, hospitalcode);
				// Check With UserCode
				if (isUser != null) {
					if (isUser != null && isUser.getUserGroupCode() != null) {
						isLoginLdapPass = isUser.getUserGroupCode().toString();
						isUserView = isUser;
					} else {
						isLoginLdapPass = "NONEROLE";
						isUserView = null;
					}
				// Check With LicenseId (Doctor)
				} else{
					isUser = userDao.getUserByLicenseIdLdap(username, hospitalcode);
					if (isUser != null) {
						if (isUser != null && isUser.getUserGroupCode() != null) {
							isLoginLdapPass = isUser.getUserGroupCode().toString();
							isUserView = isUser;
						} else {
							isLoginLdapPass = "NONEROLE";
							isUserView = null;
						}
						// save user login history
					}else{
			        	isLoginLdapPass = "FAIL";
			        	isUserView = null;
			        }
				}

			}else if(check.equals("U")){
				isLoginLdapPass = "FAIL";
			}
			
			// internal database
			else {
//					 encryption password, before authentication
			   UserDao userDao = new UserDao();
		       UserView isUser = userDao.getUser(username, MD5.encrypt(password), hospitalcode);
		       if(isUser != null){
		    	   if (isUser != null && isUser.getUserGroupCode() != null) {
		    		   isLoginLdapPass = isUser.getUserGroupCode().toString();
		    		   isUserView = isUser;
		    	   }else{
		    		   isLoginLdapPass = "NONEROLE";  
		    		   isUserView = null;
		    	   } 
		    		//save user login history
		        }else{
		        	isLoginLdapPass = "FAIL";
		        	isUserView = null;
		        }
			}

		} catch (Exception e) {
			isLoginLdapPass = "FAIL";
			e.printStackTrace();
		}
		return isUserView;

	}

	/*public String doLoginProcess(String username, String password,
			String hospitalcode) {
		String isLoginLdapPass = "FAIL";
		AuthenticationLDAP voAuthenticationLDAP = new AuthenticationLDAP();
		// String messege = voAuthenticationLDAP.verifyUser("nobel",
		// "password");
		try {
			// �� external database LDAP
			if (!voAuthenticationLDAP.verifyUser(username,password, hospitalcode)) {
				isLoginLdapPass = "LDAPLOGIN";
			}
			// �� internal database
			else {

				isLoginLdapPass = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isLoginLdapPass;
	}*/
}
