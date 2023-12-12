package model.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Order;
import model.bean.Product;

public class OrderDAO {

	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderDAO(Connection con) {
		super();
		this.con = con;
	}

	public Order getAnOrderByID(int orderID) {
		Order row = null;
		try {
			query = "SELECT * FROM orders WHERE o_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, orderID);
			rs = pst.executeQuery();
			if (rs.next()) {
				row = new Order();
				row.setOrderId(rs.getInt("o_id"));
				row.setProductID(rs.getInt("p_id"));
				row.setUid(rs.getInt("u_id"));
				row.setQuantity(rs.getInt("o_quantity"));
				row.setDate(rs.getDate("o_date"));
				row.setStatus(rs.getString("status"));
				row.setOrderPrice(rs.getDouble("orderPrice"));
				row.setAddress(rs.getString("address"));
			}
		} catch (SQLException e) {
			System.out.print(e.getMessage());
		}
		return row;
	}

	public int updateAnOrder(Order order) {
		int res = 0;
		try {
			query = "UPDATE orders SET p_id=?, u_id=?, o_quantity=?, o_date=?, status=?, orderPrice=?, address=? WHERE o_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, order.getProductID());
			pst.setInt(2, order.getUid());
			pst.setInt(3, order.getQuantity());
			pst.setDate(4, order.getDate());
			pst.setString(5, order.getStatus());
			pst.setDouble(6, order.getOrderPrice());
			pst.setString(7, order.getAddress());
			pst.setInt(8, order.getOrderId());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return res;
	}

	public int addAnOrder(Order order) {
		int res = 0;
		try {
			query = "INSERT INTO orders (p_id, u_id, o_quantity, o_date, status, orderPrice, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, order.getProductID());
			pst.setInt(2, order.getUid());
			pst.setInt(3, order.getQuantity());
			pst.setDate(4, order.getDate());
			pst.setString(5, order.getStatus());
			pst.setDouble(6, order.getOrderPrice());
			pst.setString(7, order.getAddress());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return res;
	}

	public List<Order> getOrdersByDate(Date dateFrom, Date dateTo) {
		List<Order> orders = new ArrayList<>();
		try {

			query = "SELECT * FROM orders WHERE o_date between ? AND ? ORDER BY o_date DESC";
			pst = this.con.prepareStatement(query);
			pst.setDate(1, dateFrom);
			pst.setDate(2, dateTo);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order row = new Order();
				row.setOrderId(rs.getInt("o_id"));
				row.setProductID(rs.getInt("p_id"));
				row.setUid(rs.getInt("u_id"));
				row.setQuantity(rs.getInt("o_quantity"));
				row.setDate(rs.getDate("o_date"));
				row.setStatus(rs.getString("status"));
				row.setOrderPrice(rs.getDouble("orderPrice"));
				row.setAddress(rs.getString("address"));
				orders.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return orders;
	}

	public int getNumberOfRows() {
		int numRows = -1;
		try {

			query = "SELECT COUNT(*) AS row_count FROM `orders`";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				numRows = rs.getInt("row_count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return numRows;
	}

	public List<Order> getAllOrders() {
		List<Order> orders = new ArrayList<>();
		try {

			query = "SELECT * FROM orders ORDER BY o_date DESC";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Order row = new Order();
				row.setOrderId(rs.getInt("o_id"));
				row.setProductID(rs.getInt("p_id"));
				row.setUid(rs.getInt("u_id"));
				row.setQuantity(rs.getInt("o_quantity"));
				row.setDate(rs.getDate("o_date"));
				row.setStatus(rs.getString("status"));
				row.setOrderPrice(rs.getDouble("orderPrice"));
				row.setAddress(rs.getString("address"));
				orders.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return orders;
	}

//	public boolean insertOrder(Order model) {
//		boolean result = false;
//		try {
//			query = "insert into orders (p_id, u_id, o_quantity, o_date) values(?,?,?,?)";
//			pst = this.con.prepareStatement(query);
//			pst.setInt(1, model.getId());
//			pst.setInt(2, model.getUid());
//			pst.setInt(3, model.getQuantity());
//			pst.setDate(4, model.getDate());
//			pst.executeUpdate();
//			result = true;
//		} catch (SQLException e) {
//			System.out.println(e.getMessage());
//		}
//		return result;
//	}

	public List<Order> userOrders(int id) {
		List<Order> list = new ArrayList<>();
		try {
			query = "select * from orders where u_id=? order by orders.o_id desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				ProductDAO ProductDAO = new ProductDAO(this.con);
				int pId = rs.getInt("p_id");
				Product product = ProductDAO.getSingleProduct(pId);
				order.setOrderId(rs.getInt("o_id"));
				order.setProductID(pId);
//				order.setName(product.getName());
//				order.setCategory(product.getCategory());
//				order.setPrice(product.getPrice() * rs.getInt("o_quantity"));
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getDate("o_date"));
				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return list;
	}

	public void cancelOrder(int id) {
		// boolean result = false;
		try {
			query = "delete from orders where o_id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();
			// result = true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print(e.getMessage());
		}
		// return result;
	}
}
