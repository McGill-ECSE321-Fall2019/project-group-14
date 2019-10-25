package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set;

public class CourseDto {

	private String courseName;
	private InstitutionDto institution;
	private String subjectName;
	private Set<WageDto> wage;

	public CourseDto(String courseName, InstitutionDto institution, String subjectName, Set<WageDto> wage) {
		super();
		this.courseName = courseName;
		this.institution = institution;
		this.subjectName = subjectName;
		this.wage = wage;
	}
	
	public CourseDto() {
		super();
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public InstitutionDto getInstitution() {
		return institution;
	}

	public void setInstitution(InstitutionDto institution) {
		this.institution = institution;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public Set<WageDto> getWage() {
		return wage;
	}

	public void setWage(Set<WageDto> wage) {
		this.wage = wage;
	}

}
