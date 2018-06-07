package com.supra.imanager.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.supra.imanager.dto.RestPanelStatusName;

public interface RestPanelStatusNameRepository  extends JpaRepository<RestPanelStatusName, Integer>, RestPanelStatusNameRepositoryCustom {


}
