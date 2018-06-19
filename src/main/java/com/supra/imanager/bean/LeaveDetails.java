package com.supra.imanager.bean;

import java.util.Date;
import java.util.List;

public class LeaveDetails {

	private String requestNumber ;
	private Date createdOn ;
	private Float leaveDays ;
	private String status ;
	private String remarkApprover ;
	private String lastModifiedBy ;
	private List<AppliedLeaveDetails> appliedLeaveDetails ;
	
	public LeaveDetails() {}
	
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
