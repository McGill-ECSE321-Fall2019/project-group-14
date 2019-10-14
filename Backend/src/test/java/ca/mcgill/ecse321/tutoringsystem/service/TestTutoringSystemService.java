package ca.mcgill.ecse321.tutoringsystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
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
	public void testCreateTutor() { // Test create and getters
		assertEquals(0, service.getAllTutors().size());
		String name = "Martin";
		String email = "martin@mail.mcgill.ca";
		String password = "password";
		try {
			service.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();
		assertEquals(name, allTutors.get(0).getName());
		assertEquals(email, allTutors.get(0).getEmail());
	}

	@Test
	public void testCreateNullTutor() { // Test condition checks
		assertEquals(0, service.getAllTutors().size());
		String error = null;
		String name = null;
		String email = null;
		String password = "password";
		try {
			service.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor name, email or password cannot be empty!", error);
		assertEquals(0, service.getAllTutors().size());
	}

	@Test
	public void testSetTutorNewName() { // Test setters
		assertEquals(0, service.getAllTutors().size());
		String name = "Martin";
		String email = "martin@mail.mcgill.ca";
		String password = "password";
		String newName = "George";
		String newEmail = "george@mail.mcgill.ca";
		try {
			Tutor t = service.createTutor(name, email, password);
			t.setName(newName);
			t.setEmail(newEmail);
			tutorRepository.save(t);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Tutor> allTutors = service.getAllTutors();
		assertEquals(newName, allTutors.get(0).getName());
		assertEquals(newEmail, allTutors.get(0).getEmail());
	}

	@Test
	public void testGetTutor() { // Test getters
		assertEquals(0, service.getAllTutors().size());
		Tutor t = null;
		String name = "Martin";
		String email = "martin@mail.mcgill.ca";
		String password = "password";
		try {
			t = service.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(t.getUserId(), service.getTutor(email).getUserId());
	}

	@Test
	public void testGetTutorRequests() { // Test getters
		assertEquals(0, service.getAllTutors().size());
		String tutorName = "Martin";
		String tutorEmail = "martin@mail.mcgill.ca";
		String tutorPassword = "password";
		Tutor tutor = service.createTutor(tutorName, tutorEmail, tutorPassword);
		Student student = service.createStudent("name1", "email11", "password11");
		Student student1 = service.createStudent("student1", "email1", "password1");
		Course course = service.createCourse("test",
				service.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request1 = service.createRequest(Time.valueOf("12:12:12"), Date.valueOf("2019-02-22"), tutor, student,
				course);
		Request request2 = service.createRequest(Time.valueOf("10:21:21"), Date.valueOf("2019-01-22"), tutor, student1,
				course);
		List<Request> tutorRequests = null;
		try {
			tutorRequests = service.getTutorRequests(tutor);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(request1.getRequestId(), tutorRequests.get(0).getRequestId());
		assertEquals(request2.getRequestId(), tutorRequests.get(1).getRequestId());
	}

	@Test
	public void testGetAcceptedTutorRequests() { // Test getters
		assertEquals(0, service.getAllTutors().size());
		String tutorName = "Martin";
		String tutorEmail = "martin@mail.mcgill.ca";
		String tutorPassword = "password";
		Tutor tutor = service.createTutor(tutorName, tutorEmail, tutorPassword);
		Student student = service.createStudent("name1", "email11", "password11");
		Student student1 = service.createStudent("student1", "email1", "password1");
		Course course = service.createCourse("test",
				service.createInstitution("institutionName", SchoolLevel.University), "subject");
		service.createRoom(1, 2);
		Request request1 = service.createRequest(Time.valueOf("12:12:12"), Date.valueOf("2019-02-22"), tutor, student,
				course);
		service.createRequest(Time.valueOf("10:21:21"), Date.valueOf("2019-01-22"), tutor, student1, course);
		service.acceptRequest(request1.getRequestId());
		List<Request> tutorRequests = null;
		try {
			tutorRequests = service.getAcceptedTutorRequests(tutor);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, tutorRequests.size());
		assertEquals(request1.getRequestId(), tutorRequests.get(0).getRequestId());
	}

	// Student class tests

	@Test
	public void testCreateStudent() { // Test create and getters
		assertEquals(0, service.getAllStudents().size());
		String name = "Jason";
		String email = "jason@mail.mcgill.ca";
		String password = "password";
		try {
			service.createStudent(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = service.getAllStudents();
		assertEquals(name, allStudents.get(0).getName());
		assertEquals(email, allStudents.get(0).getEmail());
	}

	@Test
	public void testCreateNullStudent() { // Test condition checks
		assertEquals(0, service.getAllStudents().size());
		String error = null;
		String name = null;
		String email = null;
		String password = "password";
		try {
			service.createStudent(name, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Student name, email or password cannot be empty!", error);
		assertEquals(0, service.getAllStudents().size());
	}

	@Test
	public void testSetStudentNewName() { // Test setters
		assertEquals(0, service.getAllTutors().size());
		String name = "Jason";
		String email = "jason@mail.mcgill.ca";
		String password = "password";
		String newName = "George";
		String newEmail = "george@mail.mcgill.ca";
		try {
			Student s = service.createStudent(name, email, password);
			s.setName(newName);
			s.setEmail(newEmail);
			studentRepository.save(s);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = service.getAllStudents();
		assertEquals(newName, allStudents.get(0).getName());
		assertEquals(newEmail, allStudents.get(0).getEmail());
	}

	// Manager class tests

	@Test
	public void testCreateManager() { // Test create and getters
		String name = "Marwan";
		String email = "Marwan@mail.mcgill.ca";
		String password = "password";
		try {
			service.createManager(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		Manager manager = service.getManager(email);
		assertEquals(name, manager.getName());
		assertEquals(email, manager.getEmail());
	}

	@Test
	public void testCreateNullManager() { // Test condition checks
		String error = null;
		String name = null;
		String email = null;
		String password = "password";
		try {
			service.createManager(name, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Manager name, email or password cannot be empty!", error);
	}

	@Test
	public void testSetManagerNewName() { // Test setters
		assertEquals(0, service.getAllTutors().size());
		String name = "Marwan";
		String email = "Marwan@mail.mcgill.ca";
		String password = "password";
		String newName = "Daniel";
		String newEmail = "daniel@mail.mcgill.ca";
		try {
			Manager m = service.createManager(name, email, password);
			m.setName(newName);
			m.setEmail(newEmail);
			managerRepository.save(m);
		} catch (IllegalArgumentException e) {
			fail();
		}

		Manager manager = service.getManager(newEmail);
		assertEquals(newName, manager.getName());
		assertEquals(newEmail, manager.getEmail());
	}

	// Request class tests

	@Test
	public void testCreateRequest() { // Test create and getters
		assertEquals(0, service.getAllRequests().size());
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = service.createTutor("name", "email", "password");
		Student student = service.createStudent("name", "email", "password");
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
	public void testCreateRequestWithNullTime() { // Test condition checks
		assertEquals(0, service.getAllRequests().size());
		String error = null;
		Time time = null;
		Date date = new Date(0);
		Tutor tutor = service.createTutor("name", "email", "password");
		Student student = service.createStudent("name", "email", "password");
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
	public void testCreateCourse() { // Test create and getters
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
	public void testCreateCourseWithNullName() { // Test condition checks
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

	@Test
	public void testSetCourseNewInstitution() { // Test setters
		assertEquals(0, service.getAllCourses().size());
		String name = "MATH 263";
		Institution institution = service.createInstitution("McGill University", SchoolLevel.University);
		Institution newInstitution = service.createInstitution("CEGEP Bois-de-Boulogne", SchoolLevel.CEGEP);
		String subjectName = "Mathematics";
		try {
			Course c = service.createCourse(name, institution, subjectName);
			c.setInstitution(newInstitution);
			courseRepository.save(c);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Course> allCourses = service.getAllCourses();
		assertEquals(newInstitution.getInstitutionName(), allCourses.get(0).getInstitution().getInstitutionName());
	}

	// Session test

	@Test
	public void testCreateAndAcceptSession() { // Test create and getters
		assertEquals(0, service.getAllRequests().size());
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = service.createTutor("name", "email", "password");
		Student student = service.createStudent("name", "email", "password");
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
	public void testCreateRoom() { // Test create and getters
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
	public void testCreateNullRoom() { // Test condition checks
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

	@Test
	public void testSetRoomNewCapacity() { // Test setters
		Integer roomNumber = 12;
		Integer capacity = 30;
		Integer newCapacity = 60;
		try {
			Room r = service.createRoom(roomNumber, capacity);
			r.setCapacity(newCapacity);
			roomRepository.save(r);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Room> allRooms = service.getAllRooms();
		assertEquals(newCapacity, allRooms.get(0).getCapacity());
	}

	// Notification class tests

	@Test
	public void testCreateNotification() { // Test create and getters
		assertEquals(0, service.getAllNotifications().size());
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = service.createTutor("name", "email", "password");
		Student student = service.createStudent("name", "email", "password");
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
	public void testCreateNullNotification() { // Test condition checks
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
	public void testCreateReview() { // Test create and getters
		assertEquals(0, service.getAllReviews().size());
		Integer rating = 5;
		String comment = "This is a comment.";
		Person from = service.createTutor("name", "email", "password");
		Person to = service.createStudent("name", "email", "password");
		try {
			service.createReview(rating, comment, from, to);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Review> allReviews = service.getAllReviews();
		assertEquals(rating, allReviews.get(0).getRating());
		assertEquals(comment, allReviews.get(0).getComment());
		assertEquals(from.getUserId(), allReviews.get(0).getFrom().getUserId());
		assertEquals(to.getUserId(), allReviews.get(0).getTo().getUserId());
	}

	@Test
	public void testCreateNullReview() { // Test condition checks
		assertEquals(0, service.getAllReviews().size());
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

		List<Review> allReviews = service.getAllReviews();
		assertEquals("Rating cannot be null!", error);
		assertEquals(0, allReviews.size());
	}

	@Test
	public void testSetReviewNewComment() { // Test setters
		assertEquals(0, service.getAllReviews().size());
		Integer rating = 5;
		String comment = "This is a comment.";
		String newComment = "This is a new comment";
		Person from = service.createTutor("name", "email", "password");
		Person to = service.createStudent("name", "email", "password");
		try {
			Review r = service.createReview(rating, comment, from, to);
			r.setComment(newComment);
			reviewRepository.save(r);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Review> allReviews = service.getAllReviews();
		assertEquals(newComment, allReviews.get(0).getComment());
	}

	// Application class tests

	@Test
	public void testCreateApplication() { // Test create and getters
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
	public void testCreateApplicationWithNullName() { // Test condition checks
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

	@Test
	public void testSetApplicationNewName() { // Test setters
		Application a = null;
		Boolean isExistingUser = true;
		String name = "Martin";
		String newName = "George";
		String email = "martin@mail.mcgill.ca";
		String course = "ECSE 321";
		try {
			a = service.createApplication(isExistingUser, name, email, course);
			a.setName(newName);
			applicationRepository.save(a);
		} catch (IllegalArgumentException e) {
			fail();
		}

		Application application = service.getApplication(email);
		assertEquals(newName, application.getName());
	}

	// Institution class tests

	@Test
	public void testCreateInstitution() { // Test create and getters
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
	public void testCreateNullInstitution() { // Test condition checks
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

	@Test
	public void testSetInstitutionNewName() { // Test setters
		assertEquals(0, service.getAllInstitutions().size());
		boolean pass = false;
		String institutionName = "McGill University";
		String newInstitutionName = "Concordia University";
		SchoolLevel institutionLevel = SchoolLevel.University;
		try {
			Institution i = service.createInstitution(institutionName, institutionLevel);
			i.setInstitutionName(newInstitutionName);
			institutionRepository.save(i);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Institution> allInstitutions = service.getAllInstitutions();
		for (Institution i : allInstitutions) {
			if (i.getInstitutionName().equals(newInstitutionName))
				pass = true;
		}
		assertEquals(true, pass);
	}

	// Wage class tests

	@Test
	public void testCreateWage() { // Test create and getters
		assertEquals(0, service.getAllWages().size());
		Tutor tutor = service.createTutor("name", "email", "password");
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
	public void testCreateNullWage() { // Test condition checks
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

	@Test
	public void testSetWageNewTutor() { // Test setters
		assertEquals(0, service.getAllWages().size());
		Tutor tutor = service.createTutor("name1", "email1", "password1");
		Tutor newTutor = service.createTutor("name2", "email2", "password2");
		Course course = service.createCourse("test",
				service.createInstitution("institutionName", SchoolLevel.University), "subject");
		Integer wage = 20;
		try {
			Wage w = service.createWage(tutor, course, wage);
			w.setTutor(newTutor);
			wageRepository.save(w);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Wage> allWages = service.getAllWages();
		assertEquals(newTutor.getUserId(), allWages.get(0).getTutor().getUserId());
	}

	// TimeSlot class tests

	@Test
	public void testCreateTimeSlot() { // Test create and getters
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = service.createTutor("name", "email", "password");
		try {
			service.createTimeSlot(tutor, date, time);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<TimeSlot> t = service.getTimeSlot(date, time);
		assertEquals(tutor.getUserId(), t.get(0).getTutor().getUserId());
	}

	@Test
	public void testCreateNullTimeSlot() { // Test condition checks
		String error = null;
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = null;
		try {
			service.createTimeSlot(tutor, date, time);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("A tutor needs to be specified!", error);
		assertEquals(0, service.getAllWages().size());
	}

	@Test
	public void testSetTimeSlotNewTutor() { // Test setters
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = service.createTutor("name1", "email1", "password1");
		Tutor newTutor = service.createTutor("name2", "email2", "password2");
		try {
			TimeSlot t = service.createTimeSlot(tutor, date, time);
			t.setTutor(newTutor);
			timeslotRepository.save(t);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<TimeSlot> t = service.getTimeSlot(date, time);
		assertEquals(newTutor.getUserId(), t.get(0).getTutor().getUserId());
	}
}
