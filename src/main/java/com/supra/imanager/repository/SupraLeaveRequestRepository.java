package com.supra.imanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraLeaveRequest;
import com.supra.imanager.dto.SupraLeaveRequestId;

public interface SupraLeaveRequestRepository  extends JpaRepository<SupraLeaveRequest, SupraLeaveRequestId>, SupraLeaveRequestRepositoryCustom {
	List<SupraLeaveRequest> findByIdUsername(String userId);
	SupraLeaveRequest findByIdRequestnumber(String requestNumber);
}
