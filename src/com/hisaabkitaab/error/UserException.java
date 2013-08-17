package com.hisaabkitaab.error;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class UserException extends WebApplicationException{
		
	public UserException(String message){
		super(Response.status(Status.OK).
		        entity(new UserExceptionSchema("NOK", message)).type(MediaType.APPLICATION_JSON).
		         build());
	}
	
	public UserException(Status status,String message){
		
		super(Response.status(status).
				        entity(message).type(MediaType.APPLICATION_JSON).
				         build());
	}
}
