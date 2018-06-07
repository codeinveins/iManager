package com.supra.imanager.service;

import java.util.List;

import com.supra.imanager.bean.MenuDataFromDB;

public interface MenuService {

	List<MenuDataFromDB> fetchModuleFunctionMapping(String username, String string, String string2);

}
