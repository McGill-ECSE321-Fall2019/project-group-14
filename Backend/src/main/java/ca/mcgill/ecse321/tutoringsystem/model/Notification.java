package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Notification {
  private Request request;

  @OneToOne(optional = false)
  public Request getRequest() {
    return this.request;
  }

  public void setRequest(Request request) {
    this.request = request;
  }

  private Tutor tutor;

  @ManyToOne(optional = false)
  public Tutor getTutor() {
    return this.tutor;
  }

  public void setTutor(Tutor tutor) {
    this.tutor = tutor;
  }

  public static int nextId = 1;
  private Integer notificationId;

  public void setNotificationId(Integer value) {
    this.notificationId = value;
  }

  @Id
  @GeneratedValue()
  public Integer getNotificationId() {
    return this.notificationId;
  }
}
