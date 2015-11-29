package br.com.fatec.escola.core.service;

import java.text.SimpleDateFormat;
import java.util.List;

import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.StudentClassRoom;
import br.com.fatec.escola.core.dao.StudentClassRoomDAOImpl;

public class DisciplinesConflictService {

	public Boolean matchDiscipline(Discipline d, Long studentId) throws Exception {
		if (d != null && studentId > 0) {
			StudentClassRoomDAOImpl stCrDAO = new StudentClassRoomDAOImpl();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			List<StudentClassRoom> stCrs = stCrDAO.findAllByStudent(studentId);
			long beginHourD = sdf.parse(d.getBeginHour()).getTime();
			long endHourD = sdf.parse(d.getEndHour()).getTime();
			int countDiscipline = 0;
			for (StudentClassRoom stCr : stCrs) {
				Discipline dCr = stCr.getClassRoom().getDiscipline();
				long beginHourDCr = sdf.parse(dCr.getBeginHour()).getTime();
				long endHourDCr = sdf.parse(dCr.getEndHour()).getTime();
				if (dCr.getWeekDay().equals(d.getWeekDay())) {
					if (!(beginHourD >= beginHourDCr && beginHourD < endHourDCr)
							&& !(endHourD > beginHourDCr && endHourD <= endHourDCr)) {
						return false;
					}
				}else{
					countDiscipline++; //Contador para verificar se existe alguma disciplina cadastrada para o dia da semana
				}
			}
			if(countDiscipline == stCrs.size()){
				return false;
			}
		} else {
			throw new Exception("Erro ao verificar choque de disciplinas");
		}
		return true;
	}

}
