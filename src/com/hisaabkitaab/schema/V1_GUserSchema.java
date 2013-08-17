package com.hisaabkitaab.schema;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="GuestUser")
public class V1_GUserSchema implements GUserSchema{
	
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String UID;
	
	@XmlElement
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@XmlElement
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@XmlElement
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XmlElement
	public String getUID() {
		return UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}

}
