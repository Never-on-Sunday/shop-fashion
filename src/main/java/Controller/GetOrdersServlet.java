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
import javax.servlet.http.HttpSession;

import model.bean.OrderDisplay;
import model.bean.User;
import model.bo.OrderBO;

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
		try (PrintWriter pw = response.getWriter()) {
			// check auth
			HttpSession ses = request.getSession();
			User user = (User) ses.getAttribute("authUser");
			if (user == null || !user.getrole().equals("client")) {
				response.sendRedirect("index.jsp");
				return;
			}

			int idxPage = getIdxPage(request, response);
			List<OrderDisplay> allOrders = null;
			allOrders = orderBO.get20OrdersDisplayOfAUser(idxPage, user.getId());
			request.setAttribute("allOrders", allOrders);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/orders.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

//		List<Order> orders = null;
//		User auth = (User) request.getSession().getAttribute("auth");
//
//		if (auth != null) {
//			request.setAttribute("person", auth);
//			OrderDAO OrderDAO = new OrderDAO(HelpConnectDB.getConnection());
//			orders = OrderDAO.userOrders(auth.getId());
//
//			request.setAttribute("orders", orders);
//			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/orders.jsp");
//			dispatcher.forward(request, response);
//		} else {
//			response.sendRedirect("login.jsp");
//		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public int getIdxPage(HttpServletRequest request, HttpServletResponse response) {
		String page = (String) request.getParameter("page");
		int idxPage = 1; // for someone first go to page
		// if someone click on prev or next page
		if (page != null) {
			idxPage = (int) request.getSession().getAttribute("idxPage");
			if (page.equals("prev")) {
				if (idxPage > 1)
					idxPage -= 1;
			} else if (page.equals("next")) {
				if (idxPage < orderBO.getNumberOfPages())
					idxPage += 1;
			} else {

			}
		}
		request.getSession().setAttribute("idxPage", idxPage);
		return idxPage;
	}

}
