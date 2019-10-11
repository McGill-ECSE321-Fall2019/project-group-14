package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class TimeSlot{
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
   private Date date;

public void setDate(Date value) {
    this.date = value;
}
public Date getDate() {
    return this.date;
}
private Time time;

public void setTime(Time value) {
    this.time = value;
}
public Time getTime() {
    return this.time;
}
private Integer timeSlotId;

public void setTimeSlotId(Integer value) {
    this.timeSlotId = value;
}
@Id
@GeneratedValue()public Integer getTimeSlotId() {
    return this.timeSlotId;
}
}
