package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;

//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.testes.common.EscolaBaseTest;
import br.com.spektro.minispring.core.implfinder.ImplementationFinder;

public class ClassRoomDAOImplTest extends EscolaBaseTest {

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
	public void testFindAllTest() {
		User user1 = new User();
		user1.setLogin("dante.alemao");
		user1.setName("Dante Martins");
		user1.setPassword("dante123");
		User user2 = new User();
		user2.setLogin("hugo.richard");
		user2.setName("Hugo Richard");
		user2.setPassword("hugo123");
		this.dao.save(user1);
		this.dao.save(user2);
		List<User> userList = this.dao.findAll();
		assertEquals(userList.size(), 6);
	}
	
	@Test
	public void testUpdate() {
		User user = this.dao.findById(2l);
		user.setName("testeNome");
		user = this.dao.update(user);;
		assertEquals("testeNome", user.getName());
	}
	
	@Test
	public void testDelete() {
		User user = this.dao.findById(3l);
		this.dao.delete(user);
		user = this.dao.findById(3l);
		Assert.assertNull(user);	
	}
}
