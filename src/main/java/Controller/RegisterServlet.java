package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.PersonalInfor;
import model.bean.User;
import model.bo.PersonalInforBO;
import model.bo.UserBO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserBO userBO = new UserBO();
	PersonalInforBO personalInforBO = new PersonalInforBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pw = response.getWriter()) {
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			String fullName = request.getParameter("fullName");
			String address = request.getParameter("address");
			String phoneNumber = request.getParameter("phoneNumber");

			User user = null;
			// check if username is already existed
			user = userBO.getAUserByUserName(userName);
			if (user != null) {
				String status = "Username has been already existed";
				request.setAttribute("status", status);
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/register.jsp");
				dispatcher.forward(request, response);
				return;
			}

			user = new User();
			user.setusername(userName);
			user.setpassword(password);
			user.setrole("client");
			int resUser = userBO.createAUser(user);
			int accID = userBO.getAUser(userName, password).getId();

			PersonalInfor personalInfor = new PersonalInfor();
			personalInfor.setFullName(fullName);
			personalInfor.setAddress(address);
			personalInfor.setPhoneNumber(phoneNumber);
			personalInfor.setAccID(accID);
			int resPersonalInfor = personalInforBO.createAPersonalInfor(personalInfor);

			response.sendRedirect("login.jsp");
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
