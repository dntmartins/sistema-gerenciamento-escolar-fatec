package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.RoleDAO;
import br.com.fatec.escola.api.entity.Role;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class RoleDAOImpl implements RoleDAO {

	@Override
	public Role save(Role role) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO ROLE VALUES(?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(Role.TABLE_NAME);
			insert.setLong(1, id);
			insert.setString(2, role.getRoleName());
			insert.setBoolean(3, role.getIsAdmin());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Role findById(Long id) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM ROLE WHERE "
					+ Role.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			Role role = new Role();
			role.setId(resultSet.getLong(1));
			role.setRoleName(resultSet.getString(2));
			role.setIsAdmin(resultSet.getBoolean(3));
			return role;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Role> findAll() {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<Role> rolesFound = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM "
					+ Role.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			rolesFound = new ArrayList<Role>();
			while (resultado.next()) {
				Role role = new Role();
				role.setId(resultado.getLong(Role.COL_PK));
				role.setRoleName(resultado.getString(Role.COL_ROLE_NAME));
				role.setIsAdmin(resultado.getBoolean(Role.COL_IS_ADMIN));
				rolesFound.add(role);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar roles no sistema.", e);
		}
		return rolesFound;
	}

	@Override
	public Role update(Role role) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Role.TABLE_NAME
					+ " SET " + Role.COL_ROLE_NAME + " = ?,"
					+ Role.COL_IS_ADMIN + " = ? " + " WHERE " + Role.COL_PK
					+ " = ?;");

			update.setString(1, role.getRoleName());
			update.setBoolean(2, role.getIsAdmin());
			update.execute();
			conn.close();
			return this.findById(role.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar a role:"
					+ role.getId());
		}
	}

	@Override
	public Boolean delete(Role role) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + Role.TABLE_NAME + " WHERE " + Role.COL_PK + " = ?;");
			selectStatement.setLong(1, role.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
