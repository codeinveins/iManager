package com.supra.imanager.bean;

import java.util.List;

public class DashboardAnnouncement {

	
	String panelName;
	private String panelUrl;
	List<AnnouncementData> announcementData;
	
	public String getPanelName() {
		return panelName;
	}
	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}
	public String getPanelUrl() {
		return panelUrl;
	}
	public void setPanelUrl(String panelUrl) {
		this.panelUrl = panelUrl;
	}
	public List<AnnouncementData> getAnnouncementData() {
		return announcementData;
	}
	public void setAnnouncementData(List<AnnouncementData> announcementData) {
		this.announcementData = announcementData;
	}
	
	
	

	/*@Override
	public String toString() {
		return "{ announcementId:" + announcementId + ", announcementName:" + announcementName
				+ ", viewMore:" + viewMore + "}";
	}*/

	
	
	
	
}
