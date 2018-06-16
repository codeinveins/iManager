package com.supra.imanager.bean;

import java.util.List;

public class MenuModules {

	private int moduleId;
	private String moduleName;
	private String moduleUrl;
	private List<MenuFunctions> functionBeans;
	
	public int getModuleId() {
		return moduleId;
	}
	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getModuleUrl() {
		return moduleUrl;
	}
	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}
	public List<MenuFunctions> getFunctionBeans() {
		return functionBeans;
	}
	public void setFunctionBeans(List<MenuFunctions> functionBeans) {
		this.functionBeans = functionBeans;
	}
}
