package br.com.fatec.escola.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

/**
 * @author Carlos
 * 
 * @version
 */
public class ServletLogin extends HttpServlet {

	/** */
	private static final long serialVersionUID = 7774963102366849971L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		User usuario = ImplementationFinder.getImpl(UserDAO.class).findByLoginAndPassword(login, password);
		if (usuario != null) {
			Cookie ck = new Cookie("login_usuario", login);
			ck.setMaxAge(3000);
			response.addCookie(ck);
			response.sendRedirect("menu");
		} else {
			response.sendRedirect("novoLogin.html");
		}
	}

}
