package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.User;

public class UserDAO {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UserDAO(Connection con) {
		this.con = con;
	}

	public List<User> getAllUsers() {
		List<User> user = new ArrayList<>();
		try {

			query = "SELECT * FROM account";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				User row = new User();
				row.setId(rs.getInt("id"));
				row.setusername(rs.getString("username"));
				row.setpassword(rs.getString("password"));
				row.setrole(rs.getString("role"));

				user.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return user;
	}

	public int updateAnUser(User user) {
		int res = 0;
		try {
			query = "UPDATE account SET username=?, password=?, role=? WHERE id=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, user.getusername());
			pst.setString(2, user.getpassword());
			pst.setString(3, user.getrole());
			pst.setInt(4, user.getId());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return res;
	}

	public int createAUser(User user) {
		int res = 0;
		try {
			query = "INSERT INTO account (username, password, role) VALUES (?, ?, ?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, user.getusername());
			pst.setString(2, user.getpassword());
			pst.setString(3, user.getrole());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return res;
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
