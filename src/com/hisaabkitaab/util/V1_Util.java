package com.hisaabkitaab.util;

import java.sql.Connection;
import java.util.List;

import javax.xml.crypto.Data;

import com.hisaabkitaab.dao.Database;
import com.hisaabkitaab.dao.DatabaseInstance;
import com.hisaabkitaab.schema.GUserSchema;
import com.hisaabkitaab.schema.GroupSchema;
import com.hisaabkitaab.schema.V1_GUserSchema;
import com.hisaabkitaab.schema.V1_GroupSchema;

public class V1_Util {

	public static GUserSchema getUser(String id){
		if(id == null || "".equals(id.trim()))
			return new V1_GUserSchema();
		Database db=DatabaseInstance.getInstance();
		GUserSchema user = db.getGUser(id, VersionMetadata.V1);
		return user;
	}
	
	public static List<V1_GUserSchema> getUsers(){
		Database db = DatabaseInstance.getInstance();
		return db.getUers_V1();
	}

	public static boolean updateUser(String id, V1_GUserSchema user) {
		Database db = DatabaseInstance.getInstance();
		boolean isUpdated = db.updateUser(id,user);
		return isUpdated;
	}

	public static boolean deleteUser(String id) {
		Database db = DatabaseInstance.getInstance();
		boolean isDeleted = db.deleteUser(id);
		return isDeleted;
	}

	public static V1_GUserSchema createUser(V1_GUserSchema user) {
		Database db = DatabaseInstance.getInstance();
		user = db.createUser(user);
		return user;
	}
	
	public static V1_GroupSchema getGroup(String id){
		Database db = DatabaseInstance.getInstance();
		return db.getGroup(id,VersionMetadata.V1);
	}

	public static List<V1_GroupSchema> getGroups() {
		Database db = DatabaseInstance.getInstance();
		return db.getGroups_V1();
	}
}
