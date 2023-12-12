package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.bean.AccountDisplay;
import model.bean.PersonalInfor;
import model.bean.User;

public class AccountDisplayBO {
	UserBO userBO = new UserBO();
	PersonalInforBO personalInforBO = new PersonalInforBO();

	public List<AccountDisplay> getAllAccountDisplays() {
		List<User> users = userBO.getAllUsers();
		List<AccountDisplay> accountDisplays = new ArrayList<AccountDisplay>();
		for (int i = 0; i < users.size(); i++) {
			User user = users.get(i);
			PersonalInfor personalInfor = personalInforBO.getPersonalInforByAccID(user.getId());
			AccountDisplay accountDisplay = new AccountDisplay();
			accountDisplay.setPersonalInfor(personalInfor);
			accountDisplay.setUser(user);
			accountDisplays.add(accountDisplay);
		}
		return accountDisplays;
	}
}
