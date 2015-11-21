package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.ClassRoomDAO;
import br.com.fatec.escola.api.entity.ClassRoom;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.core.dao.CourseDAOImpl;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ModuleDAOImpl;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ClassRoomDAOImplTest extends EscolaBaseTest {

	private ClassRoomDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (ClassRoomDAO) ImplementationFinder.getinstance().getImpl(ClassRoomDAO.class);
	}
	
	@Test
	public void testSave() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ClassRoom cR = new ClassRoom();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
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
		Discipline dSaved = dDAO.save(d);
		cR.setName("Sala 402");
		cR.setDiscipline(dSaved);
		cR.setModule(mSaved);
		ClassRoom cRSaved = this.dao.save(cR);
		this.dao.delete(cRSaved);
		dDAO.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
	}
	
	@Test
	public void testFindBy() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ClassRoom cR = new ClassRoom();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
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
		Discipline dSaved = dDAO.save(d);
		cR.setName("Sala 402");
		cR.setDiscipline(dSaved);
		cR.setModule(mSaved);
		ClassRoom cRSaved = this.dao.save(cR);
		cR = this.dao.findById(cRSaved.getId());
		this.dao.delete(cRSaved);
		dDAO.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
		assertEquals(cR.getId(), cRSaved.getId());		
	}
	
	@Test
	public void testFindAllTest() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ClassRoom cR = new ClassRoom();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
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
		Discipline dSaved = dDAO.save(d);
		cR.setName("Sala 402");
		cR.setDiscipline(dSaved);
		cR.setModule(mSaved);
		ClassRoom cRSaved = this.dao.save(cR);
		List<ClassRoom> cRList = this.dao.findAll();
		this.dao.delete(cRSaved);
		dDAO.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
		assertEquals(cRList.size(), 2);
	}
	
	@Test
	public void testUpdate() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ClassRoom cR = new ClassRoom();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
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
		Discipline dSaved = dDAO.save(d);
		cR.setName("Sala 402");
		cR.setDiscipline(dSaved);
		cR.setModule(mSaved);
		ClassRoom cRSaved = this.dao.save(cR);
		cRSaved.setName("Sala 408");
		cR = this.dao.update(cRSaved);
		this.dao.delete(cRSaved);
		dDAO.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
		assertEquals("Sala 408", cR.getName());
	}
	
	@Test
	public void testDelete() { //OK
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ClassRoom cR = new ClassRoom();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
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
		Discipline dSaved = dDAO.save(d);
		cR.setName("Sala 402");
		cR.setDiscipline(dSaved);
		cR.setModule(mSaved);
		ClassRoom cRSaved = this.dao.save(cR);
		this.dao.delete(cRSaved);
		cR = this.dao.findById(cRSaved.getId());
		dDAO.delete(dSaved);
		mDAO.delete(mSaved);
		cDAO.delete(cSaved);
		Assert.assertNull(cR);	
	}
}
