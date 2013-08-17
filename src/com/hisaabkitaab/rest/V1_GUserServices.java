package com.hisaabkitaab.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBElement;

import com.hisaabkitaab.error.UserException;
import com.hisaabkitaab.schema.GUserSchema;
import com.hisaabkitaab.schema.V1_GUserSchema;
import com.hisaabkitaab.util.V1_Util;
import com.sun.jersey.server.impl.application.ExceptionMapperFactory;

@Path("/v1/gusers")
public class V1_GUserServices {
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GUserSchema getUser(@PathParam("id") String id){
		if(id == null || "".equals(id.trim()))
			return new V1_GUserSchema();
		return V1_Util.getUser(id);
	}
	
	@GET
	@Path("/delete/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") String id){
		if(id == null || "".equals(id.trim()))
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		if(V1_Util.deleteUser(id)){
			return Response.ok().build();
		} else {
			throw new UserException("Cannot delete user");
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<V1_GUserSchema> getUsers(){
		List<V1_GUserSchema> userList = V1_Util.getUsers();
		if(userList == null)
			return new ArrayList<V1_GUserSchema>();
		else
			return userList;
	}
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(V1_GUserSchema user){
		String id = user.getUID();
		if(id == null)
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		boolean isUpdated = V1_Util.updateUser(id,user);
		if(isUpdated)
			return Response.ok(user).build();
		else
			throw new UserException("Cannot update user");
	}

	@PUT
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createUser(V1_GUserSchema user){
		String id = user.getUID();
		if(id != null)
			throw new UserException("UID cannot be non-null");
		user = V1_Util.createUser(user);
		if(user != null)
			return Response.ok(user).build();
		else
			throw new UserException("Cannot create user");
	}
	
}
