package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.HelpConnectDB;
import model.dao.OrderDAO;

/**
 * Servlet implementation class CancelOrderServlet
 */
@WebServlet("/CancelOrderServlet")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if (id != null) {
				OrderDAO OrderDAO = new OrderDAO(HelpConnectDB.getConnection());
				OrderDAO.cancelOrder(Integer.parseInt(id));
			}
			response.sendRedirect("GetOrdersServlet");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
