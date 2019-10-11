package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Set;
import javax.persistence.OneToMany;

@Entity
public class Course {
  private String courseName;

  public void setCourseName(String value) {
    this.courseName = value;
  }

  @Id
  public String getCourseName() {
    return this.courseName;
  }

  private Institution institution;

  @ManyToOne(optional = false)
  public Institution getInstitution() {
    return this.institution;
  }

  public void setInstitution(Institution institution) {
    this.institution = institution;
  }

  private String subjectName;

  public void setSubjectName(String value) {
    this.subjectName = value;
  }

  public String getSubjectName() {
    return this.subjectName;
  }

  private Set<Wage> wage;

  @OneToMany(mappedBy = "course")
  public Set<Wage> getWage() {
    return this.wage;
  }

  public void setWage(Set<Wage> wages) {
    this.wage = wages;
  }

}
