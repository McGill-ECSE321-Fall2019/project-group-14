package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.Set;

public class TutorDto extends PersonDto {
  private Set<TimeSlotDto> timeSlots;
  private Set<WageDto> wages;
  private Set<ReviewDto> reviews;
  
  public TutorDto() {
  }
  
  @SuppressWarnings("unchecked")
  public TutorDto(String email) {
      this(null, email, null, null, Collections.EMPTY_SET, Collections.EMPTY_SET, Collections.EMPTY_SET);
  }
  
  public TutorDto(String name, String email, Integer userId, String password, Set<TimeSlotDto> timeSlots, Set<WageDto> wages, Set<ReviewDto> reviews) {
    super(name, email, userId, password);
    this.timeSlots = timeSlots;
    this.wages = wages;
    this.reviews = reviews;
  }
  
  public Set<TimeSlotDto> getTimeSlots() {
    return timeSlots;
  }
  
  public void setTimeSlots(Set<TimeSlotDto> timeSlots) {
    this.timeSlots = timeSlots;
  }

  public Set<WageDto> getWages() {
    return wages;
  }

  public void setWages(Set<WageDto> wages) {
    this.wages = wages;
  }

  public Set<ReviewDto> getReviews() {
    return reviews;
  }

  public void setReviews(Set<ReviewDto> reviews) {
    this.reviews = reviews;
  }

  
}
