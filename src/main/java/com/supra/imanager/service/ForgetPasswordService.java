package com.supra.imanager.service;

import com.supra.imanager.bean.Response;

public interface ForgetPasswordService {

	
    boolean checkUserExist(String usermail);
	void prepareMailSentResponse(String activationLinkSaved, Response mailResponse, String usermail);
	String saveActivationLink(String usermail);
	
}
