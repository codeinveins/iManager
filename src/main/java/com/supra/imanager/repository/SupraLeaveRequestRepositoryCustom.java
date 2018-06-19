package com.supra.imanager.repository;

import java.util.List;

import com.supra.imanager.dto.SupraLeaveRequest;

public interface SupraLeaveRequestRepositoryCustom {

	public String getRequestNumber(String username);
	int updateLeaveRemarkAndStatus(String requestNumber, String status, String remark, String approverUserId, String currentDate);
	List<SupraLeaveRequest> findByOthersId(String userId);
	
}
