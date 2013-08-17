package com.hisaabkitaab.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.hisaabkitaab.schema.GUserSchema;
import com.hisaabkitaab.schema.GroupSchema;
import com.hisaabkitaab.schema.V1_GUserSchema;
import com.hisaabkitaab.schema.V1_GroupSchema;

import com.hisaabkitaab.util.Util;
import com.hisaabkitaab.util.VersionMetadata;


import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

class MySQLUtil implements Database {

	private DataSource dataSource = null;

	@Override
	public synchronized Connection getConnection() {
		Connection con = null;
		try {
			if (dataSource == null) {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext
						.lookup("java:/comp/env");
				dataSource = (DataSource) envContext
						.lookup("jdbc/hisaab_kitaab");
			}
			con = dataSource.getConnection();
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		} finally {
		}
		return con;
	}

	@Override
	public synchronized GUserSchema getGUser(String id, VersionMetadata version) {
		
		GUserSchema user = Util.getVersionSpecificRUser(version);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = (PreparedStatement) con
					.prepareStatement("select UID, FIRSTNAME, LASTNAME, EMAIL "
							+ "from GUSER where UID = ?");
			if(!Util.isInteger(id))
				throw new Exception("ERROR: SQL Injection is stopped");
			ps.setInt(1, Integer.parseInt(id));
			rs = ps.executeQuery();

			if (rs.next()) {
				user.setUID(rs.getString("UID"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null)rs.close();if (ps != null)ps.close(); if(con!=null)con.close();} catch (Exception e) {e.printStackTrace();}
		}

		return user;
	}

	@Override
	public synchronized List<V1_GUserSchema> getUers_V1() {
		
		List<V1_GUserSchema> userList = new ArrayList<V1_GUserSchema>();
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		V1_GUserSchema user = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("select UID, FIRSTNAME, LASTNAME, EMAIL "
					+ "from GUSER");
			rs = ps.executeQuery();
			
			while (rs.next()) {
				user = (V1_GUserSchema) Util
						.getVersionSpecificRUser(VersionMetadata.V1);
				user.setUID(rs.getString("UID"));
				user.setFirstName(rs.getString("FIRSTNAME"));
				user.setLastName(rs.getString("LASTNAME"));
				user.setEmail(rs.getString("EMAIL"));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {if (rs != null)rs.close();if (ps != null)ps.close(); if(con!=null)con.close();} 
			catch (Exception e) {e.printStackTrace();}
		}
		return userList;
	}

	private synchronized boolean isUserExists(String id) {
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("select UID, FIRSTNAME, LASTNAME, EMAIL "
					+ "from GUSER where UID = ?");
			ps.setInt(1, Integer.parseInt(id.toUpperCase()));
			rs = ps.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {if (rs != null)rs.close();if (ps != null)ps.close(); if(con!=null)con.close();} catch (Exception e) {e.printStackTrace();}
		}
		return false;
	}

	@Override
	public boolean updateUser(String id, V1_GUserSchema user) {
		
		if (isUserExists(id)) {
			String email = user.getEmail();
			String fName = user.getFirstName();
			String lName = user.getLastName();
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement("UPDATE GUSER "
						+ "SET FIRSTNAME=?,LASTNAME=?,EMAIL=? " + "WHERE UID=?");
				ps.setString(1, fName);
				ps.setString(2, lName);
				ps.setString(3, email);
				ps.setInt(4, Integer.parseInt(id));
				int cols = ps.executeUpdate();
				if (cols != 0)
					return true;
				else
					return false;
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {if (ps != null)ps.close(); if(con!=null)con.close();} catch (Exception e) {e.printStackTrace();}
			}
		}
		return false;
	}

	@Override
	public synchronized boolean deleteUser(String id) {
		
		if (isUserExists(id)) {
			Connection con = null;
			PreparedStatement ps = null;
			try {
				con = dataSource.getConnection();
				ps = con.prepareStatement("DELETE FROM GUSER " + "WHERE UID=?");
				ps.setInt(1, Integer.parseInt(id));
				int cols = ps.executeUpdate();
				if (cols != 0)
					return true;
				else
					return false;
			} catch (SQLException e) {

				e.printStackTrace();
			} finally {
				try {if (ps != null)ps.close(); if(con!=null)con.close();} catch (Exception e) {e.printStackTrace();}
			}
		}
		return false;
	}

	@Override
	public V1_GUserSchema createUser(V1_GUserSchema user) {
		
		if (user.getUID() != null)
			return null;
		String email = user.getEmail();
		String fName = user.getFirstName();
		String lName = user.getLastName();
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = dataSource.getConnection();
			ps = con.prepareStatement("INSERT INTO GUSER(FIRSTNAME,LASTNAME,EMAIL) "
					+ "VALUES(?,?,?)");
			ps.setString(1, fName);
			ps.setString(2, lName);
			ps.setString(3, email);
			int cols = ps.executeUpdate();
			if (cols != 0)
				return user;
			else
				return null;
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {if (ps != null)ps.close(); if(con!=null)con.close();} catch (Exception e) {e.printStackTrace();}
		}

		return null;

	}

	@Override
	public V1_GroupSchema getGroup(String id,VersionMetadata version) {
		V1_GroupSchema group = (V1_GroupSchema)Util.getVersionSpecificGroup(version);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			ps = (PreparedStatement) con
					.prepareStatement("select GID, GROUPNAME, DESCRIPTION  "
							+ "from GROUPS where GID = ?");
			if(!Util.isInteger(id))
				throw new Exception("ERROR: SQL Injection is stopped");
			ps.setInt(1, Integer.parseInt(id));
			rs = ps.executeQuery();

			if (rs.next()) {
				group.setGID(rs.getString("GID"));
				group.setGroupName(rs.getString("GROUPNAME"));
				group.setDescription(rs.getString("DESCRIPTION"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null)rs.close();if (ps != null)ps.close(); if(con!=null)con.close();} catch (Exception e) {e.printStackTrace();}
		}

		return group;
	}

	@Override
	public List<V1_GroupSchema> getGroups_V1() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<V1_GroupSchema> groupList = new ArrayList<V1_GroupSchema>();
		try {
			con = dataSource.getConnection();
			ps = (PreparedStatement) con
					.prepareStatement("select GID, GROUPNAME, DESCRIPTION  "
							+ "from GROUPS");
				
			rs = ps.executeQuery();
			V1_GroupSchema group = (V1_GroupSchema)Util.getVersionSpecificGroup(VersionMetadata.V1);
			while (rs.next()) {
				group.setGID(rs.getString("GID"));
				group.setGroupName(rs.getString("GROUPNAME"));
				group.setDescription(rs.getString("DESCRIPTION"));
				groupList.add(group);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if (rs != null)rs.close();if (ps != null)ps.close(); if(con!=null)con.close();} catch (Exception e) {e.printStackTrace();}
		}

		return groupList;
	}
}
