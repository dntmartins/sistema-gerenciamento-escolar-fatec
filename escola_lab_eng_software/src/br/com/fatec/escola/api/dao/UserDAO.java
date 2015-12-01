package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.User;

public interface UserDAO {

	public User save(User user); // Create

	public User findById(Long id); // Read

	public List<User> findAll(); // Read

	public User update(User user); // Update

	public Boolean delete(User user); // Delete

	public User findByLoginAndPassword(String login, String password);
	
}
