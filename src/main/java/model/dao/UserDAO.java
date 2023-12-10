package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.User;

public class UserDAO {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UserDAO(Connection con) {
		this.con = con;
	}

	public User getAUserByID(int userID) {
		User user = null;
		try {
			query = "SELECT * FROM account WHERE id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, userID);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setusername(rs.getString("username"));
				user.setpassword(rs.getString("password"));
				user.setrole(rs.getString("role"));
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return user;
	}

	public User getAUser(String username, String password) {
		User user = null;
		try {
			query = "SELECT * FROM account WHERE username=? and password=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setusername(rs.getString("username"));
				user.setpassword(rs.getString("password"));
				user.setrole(rs.getString("role"));
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return user;
	}

	// old
//	public User userLogin(String email, String password) {
//		User user = null;
//		try {
//			query = "select * from users where email=? and password=?";
//			pst = this.con.prepareStatement(query);
//			pst.setString(1, email);
//			pst.setString(2, password);
//			rs = pst.executeQuery();
//			if (rs.next()) {
//				user = new User();
//				user.setId(rs.getInt("id"));
//				user.setName(rs.getString("name"));
//				user.setEmail(rs.getString("email"));
//			}
//		} catch (SQLException e) {
//			System.out.print(e.getMessage());
//		}
//		return user;
//	}
}
