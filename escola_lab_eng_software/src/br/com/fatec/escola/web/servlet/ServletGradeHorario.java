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
public class ServletGradeHorario extends HttpServlet {

	/** */
	private static final long serialVersionUID = 7774963102366849971L;
	private DisciplinesConflictService dcService;
	private final static String PATH = "/grade_horarios.jsp";

	public ServletGradeHorario() {
		super();
		this.dcService = new DisciplinesConflictService();
	}

	private long[] pseudoOneStepConversion(String[] numbers) {
		long[] result = new long[numbers.length];
		for (int i = 0; i < numbers.length; i++)
			result[i] = Long.parseLong(numbers[i]);
		return result;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		List<Discipline> disciplines = dDAO.findAll();
		req.setAttribute("disciplines", disciplines);
		RequestDispatcher view = req.getRequestDispatcher(PATH);
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String[] checkedIds = req.getParameterValues("checkedRows");
		if (checkedIds != null) {
			boolean hasConflict = false;
			try {
				hasConflict = dcService.hasConflictDisciplines(pseudoOneStepConversion(checkedIds));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if (hasConflict) {
				out.println("Ocorreu conflito de disciplinas");
			} else {
				out.println("Cadastro das disciplinas realizado com sucesso");
			}
		} else {
			out.println("Selecione as disciplinas");
		}
	}
}
