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

import model.bean.AccountDisplay;
import model.bean.User;
import model.bo.AccountDisplayBO;

/**
 * Servlet implementation class ManageAccountsServlet
 */
@WebServlet("/ManageAccountsServlet")
public class ManageAccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	AccountDisplayBO accoutDisplayBO = new AccountDisplayBO();

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

			List<AccountDisplay> accountDisplays = accoutDisplayBO.getAllAccountDisplays();

			request.setAttribute("allAccountDisplays", accountDisplays);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/manageAccount.jsp");
			dispatcher.forward(request, response);
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
