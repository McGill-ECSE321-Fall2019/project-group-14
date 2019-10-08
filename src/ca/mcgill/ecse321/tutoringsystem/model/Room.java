package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Room{
   private int roomNumber;
   
   public void setRoomNumber(int value) {
      this.roomNumber = value;
   }
   
   public int getRoomNumber() {
      return this.roomNumber;
   }
   
   private int capacity;
   
   public void setCapacity(int value) {
      this.capacity = value;
   }
   
   public int getCapacity() {
      return this.capacity;
   }
   
   private Set<Session> session;
   
   @OneToMany(mappedBy="room" )
   public Set<Session> getSession() {
      return this.session;
   }
   
   public void setSession(Set<Session> sessions) {
      this.session = sessions;
   }
   
   }
