package com.supra.imanager.utilities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class ApplicationUtilities {

	public static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
	
	public static String getCurrentDateInString() {
		return dateFormat.format(new Date());
	}
	
	public static Date getCurrentDate(Date date) throws ParseException {
		Date dateToReturn = null;
		if(null != date) {
			dateToReturn = dateFormat.parse(dateFormat.format(date));
		}
		else {
			dateToReturn = dateFormat.parse(dateFormat.format(new Date()));
		}
		
		return dateToReturn;
	}

	public static String getCurrentYear() {
		Calendar now = Calendar.getInstance();  
		int currYear = now.get(Calendar.YEAR);
		return String.valueOf(currYear);
	}
	
	public static double getTotalDays(Date startDate, Date endDate, String fullOrHalfDay) throws ParseException {
		double totalDays = 0;
		Date startDateCalculated = getCurrentDate(startDate);
	    Date endDateCalculated = getCurrentDate(endDate);
	    long diff = endDateCalculated.getTime() - startDateCalculated.getTime();
		long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
		if(!(fullOrHalfDay.equalsIgnoreCase("Full Day")))
	    	totalDays = totalDays + 0.5 *((double) days);
	    else
	    	totalDays = totalDays + (double) days;
		
		return totalDays;
	}
}
