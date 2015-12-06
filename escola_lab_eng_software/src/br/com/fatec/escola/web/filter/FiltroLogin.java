package br.com.fatec.escola.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

/**
 * @author Dante
 * 
 * @version
 */
public class FiltroLogin implements Filter {

@Override
public void doFilter(ServletRequest req, ServletResponse resp,
		FilterChain chain) throws IOException,
		ServletException {

	String login = (String) req.getAttribute("login_usuario");

	HttpServletResponse httpResponse =
			(HttpServletResponse) resp;

	if (login != null) {
		User usuario = ImplementationFinder
				.getImpl(UserDAO.class).findByLogin(login);
		
		req.setAttribute("usuario", usuario);
		chain.doFilter(req, resp);
	} else {
		httpResponse.sendRedirect("login.html");
	}
	return;
}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
