package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.Student;


public interface StudentDAO {
	public Student save(Student student); // Create

	public Student findById(Long id); // Read

	public List<Student> findAll(); // Read

	public Student update(Student student); // Update

	public Boolean delete(Student student); // Delete
}
