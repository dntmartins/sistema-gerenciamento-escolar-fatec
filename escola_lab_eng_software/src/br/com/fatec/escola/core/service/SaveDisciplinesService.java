package br.com.fatec.escola.core.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Schedule;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ScheduleDAOImpl;

public class SaveDisciplinesService {

	public Boolean hasConflictDisciplines(long[] disciplinesID) throws ParseException {
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		ScheduleDAOImpl sDAO = new ScheduleDAOImpl();
		
		for (long id : disciplinesID) {
			Discipline d = dDAO.findById(id);
			if (d != null) {
				disciplines.add(d);
			}
		}

		for (int i = 0; i < disciplines.size(); i++) {
			Discipline d = disciplines.get(i);
			List<Schedule> scheduleList1 = sDAO.findAllByDiscipline(d.getId());
			for (int j = 1; j < disciplines.size(); j++) {
				for(Schedule s1 : scheduleList1){
					long beginHourD = sdf.parse(s1.getBeginHour()).getTime();
					long endHourD = sdf.parse(s1.getEndHour()).getTime();
					Discipline nextD = disciplines.get(j);
					List<Schedule> scheduleList2 = sDAO.findAllByDiscipline(nextD.getId());
					for(Schedule s2 : scheduleList2){
						long beginHourNxtD = sdf.parse(s2.getBeginHour()).getTime();
						long endHourNxtD = sdf.parse(s2.getEndHour()).getTime();
						if (s1.getWeekDay().equals(s2.getWeekDay()) && d.getId() != nextD.getId()) {
							if (!(!(beginHourD >= beginHourNxtD && beginHourD < endHourNxtD)
									&& !(endHourD > beginHourNxtD && endHourD <= endHourNxtD))) {
								return true; //Caso haja choque retorna true
							}
						}
					}
				}
			}
		}
		return false;
	}
}
