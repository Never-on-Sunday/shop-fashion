package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
			// check auth
			HttpSession ses = request.getSession();
			User user = (User) ses.getAttribute("authUser");
			if (user == null || !user.getrole().equals("admin")) {
				response.sendRedirect("index.jsp");
				return;
			}

			// process request pick date from to
			String dateFrom = request.getParameter("pickDateFrom");
			String dateTo = request.getParameter("pickDateTo");
			if (dateFrom != null && dateTo != null) {
				requestPickDate(request, response, dateFrom, dateTo);
			} else {
				dateFrom = "2000-01-01";
				LocalDate currentDate = LocalDate.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dateTo = currentDate.format(formatter);
				requestPickDate(request, response, dateFrom, dateTo);
			}

			// get all orders (old version)
//			int idxPage = getIdxPage(request, response);
//			List<OrderDisplay> allOrders = null;
//			allOrders = orderBO.get20OrdersDisplay(idxPage);
//			request.setAttribute("allOrders", allOrders);
//			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/manageOrders.jsp");
//			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void requestPickDate(HttpServletRequest request, HttpServletResponse response, String dateFrom,
			String dateTo) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// Parse the string to a java.util.Date object
		java.util.Date utilDate;
		try {
			utilDate = dateFormat.parse(dateFrom);
			Date sqlDateFrom = new Date(utilDate.getTime());
			utilDate = dateFormat.parse(dateTo);
			Date sqlDateTo = new Date(utilDate.getTime());

			int idxPage = getIdxPage(request, response);
			List<OrderDisplay> allOrders = null;
			allOrders = orderBO.get20OrdersDisplayByDate(idxPage, sqlDateFrom, sqlDateTo);
			request.setAttribute("allOrders", allOrders);
			request.setAttribute("dateFrom", dateFrom);
			request.setAttribute("dateTo", dateTo);
			double totalOrderIncome = orderBO.getTotalIncomeOrders(sqlDateFrom, sqlDateTo);
			request.setAttribute("totalOrderIncome", totalOrderIncome);

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/manageOrders.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
