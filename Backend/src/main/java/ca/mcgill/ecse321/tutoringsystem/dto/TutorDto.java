package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.List;

public class TutorDto {
  private String name;
  private String email;
  private Integer userId;
  private String password;
  private List<TimeSlotDto> timeSlots;
  private List<WageDto> wages;
  private List<RequestDto> requests;
  private List<NotificationDto> notifiactions;
  private List<ReviewDto> reviews;
  
  public TutorDto() {
    
  }
  
  @SuppressWarnings("unchecked")
  public TutorDto(String name, String email) {
      this(name, email, null, null, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST, Collections.EMPTY_LIST);
  }
  
  public TutorDto(String name, String email, Integer userId, String password, List<TimeSlotDto> timeSlots, List<WageDto> wages, List<RequestDto> requests, List<NotificationDto> notificationDto, List<ReviewDto> reviews) {
    this.name = name;
    this.email = email;
    this.userId = userId;
    this.password = password;
    this.timeslots = timeslots;
    this.wages = wages;
    this.requests = requests;
    this.notifiactions = notifications;
    this.reviews = reviews;
  }
  
  public String getName() {
    return name;
  }
  
  public String getEmail() {
    return email;
  }
  
  public Integer getId() {
    return userId;
  }
  
  public List<timeSlot> getTimeSlots() {
    return timeSlots;
  }
}
