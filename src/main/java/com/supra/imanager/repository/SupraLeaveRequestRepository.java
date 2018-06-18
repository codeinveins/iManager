package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraLeaveRequest;

public interface SupraLeaveRequestRepository  extends JpaRepository<SupraLeaveRequest, Integer>, SupraLeaveRequestRepositoryCustom
{

	


}
