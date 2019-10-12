package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("S")
public class Student extends Person{
   private Set<Review> review;
   
   @OneToMany(mappedBy="student" )
   public Set<Review> getReview() {
      return this.review;
   }
   
   public void setReview(Set<Review> reviews) {
      this.review = reviews;
   }
   
   private Set<Request> request;
   
   @OneToMany(mappedBy="student" , cascade={CascadeType.ALL})
   public Set<Request> getRequest() {
      return this.request;
   }
   
   public void setRequest(Set<Request> requests) {
      this.request = requests;
   }
   
}

