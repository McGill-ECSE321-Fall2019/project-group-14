package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.GeneratedValue;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "person_type")
public class Person {
  private String name;

  public void setName(String value) {
    this.name = value;
  }

  public String getName() {
    return this.name;
  }

  private String email;

  public void setEmail(String value) {
    this.email = value;
  }

  public String getEmail() {
    return this.email;
  }

  private Integer userId;

  public void setUserId(Integer value) {
    this.userId = value;
  }

  @Id
  @GeneratedValue()
  public Integer getUserId() {
    return this.userId;
  }
}
