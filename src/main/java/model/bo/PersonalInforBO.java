package model.bo;

import model.bean.PersonalInfor;
import model.dao.HelpConnectDB;
import model.dao.PersonalInforDAO;

public class PersonalInforBO {
	PersonalInforDAO pD = new PersonalInforDAO(HelpConnectDB.getConnection());

	public int updatePersonalInfor(PersonalInfor personalInfor) {
		return pD.updatePersonalInfor(personalInfor);
	}

	public int createAPersonalInfor(PersonalInfor personalInfor) {
		return pD.createAPersonalInfor(personalInfor);
	}

	public PersonalInfor getPersonalInforByAccID(int accID) {
		return pD.getPersonalInforByAccID(accID);
	}
}
