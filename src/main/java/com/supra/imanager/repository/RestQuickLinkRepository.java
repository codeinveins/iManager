package com.supra.imanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.Restquicklink;

public interface RestQuickLinkRepository  extends JpaRepository<Restquicklink, Integer>, RestQuickLinkRepositoryCustom {

}
