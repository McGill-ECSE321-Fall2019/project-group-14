package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.tutoringsystem.dto.RoomDto;
import ca.mcgill.ecse321.tutoringsystem.model.Room;
import ca.mcgill.ecse321.tutoringsystem.service.RoomService;


@CrossOrigin(origins = "*")
@RestController
public class RoomController {
  @Autowired
  RoomService roomService;

  @PostMapping(value = {"/rooms/create", "/rooms/create/"})
  public RoomDto createRoom(@RequestParam("id") Integer roomNumber,
      @RequestParam(name = "capacity") Integer capacity) throws IllegalArgumentException {
    Room room = roomService.createRoom(roomNumber, capacity);
    return DtoConverter.toDto(room);
  }
  
  @GetMapping(value = {"/rooms/{id}", "/rooms/{id}/"})
  public RoomDto getRoom(@PathVariable("id") Integer roomNumber) throws IllegalArgumentException {
    Room room = roomService.getRoom(roomNumber);
    return DtoConverter.toDto(room);
  }
  
  @DeleteMapping(value = {"/rooms/{id}", "/rooms/{id}/"})
  public boolean deleteRoom(@PathVariable("id") Integer roomNumber) throws IllegalArgumentException {
    return roomService.deleteRoom(roomNumber);
  }

  @GetMapping(value = {"/rooms", "/rooms/"})
  public List<RoomDto> getAllRooms() {
    List<RoomDto> roomDtos = new ArrayList<>();
    for (Room room : roomService.getAllRooms()) {
      roomDtos.add(DtoConverter.toDto(room));
    }
    return roomDtos;
  }
}
