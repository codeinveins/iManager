package com.supra.imanager.bean;

import java.util.List;


public class Dashboard implements ResponseMarker {
	
	List<DashboardPanel>  panellList; 
    List<DashboardAnnouncement> announcementBean;
    List<DashboardQuickLink>  quickLinkBean;
	
	public List<DashboardPanel> getPanellList() {
		return panellList;
	}
	public void setPanellList(List<DashboardPanel> panellList) {
		this.panellList = panellList;
	}
	
	
	public List<DashboardAnnouncement> getAnnouncementBean() {
		return announcementBean;
	}
	public void setAnnouncementBean(List<DashboardAnnouncement> announcementBean) {
		this.announcementBean = announcementBean;
	}
	public List<DashboardQuickLink> getQuickLinkBean() {
		return quickLinkBean;
	}
	public void setQuickLinkBean(List<DashboardQuickLink> quickLinkBean) {
		this.quickLinkBean = quickLinkBean;
	}
	
	@Override
	public String toString() {
		return "{ panellList:" + panellList + ", "
				+ "announcementBean:" + announcementBean +
				", quickLinkBean:"+ quickLinkBean + "}";
	}
	
	
	
}
