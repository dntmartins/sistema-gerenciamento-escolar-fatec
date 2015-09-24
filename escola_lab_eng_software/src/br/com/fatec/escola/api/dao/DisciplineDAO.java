package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.Discipline;

public interface DisciplineDAO {
	public Discipline save(Discipline discipline); // Create

	public Discipline findById(Long id); // Read

	public List<Discipline> findAll(); // Read

	public Discipline update(Discipline discipline); // Update

	public Boolean delete(Discipline discipline); // Delete
}
