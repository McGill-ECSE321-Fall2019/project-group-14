package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.Set;

public class ManagerDto {

	private Set<RoomDto> room;
	private Set<RequestDto> request;
	private Set<ApplicationDto> application;
	private String name;
	private String email;
	private String password;

	public ManagerDto(Set<RoomDto> room, Set<RequestDto> request, Set<ApplicationDto> application, String name,
			String email, String password) {
		super();
		this.room = room;
		this.request = request;
		this.application = application;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public ManagerDto(String name, String email, String password) {
		this(Collections.emptySet(), Collections.emptySet(), Collections.emptySet(), name, email, password);
	}
	
	public ManagerDto() {
		super();
	}

	public Set<RoomDto> getRoom() {
		return room;
	}

	public void setRoom(Set<RoomDto> room) {
		this.room = room;
	}

	public Set<RequestDto> getRequest() {
		return request;
	}

	public void setRequest(Set<RequestDto> request) {
		this.request = request;
	}

	public Set<ApplicationDto> getApplication() {
		return application;
	}

	public void setApplication(Set<ApplicationDto> application) {
		this.application = application;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
