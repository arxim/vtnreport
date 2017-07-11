package com.scap.vtnreport.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static String encrypt(String encData) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(encData.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
		return sb.toString();
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(MD5.encrypt("ว.1234"));
		// 0c2057d6ffb8ef368b229425c8fa848c
	}
	
}
