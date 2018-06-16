package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraAccessgroupUserMapping;
import com.supra.imanager.dto.SupraAccessgroupUserMappingId;

public interface SupraAccessgroupUserMappingRepository
		extends JpaRepository<SupraAccessgroupUserMapping, SupraAccessgroupUserMappingId>,
		SupraAccessgroupUserMappingRepositoryCustom {

}
