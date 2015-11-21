package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.StudentClassRoom;

public interface StudentClassRoomDAO {
	public StudentClassRoom save(StudentClassRoom studentClassroom); // Create

	public StudentClassRoom findById(Long id); // Read

	public List<StudentClassRoom> findAll(); // Read

	public StudentClassRoom update(StudentClassRoom studentClassroom); // Update

	public Boolean delete(StudentClassRoom studentClassroom); // Delete
}
