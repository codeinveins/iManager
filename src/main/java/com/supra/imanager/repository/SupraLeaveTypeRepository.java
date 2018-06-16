package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraLeaveType;

public interface SupraLeaveTypeRepository extends JpaRepository<SupraLeaveType, Integer>, SupraLeaveTypeRepositoryCustom {

}
