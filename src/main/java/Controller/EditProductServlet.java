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
 * Servlet implementation class EditProductServlet
 */
@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductBO productBO = new ProductBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
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

			Product product = new Product();
			product.setId(Integer.parseInt(request.getParameter("product_id")));
			product.setName(request.getParameter("product_name"));
			product.setCategory(request.getParameter("product_category"));
			product.setPrice(Double.parseDouble(request.getParameter("product_price")));
			String fileImage = request.getParameter("filebutton");
			if (fileImage == null || fileImage == "") {
				fileImage = request.getParameter("product_image");
			}
			product.setImage(fileImage);
			product.setDescription(request.getParameter("description"));
			product.setStatus(request.getParameter("selectStatus"));

			int res = productBO.updateAProduct(product);

			request.setAttribute("status", "Update a product");
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ManageProductsServlet");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
