package com.octus.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author naresh
 *
 */
@XmlRootElement
public class Applicant {
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	private String login;
	private String name;
	private String email;
	
	public Applicant(String aLogin, String aName, String aEmail){
		login = aLogin;
		name = aName;
		email = aEmail;
	}
	
	public Applicant(){
		
	}
}
