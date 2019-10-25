package ca.mcgill.ecse321.tutoringsystem.dto;

public class ApplicationDto {


	private Boolean isExistingUser;
	private String name;
	private String email;
	private String courses;

	public ApplicationDto(Boolean isExistingUser, String name, String email, String courses) {
		super();
		this.isExistingUser = isExistingUser;
		this.name = name;
		this.email = email;
		this.courses = courses;
	}
	
	public ApplicationDto() {
		super();
	}

	public Boolean getIsExistingUser() {
		return isExistingUser;
	}

	public void setIsExistingUser(Boolean isExistingUser) {
		this.isExistingUser = isExistingUser;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCourses() {
		return courses;
	}

	public void setCourses(String courses) {
		this.courses = courses;
	}

}
