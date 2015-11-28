package br.com.fatec.escola.api.entity;

public class Discipline extends IdentificadorPK {

	public static final String COL_PK = "DISCIPLINE_ID";
	public static final String COL_NAME = "name_discipline";
	public static final String COL_MODULE = "MODULE_ID";
	public static final String COL_WEEK = "week_day";
	public static final String COL_BEGIN_HOUR = "begin_hour";
	public static final String COL_END_HOUR = "end_hour";
	public static final String TABLE_NAME = "DISCIPLINE";

	private String name;
	private Module module;
	private String weekDay;
	private String beginHour;
	private String endHour;

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

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getBeginHour() {
		return beginHour;
	}

	public void setBeginHour(String beginHour) {
		this.beginHour = beginHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
}