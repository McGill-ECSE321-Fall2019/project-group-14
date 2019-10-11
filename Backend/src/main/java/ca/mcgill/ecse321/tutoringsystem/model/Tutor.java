package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;

@Entity
public class Tutor extends Person{
   private Set<Request> request;
   
   @OneToMany(mappedBy="tutor" )
   public Set<Request> getRequest() {
      return this.request;
   }
   
   public void setRequest(Set<Request> requests) {
      this.request = requests;
   }
   
   private Set<Review> review;
   
   @OneToMany(mappedBy="tutor" )
   public Set<Review> getReview() {
      return this.review;
   }
   
   public void setReview(Set<Review> reviews) {
      this.review = reviews;
   }
   
   private Set<TimeSlot> timeslot;
   
   @OneToMany(mappedBy="tutor" , cascade={CascadeType.ALL})
   public Set<TimeSlot> getTimeslot() {
      return this.timeslot;
   }
   
   public void setTimeslot(Set<TimeSlot> timeslots) {
      this.timeslot = timeslots;
   }
   
   private Set<Wage> wage;
   
   @OneToMany(mappedBy="tutor" , cascade={CascadeType.ALL})
   public Set<Wage> getWage() {
      return this.wage;
   }
   
   public void setWage(Set<Wage> wages) {
      this.wage = wages;
   }
   
   private Set<Notification> notification;
   
   @OneToMany(mappedBy="tutor" )
   public Set<Notification> getNotification() {
      return this.notification;
   }
   
   public void setNotification(Set<Notification> notifications) {
      this.notification = notifications;
   }
   
   }
