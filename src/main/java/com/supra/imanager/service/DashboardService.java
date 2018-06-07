package com.supra.imanager.service;

import java.util.List;

import org.json.JSONArray;

import com.supra.imanager.bean.DashboardAnnouncement;
import com.supra.imanager.bean.DashboardPanel;
import com.supra.imanager.bean.DashboardQuickLink;
import com.supra.imanager.dto.RestPanelStatusName;

public interface DashboardService {

	List<RestPanelStatusName> getUserAllowedPanelsWithStatus(String username);

	List<DashboardQuickLink> getQuickLink(String username);

	List<DashboardAnnouncement> getAnnouncements(String username);

	JSONArray fetchUserOwnItemCount(String username);

	JSONArray fetchOtherUsersItemCount(String username);

	void getPanelData(JSONArray jsonarr, List<RestPanelStatusName> dashboardDataList, String panelCode);

	List<DashboardPanel> preparePanelResponse(List<RestPanelStatusName> dashboardDataList);

}
