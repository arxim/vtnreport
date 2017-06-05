package com.scap.vtnreport.service;

import javax.servlet.http.HttpServletRequest;

import com.scap.vtnreport.dao.UserDao;
import com.scap.vtnreport.model.UserView;
import com.scap.vtnreport.utils.AuthenticationLDAP;
import com.scap.vtnreport.utils.MD5;
import com.scap.vtnreport.utils.AesUtil;
import com.scap.vtnreport.utils.StringUtils;

public class LoginService {
	public String doLoginProcess(HttpServletRequest request) {
		String isLoginLdapPass = "FAIL";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String hospitalcode = request.getParameter("hospitalcode");
		String passphrase = request.getParameter("hidPassphrase");
		String iv = request.getParameter("hidIv");
		String salt = request.getParameter("hidSalt");
		int iterationCount = Integer.parseInt(request.getParameter("hidIterationCount"));
		int keySize = Integer.parseInt(request.getParameter("hidKeySize"));
		String view = null;

		try {
			AesUtil aesUtil = new AesUtil(keySize, iterationCount);

			// decrypt aes from passphrase session
			String decryptPwd = aesUtil.decrypt(salt, iv, passphrase, password);

			if (StringUtils.isEmpty(username, password, hospitalcode, passphrase, iv, salt)) {
				isLoginLdapPass = "FAIL";
			} else {
				// àªç¤ external database LDAP
				AuthenticationLDAP voAuthenticationLDAP = new AuthenticationLDAP();
				
				if (voAuthenticationLDAP.verifyUser(username,decryptPwd, hospitalcode)) {
					
					isLoginLdapPass = "LDAPLOGIN";
					//save user login history
				}
				// àªç¤ internal database
				else {
					// encryption password, before authentication
				   UserDao userDao = new UserDao();
			       UserView isUser = userDao.getUser(username, MD5.encrypt(decryptPwd), hospitalcode);
			       if(isUser != null){
			    	   if (isUser != null && isUser.getRoleId() != null) {
			    		   isLoginLdapPass = isUser.getRoleId();
			    	   }else{
			    		   isLoginLdapPass = "NONEROLE";   
			    	   } 
			    		//save user login history
			        }else{
			        	isLoginLdapPass = "FAIL";
			        }
					 

				 
				}

			}
		} catch (Exception e) {
			isLoginLdapPass = "FAIL";
			e.printStackTrace();
		}

		return isLoginLdapPass;

	}

	/*public String doLoginProcess(String username, String password,
			String hospitalcode) {
		String isLoginLdapPass = "FAIL";
		AuthenticationLDAP voAuthenticationLDAP = new AuthenticationLDAP();
		// String messege = voAuthenticationLDAP.verifyUser("nobel",
		// "password");
		try {
			// àªç¤ external database LDAP
			if (!voAuthenticationLDAP.verifyUser(username,password, hospitalcode)) {
				isLoginLdapPass = "LDAPLOGIN";
			}
			// àªç¤ internal database
			else {

				isLoginLdapPass = "FAIL";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return isLoginLdapPass;
	}*/
}
