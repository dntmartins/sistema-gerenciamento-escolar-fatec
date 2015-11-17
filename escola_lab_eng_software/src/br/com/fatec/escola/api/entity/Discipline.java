package br.com.fatec.escola.api.entity;

public class Discipline extends IdentificadorPK {
	
	public static final String COL_PK = "DISCIPLINE_ID";
	public static final String COL_NAME = "name_discipline";
	public static final String TABLE_NAME = "DISCIPLINE";
	
	private String name;
	private Module module;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	
}
