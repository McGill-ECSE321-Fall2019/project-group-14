package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Room {
	private Integer capacity;

	public void setCapacity(Integer value) {
		this.capacity = value;
	}

	public Integer getCapacity() {
		return this.capacity;
	}

	private Integer roomNumber;

	public void setRoomNumber(Integer value) {
		this.roomNumber = value;
	}

	@Id
	public Integer getRoomNumber() {
		return this.roomNumber;
	}

	private Set<Request> request;

	@OneToMany(mappedBy = "room")
	public Set<Request> getRequest() {
		return this.request;
	}

	public void setRequest(Set<Request> requests) {
		this.request = requests;
	}

}
