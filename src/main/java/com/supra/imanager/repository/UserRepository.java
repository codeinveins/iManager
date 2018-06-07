package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>, UserRepositoryCustom {

	

}
