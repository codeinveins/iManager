package com.supra.imanager.repository.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.supra.imanager.repository.SupraLeaveUserBalanceRepositoryCustom;

public class SupraLeaveUserBalanceRepositoryImpl implements SupraLeaveUserBalanceRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;
	
	private String M11 = "update supra_leave_user_balance set balanceupdationflag=:flag where username=:uname";
	
	private String M12 = "update supra_leave_user_balance set leavebalance=:balance where username=:uname and leavecode=:code";
	
	@Override
	@Transactional
	public int changeUserLeaveBalanceUpdationFlag(String userId, String flag) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", userId);
		query.setParameter("flag", flag);
		return query.executeUpdate();
	}

	@Override
	@Transactional
	public int updateLeaveBalance(String balance, String userId, Long leaveCode) {
		Query query = entityManager.createNativeQuery(M12);
		query.setParameter("balance", balance);
		query.setParameter("uname", userId);
		query.setParameter("code", leaveCode);
		return query.executeUpdate();
	}
}