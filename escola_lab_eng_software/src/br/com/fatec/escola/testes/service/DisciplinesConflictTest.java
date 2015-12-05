package br.com.fatec.escola.testes.service;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.api.entity.Schedule;
import br.com.fatec.escola.core.dao.CourseDAOImpl;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ModuleDAOImpl;
import br.com.fatec.escola.core.dao.ScheduleDAOImpl;
import br.com.fatec.escola.core.service.DisciplinesConflictService;
import br.com.fatec.escola.testes.common.EscolaBaseTest;

public class DisciplinesConflictTest extends EscolaBaseTest {

	private DisciplineDAOImpl dDAO;
	private ModuleDAOImpl mDAO;
	private CourseDAOImpl cDAO;
	private ScheduleDAOImpl sDAO;

	@Before
	public void config() {
		this.dDAO = new DisciplineDAOImpl();
		this.mDAO = new ModuleDAOImpl();
		this.cDAO = new CourseDAOImpl();
		this.sDAO = new ScheduleDAOImpl();
	}

	@Test
	public void testConflicts() throws ParseException {
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		Discipline d2 = new Discipline();
		Discipline d3 = new Discipline();
		Schedule schedule = new Schedule();
		DisciplinesConflictService vd = new DisciplinesConflictService();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = this.cDAO.save(c);
		m.setCourse(cSaved);
		m.setName("1 semestre");
		Module mSaved = this.mDAO.save(m);
		
		//Primeira Disciplina
		d.setModule(mSaved);
		d.setName("Portugues");
		Discipline dSaved = this.dDAO.save(d);
		schedule.setBeginHour("07:00");
		schedule.setDiscipline(dSaved);
		schedule.setEndHour("09:00");
		schedule.setWeekDay("sexta-feira");
		sDAO.save(schedule);
		
		//Segunda Disciplina
		d2.setModule(mSaved);
		d2.setName("Matematica");
		Discipline dSaved2 = this.dDAO.save(d2);
		schedule.setBeginHour("09:00");
		schedule.setDiscipline(dSaved2);
		schedule.setEndHour("10:00");
		schedule.setWeekDay("sexta-feira");
		sDAO.save(schedule);
		
		//Terceira Disciplina
		d3.setModule(mSaved);
		d3.setName("Quimica");
		Discipline dSaved3 = this.dDAO.save(d3);
		schedule.setBeginHour("09:10");
		schedule.setDiscipline(dSaved3);
		schedule.setEndHour("11:00");
		schedule.setWeekDay("sexta-feira");
		sDAO.save(schedule);
		
		long[] ids = { dSaved.getId(), dSaved2.getId(), dSaved3.getId() };
		assertEquals(true, vd.hasConflictDisciplines(ids));
	}
}
