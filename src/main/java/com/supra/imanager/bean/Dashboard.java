package com.supra.imanager.bean;

import java.util.List;


public class Dashboard implements ResponseMarker {
	
   List<DashboardPanel>  panellList; 
   DashboardAnnouncement announcementBean;
   DashboardQuickLink  quickLinkBean;
	
	public List<DashboardPanel> getPanellList() {
		return panellList;
	}
	public void setPanellList(List<DashboardPanel> panellList) {
		this.panellList = panellList;
	}
	public DashboardAnnouncement getAnnouncementBean() {
		return announcementBean;
	}
	public void setAnnouncementBean(DashboardAnnouncement announcementBean) {
		this.announcementBean = announcementBean;
	}
	public DashboardQuickLink getQuickLinkBean() {
		return quickLinkBean;
	}
	public void setQuickLinkBean(DashboardQuickLink quickLinkBean) {
		this.quickLinkBean = quickLinkBean;
	}

	
	
	
	
}
