package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.RestQuickLinkRepositoryCustom;

public class RestQuickLinkRepositoryImpl implements RestQuickLinkRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	String M11 ="SELECT q.quicklinkid, r.quicklinkname FROM restquicklink as r ,supra_user_quicklink as q "
			+ "where q.username=':username' AND q.viewflag='Y' AND q.quicklinkid=r.quicklinkid";

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getQuickLinks(String username) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("username", username);
		List<Object[]> data =  (List<Object[]>) query.getResultList();
		System.out.println(data);
		return data;
	}

}
