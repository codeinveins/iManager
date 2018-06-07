package com.supra.imanager.bean;

import java.util.List;

public class Menu implements ResponseMarker{

	List<MenuModules> mappingList;
	
	public Menu(List<MenuModules> mappingList) {
		this.mappingList = mappingList;
	}

	public List<MenuModules> getMappingList() {
		return mappingList;
	}

	public void setMappingList(List<MenuModules> mappingList) {
		this.mappingList = mappingList;
	}

	@Override
	public String toString() {
		return "{mappingList : " + mappingList + "}";
	}
	
	
	
}
