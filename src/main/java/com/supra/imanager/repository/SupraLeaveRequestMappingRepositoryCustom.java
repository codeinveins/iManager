package com.supra.imanager.repository;

import java.util.List;

import com.supra.imanager.dto.SupraLeaveRequestMapping;

public interface SupraLeaveRequestMappingRepositoryCustom {
	List<SupraLeaveRequestMapping> findByUsername(String userId);
}
