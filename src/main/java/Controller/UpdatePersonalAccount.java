package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.PersonalInfor;
import model.bean.User;
import model.bo.PersonalInforBO;
import model.bo.UserBO;

/**
 * Servlet implementation class ManagePersonalAccount
 */
@WebServlet("/UpdatePersonalAccountServlet")
public class UpdatePersonalAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserBO userBO = new UserBO();
	PersonalInforBO personalInforBO = new PersonalInforBO();

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

			User userUpdate = new User();
			userUpdate = userBO.getAUserByID(Integer.parseInt((String) request.getParameter("userID")));
			userUpdate.setusername(request.getParameter("userName"));
			userUpdate.setpassword(request.getParameter("password"));
			PersonalInfor personalInfor = personalInforBO.getPersonalInforByAccID(userUpdate.getId());
			personalInfor.setFullName(request.getParameter("fullName"));
			personalInfor.setAddress(request.getParameter("address"));
			personalInfor.setPhoneNumber(request.getParameter("phoneNumber"));

			int resUserUpdate = userBO.updateAnUser(userUpdate);
			if (user.getrole().equals("client"))
				ses.setAttribute("authUser", userUpdate);
			int resPersonalInfor = personalInforBO.updatePersonalInfor(personalInfor);

			if (user.getrole().equals("admin")) {
				response.sendRedirect("ManageAccountsServlet");
			} else if (user.getrole().equals("client")) {
				response.sendRedirect("GetPersonalAccountServlet?accID=" + userUpdate.getId());
			} else {
				response.sendRedirect("index.jsp");
			}

//			pw.print("update");
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
