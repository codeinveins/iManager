package com.supra.imanager.bean;

public class LeaveInfo {
	
	 
	private String leaveShortName="PL";
    private String leaveFullName="Paid Leave";
    private float leaveBalance=5;
    
	public LeaveInfo() { }

	public LeaveInfo(String leaveShortName, String leaveFullName, Float float1) {
		this.leaveShortName = leaveShortName;
		this.leaveFullName = leaveFullName;
		this.leaveBalance = float1;
	}
	public String getLeaveShortName() {
		return leaveShortName;
	}
	public void setLeaveShortName(String leaveShortName) {
		this.leaveShortName = leaveShortName;
	}
	public String getLeaveFullName() {
		return leaveFullName;
	}
	public void setLeaveFullName(String leaveFullName) {
		this.leaveFullName = leaveFullName;
	}

	public float getLeaveBalance() {
		return leaveBalance;
	}

	public void setLeaveBalance(float leaveBalance) {
		this.leaveBalance = leaveBalance;
	}
	
}
