package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.DisciplineDAO;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.core.dao.CourseDAOImpl;
import br.com.fatec.escola.core.dao.ModuleDAOImpl;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class DisciplineDAOImplTest extends EscolaBaseTest {

	private DisciplineDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (DisciplineDAO) ImplementationFinder.getinstance().getImpl(DisciplineDAO.class);
	}
	
	@Test
	public void testSave() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = cDAO.save(c);
		m.setCourse(cSaved);
		m.setName("1 semestre");
		Module mSaved = mDAO.save(m);
		d.setModule(mSaved);
		d.setName("Portugues");
		d.setWeekDay("Sexta-feira");
		d.setBeginHour("14:00");
		d.setEndHour("12:00");
		Discipline dSaved = this.dao.save(d);
		this.dao.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
		assertEquals(d.getName(), dSaved.getName());	
	}
	
	@Test
	public void testFindBy() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = cDAO.save(c);
		m.setCourse(cSaved);
		m.setName("1 semestre");
		Module mSaved = mDAO.save(m);
		d.setModule(mSaved);
		d.setName("Portugues");
		d.setWeekDay("Sexta-feira");
		d.setBeginHour("14:00");
		d.setEndHour("12:00");
		Discipline dSaved = this.dao.save(d);
		d = this.dao.findById(dSaved.getId());
		this.dao.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
		assertEquals(dSaved.getId(), dSaved.getId(),1);		
	}
	
	@Test
	public void testFindAllTest() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = cDAO.save(c);
		m.setCourse(cSaved);
		m.setName("1 semestre");
		Module mSaved = mDAO.save(m);
		d.setModule(mSaved);
		d.setName("Portugues");
		d.setWeekDay("Sexta-feira");
		d.setBeginHour("14:00");
		d.setEndHour("12:00");
		Discipline dSaved = this.dao.save(d);
		List<Discipline> dList = this.dao.findAll();
		this.dao.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
		assertEquals(dList.size(), 1);
	}
	
	@Test
	public void testUpdate() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = cDAO.save(c);
		m.setCourse(cSaved);
		m.setName("1 semestre");
		Module mSaved = mDAO.save(m);
		d.setModule(mSaved);
		d.setName("Portugues");
		d.setWeekDay("Sexta-feira");
		d.setBeginHour("14:00");
		d.setEndHour("12:00");
		Discipline dSaved = this.dao.save(d);
		dSaved.setName("Matematica");
		d = this.dao.update(dSaved);
		this.dao.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
		assertEquals("Matematica", d.getName());
	}

	@Test
	public void testDelete() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		CourseDAOImpl cDAO = new CourseDAOImpl();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = cDAO.save(c);
		m.setCourse(cSaved);
		m.setName("1 semestre");
		Module mSaved = mDAO.save(m);
		d.setModule(mSaved);
		d.setName("Portugues");
		d.setWeekDay("Sexta-feira");
		d.setBeginHour("14:00");
		d.setEndHour("12:00");
		Discipline dSaved = this.dao.save(d);
		this.dao.delete(dSaved);
		d = this.dao.findById(dSaved.getId());
		Assert.assertNull(d);	
	}
}
