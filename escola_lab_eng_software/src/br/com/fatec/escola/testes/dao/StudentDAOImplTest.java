package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.StudentDAO;
import br.com.fatec.escola.api.entity.Student;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class StudentDAOImplTest extends EscolaBaseTest {

	private StudentDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (StudentDAO) ImplementationFinder.getinstance().getImpl(StudentDAO.class);
	}
	
	@Test
	public void testSave() {
		Student student = new Student();
		student.setUserId(2l);
		Student studentSaved = this.dao.save(student);
		assertEquals(student.getUserId(), studentSaved.getUserId());		
	}
	
	@Test
	public void testFindBy() {
		Student student = this.dao.findById(1l);
		assertEquals(1L, student.getId(),1);		
	}

	@Test
	public void testFindAllTest() {
		Student student1 = new Student();
		student1.setUserId(2l);
		Student student2 = new Student();
		student2.setUserId(3l);
		this.dao.save(student1);
		this.dao.save(student2);
		List<Student> studentList = this.dao.findAll();
		assertEquals(studentList.size(), 4);
	}
	
	@Test
	public void testUpdate() {
		Student student = this.dao.findById(1l);
		student.setUserId(4l);
		student = this.dao.update(student);;
		assertEquals(4l, student.getUserId(),1);
	}
	
	@Test
	public void testDelete() {
		Student student = this.dao.findById(2l);
		this.dao.delete(student);
		student = this.dao.findById(2l);
		Assert.assertNull(student);	
	}
	
}
