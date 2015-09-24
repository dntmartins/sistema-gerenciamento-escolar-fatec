package br.com.fatec.escola.api.entity;

public class Discipline extends IdentificadorPK {

	private String name;
	public static final String COL_PK = "DISCIPLINE_ID";
	public static final String COL_NAME = "name_discipline";
	public static final String TABLE_NAME = "DISCIPLINE";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
