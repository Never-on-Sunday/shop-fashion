package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class HelpConnectDB {
	private static Connection connection = null;

	public static Connection getConnection() {
		try {
			if (connection == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_cart", "root", "");
				System.out.print("connected");
			}
			return connection;
		} catch (Exception e) {
			System.out.println("Database error: " + e.getMessage());
			return null;
		}
	}
}