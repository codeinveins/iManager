package com.supra.imanager.repository;

import java.util.List;

public interface SupraEmpCompanyNotificationRepositoryCustom {

	List<Object[]> getAnnouncementForUser(String username);
	
}
