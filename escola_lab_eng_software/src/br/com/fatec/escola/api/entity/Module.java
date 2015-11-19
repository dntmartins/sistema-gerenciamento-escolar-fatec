package br.com.fatec.escola.api.entity;

public class Module extends IdentificadorPK {
	
	public static final String COL_PK = "MODULE_ID";
	public static final String COL_DISCIPLINE = "DISCIPLINE_ID";
	public static final String COL_COURSE = "COURSE_ID";
	public static final String COL_NAME = "module_name";
	public static final String TABLE_NAME = "MODULE";
	
	private String name;
	private Course course;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
}
