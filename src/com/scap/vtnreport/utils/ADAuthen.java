package com.scap.vtnreport.utils;

import java.util.Hashtable;
import java.util.Map;
import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.apache.log4j.Logger;
import com.scap.vtnreport.utils.ReadProperties;

public class ADAuthen {
	
	final static Logger logger = Logger.getLogger(ADAuthen.class);
	
	private static final String CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";
	private static String ldapUrl;
	
	/*
	 * Main process for LDAP Authen
	 * @accountName for username login
	 * @accountPassword for password login
	 */
	public String processADAuthen(String accountName, String accountPassword) {
		String process = "N";
		try {
			System.out.println();
			System.out.println();
			System.out.println("#############################");
			System.out.println("AD Authen");
			System.out.println("#############################");
			System.out.println();

			// Admin search directory
			ReadProperties prop = new ReadProperties();
			Map<String, String>  mData = prop.getDataReadPropertiesFile("ldap.properties");
			
			ldapUrl = mData.get("ldap.url");
			String userDn = mData.get("ldap.user_dn");
			String password = mData.get("ldap.password");
			
			if ((ldapUrl == null) || (userDn == null) || (password == null) || (accountName == null)) {
				logger.debug("##### missing data from conf.properties file #####");
			}
			Hashtable<String, Object> env = new Hashtable();
			env.put("java.naming.security.authentication", "simple");
			env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
			env.put("java.naming.security.principal", userDn);
			env.put("java.naming.security.credentials", password);
			env.put("java.naming.provider.url", ldapUrl);
			
			DirContext ctx = new InitialDirContext(env);
			logger.debug("Ldap Connection to server success !!!");
			String dn = findAccountByAccountName(ctx, accountName);
			
			if (dn != null) {
				if (testBindUserDN(dn, accountPassword)) {
					logger.debug("##### Authentication success #####");
					process = "Y";
				} else {
					logger.debug("##### Authentication failed #####");
					// Fail Password
					process = "U";
				}
			} else {
				logger.debug("##### AD Not found #####");
			}
		} catch (Exception e) {
			logger.error("##### Exception #####", e);
		}
		return process;
	}

	/*
	 * Find Account with account name / sAMAccountName (Logon Name)
	 */
	private String findAccountByAccountName(DirContext ctx, String accountName) throws NamingException {
		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + ")(memberof=CN=DF-Doctor,CN=Users,DC=vejthani,DC=com))";

		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(2);

		NamingEnumeration<SearchResult> results = ctx.search("", searchFilter, searchControls);
		String dn;
		if (results.hasMore()) {
			SearchResult result = (SearchResult) results.next();
			dn = result.getNameInNamespace();
		} else {
			dn = null;
		}
		results.close();
		return dn;
	}
	
	/*
	 * Pass by value with User DN from Search filter
	 */
	private static boolean testBindUserDN(String dn, String password) throws Exception {
		Hashtable<String, String> env = new Hashtable();
		env.put("java.naming.security.authentication", "simple");
		env.put("java.naming.security.principal", dn);
		env.put("java.naming.security.credentials", password);
		
		try {
			ldapContext(env);
		} catch (AuthenticationException e) {
			return false;
		}
		return true;
	}
	
	/*
	 * Initial Dir Context for check
	 */
	private static DirContext ldapContext(Hashtable<String, String> env) throws Exception {
		env.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");
		env.put("java.naming.provider.url", ldapUrl);
		DirContext ctx = new InitialDirContext(env);
		return ctx;
	}
}
