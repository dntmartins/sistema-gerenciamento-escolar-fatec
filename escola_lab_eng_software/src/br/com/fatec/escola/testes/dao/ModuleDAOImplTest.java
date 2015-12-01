package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.ModuleDAO;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.core.dao.CourseDAOImpl;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ModuleDAOImplTest extends EscolaBaseTest {

	private ModuleDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (ModuleDAO) ImplementationFinder.getImpl(ModuleDAO.class);
	}
	
	@Test
	public void testSave() { //OK
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
		Module sModule = this.dao.save(m);
		this.dao.delete(sModule);
		cDAO.delete(savedC);
		assertEquals(m.getName(), sModule.getName());		
	}
	
	@Test
	public void testFindBy() { //OK
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
		m = this.dao.save(m);
		Module sModule = this.dao.findById(m.getId());
		this.dao.delete(sModule);
		cDAO.delete(savedC);
		assertEquals(m.getId(), sModule.getId(),1);		
	}
	
	@Test
	public void testFindAllTest() { //OK
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
		m = this.dao.save(m);
		List<Module> mList = this.dao.findAll();
		this.dao.delete(m);
		cDAO.delete(savedC);
 		assertEquals(mList.size(), 1);
	}
	
	@Test
	public void testUpdate() { //OK
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
		m = this.dao.save(m);
		m.setName("2 semestre");
		Module mUpdated = this.dao.update(m);
		this.dao.delete(m);
		cDAO.delete(savedC);
		assertEquals("2 semestre", mUpdated.getName());
	}
	
	@Test
	public void testDelete() { //OK
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
		m = this.dao.save(m);
		m = this.dao.findById(m.getId());
		this.dao.delete(m);
		m = this.dao.findById(2l);
		Assert.assertNull(m);	
	}
	
}
