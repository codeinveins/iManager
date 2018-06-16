package com.supra.imanager.repository.impl;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.SupraLeaveUserBalanceRepositoryCustom;

public class SupraLeaveUserBalanceRepositoryImpl implements SupraLeaveUserBalanceRepositoryCustom{


	@PersistenceContext
	private EntityManager entityManager;
	
	String M11 = "update supra_leave_user_balance set balanceupdationflag=:flag where username=:uname";
	
	
	@SuppressWarnings("unchecked")
	@Override
	public int changeUserLeaveBalanceUpdationFlag(int uname,String flag) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("uname", uname);
		query.setParameter("flag", flag);
		return  query.executeUpdate();
	}




	}


