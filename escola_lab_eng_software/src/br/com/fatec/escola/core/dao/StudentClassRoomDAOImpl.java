package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.StudentClassRoomDAO;
import br.com.fatec.escola.api.entity.StudentClassRoom;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class StudentClassRoomDAOImpl implements StudentClassRoomDAO {

	@Override
	public StudentClassRoom save(StudentClassRoom studentClassroom) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO STUDENT_CLASS_ROOM VALUES(?,?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(StudentClassRoom.TABLE_NAME);
			insert.setLong(1, id);
			insert.setLong(2, studentClassroom.getStudent().getId());
			insert.setLong(3, studentClassroom.getClassRoom().getId());
			insert.setFloat(4, studentClassroom.getTestNote());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public StudentClassRoom findById(Long id) {
		Connection conn = null;
		StudentDAOImpl sDAO = new StudentDAOImpl();
		ClassRoomDAOImpl cRDAO = new ClassRoomDAOImpl();
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM STUDENT_CLASS_ROOM WHERE " + StudentClassRoom.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			StudentClassRoom studentClassroom = new StudentClassRoom();
			studentClassroom.setId(resultSet.getLong(1));
			studentClassroom.setStudent(sDAO.findById(resultSet.getLong(2)));
			studentClassroom.setClassRoom(cRDAO.findById(resultSet.getLong(3)));
			studentClassroom.setTestNote(resultSet.getFloat(4));
			return studentClassroom;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<StudentClassRoom> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StudentClassRoom update(StudentClassRoom studentClassroom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(StudentClassRoom studentClassroom) {
		// TODO Auto-generated method stub
		return null;
	}



}
