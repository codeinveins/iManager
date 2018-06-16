package com.supra.imanager.repository.impl;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.UserRepositoryCustom;

public class UserRepositoryImpl implements UserRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	final String M11 = "select "
							+ "u1.username as empId, u1.firstname as empName, u2.username as managerId, "
							+ "CONCAT(u2.firstname,' ', u2.lastname) as managerName, u3.username as hrmId, "
							+ "CONCAT(u3.firstname,' ', u3.lastname) as hrmName, u1.primaryemail as primaryemail, "
							+ "u1.firstname as firstname, u1.lastname as lastname, u1.password as password, "
							+ "u1.role as role, u1.usergroup as usergroup, u1.userstatus as userstatus, "
							+ "u1.accountunit as accountunit, u1.baselocation as baselocation, u1.designation as designation, "
							+ "u1.department as department, u1.policygroup as policygroup, u1.usercode as usercode, "
							+ "m.clientid as clientid "
					+ "from "
							+ "user u1, user u2, user u3, "
							+ "supra_emp_client_mapping m "
					+ "where "
							+ "u1.username = m.username AND u2.username = u1.reportingManager "
							+ "AND u3.username = u1.hrManager AND u1.primaryemail = :primaryEmail";

	
	
	final String M12 = "select "
			+ "imagePath from supra_emp_profile_image_metadata "
			+ "where"
			       + " username =:username";
	
	
	@Override
	public Object[] fetchUserData(String email) {
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("primaryEmail", email);
		Object[] data =  (Object[]) query.getSingleResult();
		return data;
	}


	@Override
	public String fetchProfileImage(String empId) {
		Query query=entityManager.createNativeQuery(M12);
		query.setParameter("username", empId);
		try {
			return (String) query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
}
