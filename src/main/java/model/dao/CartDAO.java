package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Cart;

public class CartDAO {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public CartDAO(Connection con) {
		super();
		this.con = con;
	}

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		List<Cart> cartProducts = new ArrayList<>();
		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					query = "select * from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						Cart row = new Cart();
						row.setId(rs.getInt("id"));
						row.setName(rs.getString("name"));
						row.setCategory(rs.getString("category"));
						row.setPrice(rs.getDouble("price") * item.getQuantity());
						row.setQuantity(item.getQuantity());
						cartProducts.add(row);
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return cartProducts;
	}

	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		double sum = 0;
		try {
			if (cartList.size() > 0) {
				for (Cart item : cartList) {
					query = "select price from products where id=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						sum += rs.getDouble("price") * item.getQuantity();
					}

				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return sum;
	}
}
