package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.Set;

public class StudentDto extends PersonDto {
  private Set<ReviewDto> reviews;

  public StudentDto() {
    
  }
  
  @SuppressWarnings("unchecked")
  public StudentDto(String email) {
    this(null, email, null, null, Collections.EMPTY_SET);
  }
  
  public StudentDto(String name, String email, Integer Id, String password, Set<ReviewDto> reviews) {
    super(name, email, Id, password);
    this.reviews = reviews;
  }
  
  public Set<ReviewDto> getReviews() {
    return reviews;
  }

  public void setReviews(Set<ReviewDto> reviews) {
    this.reviews = reviews;
  }

}
