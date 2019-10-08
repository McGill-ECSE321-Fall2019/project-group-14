package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Id;

@Entity
public class Application{
   private Manager manager;
   
   @ManyToOne(optional=false)
   public Manager getManager() {
      return this.manager;
   }
   
   public void setManager(Manager manager) {
      this.manager = manager;
   }
   
   private boolean isExistingUser;
   
   public void setIsExistingUser(boolean value) {
      this.isExistingUser = value;
   }
   
   public boolean isIsExistingUser() {
      return this.isExistingUser;
   }
   
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
private String courses;

public void setCourses(String value) {
    this.courses = value;
    }
public String getCourses() {
    return this.courses;
    }
private int applicationId;

public void setApplicationId(int value) {
    this.applicationId = value;
    }
@Id
public int getApplicationId() {
    return this.applicationId;
       }
   }
