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

import model.bean.User;
import model.bo.ProductBO;

/**
 * Servlet implementation class DeleteAProduct
 */
@WebServlet("/DeleteAProductServlet")
public class DeleteAProductServlet extends HttpServlet {
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

			int idProduct = Integer.parseInt(request.getParameter("product_id"));
			String confirmDelete = request.getParameter("confirmDelete");
			if (productBO.numberProductExistInOrders(idProduct) == 0
					|| (confirmDelete != null && confirmDelete.equals("true"))) {
				int res = productBO.deleteAProduct(idProduct);
				request.setAttribute("status", "Delete a product!");

			} else {
				request.setAttribute("status", "warningDelete");
				request.setAttribute("product_id", idProduct + "");
			}

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ManageProductsServlet");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
