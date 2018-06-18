package com.supra.imanager.repository;

import java.util.List;

import com.supra.imanager.dto.SupraLeaveRequest;

public interface SupraLeaveRequestRepositoryCustom {

	public String getRequestNumber(String username);
	int updateLMSRemarkAndStatus(String reqNumber, String approveFlag, String pendingstatus, String remark);
	List<SupraLeaveRequest> findByUsername(String userId);
}
