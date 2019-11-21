package ca.mcgill.ecse321.tutoringsystem.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ca.mcgill.ecse321.tutoringsystem.dao.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTutoringSystemCRUD {

	@Autowired
	ApplicationService applicationService;
	@Autowired
	CourseService courseService;
	@Autowired
	InstitutionService institutionService;
	@Autowired
	ManagerService managerService;
	@Autowired
	NotificationService notificationService;
	@Autowired
	RequestService requestService;
	@Autowired
	ReviewService reviewService;
	@Autowired
	RoomService roomService;
	@Autowired
	StudentService studentService;
	@Autowired
	TimeSlotService timeSlotService;
	@Autowired
	TutorService tutorService;
	@Autowired
	WageService wageService;
	@Autowired
	PersonService personService;

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
	public void aclearDb() {
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
		assertTrue(true);
	}
	
	@Test
	public void testCreateTutor() { // Test create and getters
		String name = "Martin";
		String email = "ecse321test+tutor@protonmail.com";
		String password = "password";
		try {
			tutorService.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Tutor> allTutors = tutorService.getAllTutors();
		assertEquals(name, allTutors.get(0).getName());
		assertEquals(email, allTutors.get(0).getEmail());
	}

	@Test
	public void testCreateNullTutor() { // Test condition checks
		assertEquals(0, tutorService.getAllTutors().size());
		String error = null;
		String name = null;
		String email = null;
		String password = "password";
		try {
			tutorService.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor name, email or password cannot be empty!", error);
		assertEquals(0, tutorService.getAllTutors().size());
	}

	@Test
	public void testSetTutorNewName() { // Test setters
		assertEquals(0, tutorService.getAllTutors().size());
		String name = "Martin";
		String email = "ecse321test+tutor@protonmail.com";
		String password = "password";
		String newName = "George";
		String newEmail = "ecse321test+tutor1@protonmail.com";
		try {
			Tutor t = tutorService.createTutor(name, email, password);
			t.setName(newName);
			t.setEmail(newEmail);
			tutorRepository.save(t);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Tutor> allTutors = tutorService.getAllTutors();
		assertEquals(newName, allTutors.get(0).getName());
		assertEquals(newEmail, allTutors.get(0).getEmail());
	}

	@Test
	public void testGetTutor() { // Test getters
		assertEquals(0, tutorService.getAllTutors().size());
		Tutor t = null;
		String name = "Martin";
		String email = "ecse321test+tutor@protonmail.com";
		String password = "password";
		try {
			t = tutorService.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(t.getUserId(), tutorService.getTutor(email).getUserId());
	}

	@Test
	public void testGetTutorRequests() { // Test getters
		assertEquals(0, tutorService.getAllTutors().size());
		String tutorName = "Martin";
		String tutorEmail = "ecse321test+tutor@protonmail.com";
		String tutorPassword = "password";
		Tutor tutor = tutorService.createTutor(tutorName, tutorEmail, tutorPassword);
		Student student = studentService.createStudent("name1", "ecse321test+student@protonmail.com", "password11");
		Student student1 = studentService.createStudent("student1", "ecse321test+student1@protonmail.com", "password1");
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request1 = requestService.createRequest(Time.valueOf("12:12:12"), Date.valueOf("2019-02-22"), tutor,
				student, course);
		Request request2 = requestService.createRequest(Time.valueOf("10:21:21"), Date.valueOf("2019-01-22"), tutor,
				student1, course);
		List<Request> tutorRequests = null;
		try {
			tutorRequests = requestService.getTutorRequests(tutor);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(request1.getRequestId(), tutorRequests.get(0).getRequestId());
		assertEquals(request2.getRequestId(), tutorRequests.get(1).getRequestId());
	}

	@Test
	public void testGetAcceptedTutorRequests() { // Test getters
		assertEquals(0, tutorService.getAllTutors().size());
		String tutorName = "Martin";
		String tutorEmail = "martin@mail.mcgill.ca";
		String tutorPassword = "password";
		Tutor tutor = tutorService.createTutor(tutorName, tutorEmail, tutorPassword);
		Student student = studentService.createStudent("name1", "ecse321test+student@protonmail.com", "password11");
		Student student1 = studentService.createStudent("student1", "ecse321test+student1@protonmail.com", "password1");
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		roomService.createRoom(1, 2);
		Request request1 = requestService.createRequest(Time.valueOf("12:12:12"), Date.valueOf("2019-02-22"), tutor,
				student, course);
		requestService.createRequest(Time.valueOf("10:21:21"), Date.valueOf("2019-01-22"), tutor, student1, course);
		try {
			requestService.acceptRequest(request1.getRequestId());
		} catch (IOException e1) {
		}
		List<Request> tutorRequests = null;
		try {
			tutorRequests = requestService.getAcceptedTutorRequests(tutor);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(1, tutorRequests.size());
		assertEquals(request1.getRequestId(), tutorRequests.get(0).getRequestId());
	}

	@Test
	public void testDeleteTutor() {
		assertEquals(0, tutorService.getAllTutors().size());
		String name = "Martin";
		String email = "ecse321test+tutor@protonmail.com";
		String password = "password";
		Tutor tutor = tutorService.createTutor(name, email, password);
		try {
			tutorService.deleteTutor(tutor.getUserId());
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(0, tutorService.getAllTutors().size());
	}

	@Test
	public void testDeleteNonExistingTutor() {
		assertEquals(0, tutorService.getAllTutors().size());
		String error = null;
		try {
			tutorService.deleteTutor(1);
		} catch (NullPointerException e) {
			error = e.getMessage();
		}

		assertEquals("No Tutor by this id.", error);
		assertEquals(0, tutorService.getAllTutors().size());
	}

	// Student class tests

	@Test
	public void testCreateStudent() { // Test create and getters
		assertEquals(0, studentService.getAllStudents().size());
		String name = "Jason";
		String email = "ecse321test+student@protonmail.com";
		String password = "password";
		try {
			studentService.createStudent(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = studentService.getAllStudents();
		assertEquals(name, allStudents.get(0).getName());
		assertEquals(email, allStudents.get(0).getEmail());
	}

	@Test
	public void testCreateNullStudent() { // Test condition checks
		assertEquals(0, studentService.getAllStudents().size());
		String error = null;
		String name = null;
		String email = null;
		String password = "password";
		try {
			studentService.createStudent(name, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Student name, email or password cannot be empty!", error);
		assertEquals(0, studentService.getAllStudents().size());
	}

	@Test
	public void testSetStudentNewName() { // Test setters
		assertEquals(0, tutorService.getAllTutors().size());
		String name = "Jason";
		String email = "ecse321test+student@protonmail.com";
		String password = "password";
		String newName = "George";
		String newEmail = "ecse321test+student1@protonmail.com";
		try {
			Student s = studentService.createStudent(name, email, password);
			s.setName(newName);
			s.setEmail(newEmail);
			studentRepository.save(s);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Student> allStudents = studentService.getAllStudents();
		assertEquals(newName, allStudents.get(0).getName());
		assertEquals(newEmail, allStudents.get(0).getEmail());
	}

	// Manager class tests

	@Test
	public void testCreateManager() { // Test create and getters
		String name = "Marwan";
		String email = "ecse321test+manager@protonmail.com";
		String password = "password";
		try {
			managerService.createManager(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		Manager manager = managerService.getManager(email);
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
			managerService.createManager(name, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Manager name, email or password cannot be empty!", error);
	}

	@Test
	public void testSetManagerNewName() { // Test setters
		assertEquals(0, tutorService.getAllTutors().size());
		String name = "Marwan";
		String email = "ecse321test+manager@protonmail.com";
		String password = "password";
		String newName = "Daniel";
		String newEmail = "ecse321test+manager1@protonmail.com";
		try {
			Manager m = managerService.createManager(name, email, password);
			m.setName(newName);
			m.setEmail(newEmail);
			managerRepository.save(m);
		} catch (IllegalArgumentException e) {
			fail();
		}

		Manager manager = managerService.getManager(newEmail);
		assertEquals(newName, manager.getName());
		assertEquals(newEmail, manager.getEmail());
	}

	// Request class tests

	@Test
	public void testCreateRequest() { // Test create and getters
		assertEquals(0, requestService.getAllRequests().size());
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		try {
			requestService.createRequest(time, date, tutor, student, course);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Request> allRequests = requestService.getAllRequests();
		assertEquals(time, allRequests.get(0).getTime());
		assertEquals(date, allRequests.get(0).getDate());
		assertEquals(tutor.getUserId(), allRequests.get(0).getTutor().getUserId());
		assertEquals(student.getUserId(), allRequests.get(0).getStudent().getUserId());
		assertEquals(course.getCourseName(), allRequests.get(0).getCourse().getCourseName());
	}

	@Test
	public void testCreateRequestWithAllNull() { // Test condition checks
		assertEquals(0, requestService.getAllRequests().size());
		String error = null;
		try {
			requestService.createRequest(null, null, null, null, null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals(
				"Time cannot be empty! Date cannot be empty! Tutor cannot be empty! Student cannot be empty! Course cannot be empty!",
				error);
		assertEquals(0, requestService.getAllRequests().size());
	}

	// Course class tests

	@Test
	public void testCreateCourse() { // Test create and getters
		assertEquals(0, courseService.getAllCourses().size());
		String name = "MATH 263";
		Institution institution = institutionService.createInstitution("McGill University", SchoolLevel.University);
		String subjectName = "Mathematics";
		try {
			courseService.createCourse(name, institution, subjectName);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Course> allCourses = courseService.getAllCourses();
		assertEquals(name, allCourses.get(0).getCourseName());
		assertEquals(institution.getInstitutionName(), allCourses.get(0).getInstitution().getInstitutionName());
		assertEquals(subjectName, allCourses.get(0).getSubjectName());
	}

	@Test
	public void testCreateCourseWithNullName() { // Test condition checks
		assertEquals(0, courseService.getAllCourses().size());
		String error = null;
		String name = null;
		Institution institution = institutionService.createInstitution("McGill University", SchoolLevel.University);
		String subjectName = "Mathematics";
		try {
			courseService.createCourse(name, institution, subjectName);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Course name cannot be empty!", error);
		assertEquals(0, courseService.getAllCourses().size());

	}

	@Test
	public void testSetCourseNewInstitution() { // Test setters
		assertEquals(0, courseService.getAllCourses().size());
		String name = "MATH 263";
		Institution institution = institutionService.createInstitution("McGill University", SchoolLevel.University);
		Institution newInstitution = institutionService.createInstitution("CEGEP Bois-de-Boulogne", SchoolLevel.CEGEP);
		String subjectName = "Mathematics";
		try {
			Course c = courseService.createCourse(name, institution, subjectName);
			c.setInstitution(newInstitution);
			courseRepository.save(c);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Course> allCourses = courseService.getAllCourses();
		assertEquals(newInstitution.getInstitutionName(), allCourses.get(0).getInstitution().getInstitutionName());
	}

	// Session test

	@Test
	public void testCreateAndAcceptSession() { // Test create and getters
		assertEquals(0, requestService.getAllRequests().size());
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request = requestService.createRequest(time, date, tutor, student, course);
		Room room = roomService.createRoom(1, 2);
		try {
			requestService.acceptRequest(request.getRequestId());
		} catch (IllegalArgumentException | IOException e) {
			fail();
		}
		List<Request> allRequests = requestService.getAllRequests();
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
		assertEquals(0, roomService.getAllRooms().size());
		Integer roomNumber = 12;
		Integer capacity = 30;
		try {
			roomService.createRoom(roomNumber, capacity);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Room> allRooms = roomService.getAllRooms();
		assertEquals(roomNumber, allRooms.get(0).getRoomNumber());
		assertEquals(capacity, allRooms.get(0).getCapacity());
	}

	@Test
	public void testCreateNullRoom() { // Test condition checks
		assertEquals(0, roomService.getAllRooms().size());
		String error = null;
		Integer roomNumber = null;
		Integer capacity = 30;
		try {
			roomService.createRoom(roomNumber, capacity);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Room number cannot be empty!", error);
		assertEquals(0, roomService.getAllRooms().size());
	}

	@Test
	public void testSetRoomNewCapacity() { // Test setters
		Integer roomNumber = 12;
		Integer capacity = 30;
		Integer newCapacity = 60;
		try {
			Room r = roomService.createRoom(roomNumber, capacity);
			r.setCapacity(newCapacity);
			roomRepository.save(r);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Room> allRooms = roomService.getAllRooms();
		assertEquals(newCapacity, allRooms.get(0).getCapacity());
	}

	// Notification class tests

	@Test
	public void testCreateNotification() { // Test create and getters
		assertEquals(0, notificationService.getAllNotifications().size());
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request = requestService.createRequest(time, date, tutor, student, course);
		try {
			notificationService.createNotification(request, NotificationType.Requested);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Notification> allNotifications = notificationService.getAllNotifications();
		assertEquals(request.getRequestId(), allNotifications.get(0).getRequest().getRequestId());
	}

	@Test
	public void testCreateNullNotification() { // Test condition checks
		assertEquals(0, notificationService.getAllNotifications().size());
		String error = null;
		Request request = null;
		try {
			notificationService.createNotification(request, NotificationType.Requested);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Request cannot be null!", error);
		assertEquals(0, notificationService.getAllNotifications().size());
	}

	// Review class tests

	@Test
	public void testCreateReview() { // Test create and getters
		assertEquals(0, reviewService.getAllReviews().size());
		Integer rating = 5;
		String comment = "This is a comment.";
		Person from = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Person to = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		try {
			reviewService.createReview(rating, comment, from, to);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Review> allReviews = reviewService.getAllReviews();
		assertEquals(rating, allReviews.get(0).getRating());
		assertEquals(comment, allReviews.get(0).getComment());
		assertEquals(from.getUserId(), allReviews.get(0).getFrom().getUserId());
		assertEquals(to.getUserId(), allReviews.get(0).getTo().getUserId());
	}

	@Test
	public void testCreateNullReview() { // Test condition checks
		assertEquals(0, reviewService.getAllReviews().size());
		String error = null;
		Integer rating = null;
		String comment = null;
		Person from = null;
		Person to = null;
		try {
			reviewService.createReview(rating, comment, from, to);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		List<Review> allReviews = reviewService.getAllReviews();
		assertEquals("Rating cannot be null!", error);
		assertEquals(0, allReviews.size());
	}

	@Test
	public void testSetReviewNewComment() { // Test setters
		assertEquals(0, reviewService.getAllReviews().size());
		Integer rating = 5;
		String comment = "This is a comment.";
		String newComment = "This is a new comment";
		Person from = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Person to = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		try {
			Review r = reviewService.createReview(rating, comment, from, to);
			r.setComment(newComment);
			reviewRepository.save(r);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Review> allReviews = reviewService.getAllReviews();
		assertEquals(newComment, allReviews.get(0).getComment());
	}

	// Application class tests

	@Test
	public void testCreateApplication() { // Test create and getters
		Boolean isExistingUser = true;
		String name = "Martin";
		String email = "ecse321test+applicant@protonmail.com";
		String course = "ECSE 321";
		try {
			applicationService.createApplication(isExistingUser, name, email, course);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Application> application = applicationService.getApplication(email);
		assertEquals(true, application.get(0).getIsExistingUser());
		assertEquals(name, application.get(0).getName());
		assertEquals(email, application.get(0).getEmail());
		assertEquals(course, application.get(0).getCourses());
	}

	@Test
	public void testCreateApplicationWithNullName() { // Test condition checks
		String error = null;
		Boolean isExistingUser = true;
		String name = null;
		String email = "ecse321test+applicant@protonmail.com";
		String course = "ECSE 321";
		try {
			applicationService.createApplication(isExistingUser, name, email, course);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Applicant name cannot be empty!", error);
	}

	@Test
	public void testSetApplicationNewName() { // Test setters
		Application a = null;
		Boolean isExistingUser = true;
		String name = "Martin";
		String newName = "George";
		String email = "ecse321test+applicant@protonmail.com";
		String course = "ECSE 321";
		try {
			a = applicationService.createApplication(isExistingUser, name, email, course);
			a.setName(newName);
			applicationRepository.save(a);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Application> application = applicationService.getApplication(email);
		assertEquals(newName, application.get(0).getName());
	}

	// Institution class tests

	@Test
	public void testCreateInstitution() { // Test create and getters
		assertEquals(0, institutionService.getAllInstitutions().size());
		String institutionName = "McGill University";
		SchoolLevel institutionLevel = SchoolLevel.University;
		try {
			institutionService.createInstitution(institutionName, institutionLevel);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Institution> allInstitutions = institutionService.getAllInstitutions();
		assertEquals(institutionName, allInstitutions.get(0).getInstitutionName());
		assertEquals(institutionLevel, allInstitutions.get(0).getInstitutionLevel());
	}

	@Test
	public void testCreateNullInstitution() { // Test condition checks
		assertEquals(0, institutionService.getAllInstitutions().size());
		String error = null;
		String institutionName = null;
		SchoolLevel institutionLevel = SchoolLevel.University;
		try {
			institutionService.createInstitution(institutionName, institutionLevel);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Institution name cannot be null!", error);
		assertEquals(0, institutionService.getAllInstitutions().size());

	}

	@Test
	public void testSetInstitutionNewName() { // Test setters
		assertEquals(0, institutionService.getAllInstitutions().size());
		boolean pass = false;
		String institutionName = "McGill University";
		String newInstitutionName = "Concordia University";
		SchoolLevel institutionLevel = SchoolLevel.University;
		try {
			Institution i = institutionService.createInstitution(institutionName, institutionLevel);
			i.setInstitutionName(newInstitutionName);
			institutionRepository.save(i);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Institution> allInstitutions = institutionService.getAllInstitutions();
		for (Institution i : allInstitutions) {
			if (i.getInstitutionName().equals(newInstitutionName))
				pass = true;
		}
		assertEquals(true, pass);
	}

	// Wage class tests

	@Test
	public void testCreateWage() { // Test create and getters
		assertEquals(0, wageService.getAllWages().size());
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Integer wage = 20;
		try {
			wageService.createWage(tutor, course, wage);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Wage> allWages = wageService.getAllWages();
		assertEquals(tutor.getUserId(), allWages.get(0).getTutor().getUserId());
		assertEquals(course.getCourseName(), allWages.get(0).getCourse().getCourseName());
		assertEquals(wage, allWages.get(0).getWage());
	}

	@Test
	public void testCreateNullWage() { // Test condition checks
		assertEquals(0, wageService.getAllWages().size());
		String error = null;
		Tutor tutor = null;
		Course course = null;
		Integer wage = 20;
		try {
			wageService.createWage(tutor, course, wage);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("A tutor needs to be specified!", error);
		assertEquals(0, wageService.getAllWages().size());
	}

	@Test
	public void testSetWageNewTutor() { // Test setters
		assertEquals(0, wageService.getAllWages().size());
		Tutor tutor = tutorService.createTutor("name1", "ecse321test+tutor@protonmail.com", "password1");
		Tutor newTutor = tutorService.createTutor("name2", "ecse321test+tutor1@protonmail.com", "password2");
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Integer wage = 20;
		try {
			Wage w = wageService.createWage(tutor, course, wage);
			w.setTutor(newTutor);
			wageRepository.save(w);
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<Wage> allWages = wageService.getAllWages();
		assertEquals(newTutor.getUserId(), allWages.get(0).getTutor().getUserId());
	}

	// TimeSlot class tests

	@Test
	public void testCreateTimeSlot() { // Test create and getters
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		try {
			timeSlotService.createTimeSlot(tutor, date, time);
		} catch (IllegalArgumentException e) {
			fail();
		}

		TimeSlot t = timeSlotService.getTimeSlot(date, time);
		assertEquals(tutor.getUserId(), t.getTutor().getUserId());
	}

	@Test
	public void testCreateNullTimeSlot() { // Test condition checks
		String error = null;
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = null;
		try {
			timeSlotService.createTimeSlot(tutor, date, time);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("A tutor needs to be specified!", error);
		assertEquals(0, wageService.getAllWages().size());
	}

	@Test
	public void testSetTimeSlotNewTutor() { // Test setters
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Tutor tutor = tutorService.createTutor("name1", "ecse321test+tutor@protonmail.com", "password1");
		Tutor newTutor = tutorService.createTutor("name2", "ecse321test+tutor1@protonmail.com", "password2");
		try {
			TimeSlot t = timeSlotService.createTimeSlot(tutor, date, time);
			t.setTutor(newTutor);
			timeslotRepository.save(t);
		} catch (IllegalArgumentException e) {
			fail();
		}

		TimeSlot t = timeSlotService.getTimeSlot(date, time);
		assertEquals(newTutor.getUserId(), t.getTutor().getUserId());
	}

	@Test
	public void testLogin() {
		assertEquals(0, tutorService.getAllTutors().size());
		Person user = null;
		Tutor tutor = null;
		String name = "Martin";
		String email = "ecse321test+tutor@protonmail.com";
		String password = "password";
		try {
			tutor = tutorService.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			user = personService.login(email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertThat(tutor, instanceOf(user.getClass()));
		assertEquals(tutor.getName(), (user.getName()));
		assertEquals(tutor.getEmail(), (user.getEmail()));
		assertEquals(tutor.getPassword(), (user.getPassword()));

	}

	@Test
	public void testGetPersonById() {
		assertEquals(0, tutorService.getAllTutors().size());
		Person user = null;
		Tutor tutor = null;
		String name = "Martin";
		String email = "ecse321test+tutor@protonmail.com";
		String password = "password";
		try {
			tutor = tutorService.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			user = personService.getPersonById(tutor.getUserId());
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertThat(tutor, instanceOf(user.getClass()));
		assertEquals(tutor.getName(), (user.getName()));
		assertEquals(tutor.getEmail(), (user.getEmail()));
		assertEquals(tutor.getPassword(), (user.getPassword()));
	}
	
	@Test
	public void testGetPersonByIdWithNullId() {
		String error = "";
		assertEquals(0, tutorService.getAllTutors().size());
		try {
			personService.getPersonById(null);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Id cannot be empty!", error);
	}

	@Test
	public void testLoginWithWrongPassword() {
		assertEquals(0, tutorService.getAllTutors().size());
		Person user = null;
		String name = "Martin";
		String email = "ecse321test+tutor@protonmail.com";
		String password = "password";
		String wrongPassword = "wrongPassword";
		try {
			tutorService.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			user = personService.login(email, wrongPassword);
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertNull(user);
	}
	
	@Test
	public void testLoginWithEmptyPassword() {
		String error = "";
		assertEquals(0, tutorService.getAllTutors().size());
		try {
			personService.login("ecse321test@protonmail.com", "");
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Password cannot be empty!", error);
	}

	@Test
	public void testLoginWithNullEmail() {
		assertEquals(0, tutorService.getAllTutors().size());
		Person user = null;
		String name = "Martin";
		String email = "ecse321test+tutor@protonmail.com";
		String password = "password";
		String error = "";
		try {
			tutorService.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		try {
			user = personService.login(null, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}
		assertEquals("Email cannot be empty!", error);
		assertNull(user);
	}

	@Test
	public void testAcceptRequest() {
		assertEquals(0, tutorService.getAllTutors().size());
		assertEquals(0, studentService.getAllStudents().size());
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		roomService.createRoom(12, 2);
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request = requestService.createRequest(time, date, tutor, student, course);
		try {
			requestService.acceptRequest(request.getRequestId());
		} catch (IOException e) {
			fail();
		}

		assertEquals(request.getRequestId(), requestService.getAcceptedTutorRequests(tutor).get(0).getRequestId());
	}

	@Test
	public void testAcceptRequestExistingRequests() { // test when there already are requests in the room
		assertEquals(0, tutorService.getAllTutors().size());
		assertEquals(0, studentService.getAllStudents().size());
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		roomService.createRoom(12, 2);
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request = requestService.createRequest(time, date, tutor, student, course);
		Request request2 = requestService.createRequest(Time.valueOf("06:00:00"), date,
				tutorService.createTutor("name", "ecse321test+tutor1@protonmail.com", "password"), student, course);
		try {
			requestService.acceptRequest(request2.getRequestId());
			requestService.acceptRequest(request.getRequestId());
		} catch (IOException e) {
			fail();
		}

		assertEquals(request.getRequestId(), requestService.getAcceptedTutorRequests(tutor).get(0).getRequestId());
	}
	
	@Test
	public void testAcceptButFull() { // accept but unable to assign a room
		assertEquals(0, tutorService.getAllTutors().size());
		assertEquals(0, studentService.getAllStudents().size());
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		roomService.createRoom(12, 2);
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request = requestService.createRequest(time, date, tutor, student, course);
		Request request2 = requestService.createRequest(time, date,
				tutorService.createTutor("name", "ecse321test+tutor1@protonmail.com", "password"), student, course);
		try {
			requestService.acceptRequest(request2.getRequestId());
			requestService.acceptRequest(request.getRequestId());
		} catch (IOException e) {
			fail();
		}

		assertNull(requestService.getRequest(request.getRequestId()));
	}

	@Test
	public void testRejectRequest() {
		assertEquals(0, tutorService.getAllTutors().size());
		assertEquals(0, studentService.getAllStudents().size());
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		roomService.createRoom(12, 2);
		Course course = courseService.createCourse("test",
				institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
		Request request = requestService.createRequest(time, date, tutor, student, course);

		requestService.rejectRequest(request.getRequestId());

		assertTrue(requestService.getAcceptedTutorRequests(tutor).isEmpty());
	}

	@Test
	public void testUpdateTutorInfo() {
		assertEquals(0, tutorService.getAllTutors().size());
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		String newName = "newName";
		String newPassword = "newPassword";
		try {
			tutor = tutorService.changeTutorSettings(tutor.getUserId(), newName, newPassword);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(tutorService.getAllTutors().get(0).getName(), newName);
		assertEquals(tutorService.getAllTutors().get(0).getPassword(), newPassword);

	}
}
