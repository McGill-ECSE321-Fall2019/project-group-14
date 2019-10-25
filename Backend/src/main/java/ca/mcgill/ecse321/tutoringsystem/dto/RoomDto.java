package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.Set;

public class RoomDto {
  private Integer capacity;
  private Integer roomNumber;
  private Set<RequestDto> requests;
  
  public RoomDto() {
    
  }
  
  @SuppressWarnings("unchecked")
  public RoomDto(Integer roomNumber) {
    this(null, roomNumber, (Set<RequestDto>) Collections.EMPTY_LIST);
  }
  
  public RoomDto(Integer capacity, Integer roomNumber, Set<RequestDto> set) {
    this.capacity = capacity;
    this.roomNumber = roomNumber;
    this.requests = set;
  }

  public Integer getCapacity() {
    return capacity;
  }

  public void setCapacity(Integer capacity) {
    this.capacity = capacity;
  }

  public Integer getRoomNumber() {
    return roomNumber;
  }

  public void setRoomNumber(Integer roomNumber) {
    this.roomNumber = roomNumber;
  }

  public Set<RequestDto> getRequests() {
    return requests;
  }

  public void setRequests(Set<RequestDto> requests) {
    this.requests = requests;
  }
}
