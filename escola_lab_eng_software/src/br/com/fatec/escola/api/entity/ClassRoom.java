package br.com.fatec.escola.api.entity;

import java.util.List;

import br.com.fatec.escola.core.dao.StudentClassRoomDAOImpl;

public class ClassRoom extends IdentificadorPK {

	public static final String COL_PK = "CLASS_ROOM_ID";
	public static final String COL_NAME = "class_room_name";
	public static final String COL_DISCIPLINE = "DISCIPLINE_ID";
	public static final String COL_MODULE = "MODULE_ID";
	public static final String TABLE_NAME = "CLASS_ROOM";

	private String name;
	private Module module;
	private Discipline discipline;
	private List<StudentClassRoom> studentsClassRoom;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public List<StudentClassRoom> getStudentsClassRoom() {
		this.studentsClassRoom = new StudentClassRoomDAOImpl().findAllByClassRoom(this.getId());
		return studentsClassRoom;
	}

	public void setStudentsClassRoom(List<StudentClassRoom> studentsClassRoom) {
		this.studentsClassRoom = studentsClassRoom;
	}

	public List<Student> getStudents() {
		return null;
	}

	public Teacher getTeacher() {
		return null;
	}

}
