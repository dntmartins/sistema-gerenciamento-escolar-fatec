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


//Responsável por pegar cookies do cabeçalho da requisição e setar para aplicação utilizar
public class CookiesConverterFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = HttpServletRequest.class.cast(req);
		//Pega todos os cookies enviados pelo navegador
		Cookie[] cookies = request.getCookies();

		//Se existir algum cookie, setar para a aplicação
		if (cookies != null) {
			for (Cookie ck : cookies) {
				request.setAttribute(ck.getName(), ck.getValue());
			}
		}
		//continua o processo para outros filtros
		chain.doFilter(request, resp);

	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
