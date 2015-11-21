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
		
	}
	
	@Test
	public void testFindBy() {
			
	}

	@Test
	public void testFindAllTest() {
		
	}
	
	@Test
	public void testUpdate() {
		
	}
	
	@Test
	public void testDelete() {
		
	}
}
