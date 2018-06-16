package com.supra.imanager.bean;

public class AnnouncementData {
	Long announcementId;
  	String announcementName;
    String Link;
    String ViewMore="view more...";
    
	public Long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Long announcementId) {
		this.announcementId = announcementId;
	}

	public String getAnnouncementName() {
		return announcementName;
	}

	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}

	public String getLink() {
		return Link;
	}

	public void setLink(String link) {
		Link = link;
	}

	public String getViewMore() {
		return ViewMore;
	}

	public void setViewMore(String viewMore) {
		ViewMore = viewMore;
	}

}
