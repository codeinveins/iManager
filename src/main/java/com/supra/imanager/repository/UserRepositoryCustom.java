package com.supra.imanager.repository;

public interface UserRepositoryCustom {

	Object[] fetchUserData(String email);
	String fetchProfileImage(String empId);

}
