package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import br.com.fatec.escola.api.dao.DisciplineDAO;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class DisciplineDAOImpl implements DisciplineDAO {

	@Override
	public Discipline save(Discipline discipline) { // OK
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO DISCIPLINE VALUES(?,?,?,?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(Discipline.TABLE_NAME);
			insert.setLong(1, id);
			insert.setString(2, discipline.getName());
			insert.setLong(3, discipline.getModule().getId());
			insert.setString(4, discipline.getWeekDay());
			insert.setString(5, discipline.getBeginHour());
			insert.setString(6, discipline.getEndHour());
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
	public Discipline findById(Long id) { // OK
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM DISCIPLINE WHERE " + Discipline.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			ResultSet resultSet = selectStatement.executeQuery();
			return this.buildDiscipline(resultSet);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
	}

	@Override
	public List<Discipline> findAll() { // OK
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<Discipline> disciplineFound = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + Discipline.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			disciplineFound = this.buildDisciplines(resultado);
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar disciplinas no sistema.", e);
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
		return disciplineFound;
	}

	@Override
	public Discipline update(Discipline discipline) { // OK
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Discipline.TABLE_NAME + " SET " + Discipline.COL_NAME + " = ?,"
					+ Discipline.COL_MODULE + " = ?," + Discipline.COL_WEEK + " = ?," + Discipline.COL_BEGIN_HOUR
					+ " = ?," + Discipline.COL_END_HOUR + " = ? " + " WHERE " + Discipline.COL_PK + " = ?;");
			update.setString(1, discipline.getName());
			update.setLong(2, discipline.getModule().getId());
			update.setString(3, discipline.getWeekDay());
			update.setString(4, discipline.getBeginHour());
			update.setString(5, discipline.getEndHour());
			update.setLong(6, discipline.getId());
			update.execute();
			conn.close();
			return this.findById(discipline.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar a disciplina:" + discipline.getId());
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(update);
		}
	}

	@Override
	public Boolean delete(Discipline discipline) { // OK
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + Discipline.TABLE_NAME + " WHERE " + Discipline.COL_PK + " = ?;");
			selectStatement.setLong(1, discipline.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			DbUtils.closeQuietly(conn);
			DbUtils.closeQuietly(selectStatement);
		}
	}

	private List<Discipline> buildDisciplines(ResultSet rs) {
		List<Discipline> disciplineFound = null;
		ModuleDAOImpl moduleDAO = new ModuleDAOImpl();
		disciplineFound = new ArrayList<Discipline>();
		try {
			while (rs.next()) {
				Discipline discipline = new Discipline();
				discipline.setId(rs.getLong(Discipline.COL_PK));
				discipline.setName(rs.getString(Discipline.COL_NAME));
				discipline.setModule(moduleDAO.findById(rs.getLong(Discipline.COL_MODULE)));
				discipline.setWeekDay(rs.getString(Discipline.COL_WEEK));
				discipline.setBeginHour(Discipline.COL_BEGIN_HOUR);
				discipline.setEndHour(Discipline.COL_END_HOUR);
				disciplineFound.add(discipline);
			}
			return disciplineFound;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar disciplinas no sistema.", e);
		}
	}

	private Discipline buildDiscipline(ResultSet rs) {
		ModuleDAOImpl moduleDAO = new ModuleDAOImpl();
		try {
			if (!rs.next()) {
				return null;
			}
			Discipline discipline = new Discipline();
			discipline.setId(rs.getLong(1));
			discipline.setName(rs.getString(2));
			discipline.setModule(moduleDAO.findById(rs.getLong(3)));
			discipline.setWeekDay(rs.getString(4));
			discipline.setBeginHour(rs.getString(5));
			discipline.setEndHour(rs.getString(6));
			return discipline;
		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar disciplinas no sistema.", e);
		}
	}
}
