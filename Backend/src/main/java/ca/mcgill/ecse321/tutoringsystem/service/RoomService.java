package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Room;

@Service
public class RoomService {
  @Autowired
  RoomRepository roomRepository;

  @Transactional
  public Room createRoom(Integer roomNumber, Integer capacity) {
    String error = "";
    if (roomNumber == null) {
      error = error + "Room number cannot be empty! ";
    }
    if (capacity == null || capacity == 0) {
      error = error + "Room capacity cannot be empty! ";
    }
    error = error.trim();
    if (error.length() > 0) {
      throw new IllegalArgumentException(error);
    }
    Room r = new Room();
    r.setRoomNumber(roomNumber);
    r.setCapacity(capacity);
    roomRepository.save(r);
    return r;
  }

  @Transactional
  public Room getRoom(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Room id cannot be empty!");
    }
    Room p = roomRepository.findRoomByRoomNumber(id);
    return p;
  }

  @Transactional
  public List<Room> getAllRooms() {
    return toList(roomRepository.findAll());
  }
  
  @Transactional
  public boolean deleteRoom(Integer id) {
      Room r = roomRepository.findRoomByRoomNumber(id);
      if (r == null) {
          throw new NullPointerException("No Room by this id.");
      }
      roomRepository.delete(r);
      return true;
  }

  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }
}
