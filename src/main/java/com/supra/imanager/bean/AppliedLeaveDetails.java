package com.supra.imanager.bean;

import java.util.Date;

public class AppliedLeaveDetails {

	private String leaveType = "PL";
	private Date startDate = new Date();
	private Date endDate = new Date();
	private String fullOrHalfDay = "Full Day";
	private Float days = new Float(1.5);
	private String purpose = "Dummy purpose";

	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getFullOrHalfDay() {
		return fullOrHalfDay;
	}
	public void setFullOrHalfDay(String fullOrHalfDay) {
		this.fullOrHalfDay = fullOrHalfDay;
	}
	public Float getDays() {
		return days;
	}
	public void setDays(Float days) {
		this.days = days;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
}
