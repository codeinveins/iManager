package com.supra.imanager.service;

import java.util.List;

import com.supra.imanager.bean.MenuDataFromDB;
import com.supra.imanager.bean.MenuModules;

public interface MenuService {

	List<MenuDataFromDB> fetchModuleFunctionMapping(String username, String moduleA, String moduleB);

	List<MenuModules> prepareMenuResponse(List<MenuDataFromDB> menuDataFromDbList);

}
