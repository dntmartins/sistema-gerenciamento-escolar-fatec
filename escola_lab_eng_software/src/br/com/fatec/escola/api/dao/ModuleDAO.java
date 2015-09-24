package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.Module;


public interface ModuleDAO {
	public Module save(Module module); // Create

	public Module findById(Long id); // Read

	public List<Module> findAll(); // Read

	public Module update(Module module); // Update

	public Boolean delete(Module module); // Delete
}
