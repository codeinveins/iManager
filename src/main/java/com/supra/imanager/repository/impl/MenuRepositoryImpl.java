/*package com.supra.imanager.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.jdbc.core.BeanPropertyRowMapper;

import com.supra.imanager.bean.MenuDataFromDB;
import com.supra.imanager.repository.MenuRepositoryCustom;

public class MenuRepositoryImpl implements MenuRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	String M11 = "select sagum.username, samf.functionid,smf.functionname,smf.moduleid,sam.modulename from supra_accessgroup_user_mapping as sagum, supra_accessgroup_modulefunction_mapping as samf,supra_module_function as smf, supra_app_module as sam where username=':username'AND samf.groupid = sagum.groupid AND	samf.functionid = smf.functionid AND smf.moduleid=sam.moduleid AND smf.moduleid in('1','8')";

	@Override
	public void fetchModuleFunctionMapping(String username, String module1, String module2) {
		// TODO Auto-generated method stub

		List<MenuDataFromDB> userGroupList = new ArrayList<>();
		try {

			Query query = entityManager.createNativeQuery(M11);
			query.setParameter("username", username);
			query.setParameter("module1", module1);
			query.setParameter("module2", module2);

			Object[] data = (Object[]) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userGroupList;

	}

}
*/