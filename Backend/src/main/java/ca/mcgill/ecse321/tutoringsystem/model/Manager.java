package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends Person{
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
}