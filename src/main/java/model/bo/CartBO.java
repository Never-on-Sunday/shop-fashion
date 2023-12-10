package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.bean.Cart;
import model.dao.CartDAO;
import model.dao.HelpConnectDB;

public class CartBO {
	CartDAO cD = new CartDAO(HelpConnectDB.getConnection());

	public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
		return cD.getCartProducts(cartList);
	}

	public double getTotalCartPrice(ArrayList<Cart> cartList) {
		return cD.getTotalCartPrice(cartList);
	}
}
