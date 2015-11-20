package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.Role;

public interface RoleDAO {
	public Role save(Role role); // Create

	public Role findById(Long id); // Read

	public List<Role> findAll(); // Read

	public Role update(Role role); // Update

	public Boolean delete(Role role); // Delete
}
