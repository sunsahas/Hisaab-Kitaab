package com.hisaabkitaab.schema;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="group")
public class V1_GroupSchema implements GroupSchema{
	private String GID;
	private String groupName;
	private String description;
	
	@XmlElement
	public String getGID() {
		return GID;
	}
	public void setGID(String gID) {
		GID = gID;
	}
	
	@XmlElement
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@XmlElement
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
