package br.com.fatec.escola.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.service.DisciplinesConflictService;

/**
 * @author Dante
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
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		List<Discipline> disciplines = dDAO.findAll();
		req.setAttribute("disciplines", disciplines);
		RequestDispatcher view = req.getRequestDispatcher(PATH);
		view.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json");
		req.setCharacterEncoding("utf8");
		resp.setCharacterEncoding("utf8");
		PrintWriter out = resp.getWriter();
		String[] checkedIds = req.getParameterValues("checkedRows[]");
		if (checkedIds != null) {
			if(checkedIds.length == 3){
				boolean hasConflict = false;
				try {
					hasConflict = dcService.hasConflictDisciplines(pseudoOneStepConversion(checkedIds));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if (hasConflict) {
					out.print("{\"msg\":\"Ocorreu conflito de disciplinas, verificar horário das materias\", \"status\":false}");
				} else {
					out.print("{\"msg\":\"Disciplinas matriculadas com sucesso\", \"status\":true}");
				}
			}else{
				out.print("{\"msg\":\"Cadastre 3 disciplinas\", \"status\":false}");
			}
		} else {
			out.print("{\"msg\":\"Selecione as disciplinas\", \"status\":false}");
		}
	}
}
