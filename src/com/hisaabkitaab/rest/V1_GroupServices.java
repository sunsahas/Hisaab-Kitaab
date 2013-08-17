package com.hisaabkitaab.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.hisaabkitaab.schema.GroupSchema;
import com.hisaabkitaab.schema.V1_GroupSchema;
import com.hisaabkitaab.util.V1_Util;

@Path("/v1/groups")
public class V1_GroupServices {
	
	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GroupSchema getGroup(@PathParam("id") String id){
		if(id == null || "".equals(id.trim()))
			return new V1_GroupSchema();
		return V1_Util.getGroup(id);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<V1_GroupSchema> getGroups(){
		return V1_Util.getGroups();
	}
}
