package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.Student;
import br.com.fatec.escola.api.entity.Teacher;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class UserDAOImpl implements UserDAO {

	@Override
	public User save(User user) { // OK
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO USER VALUES(?,?,?,?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(User.TABLE_NAME);
			insert.setLong(1, id);
			insert.setLong(2, user.getRole().getId());
			insert.setString(3, user.getLogin());
			insert.setString(4, user.getName());
			insert.setString(5, user.getPassword());
			insert.setBoolean(6, user.getIsTeacher());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(insert);
		}
	}

	@Override
	public User findById(Long id) { // OK
		Connection conn = null;
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM USER WHERE " + User.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			User user = null;
			if (resultSet.getBoolean(6)) {
				user = new Teacher();
			}else{
				user = new Student();
			}
			user.setId(resultSet.getLong(1));
			user.setRole(roleDAO.findById(resultSet.getLong(2)));
			user.setLogin(resultSet.getString(3));
			user.setPassword(resultSet.getString(4));
			user.setName(resultSet.getString(5));
			user.setIsTeacher(resultSet.getBoolean(6));
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
	}

	@Override
	public List<User> findAll() { // OK
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<User> usersFound = null;
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + User.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			usersFound = new ArrayList<User>();
			while (resultado.next()) {
				User user = null;
				if (resultado.getBoolean(User.COL_IS_TEACHER)) {
					user = new Teacher();
				}else{
					user = new Student();
				}
				user.setId(resultado.getLong(User.COL_PK));
				user.setRole(roleDAO.findById(resultado.getLong(User.COL_ROLE)));
				user.setLogin(resultado.getString(User.COL_LOGIN));
				user.setName(resultado.getString(User.COL_NAME));
				user.setPassword(resultado.getString(User.COL_PASSWORD));
				user.setIsTeacher(resultado.getBoolean(User.COL_IS_TEACHER));
				usersFound.add(user);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar usuarios no sistema.", e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
		return usersFound;
	}

	@Override
	public User update(User user) { // OK
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + User.TABLE_NAME + " SET " + User.COL_LOGIN + " = ?,"
					+ User.COL_NAME + " = ?," + User.COL_PASSWORD + " = ?," + User.COL_IS_TEACHER + " = ? " + " WHERE "
					+ User.COL_PK + " = ?;");
			update.setString(1, user.getLogin());
			update.setString(2, user.getName());
			update.setString(3, user.getPassword());
			update.setBoolean(4, user.getIsTeacher());
			update.setLong(5, user.getId());
			update.execute();
			conn.close();
			return this.findById(user.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar usu�rio:" + user.getId());
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(update);
		}
	}

	@Override
	public Boolean delete(User user) { // OK
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + User.TABLE_NAME + " WHERE " + User.COL_PK + " = ?;");
			selectStatement.setLong(1, user.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
	}
	
	public User findByLogin(String login) {
		Connection conn = null;
		PreparedStatement stmt = null;
		User user = null;
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			stmt = conn.prepareStatement("SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COL_LOGIN
					+ " = ? ");
			stmt.setString(1, login);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				if (resultado.getBoolean(User.COL_IS_TEACHER)) {
					user = new Teacher();
				}else{
					user = new Student();
				}
				user.setId(resultado.getLong(User.COL_PK));
				user.setRole(roleDAO.findById(resultado.getLong(User.COL_ROLE)));
				user.setLogin(resultado.getString(User.COL_LOGIN));
				user.setName(resultado.getString(User.COL_NAME));
				user.setPassword(resultado.getString(User.COL_PASSWORD));
				user.setIsTeacher(resultado.getBoolean(User.COL_IS_TEACHER));
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}
	
	
	public User findByLoginAndPassword(String login, String password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		User user = null;
		RoleDAOImpl roleDAO = new RoleDAOImpl();
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			stmt = conn.prepareStatement("SELECT * FROM " + User.TABLE_NAME + " WHERE " + User.COL_LOGIN
					+ " = ? and " + User.COL_PASSWORD + " = ?");
			stmt.setString(1, login);
			stmt.setString(2, password);
			ResultSet resultado = stmt.executeQuery();
			if (resultado.next()) {
				if (resultado.getBoolean(User.COL_IS_TEACHER)) {
					user = new Teacher();
				}else{
					user = new Student();
				}
				user.setId(resultado.getLong(User.COL_PK));
				user.setRole(roleDAO.findById(resultado.getLong(User.COL_ROLE)));
				user.setLogin(resultado.getString(User.COL_LOGIN));
				user.setName(resultado.getString(User.COL_NAME));
				user.setPassword(resultado.getString(User.COL_PASSWORD));
				user.setIsTeacher(resultado.getBoolean(User.COL_IS_TEACHER));
			}
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(stmt);
		}
	}
}
