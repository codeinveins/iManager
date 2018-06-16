package com.supra.imanager.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;


public class ApplicationUtilities {

	public static String generateToken() {
		return UUID.randomUUID().toString();
	}

	public static String generateHash(String input) {
		StringBuilder hash = new StringBuilder();

		try {
			MessageDigest sha = MessageDigest.getInstance("SHA-1");
			byte[] hashedBytes = sha.digest(input.getBytes());
			char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
			for (int idx = 0; idx < hashedBytes.length; ++idx) {
				byte b = hashedBytes[idx];
				hash.append(digits[(b & 0xf0) >> 4]);
				hash.append(digits[b & 0x0f]);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return hash.toString();
	}

	public static String createActivationRamdomKey() {
		String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

		SecureRandom rnd = new SecureRandom();
		StringBuilder sb = new StringBuilder(11);
		for (int i = 0; i < 11; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		String temp = new String(sb);
		return temp;
	}

}
