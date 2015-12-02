package br.com.fatec.escola.core.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;

public class DisciplinesConflictService {

	public Boolean hasConflictDisciplines(long[] disciplinesID) throws ParseException {
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();

		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

		for (long id : disciplinesID) {
			Discipline d = dDAO.findById(id);
			if (d != null) {
				disciplines.add(d);
			}
		}

		for (int i = 0; i < disciplines.size(); i++) {
			Discipline d = disciplines.get(i);
			long beginHourD = sdf.parse(d.getBeginHour()).getTime();
			long endHourD = sdf.parse(d.getEndHour()).getTime();
			
			for (int j = 1; j < disciplines.size(); j++) {
				Discipline nextD = disciplines.get(j);
				long beginHourNxtD = sdf.parse(nextD.getBeginHour()).getTime();
				long endHourNxtD = sdf.parse(nextD.getEndHour()).getTime();
				
				if (d.getWeekDay().equals(nextD.getWeekDay()) && d.getId() != nextD.getId()) {
					if (!(!(beginHourD >= beginHourNxtD && beginHourD < endHourNxtD)
							&& !(endHourD > beginHourNxtD && endHourD <= endHourNxtD))) {
						return true; //Caso haja choque retorna true
					}
				}
			}
		}
		return false;
	}
}
