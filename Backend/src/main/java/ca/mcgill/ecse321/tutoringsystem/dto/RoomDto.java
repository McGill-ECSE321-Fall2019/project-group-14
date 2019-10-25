package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.List;

public class RoomDto {
  private Integer capacity;
  private Integer roomNumber;
  private List<RequestDto> requests;
  
  public RoomDto() {
    
  }
  
  @SuppressWarnings("unchecked")
  public RoomDto(Integer roomNumber) {
    this(null, roomNumber, Collections.EMPTY_LIST);
  }
  
  public RoomDto(Integer capacity, Integer roomNumber, List<RequestDto> requests) {
    this.capacity = capacity;
    this.roomNumber = roomNumber;
    this.requests = requests;
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

  public List<RequestDto> getRequests() {
    return requests;
  }

  public void setRequests(List<RequestDto> requests) {
    this.requests = requests;
  }
}
