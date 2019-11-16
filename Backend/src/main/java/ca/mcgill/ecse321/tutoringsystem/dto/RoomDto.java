package ca.mcgill.ecse321.tutoringsystem.dto;

import java.util.Collections;
import java.util.List;

public class RoomDto {
  private Integer capacity;
  private Integer roomNumber;
  private List<Integer> requestIds;
  
  public RoomDto() {
    
  }
  
  @SuppressWarnings("unchecked")
  public RoomDto(Integer roomNumber) {
    this(null, roomNumber, Collections.EMPTY_LIST);
  }
  
  public RoomDto(Integer capacity, Integer roomNumber, List<Integer> requestIds) {
    this.capacity = capacity;
    this.roomNumber = roomNumber;
    this.requestIds = requestIds;
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

  public List<Integer> getRequestIds() {
    return requestIds;
  }

  public void setRequestIds(List<Integer> requestIds) {
    this.requestIds = requestIds;
  }
}
