package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.ScheduleDAO;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Schedule;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ScheduleDAOImplTest extends EscolaBaseTest {

	private ScheduleDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (ScheduleDAO) ImplementationFinder.getinstance().getImpl(ScheduleDAO.class);
	}
	
	@Test
	public void testSave() { //OK
		Schedule schedule = new Schedule();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		Discipline d = new Discipline();
		d.setModule(null);
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
		d.setModule(null);
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
		d.setModule(null);
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
		assertEquals(sList.size(), 3);
	}
	
	@Test
	public void testUpdate() { //OK
		Schedule schedule = new Schedule();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		Discipline d = new Discipline();
		d.setModule(null);
		d.setName("Matematica");
		d = dDAO.save(d);
		schedule.setBeginHour("07:00");
		schedule.setDiscipline(d);
		schedule.setEndHour("09:00");
		schedule.setWeekDay("Sexta-Feira");
		Schedule scheduleSaved = this.dao.save(schedule);
		schedule = this.dao.findById(scheduleSaved.getId());
		schedule.setWeekDay("Quarta");
		schedule = this.dao.update(schedule);
		this.dao.delete(scheduleSaved);
		dDAO.delete(d);
		assertEquals("Quarta", schedule.getWeekDay());
	}

	@Test
	public void testDelete() { //OK
		Schedule s1 = this.dao.findById(12l);
		this.dao.delete(s1);
		s1 = this.dao.findById(12l);
		Assert.assertNull(s1);	
	}
}
