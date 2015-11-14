package br.com.fatec.escola.api.entity;

public class StudentClassRoom extends IdentificadorPK{
	private Long studentId;
	private Long classRoomId;
	private Long testNote;
	public static final String COL_PK = "STUDENT_CLASS_ROOM_ID";
	public static final String COL_STUDENT = "STUDENT_ID";
	public static final String COL_CLASS_ROOM = "CLASS_ROOM_ID";
	public static final String COL_TEST_NOTE = "test_note";
	public static final String TABLE_NAME = "STUDENT_CLASS_ROOM";
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public Long getClassRoomId() {
		return classRoomId;
	}
	public void setClassRoomId(Long classRoomId) {
		this.classRoomId = classRoomId;
	}
	public Long getTestNote() {
		return testNote;
	}
	public void setTestNote(Long testNote) {
		this.testNote = testNote;
	}
	
}
