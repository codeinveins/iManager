package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraLeaveRequestMapping;
import com.supra.imanager.dto.SupraLeaveRequestMappingId;

public interface SupraLeaveRequestMappingRepository
		extends JpaRepository<SupraLeaveRequestMapping, SupraLeaveRequestMappingId> ,SupraLeaveRequestMappingRepositoryCustom {
	
}