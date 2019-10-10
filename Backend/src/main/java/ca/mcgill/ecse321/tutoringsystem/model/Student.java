package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Id;

@Entity
public class Student extends User{
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
   
   private Integer studentId;

public void setStudentId(Integer value) {
    this.studentId = value;
}
@Id
public Integer getStudentId() {
    return this.studentId;
}
}
