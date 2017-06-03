package com.scap.vtnreport.service;

 
import com.scap.vtnreport.utils.AuthenticationLDAP;
import com.scap.vtnreport.utils.MD5;

public class LoginService {
	public String doLoginProcess(String username,String password,String hospitalcode) {
		String isLoginLdapPass = "FAIL";
		AuthenticationLDAP voAuthenticationLDAP = new AuthenticationLDAP();
//		String messege = voAuthenticationLDAP.verifyUser("nobel", "password");
		try {
			//àªç¤ external database  LDAP 
			String messege = voAuthenticationLDAP.verifyUser(username,  password,hospitalcode);
		    if(messege == "PASS"){
		    	isLoginLdapPass = "LDAPLOGIN";
		    } 
		    //àªç¤ internal database
		    else{
		    	
		    	
		    	isLoginLdapPass = "FAIL";
		    } 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return isLoginLdapPass;
	}
}
