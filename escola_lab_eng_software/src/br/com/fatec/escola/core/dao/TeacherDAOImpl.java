package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.TeacherDAO;
import br.com.fatec.escola.api.entity.Teacher;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class TeacherDAOImpl implements TeacherDAO{

	@Override
	public Teacher save(Teacher teacher) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO TEACHER VALUES(?,?)");
			Long id = GeradorIdService.getInstance().getNextId(Teacher.TABLE_NAME);
			insert.setLong(1, id);
			insert.setLong(2, teacher.getId());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Teacher findById(Long id) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM TEACHER WHERE " + Teacher.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			Teacher teacher = new Teacher();
			teacher.setId(resultSet.getLong(1));
			return teacher;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Teacher> findAll() {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<Teacher> teachersFound = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + Teacher.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			teachersFound = new ArrayList<Teacher>();
			while (resultado.next()) {
				Teacher teacher = new Teacher();
				teacher.setId(resultado.getLong(Teacher.COL_PK));
				//teacher.setUserId(resultado.getLong(Teacher.COL_USER));
				teachersFound.add(teacher);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar professores no sistema.", e);
		}
		return teachersFound;
	}

	@Override
	public Teacher update(Teacher teacher) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Teacher.TABLE_NAME + " SET " + Teacher.COL_USER + " = ?," + " WHERE " + Teacher.COL_PK + " = ?;");
			//update.setLong(1, teacher.getUserId());
			update.execute();
			conn.close();
			return this.findById(teacher.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar professor:" + teacher.getId());
		}
	}

	@Override
	public Boolean delete(Teacher teacher) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + Teacher.TABLE_NAME + " WHERE " + Teacher.COL_PK + " = ?;");
			selectStatement.setLong(1, teacher.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
