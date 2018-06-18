package com.supra.imanager.service;

import java.text.ParseException;
import java.util.List;

import com.supra.imanager.bean.LeaveInput;
import com.supra.imanager.bean.LeaveSummary;
import com.supra.imanager.bean.TrackLeave;
import com.supra.imanager.dto.SupraLeaveRequest;

public interface LeaveService {

	LeaveSummary leaveBalance(String email) throws Exception;
	int updateLMSRemarkAndStatus(String reqNumber, String approveFlag, String string, String remark);
	int changeUserLeaveBalanceUpdationFlag(String userId, String status);
	boolean createNewLeaveRequest(LeaveInput leaveInput, String username) throws ParseException;
	List<TrackLeave> trackLeave(String userId, String whome);
}
