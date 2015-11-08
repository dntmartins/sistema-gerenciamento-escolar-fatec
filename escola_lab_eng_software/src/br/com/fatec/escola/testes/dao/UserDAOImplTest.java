package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;
//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class UserDAOImplTest extends EscolaBaseTest {

	private UserDAO dao;
	
	@Before
	public void config()
	{
		this.dao = (UserDAO) ImplementationFinder.getinstance().getImpl(UserDAO.class);
	}
	
	@Test
	public void testSave() {
		User user = new User();
		user.setLogin("dante.alemao");
		user.setName("Dante Martins");
		user.setPassword("dante123");
		User userSaved = this.dao.save(user);
		assertEquals(user.getLogin(), userSaved.getLogin());		
	}
	
	@Test
	public void testFindBy() {
		User user = this.dao.findById(1l);
		assertEquals(1L, user.getId(),1);		
	}
	
	@Test
	public void testDelete() {
		User user = this.dao.findById(2l);
		Boolean result = this.dao.delete(user);
		user = this.dao.findById(2l);
		Assert.assertNull(user);	
	}
}
