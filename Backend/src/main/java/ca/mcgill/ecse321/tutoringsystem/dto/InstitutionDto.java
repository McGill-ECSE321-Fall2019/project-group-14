package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class InstitutionDto {
	private String institutionName;
	private Set<CourseDto> courses;
	private SchoolLevelDto institutionLevel;

	public InstitutionDto(String institutionName, Set<CourseDto> courses, SchoolLevelDto institutionLevel) {
		super();
		this.institutionName = institutionName;
		this.courses = courses;
		this.institutionLevel = institutionLevel;
	}

	public InstitutionDto() {
		super();
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public Set<CourseDto> getCourses() {
		return courses;
	}

	public void setCourses(Set<CourseDto> courses) {
		this.courses = courses;
	}

	@Enumerated(EnumType.STRING)
	public SchoolLevelDto getInstitutionLevel() {
		return institutionLevel;
	}

	public void setInstitutionLevel(SchoolLevelDto institutionLevel) {
		this.institutionLevel = institutionLevel;
	}
}
