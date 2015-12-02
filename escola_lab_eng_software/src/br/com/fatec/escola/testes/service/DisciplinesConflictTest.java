package br.com.fatec.escola.testes.service;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.core.dao.CourseDAOImpl;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ModuleDAOImpl;
import br.com.fatec.escola.core.service.DisciplinesConflictService;
import br.com.fatec.escola.testes.common.EscolaBaseTest;

public class DisciplinesConflictTest extends EscolaBaseTest {

	private DisciplineDAOImpl dDAO;
	private ModuleDAOImpl mDAO;
	private CourseDAOImpl cDAO;

	@Before
	public void config() {
		this.dDAO = new DisciplineDAOImpl();
		this.mDAO = new ModuleDAOImpl();
		this.cDAO = new CourseDAOImpl();
	}

	@Test
	public void testConflicts() throws ParseException {
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		Discipline d2 = new Discipline();
		Discipline d3 = new Discipline();
		DisciplinesConflictService vd = new DisciplinesConflictService();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = this.cDAO.save(c);
		m.setCourse(cSaved);
		m.setName("1 semestre");
		Module mSaved = this.mDAO.save(m);
		d.setModule(mSaved);
		d.setName("Portugues");
		d.setBeginHour("09:00");
		d.setWeekDay("Sexta-feira");
		d.setEndHour("10:00");
		d2.setModule(mSaved);
		d2.setName("Matematica");
		d2.setBeginHour("10:00");
		d2.setWeekDay("Sexta-feira");
		d2.setEndHour("12:00");
		d3.setModule(mSaved);
		d3.setName("Quimica");
		d3.setBeginHour("12:00");
		d3.setWeekDay("Sexta-feira");
		d3.setEndHour("14:00");
		Discipline dSaved = this.dDAO.save(d);
		Discipline dSaved2 = this.dDAO.save(d2);
		Discipline dSaved3 = this.dDAO.save(d3);
		long[] ids = { dSaved.getId(), dSaved2.getId(), dSaved3.getId() };
		assertEquals(false, vd.hasConflictDisciplines(ids));
	}
}
