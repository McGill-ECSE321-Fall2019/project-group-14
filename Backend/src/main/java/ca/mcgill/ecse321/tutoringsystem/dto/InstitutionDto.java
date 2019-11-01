package ca.mcgill.ecse321.tutoringsystem.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class InstitutionDto {
	private String institutionName;
	private SchoolLevelDto institutionLevel;

	public InstitutionDto(String institutionName, SchoolLevelDto institutionLevel) {
		super();
		this.institutionName = institutionName;
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

	@Enumerated(EnumType.STRING)
	public SchoolLevelDto getInstitutionLevel() {
		return institutionLevel;
	}

	public void setInstitutionLevel(SchoolLevelDto institutionLevel) {
		this.institutionLevel = institutionLevel;
	}
}
