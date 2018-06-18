package com.supra.imanager.bean;

import java.util.ArrayList;
import java.util.List;

public class TrackLeave implements ResponseMarker{

	private List<String> leaveStatuses = new ArrayList<>();
	private List<LeaveDetails> leaveDetails = new ArrayList<>();

	public TrackLeave() {
		leaveStatuses.add("Approval Pending");
		leaveStatuses.add("Approved");
		leaveStatuses.add("Rejected");
		leaveStatuses.add("Cancelled");
		leaveStatuses.add("Leave Reversed");
		LeaveDetails l1 = new LeaveDetails();
		LeaveDetails l2 = new LeaveDetails();
		leaveDetails.add(l1);
		leaveDetails.add(l2);
	}
	
	public List<String> getLeaveStatuses() {
		return leaveStatuses;
	}
	public void setLeaveStatuses(List<String> leaveStatuses) {
		this.leaveStatuses = leaveStatuses;
	}
	public List<LeaveDetails> getLeaveDetails() {
		return leaveDetails;
	}
	public void setLeaveDetails(List<LeaveDetails> leaveDetails) {
		this.leaveDetails = leaveDetails;
	}
	
	
	
}
