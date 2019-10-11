package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Review {
  private Tutor tutor;

  @ManyToOne(optional = false)
  public Tutor getTutor() {
    return this.tutor;
  }

  public void setTutor(Tutor tutor) {
    this.tutor = tutor;
  }

  private Student student;

  @ManyToOne(optional = false)
  public Student getStudent() {
    return this.student;
  }

  public void setStudent(Student student) {
    this.student = student;
  }

  private Integer rating;

  public void setRating(Integer value) {
    this.rating = value;
  }

  public Integer getRating() {
    return this.rating;
  }

  private String comment;

  public void setComment(String value) {
    this.comment = value;
  }

  public String getComment() {
    return this.comment;
  }

  private User from;

  public void setFrom(User value) {
    this.from = value;
  }

  @ManyToOne
  public User getFrom() {
    return this.from;
  }

  private User to;

  public void setTo(User value) {
    this.to = value;
  }

  @ManyToOne
  public User getTo() {
    return this.to;
  }
  
  public static int nextId = 1;
  private Integer reviewId;

  public void setReviewId(Integer value) {
    this.reviewId = value;
  }

  @Id
  public Integer getReviewId() {
    return this.reviewId;
  }
}
