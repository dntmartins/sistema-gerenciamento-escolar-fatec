package br.com.fatec.escola.core.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Schedule;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.ScheduleDAOImpl;

public class DisciplinesConflictService {

	/*
	 * Ficou um pouco complexo o método.
	 * Método recebe uma lista de IDs de disciplinas vindas da tela de Matricula de Disciplinas
	 */
	
	public Boolean hasConflictDisciplines(long[] disciplinesID) throws ParseException {
		//Instancia DAOS e classes necessárias
		DisciplineDAOImpl dDAO = new DisciplineDAOImpl();
		ArrayList<Discipline> disciplines = new ArrayList<Discipline>();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		ScheduleDAOImpl sDAO = new ScheduleDAOImpl();
		
		//Instancia disciplinas de acordo com o ID da lista e adiciona para o ArrayList
		for (long id : disciplinesID) {
			Discipline d = dDAO.findById(id);
			if (d != null) {
				disciplines.add(d);
			}
		}
		//Realiza comparação de horarios de todas as disciplinas enviadas para matricula
		for (int i = 0; i < disciplines.size(); i++) {
			Discipline d = disciplines.get(i);
			List<Schedule> scheduleList1 = sDAO.findAllByDiscipline(d.getId());
			for (int j = 1; j < disciplines.size(); j++) {
				//Inicio da logica de comparação dos horários das disciplinas
				for(Schedule s1 : scheduleList1){
					
					//Realiza parser de string para horario no java
					long beginHourD = sdf.parse(s1.getBeginHour()).getTime();
					long endHourD = sdf.parse(s1.getEndHour()).getTime();
					
					Discipline nextD = disciplines.get(j);
					List<Schedule> scheduleList2 = sDAO.findAllByDiscipline(nextD.getId());
					for(Schedule s2 : scheduleList2){
						
						//Realiza parser de string para horario no java
						long beginHourNxtD = sdf.parse(s2.getBeginHour()).getTime();
						long endHourNxtD = sdf.parse(s2.getEndHour()).getTime();
						
						//Faz a comparação das disciplinas
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
