package com.supra.imanager.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.supra.imanager.repository.SupraLeaveRequestRepositoryCustom;

public class SupraLeaveRequestRepositoryImpl implements SupraLeaveRequestRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	private String M11="select RequestNumber from supra_leave_request where username=:uname order by lastmodifiedon desc limit 1";
	
	private String M12 ="update supra_leave_request set approverremark=:remark where requestnumber=:reqnumber";
   
	@Override
	public String getRequestNumber(String username) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", username);
		return  (String) query.getSingleResult();
	}

	@Transactional
	@Override
	public int updateLMSRemarkAndStatus(String reqNumber, String approveFlag, String pendingstatus, String remark) {
		Query query = entityManager.createNativeQuery(M12);
		query.setParameter("reqNumber", "LEAVESITS160-0008");
		query.setParameter("approveFlag", approveFlag);
		query.setParameter("pendingstatus", pendingstatus);
		query.setParameter("remark", remark);
		return  (int) query.executeUpdate();
	}
	
}
