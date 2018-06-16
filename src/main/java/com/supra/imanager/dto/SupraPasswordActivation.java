package com.supra.imanager.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="supra_password_activation")
public class SupraPasswordActivation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5695515043346763998L;

	private Integer id;
	private String email;
	private String activationcode;

	
	
	public SupraPasswordActivation() {	}

	public SupraPasswordActivation(String email, String activationcode) {
		this.email = email;
		this.activationcode = activationcode;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", length=10)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Column(name="email", length=50)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Column(name="activationcode", length=50)
	public String getActivationcode() {
		return activationcode;
	}
	public void setActivationcode(String activationcode) {
		this.activationcode = activationcode;
	}

}
