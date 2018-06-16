package com.supra.imanager.repository;

import java.util.List;

public interface SupraAccessgroupUserMappingRepositoryCustom {

	public List<Object[]> fetchModuleFunctionMapping(String username, String module1, String module2);
}
