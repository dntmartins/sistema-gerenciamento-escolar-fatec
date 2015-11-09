package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.StudentDAO;
import br.com.fatec.escola.api.entity.Student;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class StudentDAOImpl implements StudentDAO{

	@Override
	public Student save(Student student) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO STUDENT VALUES(?,?)");
			//
			Long id = GeradorIdService.getInstance().getNextId(Student.TABLE_NAME);
			insert.setLong(1, id);
			insert.setLong(2, student.getUserId());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Student findById(Long id) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM STUDENT WHERE " + Student.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			Student student = new Student();
			student.setId(resultSet.getLong(1));
			student.setUserId(resultSet.getLong(2));
			return student;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Student> findAll() {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<Student> studentsFound = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + Student.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			studentsFound = new ArrayList<Student>();
			while (resultado.next()) {
				Student student = new Student();
				student.setId(resultado.getLong(Student.COL_PK));
				student.setUserId(resultado.getLong(Student.COL_USER));
				studentsFound.add(student);
			}
			selectStatement.close();
			conn.close();
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar usuarios no sistema.", e);
		}
		return studentsFound;
	}

	@Override
	public Student update(Student student) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Student.TABLE_NAME + " SET " + Student.COL_USER + " = ?,"
					+ " WHERE " + User.COL_PK + " = ?;");
			update.setLong(1, student.getUserId());
			update.execute();
			conn.close();
			return this.findById(student.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar usuário:" + student.getId());
		}
	}

	@Override
	public Boolean delete(Student student) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + Student.TABLE_NAME + " WHERE " + Student.COL_PK + " = ?;");
			selectStatement.setLong(1, student.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
