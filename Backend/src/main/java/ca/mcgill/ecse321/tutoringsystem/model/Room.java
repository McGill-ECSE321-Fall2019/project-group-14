package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Room {
  private Integer capacity;

  public void setCapacity(Integer value) {
    this.capacity = value;
  }

  public Integer getCapacity() {
    return this.capacity;
  }

  private Set<Session> session;

  @OneToMany(mappedBy = "room")
  public Set<Session> getSession() {
    return this.session;
  }

  public void setSession(Set<Session> sessions) {
    this.session = sessions;
  }

  private Integer roomNumber;

  public void setRoomNumber(Integer value) {
    this.roomNumber = value;
  }

  @Id
  public Integer getRoomNumber() {
    return this.roomNumber;
  }
}
