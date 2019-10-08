package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Wage{
   private Tutor tutor;
   
   @ManyToOne(optional=false)
   public Tutor getTutor() {
      return this.tutor;
   }
   
   public void setTutor(Tutor tutor) {
      this.tutor = tutor;
   }
   
   private Course course;
   
   @ManyToOne(optional=false)
   public Course getCourse() {
      return this.course;
   }
   
   public void setCourse(Course course) {
      this.course = course;
   }
   
   private double wage;
   
   public void setWage(double value) {
    this.wage = value;
    }
public double getWage() {
    return this.wage;
    }
private int wageId;

public void setWageId(int value) {
    this.wageId = value;
    }
@Id
public int getWageId() {
    return this.wageId;
       }
   }
