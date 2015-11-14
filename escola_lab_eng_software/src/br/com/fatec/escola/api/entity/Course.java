package br.com.fatec.escola.api.entity;

public class Course extends IdentificadorPK {

	private String name;
	private String beginHour;
	private String endHour;
	private Integer courseDuration;
	private Long module;
	public static final String COL_PK = "COURSE_ID";
	public static final String COL_NAME = "course_name";
	public static final String COL_BEGIN_HOUR = "begin_hour";
	public static final String COL_END_HOUR = "end_hour";
	public static final String COL_DURATION = "course_duration";
	public static final String COL_MODULE = "MODULE_ID";
	public static final String TABLE_NAME = "COURSE";
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Integer getCourseDuration() {
		return courseDuration;
	}
	public void setCourseDuration(Integer courseDuration) {
		this.courseDuration = courseDuration;
	}
	public Long getModule() {
		return module;
	}
	public void setModule(Long module) {
		this.module = module;
	}
	
}