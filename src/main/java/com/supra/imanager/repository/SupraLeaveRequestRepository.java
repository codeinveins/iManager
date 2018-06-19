package com.supra.imanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraLeaveRequest;

public interface SupraLeaveRequestRepository  extends JpaRepository<SupraLeaveRequest, Integer>, SupraLeaveRequestRepositoryCustom
{
	List<SupraLeaveRequest> findByIdUsername(String userId);
}
