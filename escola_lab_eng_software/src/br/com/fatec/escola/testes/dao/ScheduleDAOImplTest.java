package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.ScheduleDAO;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.api.entity.Schedule;
import br.com.fatec.escola.core.dao.CourseDAOImpl;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ModuleDAOImpl;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ScheduleDAOImplTest extends EscolaBaseTest {

	private ScheduleDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (ScheduleDAO) ImplementationFinder.getImpl(ScheduleDAO.class);
	}
	
	@Test
	public void testSave() { //OK
		Schedule schedule = new Schedule();
		Discipline d = new Discipline();
		Module m = new Module();
		Course c = new Course();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course savedC = cDAO.save(c);
		m.setCourse(savedC);
		m.setName("1 semestre");
		Module sModule = mDAO.save(m);
		d.setModule(sModule);
		d.setName("Matematica");
		d = dDAO.save(d);
		schedule.setBeginHour("07:00");
		schedule.setDiscipline(d);
		schedule.setEndHour("09:00");
		schedule.setWeekDay("sexta-feira");
		Schedule scheduleSaved = this.dao.save(schedule);
		this.dao.delete(scheduleSaved);
		dDAO.delete(d);
		assertEquals(schedule.getBeginHour(), scheduleSaved.getBeginHour());		
	}
	
	@Test
	public void testFindBy() { //OK
		Schedule schedule = new Schedule();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		Module m = new Module();
		Course c = new Course();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course savedC = cDAO.save(c);
		m.setCourse(savedC);
		m.setName("1 semestre");
		Module sModule = mDAO.save(m);
		d.setModule(sModule);
		d.setName("Matematica");
		d = dDAO.save(d);
		schedule.setBeginHour("07:00");
		schedule.setDiscipline(d);
		schedule.setEndHour("09:00");
		schedule.setWeekDay("sexta-feira");
		Schedule scheduleSaved = this.dao.save(schedule);
		schedule = this.dao.findById(scheduleSaved.getId());
		this.dao.delete(scheduleSaved);
		dDAO.delete(d);
		assertEquals(scheduleSaved.getId(), schedule.getId(),1);		
	}
	
	@Test
	public void testFindAllTest() { //OK
		Schedule schedule = new Schedule();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		Module m = new Module();
		Course c = new Course();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course savedC = cDAO.save(c);
		m.setCourse(savedC);
		m.setName("1 semestre");
		Module sModule = mDAO.save(m);
		d.setModule(sModule);
		d.setName("Matematica");
		d = dDAO.save(d);
		schedule.setBeginHour("07:00");
		schedule.setDiscipline(d);
		schedule.setEndHour("09:00");
		schedule.setWeekDay("sexta-feira");
		Schedule scheduleSaved = this.dao.save(schedule);
		List<Schedule> sList = this.dao.findAll();
		this.dao.delete(scheduleSaved);
		dDAO.delete(d);
		assertEquals(sList.size(), 1);
	}
	
	@Test
	public void testUpdate() { //OK
		Schedule schedule = new Schedule();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		Module m = new Module();
		Course c = new Course();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course savedC = cDAO.save(c);
		m.setCourse(savedC);
		m.setName("1 semestre");
		Module sModule = mDAO.save(m);
		d.setModule(sModule);
		d.setName("Matematica");
		d = dDAO.save(d);
		schedule.setBeginHour("07:00");
		schedule.setDiscipline(d);
		schedule.setEndHour("09:00");
		schedule.setWeekDay("sexta-feira");
		Schedule scheduleSaved = this.dao.save(schedule);
		schedule = this.dao.update(scheduleSaved);
		this.dao.delete(scheduleSaved);
		dDAO.delete(d);
		assertEquals("Quarta", schedule.getWeekDay());
	}

	@Test
	public void testDelete() { //OK
		Schedule schedule = new Schedule();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		Module m = new Module();
		Course c = new Course();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course savedC = cDAO.save(c);
		m.setCourse(savedC);
		m.setName("1 semestre");
		Module sModule = mDAO.save(m);
		d.setModule(sModule);
		d.setName("Matematica");
		d = dDAO.save(d);
		schedule.setBeginHour("07:00");
		schedule.setDiscipline(d);
		schedule.setEndHour("09:00");
		schedule.setWeekDay("sexta-feira");
		Schedule scheduleSaved = this.dao.save(schedule);
		this.dao.delete(scheduleSaved);
		Schedule s1 = this.dao.findById(scheduleSaved.getId());
		Assert.assertNull(s1);	
	}
}
