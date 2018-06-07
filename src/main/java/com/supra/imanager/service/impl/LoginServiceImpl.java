package com.supra.imanager.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.supra.imanager.bean.LoginInput;
import com.supra.imanager.bean.LoginResult;
import com.supra.imanager.bean.Response;
import com.supra.imanager.dto.RestToken;
import com.supra.imanager.repository.TokenRepository;
import com.supra.imanager.repository.UserRepository;
import com.supra.imanager.service.LoginService;
import com.supra.imanager.utilities.ApplicationUtilities;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private TokenRepository tokenRepository;
	
	@Value("${app.domainUrl}")
	private String domainUrl;

	@Override
	public LoginResult isValidUser(LoginInput loginInput, String ipAddress) {

		LoginResult loginResult = new LoginResult();

		Object[] data = userRepository.fetchUserData(loginInput.getUsername());
		
		if(null != data) {
			final String SALT = "supra-its-text";
			try {
				String saltedPassword = SALT + loginInput.getPassword();
				String hashedPassword = ApplicationUtilities.generateHash(saltedPassword);
				final String finalHashedPassword = hashedPassword;

				if (finalHashedPassword.equals(data[9])) {
					String token = ApplicationUtilities.generateToken();
					if (null != saveToken(token, loginInput, loginResult).getIdrestToken()) {
					
						loginResult.setEmpId(String.valueOf(data[0]));
						loginResult.setEmpName(String.valueOf(data[1]));
						loginResult.setManagerId(String.valueOf(data[2]));
						loginResult.setManagerName(String.valueOf(data[3]));
						loginResult.setHrmId(String.valueOf(data[4]));
						loginResult.setHrmName(String.valueOf(data[5]));
						loginResult.setPrimaryEmail(String.valueOf(data[6]));
						loginResult.setFirstname(String.valueOf(data[7]));
						loginResult.setLastname(String.valueOf(data[8]));
						loginResult.setPassword("hidden - security reasons");
						loginResult.setRole(String.valueOf(data[10]));
						loginResult.setUserGroup(String.valueOf(data[11]));
						loginResult.setUserStatus(String.valueOf(data[12]));
						loginResult.setAccountUnit(String.valueOf(data[13]));
						loginResult.setBaseLocation(String.valueOf(data[14]));
						loginResult.setDesignation(String.valueOf(data[15]));
						loginResult.setDepartment(String.valueOf(data[16]));
						loginResult.setPolicyGroup(String.valueOf(data[17]));
						loginResult.setUserCode(String.valueOf(data[18]));
						loginResult.setClientId(Integer.valueOf(String.valueOf(data[19])));
						loginResult.setLoginMessage("Login Successfull");
						loginResult.setLoggedInUserProxy("");
						loginResult.setToken(token);
						
						String imagePath = userRepository.fetchProfileImage(loginResult.getEmpId());
						if(imagePath == null) {
							imagePath = domainUrl+"/profileImages/sample.png";
						}
						
						loginResult.setProfileImagePath(imagePath);
					}
				} else {
					loginResult.setLoginMessage("Invalid credential");
				}
			} catch (Exception e) {
				e.printStackTrace();
				loginResult.setLoginMessage("Contact administrator");
			}	
		}
		else {
			loginResult.setLoginMessage("Username is not registered with us.");
		}

		return loginResult;
	}

	public void prepareLoginReponse(LoginResult loginResult, LoginInput loginInput, Response restResponse) {
		if (loginResult.getLoginMessage().equalsIgnoreCase("Login Successfull") && loginInput != null) {
			if (!("Active".equalsIgnoreCase(loginResult.getUserStatus()))) {
				restResponse.setResponseCode(HttpStatus.OK.value());
				restResponse.setResponseMessage("Kindly ask your Manager to activate your id!!");
				restResponse.setResponseData(loginResult);
			} else {
				insertIntoLoginHistory(loginResult, true);
				// setting data in session as it is used in further methods in service
				//				setDataInSession(request, session, loginResult);
				restResponse.setResponseCode(HttpStatus.OK.value());
				restResponse.setResponseMessage(loginResult.getLoginMessage());
				restResponse.setResponseData(loginResult);
			}
		} else {
			restResponse.setResponseCode(HttpStatus.UNAUTHORIZED.value());
			restResponse.setResponseMessage(loginResult.getLoginMessage());
			restResponse.setResponseData(loginResult);
		}
	}

	public RestToken saveToken(String token, LoginInput loginBean, LoginResult loginResult) {

		RestToken tokenObj = new RestToken();
		tokenObj.setToken(token);
		tokenObj.setDeviceId(loginBean.getDeviceId());
		tokenObj.setDeviceName(loginBean.getDeviceType());
		tokenObj.setUserName(loginBean.getUsername());
		
		return tokenRepository.save(tokenObj);
	}

	@Override
	public void insertIntoLoginHistory(LoginResult loginResult, boolean b) {

	}



}
