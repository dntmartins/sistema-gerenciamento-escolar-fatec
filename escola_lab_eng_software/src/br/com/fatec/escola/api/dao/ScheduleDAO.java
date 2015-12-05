package br.com.fatec.escola.api.dao;

import java.util.List;

import br.com.fatec.escola.api.entity.Schedule;

public interface ScheduleDAO {
	public Schedule save(Schedule schedule); // Create

	public Schedule findById(Long id); // Read

	public List<Schedule> findAll(); // Read

	public Schedule update(Schedule schedule); // Update

	public Boolean delete(Schedule schedule); // Delete
}
