package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.Teacher;

public interface TeacherDAO {
	public Teacher save(Teacher teacher); // Create

	public Teacher findById(Long id); // Read

	public List<Teacher> findAll(); // Read

	public Teacher update(Teacher teacher); // Update

	public Boolean delete(Teacher teacher); // Delete
}
