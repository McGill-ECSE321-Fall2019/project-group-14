package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.List;

public class StudentDto extends PersonDto {
  private List<ReviewDto> reviews;
  private List<RequestDto> requests;

  public StudentDto() {
    
  }
  
  @SuppressWarnings("unchecked")
  public StudentDto(String email) {
    this(null, email, null, null, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
  }
  
  public StudentDto(String name, String email, Integer Id, String password, List<ReviewDto> reviews, List<RequestDto> requests) {
    super(name, email, Id, password);
    this.reviews = reviews;
    this.requests = requests;
  }
  
  public List<ReviewDto> getReviews() {
    return reviews;
  }

  public void setReviews(List<ReviewDto> reviews) {
    this.reviews = reviews;
  }

  public List<RequestDto> getRequests() {
    return requests;
  }

  public void setRequests(List<RequestDto> requests) {
    this.requests = requests;
  }
}
