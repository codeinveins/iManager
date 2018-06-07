package com.supra.imanager.bean;

public class DashboardStatus {

	
	String statusName;
	int statusCount;
	private String statusUrl;
	
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public int getStatusCount() {
		return statusCount;
	}
	public void setStatusCount(int statusCount) {
		this.statusCount = statusCount;
	}
	public String getStatusUrl() {
		return statusUrl;
	}
	public void setStatusUrl(String statusUrl) {
		this.statusUrl = statusUrl;
	}

	@Override
	public String toString() {
		return "{ statusName:" + statusName + 
				", statusCount:" + statusCount
				+ "}";
	}
	
	
	
	
	
	
}
