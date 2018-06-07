package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.SupraEmpCompanyNotificationRepositoryCustom;

public class SupraEmpCompanyNotificationRepositoryImpl implements SupraEmpCompanyNotificationRepositoryCustom {


	
	@PersistenceContext
	private EntityManager entityManager;
	
	String M11="SELECT notificationrfnum, title FROM rolemanagement.supra_emp_company_notification "
			+ "where policyGroup = (select policyGroup from user where userName=':userName') AND  isActive='Y'";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getAnnouncementForUser(String username) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("username", username);
		List<Object[]>data =  (List<Object[]>) query.getResultList();
		System.out.println(data);
		return data;
	}

}
