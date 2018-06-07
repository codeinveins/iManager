package com.supra.imanager.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.dto.RestPanelStatusName;
import com.supra.imanager.repository.RestPanelStatusNameRepositoryCustom;

public class RestPanelStatusNameRepositoryImpl implements RestPanelStatusNameRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	String M11 = "select id, panelId, statusName, moduleName, statuscount, panelName, panelCode from restPanelStatusName "
			+ "where panelId in ( select panelid from supra_user_panel_display_list where username=:username)";

	@SuppressWarnings("unchecked")
	@Override
	public List<RestPanelStatusName> fetchStatusCountByPanelIdsForUsername(List<String> panelIdList) {
		Query query = entityManager.createNamedQuery("M11", RestPanelStatusName.class).setParameter("selectedPanels", panelIdList);
		List<RestPanelStatusName> data =  (List<RestPanelStatusName>) query.getResultList();
		return data;
	}

}
