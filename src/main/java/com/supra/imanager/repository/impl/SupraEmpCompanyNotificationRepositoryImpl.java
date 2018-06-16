package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.SupraEmpCompanyNotificationRepositoryCustom;

public class SupraEmpCompanyNotificationRepositoryImpl implements SupraEmpCompanyNotificationRepositoryCustom {


	
	@PersistenceContext
	private EntityManager entityManager;
	
	String M11="SELECT ecn.notificationrfnum, ecn.title, ecn.docextension FROM supra_emp_company_notification ecn "
			+ "where ecn.policyGroup = (select u.policyGroup from user u where u.username=:uname) AND  ecn.isActive='Y'";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAnnouncementForUser(String username) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", username);
		List<Object[]>data =  (List<Object[]>) query.getResultList();
		return data;
	}

}
