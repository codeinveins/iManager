package com.supra.imanager.service;

import com.supra.imanager.bean.LeaveSummary;

public interface LeaveService {

	LeaveSummary leaveBalance(String email) throws Exception;
	int changeUserLeaveBalanceUpdationFlag(int usercode, String status2);
	int updateLMSRemarkAndStatus(String reqNumber, String approveFlag, String string, String remark);
}
