package br.com.fatec.escola.api.entity;

public class Teacher extends IdentificadorPK {

	private Long userId;
	public static final String COL_PK = "TEACHER_ID";
	public static final String COL_USER = "USER_ID";
	public static final String TABLE_NAME = "TEACHER";
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
