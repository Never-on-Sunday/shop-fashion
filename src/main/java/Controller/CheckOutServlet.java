package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cart;
import model.bean.Order;
import model.bean.Product;
import model.bean.User;
import model.bo.OrderBO;
import model.bo.PersonalInforBO;
import model.bo.ProductBO;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/CheckOutServlet")
public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderBO orderBO = new OrderBO();
	ProductBO productBO = new ProductBO();
	PersonalInforBO personalInfor = new PersonalInforBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try (PrintWriter out = response.getWriter()) {
			// check auth
			HttpSession ses = request.getSession();
			User user = (User) ses.getAttribute("authUser");
			if (user == null || !user.getrole().equals("client")) {
				response.sendRedirect("login.jsp");
				return;
			}

			LocalDate currentDate = LocalDate.now();
			Date sqlDate = Date.valueOf(currentDate);
			ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart_list");
			if (cart_list != null) {
				for (Cart cart : cart_list) {
					Order order = new Order();
					order.setProductID(cart.getId());
					order.setUid(user.getId());
					order.setQuantity(cart.getQuantity());
					order.setDate(sqlDate);
					order.setStatus("Handling");
					order.setAddress(personalInfor.getPersonalInforByAccID(user.getId()).getAddress());
					Product product = productBO.getAProduct(order.getProductID());
					order.setOrderPrice(product.getPrice() * order.getQuantity());

					int res = orderBO.addAnOrder(order);
				}
				cart_list.clear();
				response.sendRedirect("GetOrdersServlet");
			} else {
				response.sendRedirect("GetCartProductsServlet");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
