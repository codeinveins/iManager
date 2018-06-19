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
	
	
	
   private String M11 ="SELECT * FROM supra_leave_request_mapping where username in(select username from user where reportingManager=:repmanager)";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SupraLeaveRequestMapping> findByOthersId(String userId) {
		Query query = entityManager.createNativeQuery(M11, SupraLeaveRequestMapping.class);
		query.setParameter("repmanager", userId);
		List<SupraLeaveRequestMapping> data = query.getResultList();
		return data;
	}
}
