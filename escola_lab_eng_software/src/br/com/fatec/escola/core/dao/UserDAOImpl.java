package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.UserDAO;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class UserDAOImpl implements UserDAO {

	@Override
	public User save(User user) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO USER VALUES(?,?,?,?)");
			//
			Long id = GeradorIdService.getInstance().getNextId(User.TABLE_NAME);
			insert.setLong(1, id);
			insert.setString(2, user.getLogin());
			insert.setString(3, user.getName());
			insert.setString(4, user.getPassword());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findById(Long id) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("SELECT * FROM USER WHERE "
							+ User.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			//selectStatement.execute();
			ResultSet resultSet = selectStatement.executeQuery();
			resultSet.next();
			User user = new User();
			user.setId(resultSet.getLong(1));
			user.setLogin(resultSet.getString(2));
			user.setName(resultSet.getString(3));
			user.setPassword(resultSet.getString(4));
			return user;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}		
	}

	@Override
	public List<User> findAll() {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<User> usersFound = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("SELECT * FROM "
							+ User.TABLE_NAME+";");
			ResultSet resultado = selectStatement.executeQuery();
			usersFound = new ArrayList<User>();
			while (resultado.next()) {
				User user = new User();
				user.setId(resultado.getLong(User.COL_PK));
				user.setLogin(resultado.getString(User.COL_LOGIN));
				user.setName(resultado.getString(User.COL_NAME));
				user.setPassword(resultado.getString(User.COL_PASSWORD));
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar usuarios no sistema.", e);
		}
		return usersFound;
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean delete(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
