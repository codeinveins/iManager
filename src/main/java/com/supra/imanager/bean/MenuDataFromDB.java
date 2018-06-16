package com.supra.imanager.bean;

public class MenuDataFromDB {

	private String username;
	
	private int functionId;
	private String functionName;
	private int moduleId;
	private String moduleName;

	public MenuDataFromDB() {}

	public MenuDataFromDB(String username, int functionId, String functionName, int moduleId, String moduleName) {
		this.username = username;
		this.functionId = functionId;
		this.functionName = functionName;
		this.moduleId = moduleId;
		this.moduleName = moduleName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
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
	@Override
	public String toString() {
		return "{ username=" + username 
				+ ", functionId=" + functionId 
				+ ", functionName=" + functionName 
				+ ", moduleId=" + moduleId
				+ ", moduleName=" + moduleName + "}";
	}
}
