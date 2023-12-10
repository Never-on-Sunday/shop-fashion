package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Cart;

/**
 * Servlet implementation class RemoveFromCartServlet
 */
@WebServlet("/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			String productID = request.getParameter("id");
			if (productID != null) {
				ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");

				if (cart_list != null) {
					for (Cart c : cart_list) {
						if (c.getId() == Integer.parseInt(productID)) {
							cart_list.remove(cart_list.indexOf(c));
							break;
						}
					}
				}
				response.sendRedirect("GetCartProductsServlet");

			} else {
				response.sendRedirect("cart.jsp");
			}

		}
	}

}
