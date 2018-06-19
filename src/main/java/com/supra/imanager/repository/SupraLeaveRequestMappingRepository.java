package com.supra.imanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraLeaveRequestMapping;
import com.supra.imanager.dto.SupraLeaveRequestMappingId;

public interface SupraLeaveRequestMappingRepository
		extends JpaRepository<SupraLeaveRequestMapping, SupraLeaveRequestMappingId> ,SupraLeaveRequestMappingRepositoryCustom {

	

	List<SupraLeaveRequestMapping> findByIdUsername(String userId);
	
}