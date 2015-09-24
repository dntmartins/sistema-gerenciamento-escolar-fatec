package br.com.fatec.escola.api.entity;

public class Student extends IdentificadorPK {

	private Long userId;
	public static final String COL_PK = "STUDENT_ID";
	public static final String COL_USER = "USER_ID";
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
