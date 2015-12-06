package br.com.fatec.escola.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.StudentClassRoom;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.StudentClassRoomDAOImpl;
import br.com.fatec.escola.core.dao.UserDAOImpl;
import br.com.fatec.escola.core.service.DisciplinesConflictService;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

/**
 * @author Carlos
 * 
 * @version
 */
public class ServletMenu extends HttpServlet {

	/** */
	private static final long serialVersionUID = 7774963102366849971L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpServletRequest request = HttpServletRequest.class.cast(req);
		Cookie[] cookies = request.getCookies();
		User user = null;
		if (cookies != null) {
			for (Cookie ck : cookies) {
				if(ck.getName().equals("login_usuario")){
					user = ImplementationFinder.getImpl(UserDAO.class).findByLogin(ck.getValue());
				}
			}
			if(user != null){
				request.setAttribute("nome", user.getName());
				RequestDispatcher view = req.getRequestDispatcher("/menu.jsp");
				view.forward(req, resp);
			}else{
				
			}
		}
	}
}
