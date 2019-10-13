package ca.mcgill.ecse321.tutoringsystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.portable.ValueOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringsystem.dao.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringSystemService {

	@Autowired
	TutoringSystemService service;

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

	@After
	public void clearDatabase() {
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
	}

	// Tutor class tests

	@Test
	public void testCreateTutor() {
		assertEquals(0, service.getAllTutors().size());
		String name = "Martin";
		String email = "martin@mail.mcgill.ca";
		try {
			service.createTutor(name, email);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();
		assertEquals(name, allTutors.get(0).getName());
		assertEquals(email, allTutors.get(0).getEmail());
	}
	
	@Test
	public void testCreateNullTutor() {
		assertEquals(0, service.getAllTutors().size());
		String error = null;
		String name = null;
		String email = null;
		try {
			service.createTutor(name, email);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor name and email cannot be empty!", error);
		assertEquals(0, service.getAllTutors().size());
	}

	// Student class tests

	@Test
	public void testCreateStudent() {
		assertEquals(0, service.getAllStudents().size());
		String name = "Jason";
		String email = "jason@mail.mcgill.ca";
		try {
			service.createStudent(name, email);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = service.getAllStudents();
		assertEquals(name, allStudents.get(0).getName());
		assertEquals(email, allStudents.get(0).getEmail());
	}

	@Test
	public void testCreateNullStudent() {
		assertEquals(0, service.getAllStudents().size());
		String error = null;
		String name = null;
		String email = null;
		try {
			service.createStudent(name, email);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Student name and email cannot be empty!", error);
		assertEquals(0, service.getAllStudents().size());
	}

	// Manager class tests

	@Test
	public void testCreateManager() {
		String name = "Marwan";
		String email = "Marwan@mail.mcgill.ca";
		try {
			service.createManager(name, email);
		} catch (IllegalArgumentException e) {
			fail();
		}

		Manager manager = service.getManager(email);
		assertEquals(name, manager.getName());
		assertEquals(email, manager.getEmail());
	}

	@Test
	public void testCreateNullManager() {
		String error = null;
		String name = null;
		String email = null;
		try {
			service.createManager(name, email);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Manager name and email cannot be empty!", error);
	}

	// Request class tests

	@Test
	public void testCreateRequest() {
		assertEquals(0, service.getAllRequests().size());
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = service.createTutor("name", "email");
		Student student = service.createStudent("name", "email");
		Course course = service.createCourse("test",
				service.createInstitution("institutionName", SchoolLevel.University), "subject");
		try {
			service.createRequest(time, date, tutor, student, course);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Request> allRequests = service.getAllRequests();
		assertEquals(time, allRequests.get(0).getTime());
		assertEquals(date, allRequests.get(0).getDate());
		assertEquals(tutor.getUserId(), allRequests.get(0).getTutor().getUserId());
		assertEquals(student.getUserId(), allRequests.get(0).getStudent().getUserId());
		assertEquals(course.getCourseName(), allRequests.get(0).getCourse().getCourseName());
	}

	@Test
	public void testCreateRequestWithNullTime() {
		assertEquals(0, service.getAllRequests().size());
		String error = null;
		Time time = null;
		Date date = new Date(0);
		Tutor tutor = service.createTutor("name", "email");
		Student student = service.createStudent("name", "email");
		Course course = service.createCourse("test",
				service.createInstitution("institutionName", SchoolLevel.University), "subject");
		try {
			service.createRequest(time, date, tutor, student, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Time cannot be empty!", error);
		assertEquals(0, service.getAllRequests().size());

	}

	// Course class tests

	@Test
	public void testCreateCourse() {
		assertEquals(0, service.getAllCourses().size());
		String name = "MATH 263";
		Institution institution = service.createInstitution("McGill University", SchoolLevel.University);
		String subjectName = "Mathematics";
		try {
			service.createCourse(name, institution, subjectName);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Course> allCourses = service.getAllCourses();
		assertEquals(name, allCourses.get(0).getCourseName());
		assertEquals(institution.getInstitutionName(), allCourses.get(0).getInstitution().getInstitutionName());
		assertEquals(subjectName, allCourses.get(0).getSubjectName());
	}

	@Test
	public void testCreateCourseWithNullName() {
		assertEquals(0, service.getAllCourses().size());
		String error = null;
		String name = null;
		Institution institution = service.createInstitution("McGill University", SchoolLevel.University);
		String subjectName = "Mathematics";
		try {
			service.createCourse(name, institution, subjectName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Course name cannot be empty!", error);
		assertEquals(0, service.getAllCourses().size());

	}

	// Session class tests

	@Test
	public void testCreateAndAcceptSession() {
		assertEquals(0, service.getAllRequests().size());
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = service.createTutor("name", "email");
		Student student = service.createStudent("name", "email");
		Course course = service.createCourse("test",
				service.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request = service.createRequest(time, date, tutor, student, course);
		Room room = service.createRoom(1, 2);
		try {
			service.acceptRequest(request.getRequestId());
		} catch (IllegalArgumentException e) {
			fail();
		}
		List<Request> allRequests = service.getAllRequests();
		assertEquals(time, allRequests.get(0).getTime());
		assertEquals(date, allRequests.get(0).getDate());
		assertEquals(tutor.getUserId(), allRequests.get(0).getTutor().getUserId());
		assertEquals(student.getUserId(), allRequests.get(0).getStudent().getUserId());
		assertEquals(course.getCourseName(), allRequests.get(0).getCourse().getCourseName());
		assertEquals(room.getRoomNumber(), allRequests.get(0).getRoom().getRoomNumber());
	}

	// Room class tests

	@Test
	public void testCreateRoom() {
		assertEquals(0, service.getAllRooms().size());
		Integer roomNumber = 12;
		Integer capacity = 30;
		try {
			service.createRoom(roomNumber, capacity);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Room> allRooms = service.getAllRooms();
		assertEquals(roomNumber, allRooms.get(0).getRoomNumber());
		assertEquals(capacity, allRooms.get(0).getCapacity());
	}

	@Test
	public void testCreateNullRoom() {
		assertEquals(0, service.getAllRooms().size());
		String error = null;
		Integer roomNumber = null;
		Integer capacity = 30;
		try {
			service.createRoom(roomNumber, capacity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Room number cannot be empty!", error);
		assertEquals(0, service.getAllRooms().size());
	}

	// Notification class tests

	@Test
	public void testCreateNotification() {
		assertEquals(0, service.getAllNotifications().size());
		Time time = new Time(0);
		Date date = new Date(0);
		Tutor tutor = service.createTutor("name", "email");
		Student student = service.createStudent("name", "email");
		Course course = service.createCourse("test",
				service.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request = service.createRequest(time, date, tutor, student, course);
		try {
			service.createNotification(request);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Notification> allNotifications = service.getAllNotifications();
		assertEquals(request.getRequestId(), allNotifications.get(0).getRequest().getRequestId());
	}

	@Test
	public void testCreateNullNotification() {
		assertEquals(0, service.getAllNotifications().size());
		String error = null;
		Request request = null;
		try {
			service.createNotification(request);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Notification ID cannot be null!", error);
		assertEquals(0, service.getAllNotifications().size());
	}

	// Review class tests

	@Test
	public void testCreateReview() {
		// TODO getAllReviews() needs to be implemented to use this test
		Integer rating = 5;
		String comment = "This is a comment.";
		Person from = service.createTutor("name", "email");
		Person to = service.createStudent("name", "email");
		try {
			service.createReview(rating, comment, from, to);
		} catch (IllegalArgumentException e) {
			fail();
		}

		// TODO "allReviews" list retrieved + assertEquals are written here.
	}

	@Test
	public void testCreateNullReview() {
		// TODO getAllReviews() needs to be implemented to use this test
		String error = null;
		Integer rating = null;
		String comment = null;
		Person from = null;
		Person to = null;
		try {
			service.createReview(rating, comment, from, to);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Rating cannot be null!", error);
		// TODO call getAllReviews() here to test size
	}

	// Application class tests

	@Test
	public void testCreateApplication() {
		Boolean isExistingUser = true;
		String name = "Martin";
		String email = "martin@mail.mcgill.ca";
		String course = "ECSE 321";
		try {
			service.createApplication(isExistingUser, name, email, course);
		} catch (IllegalArgumentException e) {
			fail();
		}

		Application application = service.getApplication(email);
		assertEquals(true, application.getIsExistingUser());
		assertEquals(name, application.getName());
		assertEquals(email, application.getEmail());
		assertEquals(course, application.getCourses());
	}

	@Test
	public void testCreateApplicationWithNullName() {
		String error = null;
		Boolean isExistingUser = true;
		String name = null;
		String email = "martin@mail.mcgill.ca";
		String course = "ECSE 321";
		try {
			service.createApplication(isExistingUser, name, email, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Application name cannot be empty!", error);
	}

	// Institution class tests

	@Test
	public void testCreateInstitution() {
		assertEquals(0, service.getAllInstitutions().size());
		String institutionName = "McGill University";
		SchoolLevel institutionLevel = SchoolLevel.University;
		try {
			service.createInstitution(institutionName, institutionLevel);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Institution> allInstitutions = service.getAllInstitutions();
		assertEquals(institutionName, allInstitutions.get(0).getInstitutionName());
		assertEquals(institutionLevel, allInstitutions.get(0).getInstitutionLevel());
	}
	
	@Test
	public void testCreateNullInstitution() {
		assertEquals(0, service.getAllInstitutions().size());
		String error = null;
		String institutionName = null;
		SchoolLevel institutionLevel = SchoolLevel.University;
		try {
			service.createInstitution(institutionName, institutionLevel);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Institution name cannot be null!", error);
		assertEquals(0, service.getAllInstitutions().size());

	}

	// Wage class tests

	@Test
	public void testCreateWage() {
		assertEquals(0, service.getAllWages().size());
		Tutor tutor = service.createTutor("name", "email");
		Course course = service.createCourse("test",
				service.createInstitution("institutionName", SchoolLevel.University), "subject");
		Integer wage = 20;
		try {
			service.createWage(tutor, course, wage);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Wage> allWages = service.getAllWages();
		assertEquals(tutor.getUserId(), allWages.get(0).getTutor().getUserId());
		assertEquals(course.getCourseName(), allWages.get(0).getCourse().getCourseName());
		assertEquals(wage, allWages.get(0).getWage());
	}
	
	@Test
	public void testCreateNullWage() {
		assertEquals(0, service.getAllWages().size());
		String error = null;
		Tutor tutor = null;
		Course course = null;
		Integer wage = 20;
		try {
			service.createWage(tutor, course, wage);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("A tutor needs to be specified!", error);
		assertEquals(0, service.getAllWages().size());
	}

	// TimeSlot class tests

	@Test
	public void testCreateTimeSlot() {
		Time time = new Time(0);
		Date date = new Date(0);
		Tutor tutor = service.createTutor("name", "email");
		try {
			service.createTimeSlot(tutor, date, time);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
}
