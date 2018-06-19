package com.supra.imanager.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AppliedLeaveDetails {

/*	private String leaveType = "PL";
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern="yyyy-MM-dd")
	private Date startDate = new Date();
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate = new Date();
	private String fullOrHalfDay = "Full Day";
	private Float days = new Float(1.5);
	private String purpose = "Dummy purpose";*/
	
	private String leaveType;
	
	@DateTimeFormat(iso=DateTimeFormat.ISO.DATE, pattern="yyyy-MM-dd")
	private Date startDate ;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date endDate;
	private String fullOrHalfDay ;
	private Float days ;
	private String purpose;
	
	

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
