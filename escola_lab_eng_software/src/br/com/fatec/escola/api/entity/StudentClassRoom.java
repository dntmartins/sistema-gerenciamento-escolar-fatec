package br.com.fatec.escola.api.entity;

public class StudentClassRoom extends IdentificadorPK {

	public static final String COL_PK = "STUDENT_CLASS_ROOM_ID";
	public static final String COL_STUDENT = "STUDENT_ID";
	public static final String COL_CLASS_ROOM = "CLASS_ROOM_ID";
	public static final String COL_TEST_NOTE = "test_note";
	public static final String TABLE_NAME = "STUDENT_CLASS_ROOM";

	private Student student;
	private ClassRoom classRoom;
	private Float testNote;


	public ClassRoom getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(ClassRoom classRoom) {
		this.classRoom = classRoom;
	}

	public Float getTestNote() {
		return testNote;
	}

	public void setTestNote(Float testNote) {
		this.testNote = testNote;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

}
