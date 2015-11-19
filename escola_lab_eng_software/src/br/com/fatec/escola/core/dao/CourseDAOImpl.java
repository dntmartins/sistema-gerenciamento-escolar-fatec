package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.CourseDAO;
import br.com.fatec.escola.api.entity.Course;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class CourseDAOImpl implements CourseDAO {

	@Override
	public Course save(Course course) { //OK
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO COURSE VALUES(?,?,?,?,?)");
			//
			Long id = GeradorIdService.getInstance().getNextId(Course.TABLE_NAME);
			insert.setLong(1, id);
			insert.setString(2, course.getName());
			insert.setString(3, course.getBeginHour());
			insert.setString(4, course.getEndHour());
			insert.setLong(5, course.getCourseDuration());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Course findById(Long id) { //OK
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM COURSE WHERE " + Course.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			// selectStatement.execute();
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			Course course = new Course();
			course.setId(resultSet.getLong(1));
			course.setName(resultSet.getString(2));
			course.setBeginHour(resultSet.getString(3));
			course.setEndHour(resultSet.getString(4));
			course.setCourseDuration((int) resultSet.getLong(5));
			return course;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Course> findAll() { //OK
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<Course> courseFound = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + Course.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			courseFound = new ArrayList<Course>();
			while (resultado.next()) {
				Course course = new Course();
				course.setId(resultado.getLong(Course.COL_PK));
				course.setName(resultado.getString(Course.COL_NAME));
				course.setBeginHour(resultado.getString(Course.COL_BEGIN_HOUR));
				course.setEndHour(resultado.getString(Course.COL_END_HOUR));
				course.setCourseDuration((int) resultado.getLong(Course.COL_DURATION));
				courseFound.add(course);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar cursos no sistema.", e);
		}
		return courseFound;
	}

	@Override
	public Course update(Course course) { //OK
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Course.TABLE_NAME + " SET " + Course.COL_NAME + " = ?,"
					+ Course.COL_BEGIN_HOUR + " = ?, " + Course.COL_END_HOUR + " = ?, " + Course.COL_DURATION + " = ? "
					+ " WHERE " + Course.COL_PK + " = ?;");
			
			update.setString(1, course.getName());
			update.setString(2, course.getBeginHour());
			update.setString(3, course.getEndHour());
			update.setLong(4, course.getCourseDuration());
			update.setLong(5, course.getId());
			update.execute();
			conn.close();
			return this.findById(course.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar o curso:" + course.getId());
		}
	}

	@Override
	public Boolean delete(Course course) { //OK
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + Course.TABLE_NAME + " WHERE " + Course.COL_PK + " = ?;");
			selectStatement.setLong(1, course.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
