package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.ClassRoom;

public interface ClassRoomDAO {
	public ClassRoom save(ClassRoom classroom); // Create

	public ClassRoom findById(Long id); // Read

	public List<ClassRoom> findAll(); // Read

	public ClassRoom update(ClassRoom classroom); // Update

	public Boolean delete(ClassRoom classroom); // Delete
}
