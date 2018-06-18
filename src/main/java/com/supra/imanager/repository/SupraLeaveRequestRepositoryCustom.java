package com.supra.imanager.repository;

public interface SupraLeaveRequestRepositoryCustom {

	public String getRequestNumber(String username);
	int updateLMSRemarkAndStatus(String reqNumber, String approveFlag, String pendingstatus, String remark);
}
