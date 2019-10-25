package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.Set;

public class TutorDto extends PersonDto {
  private Set<TimeSlotDto> timeSlots;
  private Set<WageDto> wages;
  private Set<RequestDto> requests;
  private Set<NotificationDto> notifications;
  private Set<ReviewDto> reviews;
  
  public TutorDto() {
  }
  
  @SuppressWarnings("unchecked")
  public TutorDto(String email) {
      this(null, email, null, null, Collections.EMPTY_SET, Collections.EMPTY_SET, Collections.EMPTY_SET, Collections.EMPTY_SET, Collections.EMPTY_SET);
  }
  
  public TutorDto(String name, String email, Integer userId, String password, Set<TimeSlotDto> timeSlots, Set<WageDto> wages, Set<RequestDto> requests, Set<NotificationDto> notifications, Set<ReviewDto> reviews) {
    super(name, email, userId, password);
    this.timeSlots = timeSlots;
    this.wages = wages;
    this.requests = requests;
    this.notifications = notifications;
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

  public Set<RequestDto> getRequests() {
    return requests;
  }

  public void setRequests(Set<RequestDto> requests) {
    this.requests = requests;
  }

  public Set<NotificationDto> getNotifications() {
    return notifications;
  }

  public void setNotifications(Set<NotificationDto> notifications) {
    this.notifications = notifications;
  }

  public Set<ReviewDto> getReviews() {
    return reviews;
  }

  public void setReviews(Set<ReviewDto> reviews) {
    this.reviews = reviews;
  }

  
}
