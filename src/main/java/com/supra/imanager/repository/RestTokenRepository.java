package com.supra.imanager.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.RestToken;

public interface RestTokenRepository extends JpaRepository<RestToken, Integer> {

	public RestToken findByToken(String token);
	
	@Transactional
    public void deleteByUserName(String userName);

	
}
