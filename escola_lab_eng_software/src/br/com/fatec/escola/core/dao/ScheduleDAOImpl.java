package br.com.fatec.escola.core.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.ScheduleDAO;
import br.com.fatec.escola.api.entity.Schedule;
import br.com.fatec.escola.core.service.GeradorIdService;
import br.com.spektro.minispring.core.dbmapper.ConfigDBMapper;

public class ScheduleDAOImpl implements ScheduleDAO{

	@Override
	public Schedule save(Schedule schedule) {
		Connection conn = null;
		PreparedStatement insert = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			insert = conn.prepareStatement("INSERT INTO SCHEDULE VALUES(?,?,?,?,?)");
			Long id = GeradorIdService.getInstance().getNextId(Schedule.TABLE_NAME);
			insert.setLong(1, id);
			insert.setLong(2, schedule.getDiscipline().getId());
			insert.setString(3, schedule.getWeekDay());
			insert.setString(4, schedule.getBeginHour());
			insert.setString(5, schedule.getEndHour());
			insert.execute();
			return this.findById(id);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Schedule findById(Long id) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM SCHEDULE WHERE " + Schedule.COL_PK + " = ?");
			selectStatement.setLong(1, id);
			// selectStatement.execute();
			ResultSet resultSet = selectStatement.executeQuery();
			if (!resultSet.next()) {
				return null;
			}
			Schedule schedule = new Schedule();
			schedule.setId(resultSet.getLong(1));
			schedule.setDiscipline(resultSet.getLong(2));
			schedule.setWeekDay(resultSet.getString(3));
			schedule.setBeginHour(resultSet.getString(4));
			schedule.setEndHour(resultSet.getString(5));
			return schedule;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Schedule> findAll() {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		List<Schedule> schedulesFound = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn.prepareStatement("SELECT * FROM " + Schedule.TABLE_NAME + ";");
			ResultSet resultado = selectStatement.executeQuery();
			schedulesFound = new ArrayList<Schedule>();
			while (resultado.next()) {
				Schedule schedule = new Schedule();
				schedule.setId(resultado.getLong(Schedule.COL_PK));
				schedule.setDiscipline(resultado.getLong(Schedule.COL_DISCIPLINE));
				schedule.setWeekDay(resultado.getString(Schedule.COL_WEEK));
				schedule.setBeginHour(resultado.getString(Schedule.COL_BEGIN_HOUR));
				schedule.setEndHour(resultado.getString(Schedule.COL_END_HOUR));
				schedulesFound.add(schedule);
			}
			selectStatement.close();
			conn.close();

		} catch (Exception e) {
			throw new RuntimeException("Erro ao buscar agenda no sistema.", e);
		}
		return schedulesFound;
	}

	@Override
	public Schedule update(Schedule schedule) {
		Connection conn = null;
		PreparedStatement update = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			update = conn.prepareStatement("UPDATE " + Schedule.TABLE_NAME + " SET " + Schedule.COL_DISCIPLINE + " = ?,"
					+ Schedule.COL_WEEK + " = ?," + Schedule.COL_BEGIN_HOUR + " = ? " + Schedule.COL_END_HOUR + " = ? " + " WHERE " + Schedule.COL_PK + " = ?;");
			update.setLong(1, schedule.getDiscipline());
			update.setString(2, schedule.getWeekDay());
			update.setString(3, schedule.getBeginHour());
			update.setString(4, schedule.getEndHour());
			update.setLong(5, schedule.getId());
			update.execute();
			conn.close();
			return this.findById(schedule.getId());
		} catch (SQLException e) {
			throw new RuntimeException("erro ao atualizar agenda:" + schedule.getId());
		}
	}

	@Override
	public Boolean delete(Schedule schedule) {
		Connection conn = null;
		PreparedStatement selectStatement = null;
		try {
			conn = ConfigDBMapper.getInstance().getDefaultConnection();
			selectStatement = conn
					.prepareStatement("DELETE FROM " + Schedule.TABLE_NAME + " WHERE " + Schedule.COL_PK + " = ?;");
			selectStatement.setLong(1, schedule.getId());
			return selectStatement.execute();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
