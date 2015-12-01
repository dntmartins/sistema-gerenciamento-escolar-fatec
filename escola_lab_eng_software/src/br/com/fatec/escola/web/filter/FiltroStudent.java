package br.com.fatec.escola.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.escola.api.entity.User;


/**
 * @author Carlos
 * 
 * @version
 */
public class FiltroStudent implements Filter {

@Override
public void doFilter(ServletRequest req, ServletResponse resp,
		FilterChain chain) throws IOException,
		ServletException {

	User usuario = (User) req.getAttribute("usuario");

	HttpServletResponse httpResponse = (HttpServletResponse) resp;

	if (usuario != null) {
		if (usuario.getRole().getRoleName().equals("Student")) {
			chain.doFilter(req, resp);
		} else {
			httpResponse.sendRedirect("semPermissao.html");
		}
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
