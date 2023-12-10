package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.bean.Order;
import model.bean.OrderDisplay;
import model.dao.HelpConnectDB;
import model.dao.OrderDAO;
import model.dao.ProductDAO;
import model.dao.UserDAO;

public class OrderBO {
	OrderDAO cD = new OrderDAO(HelpConnectDB.getConnection());
	ProductDAO pD = new ProductDAO(HelpConnectDB.getConnection());
	UserDAO uD = new UserDAO(HelpConnectDB.getConnection());
	int OrderPerPage = 7;

	public List<OrderDisplay> get20OrdersDisplay(int idxPage) {
		List<OrderDisplay> allOrders = getAllOrdersDisplay();
		List<OrderDisplay> listOrders = new ArrayList<OrderDisplay>();
		for (int i = (idxPage - 1) * OrderPerPage; i < (idxPage * OrderPerPage) && i < allOrders.size(); i++) {
			if (allOrders.get(i) != null) {
				listOrders.add(allOrders.get(i));
			}
		}
		return listOrders;
	}

	public List<OrderDisplay> getAllOrdersDisplay() {
		List<Order> allOrders = cD.getAllOrders();
		List<OrderDisplay> allOrdersDisplay = new ArrayList<OrderDisplay>();
		for (Order order : allOrders) {
			OrderDisplay orderDisplay = new OrderDisplay();
			orderDisplay.setOrder(order);
			orderDisplay.setProduct(pD.getSingleProduct(order.getProductID()));
			orderDisplay.setUser(uD.getAUserByID(order.getUid()));
			allOrdersDisplay.add(orderDisplay);
		}
		return allOrdersDisplay;
	}

	public List<Order> get20Orders(int idxPage) {
		List<Order> allOrders = cD.getAllOrders();
		List<Order> listOrders = new ArrayList<Order>();
		for (int i = (idxPage - 1) * OrderPerPage; i < (idxPage * OrderPerPage) && i < allOrders.size(); i++) {
			if (allOrders.get(i) != null) {
				listOrders.add(allOrders.get(i));
			}
		}
		return listOrders;
	}

	public int getNumberOfPages() {
		double numberOfPages = Math.ceil((double) cD.getNumberOfRows() / OrderPerPage);
		return (int) numberOfPages;
	}

	public List<Order> getAllOrders() {
		return cD.getAllOrders();
	}

	public List<Order> userOrders(int i) {
		return cD.userOrders(i);
	}

	public void cancelOrder(int id) {
		cD.cancelOrder(id);
	}
}
