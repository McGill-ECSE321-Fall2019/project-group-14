package ca.mcgill.ecse321.tutoringsystem.service;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.RequestRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Course;
import ca.mcgill.ecse321.tutoringsystem.model.Request;
import ca.mcgill.ecse321.tutoringsystem.model.Room;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

@Service
public class RequestService {
  @Autowired
  RequestRepository requestRepository;
  @Autowired
  RoomRepository roomRepository;
  @Autowired
  NotificationService notificationService;

  @Transactional
  public Request createRequest(Time time, Date date, Tutor tutor, Student student, Course course) {
    String error = "";
    if (time == null) {
      error = error + "Time cannot be empty! ";
    }
    if (date == null) {
      error = error + "Date cannot be empty! ";
    }
    if (tutor == null) {
      error = error + "Tutor cannot be empty! ";
    }
    if (student == null) {
      error = error + "Student cannot be empty! ";
    }
    if (course == null) {
      error = error + "Course cannot be empty! ";
    }
    error = error.trim();
    if (error.length() > 0) {
      throw new IllegalArgumentException(error);
    }
    Request r = new Request();
    r.setTime(time);
    r.setDate(date);
    r.setTutor(tutor);
    r.setStudent(student);
    r.setCourse(course);
    requestRepository.save(r);
    return r;
  }

  @Transactional
  public Request getRequest(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Request id cannot be empty!");
    }
    Request r = requestRepository.findRequestByRequestId(id);
    return r;
  }

  @Transactional
  public List<Request> getAllRequests() {
    return toList(requestRepository.findAll());
  }

  @Transactional
  public List<Request> getTutorRequests(Tutor tutor) {
    return requestRepository.findRequestByTutor(tutor);
  }

  @Transactional
  public List<Request> getAcceptedTutorRequests(Tutor tutor) {
    return requestRepository.findRequestByTutorAndRoomIsNotNull(tutor);
  }

  @Transactional
  public void acceptRequest(int requestId) throws IOException {
    Request request = getRequest(requestId);
    if (toList(roomRepository.findAll()).size() == 0) {
      throw new RuntimeException("There are no rooms created.");
    }

    List<Room> rooms = toList(roomRepository.findAll());
    for (Room room : rooms) {
    	if (!(room.getCapacity() > 2)) {
    	      if (room.getRequest().size() == 0) {
    	          request.setRoom(room);
    	          Set<Request> requests = room.getRequest();
    	          requests.add(request);
    	          room.setRequest(requests);
    	          requestRepository.save(request);
    	          roomRepository.save(room);
    	          //return;
    	        } else {
    	          Set<Request> requests = room.getRequest();
    	          for (Request checkRequest : requests) {
    	            if (checkRequest.getDate() != request.getDate() && checkRequest.getTime() != request.getTime()) {
    	              request.setRoom(room);
    	              requests.add(request);
    	              room.setRequest(requests);
    	              requestRepository.save(request);
    	              roomRepository.save(room);
    	              //return;
    	            } else {
    	              throw new RuntimeException("There are no rooms available for that time and date.");
    	            }
    	          }
    	        }	
    	}
    }
    notificationService.createNotification(request);
    notificationService.notify(request, 1);

  }

  @Transactional
  public boolean rejectRequest(Integer id) {
      Request r = requestRepository.findRequestByRequestId(id);
      if (r == null) {
          throw new NullPointerException("No Request by this id.");
      }
      requestRepository.delete(r);
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
