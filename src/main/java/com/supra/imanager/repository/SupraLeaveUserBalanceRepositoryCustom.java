package com.supra.imanager.repository;

public interface SupraLeaveUserBalanceRepositoryCustom {

	int changeUserLeaveBalanceUpdationFlag(String userId,String status);

	int updateLeaveBalance(String balance, String userId, Long leaveCode);


}


