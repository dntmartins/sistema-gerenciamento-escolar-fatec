package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.Course;

public interface CourseDAO {
	public Course save(Course course); // Create

	public Course findById(Long id); // Read

	public List<Course> findAll(); // Read

	public Course update(Course course); // Update

	public Boolean delete(Course course); // Delete
}
