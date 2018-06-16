package com.supra.imanager.service;

import com.supra.imanager.bean.Response;
import com.supra.imanager.dto.SupraPasswordActivation;

public interface UserService {

	
	Response prepareMailSentResponse(String activationLinkSaved, Response mailResponse, String usermail);
	String saveActivationLink(String usermail);
	
}
