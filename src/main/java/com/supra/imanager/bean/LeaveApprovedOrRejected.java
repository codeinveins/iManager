package com.supra.imanager.bean;

public class LeaveApprovedOrRejected implements ResponseMarker {

	private String requestNumber = "LEAVE112-0006";
	private Boolean approveFlag = true;
	/*private String requestStatus = "Approval Pending";*/
	private String remarks = "Dummy remarks";
	
	
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public Boolean getApproveFlag() {
		return approveFlag;
	}
	public void setApproveFlag(Boolean approveFlag) {
		this.approveFlag = approveFlag;
	}
	/*public String getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}*/
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
