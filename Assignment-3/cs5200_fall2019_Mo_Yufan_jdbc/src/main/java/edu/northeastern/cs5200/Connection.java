package edu.northeastern.cs5200;
//import java.sql.*;
//import java.util.*;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Connection {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-fall2019-mo.ch72vbds6myw.us-east-2.rds.amazonaws.com/jdbc";
	private static final String USER = "yufancs5200";
	private static final String PASSWORD = "19950219myf";
	private static 	java.sql.Connection dbConnection = null;

	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
    	Class.forName(DRIVER);

		if (dbConnection == null) {
			dbConnection = DriverManager.getConnection(URL, USER, PASSWORD);
			return dbConnection;
		} else { return dbConnection; } 
	}
	
	
	public static void closeConnection() {
	   	 try {
	   		 if (dbConnection != null) {
			dbConnection.close();
			dbConnection = null; }
	   	 } catch (SQLException e) {
	   		 // TODO Auto-generated catch block
	   		 e.printStackTrace();
	   	 }
	}
	
	
};


