package br.com.fatec.escola.web.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.User;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class UserSessionHelper {

	public static User getUserSession(HttpServletRequest req) {
		HttpServletRequest request = HttpServletRequest.class.cast(req);
		Cookie[] cookies = request.getCookies();
		User user = null;
		if (cookies != null) {
			for (Cookie ck : cookies) {
				if (ck.getName().equals("login_usuario")) {
					user = ImplementationFinder.getImpl(UserDAO.class).findByLogin(ck.getValue());
					return user;
				}
			}
		}
		return null;
	}
}
