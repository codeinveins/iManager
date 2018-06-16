package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraLeaveType;
import com.supra.imanager.dto.SupraLeaveUserBalance;

public interface SupraLeaveUserBalanceRepository extends JpaRepository<SupraLeaveUserBalance, Integer>, SupraLeaveUserBalanceRepositoryCustom {

}
