package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.supra.imanager.dto.SupraLeaveRequest;
import com.supra.imanager.repository.SupraLeaveRequestRepositoryCustom;

public class SupraLeaveRequestRepositoryImpl implements SupraLeaveRequestRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	
	private String M11 ="select RequestNumber from supra_leave_request where username=:uname order by lastmodifiedon desc limit 1";
	
	private String M12 ="update supra_leave_request set approverremark=:remark, status=:status, lastmodifiedby=:lastmodifiedby, lastmodifiedon=:lastmodifiedon where requestnumber=:reqnumber";
	
	private String M13 ="SELECT * FROM supra_leave_request where username in(select username from user where reportingManager=:repmanager)";
	
	@Override
	public String getRequestNumber(String username) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", username);
		return  (String) query.getSingleResult();
	}

	
	@Override
	@Transactional
	public int updateLeaveRemarkAndStatus(String requestNumber, String status, String remark, String approverUserId, String currentDate) {
		Query query = entityManager.createNativeQuery(M12);
		query.setParameter("reqnumber", requestNumber);
		query.setParameter("status", status);
		query.setParameter("remark", remark);
		query.setParameter("lastmodifiedby", approverUserId);
		query.setParameter("lastmodifiedon", currentDate);
		return  (int) query.executeUpdate();
	}


	@Override
	public List<SupraLeaveRequest> findByOthersId(String userId) {
		Query query = entityManager.createNativeQuery(M13, SupraLeaveRequest.class);
		query.setParameter("repmanager", userId);
		List<SupraLeaveRequest> data = query.getResultList();
		return data;
	}
	

}
