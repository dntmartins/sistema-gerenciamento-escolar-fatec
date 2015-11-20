package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.DisciplineDAO;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class DisciplineDAOImpl implements DisciplineDAO {

	@Override
	public Discipline save(Discipline discipline) { //VERIFICAR
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO DISCIPLINE VALUES(?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(Discipline.TABLE_NAME);
			insert.setLong(1, id);
			insert.setString(2, discipline.getName());
			insert.setLong(3, discipline.getModule().getId());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Discipline findById(Long id) { //VERIFICAR
		Connection conn = null;
		ModuleDAOImpl moduleDAO = new ModuleDAOImpl();
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM DISCIPLINE WHERE " + Discipline.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			Discipline discipline = new Discipline();
			discipline.setId(resultSet.getLong(1));
			discipline.setName(resultSet.getString(2));
			discipline.setModule(moduleDAO.findById(resultSet.getLong(3)));
			return discipline;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Discipline> findAll() { //VERIFICAR
		Connection conn = null;
		ModuleDAOImpl moduleDAO = new ModuleDAOImpl();
		PreparedStatement selectStatement = null;
		List<Discipline> disciplineFound = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + Discipline.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			disciplineFound = new ArrayList<Discipline>();
			while (resultado.next()) {
				Discipline discipline = new Discipline();
				discipline.setId(resultado.getLong(Discipline.COL_PK));
				discipline.setName(resultado.getString(Discipline.COL_NAME));
				discipline.setModule(moduleDAO.findById(resultado.getLong(Discipline.COL_MODULE)));
				disciplineFound.add(discipline);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar disciplinas no sistema.", e);
		}
		return disciplineFound;
	}

	@Override
	public Discipline update(Discipline discipline) { //VERIFICAR
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Discipline.TABLE_NAME + " SET " + Discipline.COL_NAME + " = ?," + Discipline.COL_MODULE + " = ?,"
					+  " WHERE " + Discipline.COL_PK + " = ?;");
			update.setString(1, discipline.getName());
			update.setLong(2,discipline.getModule().getId());
			update.setLong(3, discipline.getId());
			update.execute();
			conn.close();
			return this.findById(discipline.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar a disciplina:" + discipline.getId());
		}
	}

	@Override
	public Boolean delete(Discipline discipline) { //VERIFICAR
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + Discipline.TABLE_NAME + " WHERE " + Discipline.COL_PK + " = ?;");
			selectStatement.setLong(1, discipline.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
