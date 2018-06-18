package com.supra.imanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.supra.imanager.dto.SupraLeaveUserBalance;
import com.supra.imanager.dto.SupraLeaveUserBalanceId;

public interface SupraLeaveUserBalanceRepository extends CrudRepository<SupraLeaveUserBalance, SupraLeaveUserBalanceId>, SupraLeaveUserBalanceRepositoryCustom {

	public List<SupraLeaveUserBalance> findByIdUsername(String username);
}
