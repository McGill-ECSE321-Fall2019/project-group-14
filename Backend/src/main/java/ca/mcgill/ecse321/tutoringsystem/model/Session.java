package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Id;
import javax.persistence.MapsId;

@Entity
public class Session{
   private Room room;
   
   @ManyToOne(optional=false)
   public Room getRoom() {
      return this.room;
   }
   
   public void setRoom(Room room) {
      this.room = room;
   }
   
   private Request request;
   
   @OneToOne(optional=false)
   @MapsId
   public Request getRequest() {
      return this.request;
   }
   
   public void setRequest(Request request) {
      this.request = request;
   }
   
   private Integer sessionId;

public void setSessionId(Integer value) {
    this.sessionId = value;
}
@Id
public Integer getSessionId() {
    return this.sessionId;
}
}
