package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.RestToken;

public interface TokenRepository extends JpaRepository<RestToken, Integer> {

}
