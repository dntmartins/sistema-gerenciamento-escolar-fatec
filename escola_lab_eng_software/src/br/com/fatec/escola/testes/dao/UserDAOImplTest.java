package br.com.fatec.escola.testes.dao;

import static org.junit.Assert.*;

import java.util.List;



//import junit.framework.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.Role;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.dao.RoleDAOImpl;
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
	public void testSave() { //OK
		User user = new User();
		Role role = new Role();
		RoleDAOImpl rDAO = new RoleDAOImpl();
		role.setRoleName("Visitante");
		role.setIsAdmin(false);
		role = rDAO.save(role);
		user.setLogin("dantee.alemao");
		user.setName("Dante Martins");
		user.setPassword("dante123");
		user.setRole(role);
		User userSaved = this.dao.save(user);
		this.dao.delete(userSaved);
		assertEquals(user.getLogin(), userSaved.getLogin());		
	}
	
	@Test
	public void testFindBy() { //OK
		User user = this.dao.findById(1l);
		assertEquals(1L, user.getId(),1);		
	}
	
	@Test
	public void testFindAllTest() { //OK
		User user1 = new User();
		Role role = new Role();
		RoleDAOImpl rDAO = new RoleDAOImpl();
		role.setRoleName("Visitante");
		role.setIsAdmin(false);
		role = rDAO.save(role);
		user1.setLogin("dante.alemao");
		user1.setName("Dante Martins");
		user1.setPassword("dante123");
		user1.setRole(role);
		User user2 = new User();
		user2.setLogin("hugo.richard");
		user2.setName("Hugo Richard");
		user2.setPassword("hugo123");
		user2.setRole(role);
		User userSaved1 = this.dao.save(user1);
		User userSaved2 = this.dao.save(user2);
		List<User> userList = this.dao.findAll();
		this.dao.delete(userSaved1);
		this.dao.delete(userSaved2);
		assertEquals(userList.size(), 11);
	}
	
	@Test
	public void testUpdate() { //OK
		User user = new User();
		Role role = new Role();
		RoleDAOImpl rDAO = new RoleDAOImpl();
		role.setRoleName("Visitante");
		role.setIsAdmin(false);
		role = rDAO.save(role);
		user.setLogin("dantee.alemao");
		user.setName("Dante Martins");
		user.setPassword("dante123");
		user.setRole(role);
		User userSaved = this.dao.save(user);
		user = this.dao.findById(userSaved.getId());
		user.setName("testeNome");
		user = this.dao.update(user);;
		this.dao.delete(user);
		assertEquals("testeNome", user.getName());
	}
	
	@Test
	public void testDelete() { //OK
		User user = new User();
		Role role = new Role();
		RoleDAOImpl rDAO = new RoleDAOImpl();
		role.setRoleName("Visitante");
		role.setIsAdmin(false);
		role = rDAO.save(role);
		user.setLogin("dantee.alemao");
		user.setName("Dante Martins");
		user.setPassword("dante123");
		user.setRole(role);
		User userSaved = this.dao.save(user);
		this.dao.delete(userSaved);
		user = this.dao.findById(userSaved.getId());
		Assert.assertNull(user);	
	}
}
