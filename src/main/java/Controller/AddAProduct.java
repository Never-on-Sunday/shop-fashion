package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Product;
import model.bean.User;
import model.bo.ProductBO;

/**
 * Servlet implementation class AddAProduct
 */
@WebServlet("/AddAProduct")
public class AddAProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductBO productBO = new ProductBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pw = response.getWriter()) {
			// check auth
			HttpSession ses = request.getSession();
			User user = (User) ses.getAttribute("authUser");
			if (user == null || !user.getrole().equals("admin")) {
				response.sendRedirect("index.jsp");
				return;
			}

			String name = request.getParameter("product_name");
			String category = request.getParameter("product_category");
			String price = request.getParameter("product_price");
			String image = request.getParameter("filebutton");
			Product product = new Product();
			product.setName(name);
			product.setCategory(category);
			product.setPrice(Double.parseDouble(price)); // check input double
			product.setImage(image);
			int res = productBO.addAProduct(product);

			request.setAttribute("status", "Add a product");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ManageProductsServlet");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		doGet(request, response);
	}

}
