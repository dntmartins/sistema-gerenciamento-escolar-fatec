package br.com.fatec.escola.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Carlos
 * 
 * @version
 */
public class ServletPermissaoStudent extends HttpServlet {

	/** */
	private static final long serialVersionUID = 6083870172163505171L;

@Override
protected void doGet(HttpServletRequest req,
		HttpServletResponse resp)
				throws ServletException, IOException {
	resp.sendRedirect("grade_horarios.html");
}

}
