package br.com.fatec.escola.api.entity;

public class Schedule extends IdentificadorPK {

	public static final String COL_PK = "SCHEDULE_ID";
	public static final String COL_WEEK = "week_day";
	public static final String COL_BEGIN_HOUR = "begin_hour";
	public static final String COL_END_HOUR = "end_hour";
	public static final String COL_DISCIPLINE = "DISCIPLINE_ID";
	public static final String TABLE_NAME = "SCHEDULE";
	
	private String weekDay;
	private String beginHour;	
	private String endHour;
	private Long discipline;

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
	public Long getDiscipline() {
		return discipline;
	}
	public void setDiscipline(Long discipline) {
		this.discipline = discipline;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	
}
