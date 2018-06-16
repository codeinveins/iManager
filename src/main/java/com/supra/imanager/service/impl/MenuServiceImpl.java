package com.supra.imanager.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.supra.imanager.bean.MenuDataFromDB;
import com.supra.imanager.bean.MenuFunctions;
import com.supra.imanager.bean.MenuModules;
import com.supra.imanager.repository.SupraAccessgroupUserMappingRepository;
import com.supra.imanager.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private SupraAccessgroupUserMappingRepository supraAccessgroupUserMappingRepository;

	@Value("${app.domainUrl}")
	private String domainUrl;

	@Value("${server.contextPath}")
	private String contextPath;
	
	@Override
	public List<MenuDataFromDB> fetchModuleFunctionMapping(String username, String moduleA, String moduleB) {
		List<MenuDataFromDB> menuDataList = new ArrayList<>();
		List<Object[]> menuDataFromDbList = supraAccessgroupUserMappingRepository.fetchModuleFunctionMapping(username,moduleA,moduleB);
		Iterator<Object[]> iterator = menuDataFromDbList.iterator();
		while (iterator.hasNext()) {
			Object[] menuDataFromDb = (Object[]) iterator.next();
			MenuDataFromDB menuData = new MenuDataFromDB(
														menuDataFromDb[0].toString(), 
														Integer.valueOf(menuDataFromDb[1].toString()), 
														menuDataFromDb[2].toString(), 
														Integer.valueOf(menuDataFromDb[3].toString()), 
														menuDataFromDb[4].toString());
			menuDataList.add(menuData);
		}
		
		return menuDataList;
	}

	@Override
	public List<MenuModules> prepareMenuResponse(List<MenuDataFromDB> menuDataFromDbList) {

		List<MenuModules> menuModules = new ArrayList<>();
		Iterator<MenuDataFromDB> iterator = menuDataFromDbList.iterator();
		while (iterator.hasNext()) {
			MenuDataFromDB queryData = (MenuDataFromDB) iterator.next();

			boolean alreadyExists = false;
			MenuModules oldObject = null;
			for (MenuModules menuModule : menuModules) {
				if (menuModule.getModuleId() == queryData.getModuleId()) {
					alreadyExists = true;
					oldObject = menuModule;
					break;
				}
			}

			if (alreadyExists) {
				List<MenuFunctions> oldFunctionsList = oldObject.getFunctionBeans();
				MenuFunctions newRestfunctionBean = new MenuFunctions();
				newRestfunctionBean.setFunctionId(queryData.getFunctionId());
				newRestfunctionBean.setFunctionName(queryData.getFunctionName());
				newRestfunctionBean.setFunctionUrl(domainUrl + "/images/function/" + queryData.getFunctionId() + ".png");
				oldFunctionsList.add(newRestfunctionBean);
			} else {
				MenuModules moduleFunctionMapping = new MenuModules();
				moduleFunctionMapping.setModuleId(queryData.getModuleId());
				moduleFunctionMapping.setModuleName(String.valueOf(queryData.getModuleName()));
				moduleFunctionMapping.setModuleUrl(domainUrl + "/images/module/" + queryData.getModuleId() + ".png");
				List<MenuFunctions> functionBeans = new ArrayList<MenuFunctions>();
				MenuFunctions restFunBean = new MenuFunctions();
				restFunBean.setFunctionId(queryData.getFunctionId());
				restFunBean.setFunctionName(queryData.getFunctionName());
				restFunBean.setFunctionUrl(domainUrl + "/images/function/" + queryData.getFunctionId() + ".png");
				functionBeans.add(restFunBean);
				moduleFunctionMapping.setFunctionBeans(functionBeans);
				menuModules.add(moduleFunctionMapping);
			}
		}
		
		return menuModules;
	}

}
