package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraLoginHistory;

public interface SupraLoginHistoryRepository extends JpaRepository<SupraLoginHistory, Long> {

}
