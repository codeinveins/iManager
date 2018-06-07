package com.supra.imanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.ViewSupraUserStatus;
import com.supra.imanager.dto.ViewSupraUserStatusId;

public interface ViewSupraUserStatusRepository extends JpaRepository<ViewSupraUserStatus, ViewSupraUserStatusId>, ViewSupraUserStatusRepositoryCustom {

	public List<ViewSupraUserStatus> findById_Username(String username); 
}
