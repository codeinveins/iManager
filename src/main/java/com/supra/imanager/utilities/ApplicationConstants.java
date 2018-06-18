package com.supra.imanager.utilities;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class ApplicationConstants {

	public static final String PROFILE_IMAGES_PATH = "resources/profileimages/";
	
	public static final String PANEL_CODE_SELF="SELF";
	public static final String PANEL_CODE_OTHERS="OTHERS";
	
	public static Map<String, Integer> LEAVE_CODE_MAP = new HashMap<>();
	
	
	@PostConstruct
	public void postContructConstants() {
		System.out.println("Post Constructing : Constants");
		LEAVE_CODE_MAP.put("PL", 1);
		LEAVE_CODE_MAP.put("CF", 2);
		LEAVE_CODE_MAP.put("LWP", 5);
		LEAVE_CODE_MAP.put("BRV", 21);
		LEAVE_CODE_MAP.put("tt", 23);
	}
}
