package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.bean.Product;
import model.dao.HelpConnectDB;
import model.dao.ProductDAO;

public class ProductBO {
	ProductDAO pD = new ProductDAO(HelpConnectDB.getConnection());
	int productPerPage = 8;

	public int deleteAProduct(int productID) {
		return pD.deleteAProduct(productID);
	}

	public int updateAProduct(Product product) {
		return pD.updateProduct(product);
	}

	public Product getAProduct(int id) {
		return pD.getSingleProduct(id);
	}

	public int getTheNextProductID() {
		return pD.getTheNextProductID();
	}

	public int getNumberOfPages() {
		double numberOfPages = Math.ceil((double) pD.getNumberOfRows() / productPerPage);
		return (int) numberOfPages;
	}

	public List<Product> getAllProducts() {
		return pD.getAllProducts();
	}

	public List<Product> get20Products(int idxPage) {
		List<Product> allProducts = pD.getAllProducts();
		List<Product> listProducts = new ArrayList<Product>();
		for (int i = (idxPage - 1) * productPerPage; i < (idxPage * productPerPage) && i < allProducts.size(); i++) {
			if (allProducts.get(i) != null) {
				listProducts.add(allProducts.get(i));
			}
		}
		return listProducts;
	}

	public List<Product> searchProducts(String keyWord) {
		return pD.searchProducts(keyWord);
	}

	public int addAProduct(Product product) {
		return pD.addAProduct(product);
	}
}
