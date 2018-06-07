package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraEmpCompanyNotification;

public interface SupraEmpCompanyNotificationRepository extends  JpaRepository<SupraEmpCompanyNotification, Long>, SupraEmpCompanyNotificationRepositoryCustom {

	

}
