package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.ModuleDAO;
import br.com.fatec.escola.api.entity.ClassRoom;
import br.com.fatec.escola.api.entity.Module;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class ModuleDAOImpl implements ModuleDAO{

	@Override
	public Module save(Module module) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO MODULE VALUES(?,?,?)");
			//
			Long id = GeradorIdService.getInstance().getNextId(Module.TABLE_NAME);
			insert.setLong(1, id);
			insert.setString(2, module.getName());
			insert.setLong(3, module.getDiscipline());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Module findById(Long id) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM MODULE WHERE " + ClassRoom.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			// selectStatement.execute();
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			Module module = new Module();
			module.setId(resultSet.getLong(1));
			module.setName(resultSet.getString(2));
			module.setDiscipline(resultSet.getLong(3));
			return module;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Module> findAll() {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<Module> moduleFound = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * MODULE " + ClassRoom.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			moduleFound = new ArrayList<Module>();
			while (resultado.next()) {
				Module module = new Module();
				module.setId(resultado.getLong(Module.COL_PK));
				module.setName(resultado.getString(Module.COL_NAME));
				module.setDiscipline(resultado.getLong(Module.COL_DISCIPLINE));
				moduleFound.add(module);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar modulos no sistema.", e);
		}
		return moduleFound;
	}

	@Override
	public Module update(Module module) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Module.TABLE_NAME + " SET " + Module.COL_NAME + " = ?,"
					+ Module.COL_DISCIPLINE + " = ?" + " WHERE " + Module.COL_PK + " = ?;");
			update.setString(1, module.getName());
			update.setLong(2, module.getDiscipline());
			update.setLong(4, module.getId());
			update.execute();
			conn.close();
			return this.findById(module.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar o modulo:" + module.getId());
		}
	}

	@Override
	public Boolean delete(Module module) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + Module.TABLE_NAME + " WHERE " + Module.COL_PK + " = ?;");
			selectStatement.setLong(1, module.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
