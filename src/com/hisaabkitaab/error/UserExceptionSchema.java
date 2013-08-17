package com.hisaabkitaab.error;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

@XmlRootElement
public class UserExceptionSchema {
	private String status;
	
	private String message;
	
	public UserExceptionSchema() {
		super();
	}
	
	public UserExceptionSchema(String status, String message) {
		this.status = status;
		this.message = message;
	}
	@XmlElement
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@XmlElement
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString(){
		JSONObject obj = new JSONObject();
		try {
			obj.append("status", status);
			obj.append("message", message);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return obj.toString();
	}
}
