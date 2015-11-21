package br.com.fatec.escola.api.entity;

import java.util.List;

public class Teacher extends User {

	public static final String COL_PK = "TEACHER_ID";
	public static final String COL_USER = "USER_ID";
	public static final String TABLE_NAME = "TEACHER";
	
	private List<ClassRoom> classRooms;

	public List<ClassRoom> getClassRooms() {
		return classRooms;
	}

	public void setClassRooms(List<ClassRoom> classRooms) {
		this.classRooms = classRooms;
	}
}
