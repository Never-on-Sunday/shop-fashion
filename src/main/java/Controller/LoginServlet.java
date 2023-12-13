package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.bo.UserBO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.sendRedirect("login.jsp");
		response.sendRedirect("GetAllProductsServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter out = response.getWriter()) {
			String username = request.getParameter("login-username");
			String password = request.getParameter("login-password");

			UserBO userBO = new UserBO();
			User user = userBO.getAUser(username, password);
			if (user != null) {
				request.getSession().setAttribute("authUser", user);
//				response.sendRedirect("index.jsp");
				if (user.getrole().equals("admin")) {
//					out.print("user1: " + user.getusername());
					response.sendRedirect("ManageProductsServlet");
				} else {
					response.sendRedirect("GetAllProductsServlet");
				}

			} else {
				request.setAttribute("status", "Wrong username or password!");
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			System.out.println("error: " + e.getMessage());
		}
	}

}
