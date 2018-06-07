package com.supra.imanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.SupraUserPanelDisplayList;

public interface SupraUserPanelDisplayListRepository extends JpaRepository<SupraUserPanelDisplayList, Integer> {

	List<SupraUserPanelDisplayList> findByUsername(String username);
}
