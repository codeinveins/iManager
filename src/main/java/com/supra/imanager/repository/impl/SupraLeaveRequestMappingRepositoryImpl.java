package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.dto.SupraLeaveRequestMapping;
import com.supra.imanager.repository.SupraLeaveRequestMappingRepositoryCustom;

public class SupraLeaveRequestMappingRepositoryImpl implements SupraLeaveRequestMappingRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	private String M11 ="select * from supra_leave_request where username= :uname";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SupraLeaveRequestMapping> findByUsername(String userId) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", userId);
		List<SupraLeaveRequestMapping> data = query.getResultList();
		return data;
	}
}
