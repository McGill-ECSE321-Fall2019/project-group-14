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
import ca.mcgill.ecse321.tutoringsystem.model.NotificationType;
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
    notificationService.createNotification(getRequest(r.getRequestId()), NotificationType.Requested);
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

  /**
   * This method accepts the request made by the Student by first checking if there's an available room
   * for the time and date requested by the Student. If there is one, it will assign the room to that request
   * which effectively "books" the room for that time. If no rooms are available, the Request is automatically rejected
   * @param requestId
   * @throws IOException
   */
  @Transactional
  public void acceptRequest(int requestId) throws IOException {
    Request request = getRequest(requestId);
    if (toList(roomRepository.findAll()).size() == 0) {
      throw new RuntimeException("There are no rooms created.");
    }

    List<Room> rooms = toList(roomRepository.findAll());
    for (Room room : rooms) {
		if (room.getCapacity() == 2) {
			if (room.getRequest().size() == 0) {
				request.setRoom(room);
				Set<Request> requests = room.getRequest();
				requests.add(request);
				room.setRequest(requests);
				requestRepository.save(request);
				roomRepository.save(room);
				notificationService.createNotification(getRequest(request.getRequestId()), NotificationType.Accepted);
				return;
			} else {
				Set<Request> requests = room.getRequest();
				for (Request checkRequest : requests) {
					if (!(checkRequest.getDate().equals(request.getDate())
							&& checkRequest.getTime().equals(request.getTime()))) {
						request.setRoom(room);
						requests.add(request);
						room.setRequest(requests);
						requestRepository.save(request);
						roomRepository.save(room);
						notificationService.createNotification(getRequest(request.getRequestId()), NotificationType.Accepted);
						return;
					}
				}
			}
		}
    }
    rejectRequest(requestId);
  }

  /**
   * This method rejects the Request by simply deleting it and sending an email to notify its rejection
   * @param id
   * @return true if it's successfully rejected
   */
  @Transactional
  public boolean rejectRequest(Integer id) {
      Request r = requestRepository.findRequestByRequestId(id);
      if (r == null) {
          throw new NullPointerException("No Request by this id.");
      }
      notificationService.createNotification(r, NotificationType.Rejected);
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
