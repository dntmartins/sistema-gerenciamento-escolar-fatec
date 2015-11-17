package br.com.fatec.escola.api.entity;

public class Role extends IdentificadorPK {
	
	public static final String COL_PK = "ROLE_ID";
	public static final String COL_ROLE_NAME = "role_name";
	public static final String COL_ROLE = "ROLE_ID";
	public static final String COL_IS_ADMIN = "is_admin";
	public static final String TABLE_NAME = "ROLE";
	
	private String roleName;
	private Boolean isAdmin;

	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
}
