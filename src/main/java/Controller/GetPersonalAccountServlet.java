package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.AccountDisplay;
import model.bean.PersonalInfor;
import model.bean.User;
import model.bo.PersonalInforBO;
import model.bo.UserBO;

/**
 * Servlet implementation class GetPersonalAccount
 */
@WebServlet("/GetPersonalAccountServlet")
public class GetPersonalAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PersonalInforBO personalInforBO = new PersonalInforBO();
	UserBO userBO = new UserBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pw = response.getWriter()) {
			// check auth
			HttpSession ses = request.getSession();
			User user = (User) ses.getAttribute("authUser");
			if (user == null || !(user.getrole().equals("client") || user.getrole().equals("admin"))) {
				response.sendRedirect("index.jsp");
				return;
			}

			String accID = (String) request.getParameter("accID");
			if (accID != null) {
				user = userBO.getAUserByID(Integer.parseInt(accID));
			}

			PersonalInfor personalInfor = null;
			personalInfor = personalInforBO.getPersonalInforByAccID(user.getId());
			AccountDisplay accountDisplay = new AccountDisplay();
			accountDisplay.setPersonalInfor(personalInfor);
			accountDisplay.setUser(user);

			request.setAttribute("accountDisplay", accountDisplay);
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/personalInfor.jsp");
			dispatcher.forward(request, response);

//			response.sendRedirect("")
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
