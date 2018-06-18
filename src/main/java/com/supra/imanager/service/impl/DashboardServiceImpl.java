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

import com.supra.imanager.bean.AnnouncementData;
import com.supra.imanager.bean.DashboardAnnouncement;
import com.supra.imanager.bean.DashboardPanel;
import com.supra.imanager.bean.DashboardQuickLink;
import com.supra.imanager.bean.DashboardStatus;
import com.supra.imanager.bean.QuickLinkData;
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
	private RestPanelStatusNameRepository restPanelStatusNameRepository;

	@Autowired
	private RestQuickLinkRepository restQuickLinkRepository;

	@Autowired
	private SupraEmpCompanyNotificationRepository supraEmpCompanyNotificationRepository;

	@Autowired
	private ViewSupraUserStatusRepository viewSupraUserStatusRepository;

	@Autowired
	private SupraUserPanelDisplayListRepository supraUserPanelDisplayListRepository;
	
	@Value("${app.domainUrl}")
	private String domainUrl;

	@Value("${server.contextPath}")
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
 			panStatusList = restPanelStatusNameRepository.fetchStatusCountByPanelIdsForUsername(panelIdList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return panStatusList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public DashboardQuickLink getQuickLink(String username) {
		List<Object[]> quicklinkListFromDb = restQuickLinkRepository.getQuickLinks(username);
        Iterator iquicklink = quicklinkListFromDb.iterator();
		DashboardQuickLink link = new DashboardQuickLink();
		link.setPanelName("Quick Link");
		link.setPanelUrl(domainUrl + "/images/quicklink/quickicon" + ".png");
		List<QuickLinkData> datas= new ArrayList<>();
		
		while (iquicklink.hasNext()) {
			Object[] dashboardQuickLink = (Object[]) iquicklink.next();
			
			QuickLinkData quickLinkData = new QuickLinkData();
			quickLinkData.setQuickLinkId((String) dashboardQuickLink[0]);
			quickLinkData.setQuickLinkName((String) dashboardQuickLink[1]);
			quickLinkData.setQuickLinkUrl(domainUrl + "/images/quicklink/"
					+ (String) dashboardQuickLink[0] + ".png");
			datas.add(quickLinkData);
		}
		link.setQuickLinkData(datas);
		return link;
	}

	
	
	@SuppressWarnings("rawtypes")
	@Override
	public DashboardAnnouncement getAnnouncements(String username) {
		//DashboardAnnouncement announcements = new DashboardAnnouncement();
		List<Object[]> announcementsFromDb = supraEmpCompanyNotificationRepository.getAnnouncementForUser(username);
		Iterator announceIterator = announcementsFromDb.iterator();
		
		DashboardAnnouncement announcement = new DashboardAnnouncement();
		
		announcement.setPanelName("Announcement");
		announcement.setPanelUrl(domainUrl + "/images/announcement/announcementicon"+ ".png");
		
		List<AnnouncementData> datas= new ArrayList<>();
		
		while (announceIterator.hasNext()) {
			Object[] announceObject = (Object[]) announceIterator.next();
			
			AnnouncementData announcementData= new AnnouncementData();
			//announcementData.setAnnouncementId(Long.valueOf(announceObject[0].toString()));
			//after discuession they want fixed ids for all PDF ie. 8
			announcementData.setAnnouncementId(Long.valueOf(announceObject[0].toString()));
			announcementData.setAnnouncementName((String) announceObject[1]);
			announcementData.setLink(domainUrl + "/images/announcement/"
					+ (String) announceObject[1] + announceObject[2]);
			datas.add(announcementData);
		}
		
		announcement.setAnnouncementData(datas);
		return announcement;
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
				panel.setPanelUrl(domainUrl + "/images/panels/"
						+ paneldata.getPanelId() + ".png");

								
				List<DashboardStatus> statusList = new ArrayList<>();
				DashboardStatus status = new DashboardStatus();
				
				status.setStatusName(paneldata.getStatusName());
				status.setStatusCount(Integer.valueOf(paneldata.getStatuscount()));
				status.setStatusUrl(domainUrl + "/images/status/"
						+ paneldata.getStatusName() + ".png");
				
				if(paneldata.getPanelName().equals("Leave Management(Self)")) {
					status.setFunctionId(26);
				}
				else {
					status.setFunctionId(22);	
				}
				statusList.add(status);
				panel.setStatusList(statusList);
				panelList.add(panel);
			} else {
				List<DashboardStatus> dashboardStatusList = oldObject.getStatusList();
				DashboardStatus status = new DashboardStatus();
				status.setStatusName(paneldata.getStatusName());
				status.setStatusCount(Integer.valueOf(paneldata.getStatuscount()));
				status.setStatusUrl(domainUrl + "/images/status/"
						+ paneldata.getStatusName() + ".png");
				if(paneldata.getPanelName().equals("Leave Management(Self)")) {
					status.setFunctionId(26);
				}
				else {
					status.setFunctionId(22);	
				}
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
				String jsonObjectcount = String.valueOf(jsonObject.getInt("count"));
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