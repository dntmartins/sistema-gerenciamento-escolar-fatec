package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.ClassRoomDAO;
import br.com.fatec.escola.api.entity.ClassRoom;
import br.com.fatec.escola.api.entity.Teacher;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class ClassRoomDAOImpl implements ClassRoomDAO {

	@Override
	public ClassRoom save(ClassRoom classroom) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO CLASS_ROOM VALUES(?,?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(ClassRoom.TABLE_NAME);
			insert.setLong(1, id);
			insert.setString(2, classroom.getName());
			insert.setLong(3, classroom.getModule().getId());
			insert.setLong(4, classroom.getDiscipline().getId());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public ClassRoom findById(Long id) {
		Connection conn = null;
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM CLASS_ROOM WHERE " + ClassRoom.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			ClassRoom classRoom = new ClassRoom();
			classRoom.setId(resultSet.getLong(1));
			classRoom.setName(resultSet.getString(2));
			classRoom.setModule(mDAO.findById(resultSet.getLong(3)));
			classRoom.setDiscipline(dDAO.findById(resultSet.getLong(4)));
			return classRoom;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<ClassRoom> findAll() {
		Connection conn = null;
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		PreparedStatement selectStatement = null;
		List<ClassRoom> classRoomFound = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + ClassRoom.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			classRoomFound = new ArrayList<ClassRoom>();
			while (resultado.next()) {
				ClassRoom classRoom = new ClassRoom();
				classRoom.setId(resultado.getLong(ClassRoom.COL_PK));
				classRoom.setName(resultado.getString(ClassRoom.COL_NAME));
				classRoom.setModule(mDAO.findById(resultado.getLong(ClassRoom.COL_MODULE)));
				classRoom.setDiscipline(dDAO.findById(resultado.getLong(ClassRoom.COL_DISCIPLINE)));
				classRoomFound.add(classRoom);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar salas de aula no sistema.", e);
		}
		return classRoomFound;
	}

	@Override
	public ClassRoom update(ClassRoom classroom) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + ClassRoom.TABLE_NAME + " SET " + ClassRoom.COL_NAME + " = ?,"
					+ ClassRoom.COL_MODULE + " = ?," + ClassRoom.COL_DISCIPLINE + " = ? " + " WHERE " + ClassRoom.COL_PK + " = ?;");
			update.setString(1, classroom.getName());
			update.setLong(2, classroom.getModule().getId());
			update.setLong(3, classroom.getDiscipline().getId());
			update.setLong(4, classroom.getId());
			update.execute();
			conn.close();
			return this.findById(classroom.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar a sala de aula:" + classroom.getId());
		}
	}

	@Override
	public Boolean delete(ClassRoom classroom) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + ClassRoom.TABLE_NAME + " WHERE " + ClassRoom.COL_PK + " = ?;");
			selectStatement.setLong(1, classroom.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<ClassRoom> findAllByTeacher(Long teacherId) {
		Connection conn = null;
		ModuleDAOImpl mDAO = new ModuleDAOImpl();
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		PreparedStatement selectStatement = null;
		List<ClassRoom> classRoomFound = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + ClassRoom.TABLE_NAME + "WHERE " + Teacher.COL_PK +" = ?;");
			selectStatement.setLong(1, teacherId);
			ResultSet resultado = selectStatement.executeQuery();
			classRoomFound = new ArrayList<ClassRoom>();
			while (resultado.next()) {
				ClassRoom classRoom = new ClassRoom();
				classRoom.setId(resultado.getLong(ClassRoom.COL_PK));
				classRoom.setName(resultado.getString(ClassRoom.COL_NAME));
				classRoom.setModule(mDAO.findById(resultado.getLong(ClassRoom.COL_MODULE)));
				classRoom.setDiscipline(dDAO.findById(resultado.getLong(ClassRoom.COL_DISCIPLINE)));
				classRoomFound.add(classRoom);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar salas de aula no sistema.", e);
		}
		return classRoomFound;
	}

}
