package com.hisaabkitaab.dao;

public class DatabaseInstance {
	
	private static Database db = null;
	private DatabaseInstance(){};
	
	public static Database getInstance(){
		db = new MySQLUtil();
		db.getConnection();
		return db;
	}

}
