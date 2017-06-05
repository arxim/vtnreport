package com.scap.vtnreport.utils;

import java.util.Hashtable;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class AuthenticationLDAP {
//	static String LDAP_SERVER_URL = "ldap://ldap.forumsys.com:389";
//	static String USER_CONTEXT = "dc=example,dc=com";

//	public static void main(String[] args) {
//		AuthenticationLDAP voAuthenticationLDAP = new AuthenticationLDAP();
//		String messege = voAuthenticationLDAP.verifyUser("nobel", "password");
//	}
    //PASS มีตัวตนในระบบ
	//FAIL ไม่มีตัวตนในระบบ
	public String verifyUser(String userName, String password,String hospitalcode) {
		DirContext ctx = null;
		ReadProperties prop = new ReadProperties();
		String message = "FAIL";
		try {
			Map<String, String>  propData = prop.getDataReadPropertiesFile("db.properties");
			String userContext = propData.get("ldap.user.context");
			String initailContextFactory = propData.get("ldap.initail.context.factory");
			String serverUrl = propData.get("ldap.server.url");
			
			// creating environment for initial context
			Hashtable<String, String> env = new Hashtable<String, String>(); 
			env.put(Context.INITIAL_CONTEXT_FACTORY, initailContextFactory);
			env.put(Context.PROVIDER_URL,serverUrl);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, "uid=" + userName + "," +  userContext);
			env.put(Context.SECURITY_CREDENTIALS, password);

			// Create the initial context
			ctx = new InitialDirContext(env);
			System.out.println("Authenticated: " + (ctx != null));

			// get more attributes about this user
			SearchControls scs = new SearchControls();
			scs.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrNames = { "mail", "cn" };
			scs.setReturningAttributes(attrNames);

			NamingEnumeration nes = ctx.search(userContext, "uid=" + userName, scs);
			if (nes.hasMore()) {
				Attributes attrs = ((SearchResult) nes.next()).getAttributes();
				System.out.println("mail: " + attrs.get("mail").get());
				System.out.println("cn: " + attrs.get("cn").get());
				message = "PASS";
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			if (ctx != null)
				try {
					ctx.close();
				} catch (NamingException ex) {
				}
		}
		return message;
	}
}
