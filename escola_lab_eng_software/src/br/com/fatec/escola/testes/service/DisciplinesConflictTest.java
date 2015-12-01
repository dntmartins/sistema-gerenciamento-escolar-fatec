package br.com.fatec.escola.testes.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.StudentClassRoomDAO;
import br.com.fatec.escola.api.entity.ClassRoom;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.api.entity.Role;
import br.com.fatec.escola.api.entity.Student;
import br.com.fatec.escola.api.entity.StudentClassRoom;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.dao.ClassRoomDAOImpl;
import br.com.fatec.escola.core.dao.CourseDAOImpl;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ModuleDAOImpl;
import br.com.fatec.escola.core.dao.RoleDAOImpl;
import br.com.fatec.escola.core.dao.UserDAOImpl;
import br.com.fatec.escola.core.service.DisciplinesConflictService;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class DisciplinesConflictTest extends EscolaBaseTest{

	private StudentClassRoomDAO dao;
	private DisciplineDAOImpl dDAO;
	private ClassRoomDAOImpl cRDAO;
	private ModuleDAOImpl mDAO;
	private CourseDAOImpl cDAO;
	private RoleDAOImpl rDAO;
	private UserDAOImpl uDAO;
	
	@Before
	public void config()
	{
		this.dao = (StudentClassRoomDAO) ImplementationFinder.getImpl(StudentClassRoomDAO.class);
		this.dDAO = new DisciplineDAOImpl();
		this.cRDAO =  new ClassRoomDAOImpl();
		this.mDAO = new ModuleDAOImpl();
		this.cDAO = new CourseDAOImpl();
		this.rDAO = new RoleDAOImpl();
		this.uDAO = new UserDAOImpl();
	}
	
	@Test
	public void testConflictedDiscipline() {
		Role role = new Role();
		Student s = new Student();
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ClassRoom cR = new ClassRoom();
		StudentClassRoom sCR = new StudentClassRoom();
		DisciplinesConflictService vd = new DisciplinesConflictService();
		role.setRoleName("Visitante");
		role.setIsAdmin(false);
		role = rDAO.save(role);
		s.setLogin("dantee.alemao");
		s.setName("Dante Martins");
		s.setPassword("dante123");
		s.setIsTeacher(false);
		s.setRole(role);
		User userSaved = uDAO.save(s);
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
		Discipline dSaved = this.dDAO.save(d);
		cR.setName("Sala 402");
		cR.setDiscipline(dSaved);
		cR.setModule(mSaved);
		ClassRoom cRSaved = this.cRDAO.save(cR);
		sCR.setStudent((Student)userSaved);
		sCR.setClassRoom(cRSaved);
		sCR.setTestNote(8f);
		sCR = this.dao.save(sCR);
		try {
			//Tem que retornar true pois as matérias são de sexta-feira e tem o mesmo horario, logo tem conflito.
			assertEquals(true, vd.matchDiscipline(dSaved, userSaved.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMatchScheduleDiscipline() {
		Role role = new Role();
		Student s = new Student();
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ClassRoom cR = new ClassRoom();
		StudentClassRoom sCR = new StudentClassRoom();
		DisciplinesConflictService vd = new DisciplinesConflictService();
		role.setRoleName("Visitante");
		role.setIsAdmin(false);
		role = rDAO.save(role);
		s.setLogin("dantee.alemao");
		s.setName("Dante Martins");
		s.setPassword("dante123");
		s.setIsTeacher(false);
		s.setRole(role);
		User userSaved = uDAO.save(s);
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
		Discipline dSaved = this.dDAO.save(d);
		cR.setName("Sala 402");
		cR.setDiscipline(dSaved);
		cR.setModule(mSaved);
		ClassRoom cRSaved = this.cRDAO.save(cR);
		sCR.setStudent((Student)userSaved);
		sCR.setClassRoom(cRSaved);
		sCR.setTestNote(8f);
		sCR = this.dao.save(sCR);
		Discipline d2 = new Discipline();
		d2.setModule(mSaved);
		d2.setName("Matemática");
		d2.setBeginHour("06:00");
		d2.setWeekDay("Sexta-feira");
		d2.setEndHour("9:00");
		Discipline dSaved2 = this.dDAO.save(d2);
		try {
			//Tem que retornar false pois as matérias são de sexta-feira e não tem choque de horário
			assertEquals(false, vd.matchDiscipline(dSaved2, userSaved.getId()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
