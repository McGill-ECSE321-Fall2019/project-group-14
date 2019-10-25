package ca.mcgill.ecse321.tutoringsystem.dto;

import java.sql.Date;
import java.sql.Time;

public class TimeSlotDto {
  private Date date;
  private Time time;
  private Integer timeSlotId;
  
  public TimeSlotDto() {
    
  }
  
  public TimeSlotDto(Integer timeSlotId) {
    this(null, null, timeSlotId);
  }
  
  public TimeSlotDto(Date date, Time time, Integer timeSlotId) {
    this.date = date;
    this.time = time;
    this.timeSlotId = timeSlotId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public Time getTime() {
    return time;
  }

  public void setTime(Time time) {
    this.time = time;
  }

  public Integer getTimeSlotId() {
    return timeSlotId;
  }

  public void setTimeSlotId(Integer timeSlotId) {
    this.timeSlotId = timeSlotId;
  }
  
  
}
