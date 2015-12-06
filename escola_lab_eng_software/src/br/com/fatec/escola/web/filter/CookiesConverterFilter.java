package br.com.fatec.escola.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Dante
 * 
 * @version
 */
public class CookiesConverterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = HttpServletRequest.class.cast(req);
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie ck : cookies) {
				request.setAttribute(ck.getName(), ck.getValue());
			}
		}

		chain.doFilter(request, resp);

	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
