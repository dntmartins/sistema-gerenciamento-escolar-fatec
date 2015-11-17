package br.com.fatec.escola.api.dao;

import java.util.List;

public interface StudentClassRoomDAO {
	public StudentClassRoomDAO save(StudentClassRoomDAO studentClassroom); // Create

	public StudentClassRoomDAO findById(Long id); // Read

	public List<StudentClassRoomDAO> findAll(); // Read

	public StudentClassRoomDAO update(StudentClassRoomDAO studentClassroom); // Update

	public Boolean delete(StudentClassRoomDAO studentClassroom); // Delete
}
