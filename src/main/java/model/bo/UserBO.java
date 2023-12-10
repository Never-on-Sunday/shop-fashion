package model.bo;

import model.bean.User;
import model.dao.HelpConnectDB;
import model.dao.UserDAO;

public class UserBO {
	UserDAO uD = new UserDAO(HelpConnectDB.getConnection());

	public User getAUser(String username, String password) {
		return uD.getAUser(username, password);
	}
}
