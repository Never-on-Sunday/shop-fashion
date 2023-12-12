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

import model.bean.Product;
import model.bean.User;
import model.bo.ProductBO;

/**
 * Servlet implementation class GetAllProducts
 */
@WebServlet("/GetAllProductsServlet")
public class GetAllProductsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductBO productBO = new ProductBO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try (PrintWriter pw = response.getWriter()) {
			// check auth
			HttpSession ses = request.getSession();
			User user = (User) ses.getAttribute("authUser");
			if (user != null && user.getrole().equals("admin")) {
				response.sendRedirect("ManageProductsServlet");
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
					if (idxPage < productBO.getNumberOfPages())
						idxPage += 1;
				} else {

				}
			}

			request.getSession().setAttribute("idxPage", idxPage);

			List<Product> allProducts = null;
			allProducts = productBO.get20Products(idxPage);
			request.setAttribute("allProducts", allProducts);

			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/home.jsp");
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
