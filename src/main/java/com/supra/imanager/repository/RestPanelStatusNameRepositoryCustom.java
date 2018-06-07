package com.supra.imanager.repository;

import java.util.List;

import com.supra.imanager.dto.RestPanelStatusName;

public interface RestPanelStatusNameRepositoryCustom {

	List<RestPanelStatusName> fetchStatusCountByPanelIdsForUsername(List<String> panelIdList);
	
}
