package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.supra.imanager.dto.SupraLeaveRequest;
import com.supra.imanager.repository.SupraLeaveRequestRepository;
import com.supra.imanager.repository.SupraLeaveRequestRepositoryCustom;

public class SupraLeaveRequestRepositoryImpl implements SupraLeaveRequestRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	private String M11 ="select RequestNumber from supra_leave_request where username=:uname order by lastmodifiedon desc limit 1";
	
	private String M12 ="update supra_leave_request set approverremark=:remark where requestnumber=:reqnumber";
	
	private String M13 ="select * from supra_leave_request where username= :uname";
	
   
	@Override
	public String getRequestNumber(String username) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", username);
		return  (String) query.getSingleResult();
	}

	
	@Override
	@Transactional
	public int updateLMSRemarkAndStatus(String requsetNumberdata, String approveFlag, String pendingstatus, String remark) {
		Query query = entityManager.createNativeQuery(M12);
		query.setParameter("reqnumber", requsetNumberdata);
		/*query.setParameter("approveFlag", approveFlag);*/
		/*query.setParameter("pendingstatus", pendingstatus);*/
		query.setParameter("remark", remark);
		return  (int) query.executeUpdate();
	}


	@Override
	public List<SupraLeaveRequest> findByUsername(String userId) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery(M13);
		query.setParameter("uname", userId);
		return  (List<SupraLeaveRequest>) query.getResultList();
	}
	
}
