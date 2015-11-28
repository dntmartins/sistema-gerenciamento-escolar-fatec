package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.CourseDAO;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class CourseDAOImplTest extends EscolaBaseTest {

	private CourseDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (CourseDAO) ImplementationFinder.getinstance().getImpl(CourseDAO.class);
	}
	
	@Test
	public void testSave() { //OK
		Course c = new Course();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = this.dao.save(c);
		this.dao.delete(cSaved);
		assertEquals(c.getName(), cSaved.getName());		
	}
	
	@Test
	public void testFindBy() { //OK
		Course c = new Course();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = this.dao.save(c);
		c = this.dao.findById(cSaved.getId());
		this.dao.delete(cSaved);
		assertEquals(c.getId(), cSaved.getId(),1);		
	}
	
	@Test
	public void testFindAllTest() { //OK
		Course c = new Course();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = this.dao.save(c);
		List<Course> cList = this.dao.findAll(); 
		this.dao.delete(cSaved);
		assertEquals(cList.size(), 5);
	}
	
	@Test
	public void testUpdate() { //OK
		Course c = new Course();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = this.dao.save(c);
		cSaved.setName("Analise e Desenvolvimento de Sistemas");
		c = this.dao.update(cSaved);
		this.dao.delete(cSaved);
		assertEquals("Analise e Desenvolvimento de Sistemas", c.getName());
	}
	
	@Test
	public void testDelete() { //OK
		Course c = new Course();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = this.dao.save(c);
		this.dao.delete(cSaved);
		c = this.dao.findById(cSaved.getId());
		Assert.assertNull(c);	
	}
}
