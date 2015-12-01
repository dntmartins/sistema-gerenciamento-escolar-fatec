package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.escola.api.dao.StudentClassRoomDAO;
import br.com.fatec.escola.api.entity.ClassRoom;
import br.com.fatec.escola.api.entity.Student;
import br.com.fatec.escola.api.entity.StudentClassRoom;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class StudentClassRoomDAOImpl implements StudentClassRoomDAO {

	@Override
	public StudentClassRoom save(StudentClassRoom studentClassroom) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
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
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(insert);
		}
	}

	@Override
	public StudentClassRoom findById(Long id) {
		Connection conn = null;
		UserDAOImpl uDAO = new UserDAOImpl();
		ClassRoomDAOImpl cRDAO = new ClassRoomDAOImpl();
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM STUDENT_CLASS_ROOM WHERE " + StudentClassRoom.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			User user = new User();
			user = uDAO.findById(resultSet.getLong(2));
			Student student = new Student();
			student.setId(user.getId());
			student.setLogin(user.getLogin());
			student.setName(user.getName());
			student.setPassword(user.getPassword());
			student.setRole(user.getRole());
			StudentClassRoom studentClassroom = new StudentClassRoom();
			studentClassroom.setId(resultSet.getLong(1));
			studentClassroom.setStudent(student);
			studentClassroom.setClassRoom(cRDAO.findById(resultSet.getLong(3)));
			studentClassroom.setTestNote(resultSet.getFloat(4));
			return studentClassroom;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
	}

	@Override
	public List<StudentClassRoom> findAll() {
		Connection conn = null;
		UserDAOImpl uDAO = new UserDAOImpl();
		ClassRoomDAOImpl crDAO = new ClassRoomDAOImpl();
		PreparedStatement selectStatement = null;
		List<StudentClassRoom> studentClassRoomFound = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + StudentClassRoom.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			studentClassRoomFound = new ArrayList<StudentClassRoom>();
			while (resultado.next()) {
				User user = new User();
				user = uDAO.findById(resultado.getLong(StudentClassRoom.COL_STUDENT));
				Student student = new Student();
				student.setId(user.getId());
				student.setLogin(user.getLogin());
				student.setName(user.getName());
				student.setPassword(user.getPassword());
				student.setRole(user.getRole());
				StudentClassRoom studentClassRoom = new StudentClassRoom();
				studentClassRoom.setId(resultado.getLong(StudentClassRoom.COL_PK));
				studentClassRoom.setStudent(student);
				studentClassRoom.setClassRoom(crDAO.findById(resultado.getLong(StudentClassRoom.COL_CLASS_ROOM)));
				studentClassRoom.setTestNote(resultado.getFloat(StudentClassRoom.COL_TEST_NOTE));
				studentClassRoomFound.add(studentClassRoom);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar salas de aula no sistema.", e);
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
		return studentClassRoomFound;
	}

	@Override
	public StudentClassRoom update(StudentClassRoom studentClassroom) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + StudentClassRoom.TABLE_NAME + " SET " + StudentClassRoom.COL_STUDENT + " = ?,"
					+ StudentClassRoom.COL_CLASS_ROOM + " = ?," + StudentClassRoom.COL_TEST_NOTE + " = ? " + " WHERE " + ClassRoom.COL_PK + " = ?;");
			update.setLong(1, studentClassroom.getStudent().getId());
			update.setLong(2, studentClassroom.getClassRoom().getId());
			update.setFloat(3, studentClassroom.getTestNote());
			update.setLong(4, studentClassroom.getId());
			update.execute();
			conn.close();
			return this.findById(studentClassroom.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar a sala de aula:" + studentClassroom.getId());
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(update);
		}
	}

	@Override
	public Boolean delete(StudentClassRoom studentClassroom) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + StudentClassRoom.TABLE_NAME + " WHERE " + StudentClassRoom.COL_PK + " = ?;");
			selectStatement.setLong(1, studentClassroom.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
	}
	
	public List<StudentClassRoom> findAllByClassRoom(Long classRoomId) {
		Connection conn = null;
		UserDAOImpl uDAO = new UserDAOImpl();
		ClassRoomDAOImpl crDAO = new ClassRoomDAOImpl();
		PreparedStatement selectStatement = null;
		List<StudentClassRoom> studentClassRoomFound = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + StudentClassRoom.TABLE_NAME + "WHERE " + ClassRoom.COL_PK + " = ?;");
			selectStatement.setLong(1, classRoomId);
			ResultSet resultado = selectStatement.executeQuery();
			studentClassRoomFound = new ArrayList<StudentClassRoom>();
			while (resultado.next()) {
				StudentClassRoom studentClassRoom = new StudentClassRoom();
				studentClassRoom.setId(resultado.getLong(StudentClassRoom.COL_PK));
				User user = new User();
				user = uDAO.findById(resultado.getLong(StudentClassRoom.COL_STUDENT));
				Student student = new Student();
				student.setId(user.getId());
				student.setLogin(user.getLogin());
				student.setName(user.getName());
				student.setPassword(user.getPassword());
				student.setRole(user.getRole());
				studentClassRoom.setStudent(student);
				studentClassRoom.setClassRoom(crDAO.findById(resultado.getLong(StudentClassRoom.COL_CLASS_ROOM)));
				studentClassRoom.setTestNote(resultado.getFloat(StudentClassRoom.COL_TEST_NOTE));
				studentClassRoomFound.add(studentClassRoom);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar salas de aula no sistema.", e);
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
		return studentClassRoomFound;
	}
	
	
	public List<StudentClassRoom> findAllByStudent(Long studentId) {
		Connection conn = null;
		UserDAOImpl uDAO = new UserDAOImpl();
		ClassRoomDAOImpl crDAO = new ClassRoomDAOImpl();
		PreparedStatement selectStatement = null;
		List<StudentClassRoom> studentClassRoomFound = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + StudentClassRoom.TABLE_NAME + " WHERE " + StudentClassRoom.COL_STUDENT + " = ?;");
			selectStatement.setLong(1, studentId);
			ResultSet resultado = selectStatement.executeQuery();
			studentClassRoomFound = new ArrayList<StudentClassRoom>();
			while (resultado.next()) {
				StudentClassRoom studentClassRoom = new StudentClassRoom();
				studentClassRoom.setId(resultado.getLong(StudentClassRoom.COL_PK));
				Student student = new Student();
				User user = uDAO.findById(studentId);
				student.setId(user.getId());
				student.setLogin(user.getLogin());
				student.setName(user.getName());
				student.setPassword(user.getPassword());
				student.setRole(user.getRole());
				studentClassRoom.setStudent(student);
				studentClassRoom.setClassRoom(crDAO.findById(resultado.getLong(StudentClassRoom.COL_CLASS_ROOM)));
				studentClassRoom.setTestNote(resultado.getFloat(StudentClassRoom.COL_TEST_NOTE));
				studentClassRoomFound.add(studentClassRoom);
			}
			selectStatement.close();
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar salas de aula no sistema.", e);
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
		return studentClassRoomFound;
	}
	
}
