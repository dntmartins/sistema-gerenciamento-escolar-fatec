package br.com.fatec.escola.api.entity;

public class Module extends IdentificadorPK {
	private String name;
	private Long discipline;
	public static final String COL_PK = "MODULE_ID";
	public static final String COL_DISCIPLINE = "DISCIPLINE_ID";
	public static final String COL_NAME = "module_name";
	public static final String TABLE_NAME = "MODULE";
	public Long getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Long discipline) {
		this.discipline = discipline;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
