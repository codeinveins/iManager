package com.supra.imanager.dto;
// Generated Jun 1, 2018 7:55:07 AM by Hibernate Tools 4.0.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * SupraAccessgroupModulefunctionMappingId generated by hbm2java
 */
@Embeddable
public class SupraAccessgroupModulefunctionMappingId implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 439771713269654482L;
	private long functionid;
	private long groupid;

	public SupraAccessgroupModulefunctionMappingId() {
	}

	public SupraAccessgroupModulefunctionMappingId(long functionid, long groupid) {
		this.functionid = functionid;
		this.groupid = groupid;
	}

	@Column(name = "functionid", nullable = false)
	public long getFunctionid() {
		return this.functionid;
	}

	public void setFunctionid(long functionid) {
		this.functionid = functionid;
	}

	@Column(name = "groupid", nullable = false)
	public long getGroupid() {
		return this.groupid;
	}

	public void setGroupid(long groupid) {
		this.groupid = groupid;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SupraAccessgroupModulefunctionMappingId))
			return false;
		SupraAccessgroupModulefunctionMappingId castOther = (SupraAccessgroupModulefunctionMappingId) other;

		return (this.getFunctionid() == castOther.getFunctionid()) && (this.getGroupid() == castOther.getGroupid());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (int) this.getFunctionid();
		result = 37 * result + (int) this.getGroupid();
		return result;
	}

}