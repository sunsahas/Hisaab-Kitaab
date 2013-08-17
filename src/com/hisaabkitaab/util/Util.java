package com.hisaabkitaab.util;

import com.hisaabkitaab.schema.GUserSchema;
import com.hisaabkitaab.schema.GroupSchema;
import com.hisaabkitaab.schema.V1_GUserSchema;
import com.hisaabkitaab.schema.V1_GroupSchema;

public class Util {
	
	public static GUserSchema getVersionSpecificRUser(VersionMetadata version){
		if(version == null)
			throw new RuntimeException("ERROR:Invalid user");
		if(version == VersionMetadata.V1)
			return new V1_GUserSchema();
		return null;
	}
	
	public static boolean isInteger(String str){
		return str.matches("[0-9]+");
	}

	public static GroupSchema getVersionSpecificGroup(VersionMetadata version) {
		if(version == null)
			throw new RuntimeException("ERROR:Invalid user");
		if(version == VersionMetadata.V1)
			return new V1_GroupSchema();
		return null;
	}
}
