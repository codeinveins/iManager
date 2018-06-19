package com.supra.imanager.service;

import java.text.ParseException;

import com.supra.imanager.bean.LeaveInput;
import com.supra.imanager.bean.LeaveSummary;
import com.supra.imanager.bean.TrackLeave;

public interface LeaveService {

	LeaveSummary leaveBalance(String email) throws Exception;
	int changeUserLeaveBalanceUpdationFlag(String userId, String status);
	boolean createNewLeaveRequest(LeaveInput leaveInput, String username) throws ParseException;
	TrackLeave trackLeave(String userId, String whome);
	int updateLeaveRemarkAndStatus(String requestNumber, String approveFlag, String string, String approverUserId, String currentDate);
}
