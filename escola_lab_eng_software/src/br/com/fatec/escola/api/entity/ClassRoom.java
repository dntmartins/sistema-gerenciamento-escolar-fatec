package br.com.fatec.escola.api.entity;

public class ClassRoom extends IdentificadorPK {
	private String name;
	private Long module;
	private Long discipline;
	public static final String COL_PK = "CLA_ROOM_ID";
	public static final String COL_NAME = "class_room_name";
	public static final String COL_DISCIPLINE = "DISCIPLINE_ID";
	public static final String COL_MODULE = "MODULE_ID";
	public static final String TABLE_NAME = "CLASS_ROOM";
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getModule() {
		return module;
	}
	public void setModule(Long module) {
		this.module = module;
	}
	public Long getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Long discipline) {
		this.discipline = discipline;
	}
	
}
