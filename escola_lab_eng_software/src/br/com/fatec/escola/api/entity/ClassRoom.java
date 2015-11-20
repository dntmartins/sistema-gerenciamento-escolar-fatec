package br.com.fatec.escola.api.entity;

import java.util.List;

public class ClassRoom extends IdentificadorPK {

	public static final String COL_PK = "CLASS_ROOM_ID";
	public static final String COL_NAME = "class_room_name";
	public static final String COL_DISCIPLINE = "DISCIPLINE_ID";
	public static final String COL_MODULE = "MODULE_ID";
	public static final String TABLE_NAME = "CLASS_ROOM";

	private String name;
	private Module module;
	private Discipline discipline;
	private List<StudentClassRoom> StudentsClassRoom;

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
		return StudentsClassRoom;
	}

	public void setStudentsClassRoom(List<StudentClassRoom> studentsClassRoom) {
		StudentsClassRoom = studentsClassRoom;
	}

	public List<Student> getStudents() {
		return null;
	}

	public Teacher getTeacher() {
		return null;
	}

}
