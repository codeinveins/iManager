package com.supra.imanager.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.supra.imanager.bean.DashboardAnnouncement;
import com.supra.imanager.bean.DashboardPanel;
import com.supra.imanager.bean.DashboardQuickLink;
import com.supra.imanager.bean.DashboardStatus;
import com.supra.imanager.dto.RestPanelStatusName;
import com.supra.imanager.dto.SupraUserPanelDisplayList;
import com.supra.imanager.dto.ViewSupraUserStatus;
import com.supra.imanager.repository.RestPanelStatusNameRepository;
import com.supra.imanager.repository.RestQuickLinkRepository;
import com.supra.imanager.repository.SupraEmpCompanyNotificationRepository;
import com.supra.imanager.repository.SupraUserPanelDisplayListRepository;
import com.supra.imanager.repository.ViewSupraUserStatusRepository;
import com.supra.imanager.service.DashboardService;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Autowired
	private RestPanelStatusNameRepository dashboardPanelStatusRepository;

	@Autowired
	private RestQuickLinkRepository dashboardQuickLinkRepository;

	@Autowired
	private SupraEmpCompanyNotificationRepository dashboardAnnouncementRepository;

	@Autowired
	private ViewSupraUserStatusRepository viewSupraUserStatusRepository;

	@Autowired
	private SupraUserPanelDisplayListRepository supraUserPanelDisplayListRepository;
	
	@Value("${app.domainUrl}")
	private String domainUrl;

	@Value("${server.contextPath")
	private String contextPath;

	@Override
	public List<RestPanelStatusName> getUserAllowedPanelsWithStatus(String username) {
		List<RestPanelStatusName> panStatusList = new ArrayList<>();
		try {
			List<SupraUserPanelDisplayList> list = supraUserPanelDisplayListRepository.findByUsername(username);
			List<String> panelIdList = new ArrayList<>();
			for (SupraUserPanelDisplayList supraUserPanelDisplayList : list) {
				panelIdList.add(supraUserPanelDisplayList.getPanelid());
			}
 			panStatusList = dashboardPanelStatusRepository.fetchStatusCountByPanelIdsForUsername(panelIdList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return panStatusList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<DashboardQuickLink> getQuickLink(String username) {// get quickLink
		List<DashboardQuickLink> listQuickLink = new ArrayList<>();
		List<Object[]> quicklinkListFromDb = dashboardQuickLinkRepository.getQuickLinks(username);

		Iterator iquicklink = quicklinkListFromDb.iterator();
		while (iquicklink.hasNext()) {
			Object[] dashboardQuickLink = (Object[]) iquicklink.next();
			DashboardQuickLink link = new DashboardQuickLink();
			link.setQuickLinkId((String) dashboardQuickLink[0]);
			link.setQuickLinkName((String) dashboardQuickLink[1]);
			link.setQuickLinkUrl(domainUrl + "/" + contextPath + "resources/images/quicklink/"
					+ (String) dashboardQuickLink[0] + ".png");
			listQuickLink.add(link);
		}

		return listQuickLink;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<DashboardAnnouncement> getAnnouncements(String username) {
		List<DashboardAnnouncement> announcements = new ArrayList<>();
		List<Object[]> announcementsFromDb = dashboardAnnouncementRepository.getAnnouncementForUser(username);
		Iterator announceIterator = announcementsFromDb.iterator();
		while (announceIterator.hasNext()) {
			Object[] announceObject = (Object[]) announceIterator.next();
			DashboardAnnouncement announcement = new DashboardAnnouncement();
			announcement.setAnnouncementId((String) announceObject[0]);
			announcement.setAnnouncementName((String) announceObject[1]);
			announcement.setViewMore(domainUrl + "/" + contextPath + "resources/images/announcement/"
					+ (String) announceObject[1] + ".pdf");
		}
		return announcements;
	}

	@Override
	public JSONArray fetchUserOwnItemCount(String username) {
		JSONArray userGroupList = new JSONArray();
		try {
			List<ViewSupraUserStatus> dbList = viewSupraUserStatusRepository.findById_Username(username);
			Iterator<ViewSupraUserStatus> iterator = dbList.iterator();
			while (iterator.hasNext()) {
				ViewSupraUserStatus viewSupraUserStatus = (ViewSupraUserStatus) iterator.next();
				JSONObject tempObj = new JSONObject();
				try {
					tempObj.put("modulename", viewSupraUserStatus.getId().getModule());
					tempObj.put("count", viewSupraUserStatus.getId().getStatuscount());
					tempObj.put("statusname", viewSupraUserStatus.getId().getStatus());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				userGroupList.put(tempObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}
	
	@Override
	public JSONArray fetchOtherUsersItemCount(String username) {
		JSONArray userGroupList = new JSONArray();
		try {
			List<Object[]> dbList = viewSupraUserStatusRepository.fetchOtherUsersItemCount(username);
			Iterator<Object[]> iterator = dbList.iterator();
			while (iterator.hasNext()) {
				Object[] viewSupraUserStatus = (Object[]) iterator.next();
				JSONObject tempObj = new JSONObject();
				try {
					tempObj.put("modulename", viewSupraUserStatus[0]);
					tempObj.put("count", viewSupraUserStatus[1]);
					tempObj.put("statusname", viewSupraUserStatus[2]);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				userGroupList.put(tempObj);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;
	}

	@Override
	public List<DashboardPanel> preparePanelResponse(List<RestPanelStatusName> dashboardDataList) {

		List<DashboardPanel> panelList = new ArrayList<>();

		Iterator<RestPanelStatusName> dashboardDataIterator = dashboardDataList.iterator();
		while (dashboardDataIterator.hasNext()) {
			RestPanelStatusName paneldata = (RestPanelStatusName) dashboardDataIterator.next();

			DashboardPanel oldObject = null;
			for (int i = 0; i < panelList.size(); i++) {
				if (panelList.get(i).getPanelId().equals(paneldata.getPanelId())) {
					oldObject = panelList.get(i);
					break;
				}
			}

			if (null == oldObject) {
				DashboardPanel panel = new DashboardPanel();
				panel.setPanelId(paneldata.getPanelId());
				panel.setPanelName(paneldata.getPanelName());
				panel.setPanelUrl(domainUrl + contextPath + "/resources/images/panels/"
						+ paneldata.getPanelId() + ".png");

				List<DashboardStatus> statusList = new ArrayList<>();
				DashboardStatus status = new DashboardStatus();
				status.setStatusName(paneldata.getStatusName());
				status.setStatusCount(Integer.valueOf(paneldata.getStatuscount()));
				status.setStatusUrl(domainUrl + contextPath + "/resources/images/status/"
						+ paneldata.getStatusName() + ".png");
				
				statusList.add(status);
				panel.setStatusList(statusList);
				panelList.add(panel);
			} else {
				List<DashboardStatus> dashboardStatusList = oldObject.getStatusList();
				DashboardStatus status = new DashboardStatus();
				status.setStatusName(paneldata.getStatusName());
				status.setStatusCount(Integer.valueOf(paneldata.getStatuscount()));
				status.setStatusUrl(domainUrl + contextPath + "/resources/images/status/"
						+ paneldata.getStatusName() + ".png");

				dashboardStatusList.add(status);
			}
		}
		return panelList;
	}
	
	@Override
	public void getPanelData(JSONArray jsonarr, List<RestPanelStatusName> dashboardDataList, String panelCode) {
		for (int i = 0; i < jsonarr.length(); i++) {
			try {
				JSONObject jsonObject = jsonarr.getJSONObject(i);

				String jsonObjectmoduleName = jsonObject.getString("modulename");
				String jsonObjectcount = jsonObject.getString("count");
				String jsonObjectstatusName = jsonObject.getString("statusname");

				Iterator<RestPanelStatusName> dashboardDataIterator = dashboardDataList.iterator();
				while (dashboardDataIterator.hasNext()) {
					RestPanelStatusName statusBean = (RestPanelStatusName) dashboardDataIterator.next();
					if (statusBean.getModuleName().equalsIgnoreCase(jsonObjectmoduleName)
							&& (statusBean.getPanelCode().equalsIgnoreCase(panelCode))) {
						if (statusBean.getStatusName().equalsIgnoreCase(jsonObjectstatusName)) {
							statusBean.setStatuscount(jsonObjectcount);
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}