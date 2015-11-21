package br.com.fatec.escola.api.entity;

import java.util.List;

public class Discipline extends IdentificadorPK {

	public static final String COL_PK = "DISCIPLINE_ID";
	public static final String COL_NAME = "name_discipline";
	public static final String COL_MODULE = "MODULE_ID";
	public static final String TABLE_NAME = "DISCIPLINE";

	private String name;
	private Module module;
	private List<Schedule> schedules;

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

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

}
