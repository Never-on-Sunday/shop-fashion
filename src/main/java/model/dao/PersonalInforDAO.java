package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.bean.PersonalInfor;

public class PersonalInforDAO {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public PersonalInforDAO(Connection con) {
		this.con = con;
	}

	public int updatePersonalInfor(PersonalInfor personalInfor) {
		int res = 0;
		try {
			query = "UPDATE personalInfor SET fullName=?, address=?, phoneNumber=?, accID=? WHERE id=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, personalInfor.getFullName());
			pst.setString(2, personalInfor.getAddress());
			pst.setString(3, personalInfor.getPhoneNumber());
			pst.setInt(4, personalInfor.getAccID());
			pst.setInt(5, personalInfor.getId());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int createAPersonalInfor(PersonalInfor PersonalInfor) {
		int res = 0;
		try {
			query = "INSERT INTO personalinfor (fullName, address, phoneNumber, accID) VALUES (?, ?, ?, ?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, PersonalInfor.getFullName());
			pst.setString(2, PersonalInfor.getAddress());
			pst.setString(3, PersonalInfor.getPhoneNumber());
			pst.setInt(4, PersonalInfor.getAccID());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return res;
	}

	public PersonalInfor getPersonalInforByAccID(int accID) {
		PersonalInfor personalInfor = null;
		try {
			query = "SELECT * FROM personalInfor WHERE accID=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, accID);
			rs = pst.executeQuery();
			if (rs.next()) {
				personalInfor = new PersonalInfor();
				personalInfor.setId(rs.getInt("id"));
				personalInfor.setFullName(rs.getString("fullName"));
				personalInfor.setAddress(rs.getString("address"));
				personalInfor.setPhoneNumber(rs.getString("phoneNumber"));
				personalInfor.setAccID(rs.getInt("accID"));
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return personalInfor;
	}
}
