package br.com.fatec.escola.api.entity;

import java.util.List;

public class Student extends User {
	
	private List<StudentClassRoom> studentClassRooms;

	public List<StudentClassRoom> getStudentClassRooms() {
		return studentClassRooms;
	}

	public void setStudentClassRooms(List<StudentClassRoom> studentClassRooms) {
		this.studentClassRooms = studentClassRooms;
	}
}
