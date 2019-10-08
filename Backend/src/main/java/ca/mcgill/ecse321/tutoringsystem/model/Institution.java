package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@Entity
public class Institution{
   private String institutionName;
   
   public void setInstitutionName(String value) {
    this.institutionName = value;
    }
@Id
public String getInstitutionName() {
    return this.institutionName;
    }
private Set<Course> courses;

@OneToMany(mappedBy="institution" , cascade={CascadeType.ALL})
public Set<Course> getCourses() {
   return this.courses;
}

public void setCourses(Set<Course> coursess) {
   this.courses = coursess;
}

private SchoolLevel institutionLevel;

public void setInstitutionLevel(SchoolLevel value) {
    this.institutionLevel = value;
    }
public SchoolLevel getInstitutionLevel() {
    return this.institutionLevel;
       }
   }
