package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.ScheduleDAO;
import br.com.fatec.escola.api.entity.Schedule;
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
	public void testSave() {
		Schedule schedule = null;
		schedule = this.dao.findById(1l);
		schedule.setBeginHour("09:00");
		Schedule scheduleSaved = this.dao.save(schedule);
		assertEquals(schedule.getBeginHour(), scheduleSaved.getBeginHour());		
	}
	
	@Test
	public void testFindBy() {
		Schedule schedule = this.dao.findById(1l);
		assertEquals(1L, schedule.getId(),1);		
	}
	
	
	@Test
	public void testFindAllTest() {
		Schedule s1 = new Schedule();
		s1.setDiscipline(2l);
		s1.setWeekDay("Quarta-Feira");
		s1.setBeginHour("07:00");
		this.dao.save(s1);
		List<Schedule> userList = this.dao.findAll();
		assertEquals(userList.size(), 3);
	}
	
	@Test
	public void testUpdate() {
		Schedule s1 = this.dao.findById(2l);
		s1.setDiscipline(2l);
		s1.setWeekDay("Sexta-Feira");
		s1.setBeginHour("07:00");
		s1 = this.dao.update(s1);
		assertEquals("Sexta-Feira", s1.getWeekDay());
	}

	@Test
	public void testDelete() {
		Schedule s1 = this.dao.findById(1l);
		this.dao.delete(s1);
		s1 = this.dao.findById(1l);
		Assert.assertNull(s1);	
	}
}
