package com.supra.imanager.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.SupraAccessgroupUserMappingRepositoryCustom;

public class SupraAccessgroupUserMappingRepositoryImpl implements SupraAccessgroupUserMappingRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	String M12 = "select "
					+ "sagum.username, samf.functionid,smf.functionname,smf.moduleid,sam.modulename "
				+ "from "
					+ "supra_accessgroup_user_mapping as sagum, supra_accessgroup_modulefunction_mapping as samf, "
					+ "supra_module_function as smf, supra_app_module as sam "
				+ "where "
					+ "sagum.username=:uname AND samf.groupid = sagum.groupid AND samf.functionid = smf.functionid "
					+ "AND smf.moduleid = sam.moduleid AND smf.moduleid in(:moduleA, :moduleB)";
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> fetchModuleFunctionMapping(String username, String module1, String module2) {
		Query query = entityManager.createNativeQuery(M12);
		query.setParameter("uname", username);
		query.setParameter("moduleA", module1);
		query.setParameter("moduleB", module2);
		List<Object[]> data = query.getResultList();
		return data;
	}

}
