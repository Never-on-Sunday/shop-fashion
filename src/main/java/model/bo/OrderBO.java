package model.bo;

import java.sql.Date;
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

	public Order getAnOrderByID(int orderID) {
		return cD.getAnOrderByID(orderID);
	}

	public int updateAnOrder(Order order) {
		return cD.updateAnOrder(order);
	}

	public int addAnOrder(Order order) {
		return cD.addAnOrder(order);
	}

	public double getTotalIncomeOrders(Date dateFrom, Date dateTo) {
		List<OrderDisplay> allOrders = getAllOrdersDisplayByDate(dateFrom, dateTo);
		double total = 0;
		for (OrderDisplay orderDisplay : allOrders) {
			if (orderDisplay.getOrder().getStatus().equals("Done"))
				total += orderDisplay.getOrder().getOrderPrice();
		}
		return total;
	}

	public List<OrderDisplay> get20OrdersDisplayByDate(int idxPage, Date dateFrom, Date dateTo) {
		List<OrderDisplay> allOrders = getAllOrdersDisplayByDate(dateFrom, dateTo);
		List<OrderDisplay> listOrders = new ArrayList<OrderDisplay>();
		for (int i = (idxPage - 1) * OrderPerPage; i < (idxPage * OrderPerPage) && i < allOrders.size(); i++) {
			if (allOrders.get(i) != null) {
				listOrders.add(allOrders.get(i));
			}
		}
		return listOrders;
	}

	public List<OrderDisplay> getAllOrdersDisplayByDate(Date dateFrom, Date dateTo) {
		List<Order> allOrders = cD.getOrdersByDate(dateFrom, dateTo);
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

	public List<OrderDisplay> get20OrdersDisplayOfAUser(int idxPage, int idUser) {
		List<OrderDisplay> allOrders = getAllOrdersDisplayOfAUser(idUser);
		List<OrderDisplay> listOrders = new ArrayList<OrderDisplay>();
		for (int i = (idxPage - 1) * OrderPerPage; i < (idxPage * OrderPerPage) && i < allOrders.size(); i++) {
			if (allOrders.get(i) != null) {
				listOrders.add(allOrders.get(i));
			}
		}
		return listOrders;
	}

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

	public List<OrderDisplay> getAllOrdersDisplayOfAUser(int userID) {
		List<Order> allOrders = cD.getAllOrders();
		List<OrderDisplay> allOrdersDisplay = new ArrayList<OrderDisplay>();
		for (Order order : allOrders) {
			if (order.getUid() == userID) {
				OrderDisplay orderDisplay = new OrderDisplay();
				orderDisplay.setOrder(order);
				orderDisplay.setProduct(pD.getSingleProduct(order.getProductID()));
				orderDisplay.setUser(uD.getAUserByID(order.getUid()));
				allOrdersDisplay.add(orderDisplay);
			}
		}
		return allOrdersDisplay;
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
