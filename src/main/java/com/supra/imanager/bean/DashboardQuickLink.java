package com.supra.imanager.bean;

import java.util.List;

public class DashboardQuickLink {


	String panelName;
	private String panelUrl;
    List<QuickLinkData> quickLinkData;
	
    
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
	public List<QuickLinkData> getQuickLinkData() {
		return quickLinkData;
	}
	public void setQuickLinkData(List<QuickLinkData> quickLinkData) {
		this.quickLinkData = quickLinkData;
	}
    
    
    
	
}
