package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Order;
import model.bean.User;
import model.bo.OrderBO;

/**
 * Servlet implementation class UpdateAnOrderServlet
 */
@WebServlet("/UpdateAnOrderServlet")
public class UpdateAnOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderBO orderBO = new OrderBO();

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

			int orderID = Integer.parseInt((String) request.getParameter("orderID"));
			Order order = orderBO.getAnOrderByID(orderID);
			String status = (String) request.getParameter("selectStatus");
			String address = (String) request.getParameter("address");
			order.setStatus(status);
			order.setAddress(address);
			int res = orderBO.updateAnOrder(order);

			response.sendRedirect("ManageOrdersServlet");

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
