package br.com.fatec.escola.api.entity;

public class User extends IdentificadorPK {

	public static final String COL_PK = "USER_ID";
	public static final String COL_LOGIN = "login";
	public static final String COL_ROLE = "ROLE_ID";
	public static final String COL_PASSWORD = "password";
	public static final String COL_NAME = "name";
	public static final String TABLE_NAME = "USER";

	private String login;
	private String password;
	private String name;
	private Role role;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
