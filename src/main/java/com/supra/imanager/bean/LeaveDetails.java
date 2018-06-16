package com.supra.imanager.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveDetails {

	private String requestNumber = "LEAVE112-0006";
	private Date createdOn = new Date();
	private Float leaveDays = new Float(1.5);
	private String status = "Approved";
	private String remarkApprover = "Approver remarks";
	private String lastModifiedBy = "80";
	private List<AppliedLeaveDetails> appliedLeaveDetails = new ArrayList<>();
	
	public LeaveDetails() {
		AppliedLeaveDetails a1 = new AppliedLeaveDetails();
		AppliedLeaveDetails a2 = new AppliedLeaveDetails();
		appliedLeaveDetails.add(a1);
		appliedLeaveDetails.add(a2);
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Float getLeaveDays() {
		return leaveDays;
	}

	public void setLeaveDays(Float leaveDays) {
		this.leaveDays = leaveDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarkApprover() {
		return remarkApprover;
	}

	public void setRemarkApprover(String remarkApprover) {
		this.remarkApprover = remarkApprover;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public List<AppliedLeaveDetails> getAppliedLeaveDetails() {
		return appliedLeaveDetails;
	}

	public void setAppliedLeaveDetails(List<AppliedLeaveDetails> appliedLeaveDetails) {
		this.appliedLeaveDetails = appliedLeaveDetails;
	}
	
}
