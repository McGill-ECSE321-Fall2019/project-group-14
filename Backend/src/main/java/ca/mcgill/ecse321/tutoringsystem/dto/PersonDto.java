package ca.mcgill.ecse321.tutoringsystem.dto;

public class PersonDto {
  private String name;
  private String email;
  private Integer userId;
  private String password;
  
  public PersonDto() {
    
  }

  public PersonDto(String email) {
    this(null, email, null, null);
  }
  
  public PersonDto(String name, String email, Integer userId, String password) {
    this.name = name;
    this.email = email;
    this.userId = userId;
    this.password = password;
  }
  
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
