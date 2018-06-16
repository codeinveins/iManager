package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.SupraLeaveRequestRepositoryCustom;

public class SupraLeaveRequestRepositoryImpl implements SupraLeaveRequestRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	String M11="select RequestNumber from supra_leave_request where username=:uname order by requestnumber desc limit 1";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String getRequestNumber(String username) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", username);
		return  (String) query.getSingleResult();
	}
	
}
