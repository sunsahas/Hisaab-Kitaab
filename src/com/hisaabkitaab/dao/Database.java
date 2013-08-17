package com.hisaabkitaab.dao;

import java.sql.Connection;
import java.util.List;

import com.hisaabkitaab.schema.GUserSchema;
import com.hisaabkitaab.schema.V1_GUserSchema;
import com.hisaabkitaab.schema.V1_GroupSchema;
import com.hisaabkitaab.util.VersionMetadata;

public interface Database {
	Connection getConnection();
	GUserSchema getGUser(String id, VersionMetadata version);
	List<V1_GUserSchema> getUers_V1();
	boolean updateUser(String id, V1_GUserSchema user);
	boolean deleteUser(String id);
	V1_GUserSchema createUser(V1_GUserSchema user);
	V1_GroupSchema getGroup(String id, VersionMetadata version);
	List<V1_GroupSchema> getGroups_V1();
}
