package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class Manager extends User{
   private Set<Application> application;
   
   @OneToMany(mappedBy="manager" )
   public Set<Application> getApplication() {
      return this.application;
   }
   
   public void setApplication(Set<Application> applications) {
      this.application = applications;
   }
   
   private Set<Room> room;
   
   @OneToMany
   public Set<Room> getRoom() {
      return this.room;
   }
   
   public void setRoom(Set<Room> rooms) {
      this.room = rooms;
   }
   
   private Set<Request> request;
   
   @OneToMany
   public Set<Request> getRequest() {
      return this.request;
   }
   
   public void setRequest(Set<Request> requests) {
      this.request = requests;
   }
   
   public static int nextId = 1;
   private Integer managerId;

public void setManagerId(Integer value) {
    this.managerId = value;
}

public Integer getManagerId() {
    return this.managerId;
}
}
