package com.supra.imanager.service;

import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supra.imanager.bean.Response;

public interface LoginService {
	
	LoginResult isValidUser(LoginInput loginInput, String ipAddress);

	void insertIntoLoginHistory(LoginResult loginResult, boolean b);
	
	void prepareLoginReponse(LoginResult loginResult, LoginInput loginInput, Response restResponse);

	boolean isUserExists(String email);

}
