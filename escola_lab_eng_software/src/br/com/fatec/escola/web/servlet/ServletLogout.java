package br.com.fatec.escola.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carlos
 * 
 * @version
 */

//Apaga cookie de nome login_usuario
public class ServletLogout extends HttpServlet {

	/** */
	private static final long serialVersionUID = 7774963102366849971L;

@Override
protected void doGet(HttpServletRequest request,
		HttpServletResponse response)
				throws ServletException, IOException {
	Cookie loginCookie = null;
	Cookie[] cookies = request.getCookies();
	if (cookies != null) {
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("login_usuario")) {
				loginCookie = cookie;
				break;
			}
		}
	}
	if (loginCookie != null) {
		loginCookie.setMaxAge(0);
		response.addCookie(loginCookie);
	}
	response.sendRedirect("login.html");
}

}
