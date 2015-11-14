package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.TeacherDAO;
import br.com.fatec.escola.api.entity.Teacher;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class TeacherDAOImplTest extends EscolaBaseTest {

	private TeacherDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (TeacherDAO) ImplementationFinder.getinstance().getImpl(TeacherDAO.class);
	}
	
	@Test
	public void testSave() {
		Teacher teacher = new Teacher();
		teacher.setUserId(2l);
		Teacher teacherSaved = this.dao.save(teacher);
		assertEquals(2l, teacherSaved.getUserId(),1);		
	}
	
	@Test
	public void testFindBy() {
		Teacher teacher = this.dao.findById(1l);
		assertEquals(1L, teacher.getId(),1);		
	}

	@Test
	public void testFindAllTest() {
		Teacher teacher1 = new Teacher();
		teacher1.setUserId(2l);
		Teacher teacher2 = new Teacher();
		teacher2.setUserId(3l);
		this.dao.save(teacher1);
		this.dao.save(teacher2);
		List<Teacher> userList = this.dao.findAll();
		assertEquals(userList.size(), 4);
	}
	
	@Test
	public void testUpdate() {
		Teacher teacher = this.dao.findById(2l);
		teacher.setUserId(2l);
		teacher = this.dao.update(teacher);;
		assertEquals(4l, teacher.getUserId(),1);
	}
	
	@Test
	public void testDelete() {
		Teacher teacher = this.dao.findById(1l);
		this.dao.delete(teacher);
		teacher = this.dao.findById(1l);
		Assert.assertNull(teacher);	
	}
}
