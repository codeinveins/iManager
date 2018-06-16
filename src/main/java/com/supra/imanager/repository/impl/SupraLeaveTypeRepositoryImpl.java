package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.SupraLeaveTypeRepositoryCustom;

public class SupraLeaveTypeRepositoryImpl implements SupraLeaveTypeRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	String M11="select slt.leavename, slt.leavedesc,  sluv.leavebalance , sluv.balanceupdationflag from  supra_leave_type slt, supra_leave_user_balance sluv where slt.leavecode in (select leavecode from supra_leave_user_balance sluv where sluv.username=:uname) and sluv.username=:uname and slt.leavecode=sluv.leavecode";

	//List<Object[]> // object[] == slt.leavecode, slt.leavename, sluv.leavebalance, sluv.balanceupdationflag, slt.leavedesc
	
	 

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> leaveBalance(String username) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", username);
		List<Object[]>data =  (List<Object[]>) query.getResultList();
		return data;
	}
	

}
