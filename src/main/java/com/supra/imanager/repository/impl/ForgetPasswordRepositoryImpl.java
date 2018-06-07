package com.supra.imanager.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.supra.imanager.repository.ForgetPasswordRepositoryCustom;

public class ForgetPasswordRepositoryImpl implements ForgetPasswordRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	String M11 = "select count(1) from user where primaryemail =:primaryemail";

	String M12 = "INSERT INTO supra_password_activation (email, activationcode) VALUES (:email, :activationcode)";

	//// Forget paassword mail 8////
	@Override
	public String checkUserExist(String usermail) {
		// TODO Auto-generated method stub
		Query query = entityManager.createNativeQuery(M11);
		query.setParameter("primaryEmail", usermail);
		String data =  (String) query.getSingleResult();
		System.out.println(data);
		return data;
	}

	
	//// Forget paassword mail 9////
	public String saveActivationLinkInDb(String email,String activationcode) {
		Query query = entityManager.createNativeQuery(M12);
		query.setParameter("email", email);
		query.setParameter("activationcode", activationcode);
		String data =  (String) query.getSingleResult();
		System.out.println(data);
		return data;
	}

	
			
}
