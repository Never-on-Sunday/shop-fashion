package Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Order;
import model.bean.User;
import model.bo.OrderBO;
import model.dao.HelpConnectDB;
import model.dao.OrderDAO;

/**
 * Servlet implementation class GetOrdersServlet
 */
@WebServlet("/GetOrdersServlet")
public class GetOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderBO orderBO = new OrderBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Order> orders = null;
		User auth = (User) request.getSession().getAttribute("auth");

		if (auth != null) {
			request.setAttribute("person", auth);
			OrderDAO OrderDAO = new OrderDAO(HelpConnectDB.getConnection());
			orders = OrderDAO.userOrders(auth.getId());

			request.setAttribute("orders", orders);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/orders.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
