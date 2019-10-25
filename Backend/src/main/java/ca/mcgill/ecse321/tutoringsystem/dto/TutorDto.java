package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.List;

public class TutorDto extends PersonDto {
  private List<TimeSlotDto> timeSlots;
  private List<WageDto> wages;
  private List<RequestDto> requests;
  private List<NotificationDto> notifications;
  private List<ReviewDto> reviews;
  
  public TutorDto() {
    
  }
  
  @SuppressWarnings("unchecked")
  public TutorDto(String email) {
      this(null, email, null, null, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
  }
  
  public TutorDto(String name, String email, Integer userId, String password, List<TimeSlotDto> timeSlots, List<WageDto> wages, List<RequestDto> requests, List<NotificationDto> notifications, List<ReviewDto> reviews) {
    super(name, email, userId, password);
    this.timeSlots = timeSlots;
    this.wages = wages;
    this.requests = requests;
    this.notifications = notifications;
    this.reviews = reviews;
  }
  
  public List<TimeSlotDto> getTimeSlots() {
    return timeSlots;
  }
  
  public void setTimeSlots(List<TimeSlotDto> timeSlots) {
    this.timeSlots = timeSlots;
  }

  public List<WageDto> getWages() {
    return wages;
  }

  public void setWages(List<WageDto> wages) {
    this.wages = wages;
  }

  public List<RequestDto> getRequests() {
    return requests;
  }

  public void setRequests(List<RequestDto> requests) {
    this.requests = requests;
  }

  public List<NotificationDto> getNotifications() {
    return notifications;
  }

  public void setNotifications(List<NotificationDto> notifications) {
    this.notifications = notifications;
  }

  public List<ReviewDto> getReviews() {
    return reviews;
  }

  public void setReviews(List<ReviewDto> reviews) {
    this.reviews = reviews;
  }

  
}
