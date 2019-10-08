package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Id;

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
private int timeSlotId;

public void setTimeSlotId(int value) {
    this.timeSlotId = value;
    }
@Id
public int getTimeSlotId() {
    return this.timeSlotId;
       }
   }
