package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Notification{
   private Request request;
   
   @OneToOne(optional=false)
   public Request getRequest() {
      return this.request;
   }
   
   public void setRequest(Request request) {
      this.request = request;
   }
   
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
   private Integer notificationId;

public void setNotificationId(Integer value) {
    this.notificationId = value;
}
@Id
public Integer getNotificationId() {
    return this.notificationId;
}
}
