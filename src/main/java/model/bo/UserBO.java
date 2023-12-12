package model.bo;

import java.util.List;

import model.bean.User;
import model.dao.HelpConnectDB;
import model.dao.UserDAO;

public class UserBO {
	UserDAO uD = new UserDAO(HelpConnectDB.getConnection());

	public List<User> getAllUsers() {
		return uD.getAllUsers();
	}

	public User getAUserByID(int userID) {
		return uD.getAUserByID(userID);
	}

	public int updateAnUser(User user) {
		return uD.updateAnUser(user);
	}

	public int createAUser(User user) {
		return uD.createAUser(user);
	}

	public User getAUser(String username, String password) {
		return uD.getAUser(username, password);
	}
}
