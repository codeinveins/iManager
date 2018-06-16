package com.supra.imanager.bean;

import java.util.ArrayList;
import java.util.List;

public class LeaveSummary implements ResponseMarker{
	
	private String flag="Y";
	private String requestNumber="LEAVE112-0006";
	private List<LeaveInfo> balanceLeaves = new ArrayList<>();
	
	
	
	public LeaveSummary() {
		LeaveInfo l1 = new LeaveInfo();
		LeaveInfo l2 = new LeaveInfo();
		balanceLeaves.add(l1);
		balanceLeaves.add(l2);
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getRequestNumber() {
		return requestNumber;
	}
	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}
	public List<LeaveInfo> getBalanceLeaves() {
		return balanceLeaves;
	}
	public void setBalanceLeaves(List<LeaveInfo> balanceLeaves) {
		this.balanceLeaves = balanceLeaves;
	}
	
}

  
