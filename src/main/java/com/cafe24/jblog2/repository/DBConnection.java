package com.cafe24.jblog2.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	protected Connection getConnection() throws SQLException, ClassNotFoundException {
		Connection conn = null;
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/jblog";
			conn = DriverManager.getConnection(url,"jblog","jblog");
		return conn;
	}
}
