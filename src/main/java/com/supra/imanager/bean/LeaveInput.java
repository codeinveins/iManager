package com.supra.imanager.bean;

import java.util.ArrayList;
import java.util.List;

public class LeaveInput implements ResponseMarker{

	private String requestNumber = "LEAVE112-0006";
	List<AppliedLeaveDetails> appliedLeaves = new ArrayList<>();
	
	public LeaveInput() {
		AppliedLeaveDetails a1 = new AppliedLeaveDetails();
		AppliedLeaveDetails a2 = new AppliedLeaveDetails();
		appliedLeaves.add(a1);
		appliedLeaves.add(a2);
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public List<AppliedLeaveDetails> getAppliedLeaves() {
		return appliedLeaves;
	}

	public void setAppliedLeaves(List<AppliedLeaveDetails> appliedLeaves) {
		this.appliedLeaves = appliedLeaves;
	}

	
	
}
