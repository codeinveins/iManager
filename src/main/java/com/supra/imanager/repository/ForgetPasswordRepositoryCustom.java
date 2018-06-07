package com.supra.imanager.repository;


public interface ForgetPasswordRepositoryCustom {
	String checkUserExist(String usermail);
	String saveActivationLinkInDb(String email, String temp);
}
