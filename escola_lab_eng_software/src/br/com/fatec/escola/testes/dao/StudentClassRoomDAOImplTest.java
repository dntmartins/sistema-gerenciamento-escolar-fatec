package br.com.fatec.escola.testes.dao;

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
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class StudentClassRoomDAOImplTest extends EscolaBaseTest {

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
	public void testSave() {
		Student student = new Student();
		Role role = new Role();
		Module m = new Module();
		Course c = new Course();
		Discipline d = new Discipline();
		ClassRoom cR = new ClassRoom();
		StudentClassRoom sCR = new StudentClassRoom();
		role.setRoleName("Visitante");
		role.setIsAdmin(false);
		role = rDAO.save(role);
		student.setLogin("dantee.alemao");
		student.setName("Dante Martins");
		student.setPassword("dante123");
		student.setIsTeacher(false);
		student.setRole(role);
		User userSaved = uDAO.save(student);
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
		Discipline dSaved = this.dDAO.save(d);
		cR.setName("Sala 402");
		cR.setDiscipline(dSaved);
		cR.setModule(mSaved);
		ClassRoom cRSaved = this.cRDAO.save(cR);
		sCR.setStudent((Student)userSaved);
		sCR.setClassRoom(cRSaved);
		sCR.setTestNote(8f);
		sCR = this.dao.save(sCR);
		this.dao.delete(sCR);
		this.cRDAO.delete(cRSaved);
		this.dDAO.delete(dSaved);
		this.mDAO.delete(mSaved);
		this.cDAO.delete(cSaved);
		assertEquals(sCR.getClassRoom().getName(), cRSaved.getName());		
	}
	
	@Test
	public void testFindBy() {
		//assertEquals(1L, user.getId(),1);		
	}
	
	@Test
	public void testFindAllTest() {
		//assertEquals(userList.size(), 6);
	}
	
	@Test
	public void testUpdate() {
		//assertEquals("testeNome", user.getName());
	}
	
	@Test
	public void testDelete() {
		//Assert.assertNull(user);	
	}
}
