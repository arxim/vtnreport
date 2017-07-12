package com.scap.vtnreport.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;

public class MD5 {
	
	static Logger LOGGER = Logger.getLogger(MD5.class);

	public static String encrypt(String encData) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(encData.getBytes("UTF-8"));

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		return sb.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		System.out.println(MD5.encrypt("ว.1234"));
		// 0c2057d6ffb8ef368b229425c8fa848c
		// not work 971452fc63b4e3feca289df20dc2f43e
	}
	
}
