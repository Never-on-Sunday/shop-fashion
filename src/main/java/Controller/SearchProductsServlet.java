package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Product;
import model.bean.User;
import model.bo.ProductBO;

/**
 * Servlet implementation class SearchProductsServlet
 */
@WebServlet("/SearchProductsServlet")
public class SearchProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductBO productBO = new ProductBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pw = response.getWriter()) {
			String keyWord = (String) request.getParameter("searchField");
//			pw.print(keyWord);

			List<Product> searchProducts = null;
			searchProducts = productBO.searchProducts(keyWord);
			request.setAttribute("allProducts", searchProducts);

			User user = (User) request.getSession().getAttribute("authUser");
			String des = "/home.jsp";
//			pw.print(des);
			if (user != null && user.getrole().equals("admin")) {
				des = "/manageProduct.jsp";
			}
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher(des);
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
