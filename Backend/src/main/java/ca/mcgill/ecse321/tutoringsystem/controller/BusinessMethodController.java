package ca.mcgill.ecse321.tutoringsystem.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dao.*;
import ca.mcgill.ecse321.tutoringsystem.dto.PersonDto;
import ca.mcgill.ecse321.tutoringsystem.dto.RequestDto;
import ca.mcgill.ecse321.tutoringsystem.model.Manager;
import ca.mcgill.ecse321.tutoringsystem.model.Person;
import ca.mcgill.ecse321.tutoringsystem.model.Request;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;
import ca.mcgill.ecse321.tutoringsystem.service.PersonService;
import ca.mcgill.ecse321.tutoringsystem.service.RequestService;

@CrossOrigin(origins = "*")
@RestController
public class BusinessMethodController {
  @Autowired
  PersonService personService;
  @Autowired
  RequestService requestService;
  @Autowired
	private TutorRepository tutorRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private RequestRepository requestRepository;
	@Autowired
	private CourseRepository courseRepository;
	@Autowired
	private RoomRepository roomRepository;
	@Autowired
	private NotificationRepository notificationRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private InstitutionRepository institutionRepository;
	@Autowired
	private WageRepository wageRepository;
	@Autowired
	private TimeSlotRepository timeslotRepository;

  /**
   * Logs in the user with input email and password
   * 
   * @param email
   * @param password
   * @return personDto or null if login failed
   * @throws IllegalArgumentException
   */
  @PostMapping(value = {"/login", "/login/"})
  public PersonDto login(@RequestParam("email") String email,
      @RequestParam("password") String password) throws IllegalArgumentException {
    Person person = personService.login(email, password);
    if (person instanceof Manager) {
      return DtoConverter.toDto(person);
    } else if (person instanceof Tutor) {
      return DtoConverter.toDto(person);
    } else if (person instanceof Student) {
      return DtoConverter.toDto(person);
    } else {
      return null;
    }
  }

  @PostMapping(value = {"/accept/{id}", "/accept/{id}/"})
  public RequestDto acceptRequest(@PathVariable("id") Integer id) throws IllegalArgumentException {
    try {
      requestService.acceptRequest(id);
    } catch (IOException e) {
    }
    Request request = requestService.getRequest(id);
    if (request.getRoom() != null) {
      return DtoConverter.toDto(request);
    } else {
      return null;
    }
  }

  @PostMapping(value = {"/reject/{id}", "/reject/{id}/"})
  public void rejectRequest(@PathVariable("id") Integer id) throws IllegalArgumentException {
    requestService.rejectRequest(id);
    return;
  }

	@PostMapping(value = { "/flushdb" })
	public boolean flushdb() {
		requestRepository.deleteAll();
		tutorRepository.deleteAll();
		managerRepository.deleteAll();
		studentRepository.deleteAll();
		timeslotRepository.deleteAll();
		wageRepository.deleteAll();
		institutionRepository.deleteAll();
		applicationRepository.deleteAll();
		reviewRepository.deleteAll();
		notificationRepository.deleteAll();
		roomRepository.deleteAll();
		courseRepository.deleteAll();
		return true;
	}
}
