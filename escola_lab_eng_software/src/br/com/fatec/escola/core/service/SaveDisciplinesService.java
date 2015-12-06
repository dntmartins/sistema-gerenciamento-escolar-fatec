package br.com.fatec.escola.core.service;

import java.util.ArrayList;
import java.util.List;

import br.com.fatec.escola.api.dao.StudentClassRoomDAO;
import br.com.fatec.escola.api.entity.ClassRoom;
import br.com.fatec.escola.api.entity.Discipline;
import br.com.fatec.escola.api.entity.Student;
import br.com.fatec.escola.api.entity.StudentClassRoom;
import br.com.fatec.escola.api.entity.User;
import br.com.fatec.escola.core.dao.ClassRoomDAOImpl;
import br.com.fatec.escola.core.dao.DisciplineDAOImpl;
import br.com.fatec.escola.core.dao.StudentClassRoomDAOImpl;

public class SaveDisciplinesService {

	private StudentClassRoomDAO sCrDAO;
	private DisciplineDAOImpl dDAO;
	private ClassRoomDAOImpl cRDAO;

	public SaveDisciplinesService() {
		this.sCrDAO = new StudentClassRoomDAOImpl();
		this.dDAO = new DisciplineDAOImpl();
		this.cRDAO = new ClassRoomDAOImpl();
	}

	public Boolean saveDisciplines(long[] disciplinesID, User user) {
		ClassRoom cR = new ClassRoom();
		StudentClassRoom sCR = new StudentClassRoom();
		List<StudentClassRoom> sCRList = new ArrayList<StudentClassRoom>();
		for (long id : disciplinesID) {
			try{
				Discipline d = this.dDAO.findById(id);
				if (d != null) {
					cR.setDiscipline(d);
					cR.setName("Sala 402");
					cR.setModule(d.getModule());
					ClassRoom cRSaved = this.cRDAO.save(cR);
					sCR.setStudent((Student) user);
					sCR.setClassRoom(cRSaved);
					sCR.setTestNote(0f);
					StudentClassRoom sCRSaved = this.sCrDAO.save(sCR);
					if (sCRSaved != null) {
						sCRList.add(sCRSaved);
					}
					if(sCRList.size() == 3){
						return true;
					}
				}else{
					return false;
				}
			}catch(Exception e){
				return false;
			}
		}
		return false;
	}
}
