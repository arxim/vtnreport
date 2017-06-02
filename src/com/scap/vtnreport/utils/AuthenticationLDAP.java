package com.scap.vtnreport.utils;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

public class AuthenticationLDAP {
	static String LDAP_SERVER_URL = "ldap://ldap.forumsys.com:389";
	static String USER_CONTEXT = "dc=example,dc=com";

	public static void main(String[] args) {
		verifyUser("nobel", "password");
	}

	public static void verifyUser(String userName, String password) {
		DirContext ctx = null;
		try {
			// creating environment for initial context
			Hashtable<String, String> env = new Hashtable<String, String>(); 
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL, LDAP_SERVER_URL);
			env.put(Context.SECURITY_AUTHENTICATION, "simple");
			env.put(Context.SECURITY_PRINCIPAL, "uid=" + userName + "," + USER_CONTEXT);
			env.put(Context.SECURITY_CREDENTIALS, password);

			// Create the initial context
			ctx = new InitialDirContext(env);
			System.out.println("Authenticated: " + (ctx != null));

			// get more attributes about this user
			SearchControls scs = new SearchControls();
			scs.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String[] attrNames = { "mail", "cn" };
			scs.setReturningAttributes(attrNames);

			NamingEnumeration nes = ctx.search(USER_CONTEXT, "uid=" + userName, scs);
			if (nes.hasMore()) {
				Attributes attrs = ((SearchResult) nes.next()).getAttributes();
				System.out.println("mail: " + attrs.get("mail").get());
				System.out.println("cn: " + attrs.get("cn").get());
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
	}
}
