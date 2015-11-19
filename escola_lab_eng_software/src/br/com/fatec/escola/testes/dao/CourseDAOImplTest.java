package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.CourseDAO;
import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.api.entity.User;
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
	public void testSave() {
		Course c = new Course();
		c.setBeginHour("07:00");
		c.setEndHour("12:00");
		c.setCourseDuration(6);
		c.setName("Analise de Sistemas");
		Course cSaved = this.dao.save(c);
		assertEquals(c.getName(), cSaved.getName());		
	}
	
	
}
