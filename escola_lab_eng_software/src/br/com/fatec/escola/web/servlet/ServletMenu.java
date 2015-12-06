package br.com.fatec.escola.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.web.helper.UserSessionHelper;

/**
 * @author Dante
 * 
 * @version
 */
public class ServletMenu extends HttpServlet {

	/** */
	private static final long serialVersionUID = 7774963102366849971L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = UserSessionHelper.getUserSession(req);
		if (user != null) {
			req.setAttribute("nome", user.getName());
			RequestDispatcher view = req.getRequestDispatcher("/menu.jsp");
			view.forward(req, resp);
		} else {
			resp.sendRedirect("login.html");
		}
	}
}
