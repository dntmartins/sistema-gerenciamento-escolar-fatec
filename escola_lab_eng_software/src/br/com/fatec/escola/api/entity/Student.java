package br.com.fatec.escola.api.entity;

import java.util.List;

public class Student extends User {

	public static final String COL_PK = "STUDENT_ID";
	public static final String COL_USER = "USER_ID";
	public static final String TABLE_NAME = "STUDENT";
	
	private List<StudentClassRoom> studentClassRooms;

	public List<StudentClassRoom> getStudentClassRooms() {
		return studentClassRooms;
	}

	public void setStudentClassRooms(List<StudentClassRoom> studentClassRooms) {
		this.studentClassRooms = studentClassRooms;
	}
}
