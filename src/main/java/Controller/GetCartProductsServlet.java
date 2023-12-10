package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cart;
import model.bean.User;
import model.bo.CartBO;

/**
 * Servlet implementation class GetCartProducts
 */
@WebServlet("/GetCartProductsServlet")
public class GetCartProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartBO cartBO = new CartBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User auth = (User) request.getSession().getAttribute("auth");
		if (auth != null) {
			request.setAttribute("person", auth);
		}

		HttpSession session = request.getSession();
		ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart_list");
		List<Cart> cartProduct = null;
		double total = 0;
		if (cart_list != null) {
			total = cartBO.getTotalCartPrice(cart_list);
			cartProduct = cartBO.getCartProducts(cart_list);

			session.setAttribute("cart_list", cart_list);
			request.setAttribute("cartProduct", cartProduct);
		}
		request.setAttribute("total", total);
		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/cart.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
