package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Product;

public class ProductDAO {
	private Connection con;

	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ProductDAO(Connection con) {
		super();
		this.con = con;
	}

	public int deleteAProduct(int productID) {
		int res = 0;
		try {
			query = "DELETE FROM `products` WHERE id=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, productID);
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return res;
	}

	public int getTheNextProductID() {
		int numRows = -1;
		try {

			query = "SELECT MAX(id) as max_id FROM products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				numRows = rs.getInt("max_id") + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return numRows;
	}

	public int updateProduct(Product product) {
		int res = 0;
		try {
			query = "UPDATE products SET name=?, category=?, price=?, image=?, description=?, status=? WHERE id=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, product.getName());
			pst.setString(2, product.getCategory());
			pst.setDouble(3, product.getPrice());
			pst.setString(4, product.getImage());
			pst.setString(5, product.getDescription());
			pst.setString(6, product.getStatus());
			pst.setInt(7, product.getId());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return res;
	}

	public int getNumberOfRows() {
		int numRows = -1;
		try {

			query = "SELECT COUNT(*) AS row_count FROM `products`";
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

	public int addAProduct(Product product) {
		int res = 0;
		try {
			query = "INSERT INTO products (name, category, price, image, description, status) VALUES (?, ?, ?, ?, ?, ?)";
			pst = this.con.prepareStatement(query);
			pst.setString(1, product.getName());
			pst.setString(2, product.getCategory());
			pst.setDouble(3, product.getPrice());
			pst.setString(4, product.getImage());
			pst.setString(5, product.getDescription());
			pst.setString(6, product.getStatus());
			res = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return res;
	}

	public List<Product> getAllProducts() {
		List<Product> product = new ArrayList<>();
		try {

			query = "select * from products";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				row.setDescription(rs.getString("description"));
				row.setStatus(rs.getString("status"));

				product.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return product;
	}

	public Product getSingleProduct(int id) {
		Product row = null;
		try {
			query = "SELECT * FROM products WHERE id=? ";

			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			System.out.println("id + " + id);

			while (rs.next()) {
				row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				row.setDescription(rs.getString("description"));
				row.setStatus(rs.getString("status"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return row;
	}

	public List<Product> searchProducts(String keyWord) {
		List<Product> products = new ArrayList<>();
		try {

			query = "SELECT * FROM `products` WHERE name LIKE ? or category LIKE ?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, "%" + keyWord + "%");
			pst.setString(2, "%" + keyWord + "%");
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("id"));
				row.setName(rs.getString("name"));
				row.setCategory(rs.getString("category"));
				row.setPrice(rs.getDouble("price"));
				row.setImage(rs.getString("image"));
				row.setDescription(rs.getString("description"));
				row.setStatus(rs.getString("status"));

				products.add(row);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return products;
	}
}