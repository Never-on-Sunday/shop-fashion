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
 * Servlet implementation class ManageOrdersServlet
 */
@WebServlet("/ManageOrdersServlet")
public class ManageOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderBO orderBO = new OrderBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pw = response.getWriter()) {

			if (request.getParameter("pickDateFrom") != null) {
				pw.print("hihi pickDateFrom");
				return;
			}

			// check auth
			HttpSession ses = request.getSession();
			User user = (User) ses.getAttribute("authUser");
			if (user == null || !user.getrole().equals("admin")) {
				response.sendRedirect("index.jsp");
				return;
			}

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

			List<OrderDisplay> allOrders = null;
			allOrders = orderBO.get20OrdersDisplay(idxPage);
			request.setAttribute("allOrders", allOrders);

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/manageOrders.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void requsetPickDate(HttpServletRequest request, HttpServletResponse response) {
		String dateFrom = request.getParameter("pickDateFrom");
		String dateTo = request.getParameter("pickDateTo");
	}

}
