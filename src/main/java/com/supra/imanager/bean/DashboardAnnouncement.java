package com.supra.imanager.bean;

public class DashboardAnnouncement {

	String announcementId;
  	String announcementName;
    String viewMore;
    
	public String getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(String announcementId) {
		this.announcementId = announcementId;
	}

	public String getAnnouncementName() {
		return announcementName;
	}

	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}

	public String getViewMore() {
		return viewMore;
	}

	public void setViewMore(String viewMore) {
		this.viewMore = viewMore;
	}

	@Override
	public String toString() {
		return "{ announcementId:" + announcementId + ", announcementName:" + announcementName
				+ ", viewMore:" + viewMore + "}";
	}

	
	
	
	
}
