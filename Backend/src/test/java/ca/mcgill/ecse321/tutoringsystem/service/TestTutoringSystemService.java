package ca.mcgill.ecse321.tutoringsystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.tutoringsystem.dao.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;

@RunWith(MockitoJUnitRunner.class)
public class TestTutoringSystemService {

	@InjectMocks
	TutoringSystemService service;

	@Mock
	private TutorRepository tutorDao;
	private Tutor tutor;
	
	@Mock
	private StudentRepository studentDao;
	private Student student;
	private Student student1;
	
	@Mock
	private ManagerRepository managerDao;
	private Manager manager;
	
	@Mock
	private RequestRepository requestDao;
	private Request request;
	private Request request1;
	
	@Mock
	private CourseRepository courseDao;
	private Course course;
	
	@Mock
	private RoomRepository roomDao;
	private Room room;
	
	@Mock
	private NotificationRepository notificationDao;
	private Notification notification;
	
	@Mock
	private ReviewRepository reviewDao;
	private Review review;
	
	@Mock
	private ApplicationRepository applicationDao;
	private Application application;
	
	@Mock
	private InstitutionRepository institutionDao;
	private Institution institution;
	
	@Mock
	private WageRepository wageDao;
	private Wage wage;
	
	@Mock
	private TimeSlotRepository timeslotDao;
	private TimeSlot timeslot;

	private static final String EXISTING = "ExistingThing";
	private static final String NONEXISTING = "NotExistingThing";
	@Before
	public void setMockOutput() {
		when(tutorDao.findTutorByUserId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(EXISTING)) {
				Tutor tutor = new Tutor();
				tutor.setName(EXISTING);
				return tutor;
			} else {
				return null;
			}
		});
		// Whenever anything is saved, just return the parameter object
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		when(tutorDao.save(any(Tutor.class))).thenAnswer(returnParameterAsAnswer);
		when(studentDao.save(any(Student.class))).thenAnswer(returnParameterAsAnswer);
		when(managerDao.save(any(Manager.class))).thenAnswer(returnParameterAsAnswer);
		when(requestDao.save(any(Request.class))).thenAnswer(returnParameterAsAnswer);
		when(courseDao.save(any(Course.class))).thenAnswer(returnParameterAsAnswer);
		when(roomDao.save(any(Room.class))).thenAnswer(returnParameterAsAnswer);
		when(notificationDao.save(any(Notification.class))).thenAnswer(returnParameterAsAnswer);
		when(reviewDao.save(any(Review.class))).thenAnswer(returnParameterAsAnswer);
		when(applicationDao.save(any(Application.class))).thenAnswer(returnParameterAsAnswer);
		when(institutionDao.save(any(Institution.class))).thenAnswer(returnParameterAsAnswer);
		when(wageDao.save(any(Wage.class))).thenAnswer(returnParameterAsAnswer);
		when(timeslotDao.save(any(TimeSlot.class))).thenAnswer(returnParameterAsAnswer);
	}
	
	@After
	public void clearDatabase() {
		requestDao.deleteAll();
		tutorDao.deleteAll();
		managerDao.deleteAll();
		studentDao.deleteAll();
		timeslotDao.deleteAll();
		wageDao.deleteAll();
		institutionDao.deleteAll();
		applicationDao.deleteAll();
		reviewDao.deleteAll();
		notificationDao.deleteAll();
		roomDao.deleteAll();
		courseDao.deleteAll();
	}
	// Tutor class tests

	@Test
	public void testCreateTutor() { // Test create and getters
		assertEquals(0, service.getAllTutors().size());
		String name = "Martin";
		String email = "martin@mail.mcgill.ca";
		String password = "password";
		try {
			tutor = service.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(name, tutor.getName());
		assertEquals(email, tutor.getEmail());
	}

	@Test
	public void testCreateNullTutor() { // Test condition checks
		assertEquals(0, service.getAllTutors().size());
		String error = null;
		String name = null;
		String email = null;
		String password = "password";
		try {
			tutor = service.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Tutor name, email or password cannot be empty!", error);
		assertNull(tutor);
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
			tutor = service.createTutor(name, email, password);
			tutor.setName(newName);
			tutor.setEmail(newEmail);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(newName, tutor.getName());
		assertEquals(newEmail, tutor.getEmail());
	}

	@Test
	public void testGetTutor() { // Test getters
		assertEquals(0, service.getAllTutors().size());
		String name = "Martin";
		String email = "martin@mail.mcgill.ca";
		String password = "password";
		try {
			tutor = service.createTutor(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(name, tutor.getName());
		assertEquals(email, tutor.getEmail());
	}

	@Test
	public void testGetTutorRequests() { // Test getters
		assertEquals(0, service.getAllTutors().size());
		String tutorName = "Martin";
		String tutorEmail = "martin@mail.mcgill.ca";
		String tutorPassword = "password";
		tutor = service.createTutor(tutorName, tutorEmail, tutorPassword);
		student = service.createStudent("name1", "email11", "password11");
		student1 = service.createStudent("student1", "email1", "password1");
		course = service.createCourse("test", service.createInstitution("institutionName", SchoolLevel.University), "subject");
		request = service.createRequest(Time.valueOf("12:12:12"), Date.valueOf("2019-02-22"), tutor, student, course);
		request1 = service.createRequest(Time.valueOf("10:21:21"), Date.valueOf("2019-01-22"), tutor, student1, course);
		Set<Request> tutorRequests = new HashSet<Request>();
		tutorRequests.add(request);
		tutorRequests.add(request1);
		tutor.setRequest(tutorRequests);
		tutorRequests = null;
		try {
			tutorRequests = tutor.getRequest();
		} catch (IllegalArgumentException e) {
			fail();
		}
		assertTrue(tutorRequests.contains(request));
		assertTrue(tutorRequests.contains(request1));
	}

	@Test
	public void testGetAcceptedTutorRequests() { // Test getters
		assertEquals(0, service.getAllTutors().size());
		String tutorName = "Martin";
		String tutorEmail = "martin@mail.mcgill.ca";
		String tutorPassword = "password";
		tutor = service.createTutor(tutorName, tutorEmail, tutorPassword);
		student = service.createStudent("name1", "email11", "password11");
		student1 = service.createStudent("student1", "email1", "password1");
		course = service.createCourse("test", service.createInstitution("institutionName", SchoolLevel.University), "subject");
		service.createRoom(1, 2);
		request = service.createRequest(Time.valueOf("12:12:12"), Date.valueOf("2019-02-22"), tutor, student, course);
		request1 = service.createRequest(Time.valueOf("10:21:21"), Date.valueOf("2019-01-22"), tutor, student1, course);
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
			student = service.createStudent(name, email, password);
		} catch (IllegalArgumentException e) {
			fail();
		}

		assertEquals(name, student.getName());
		assertEquals(email, student.getEmail());
	}

	@Test
	public void testCreateNullStudent() { // Test condition checks
		assertEquals(0, service.getAllStudents().size());
		String error = null;
		String name = null;
		String email = null;
		String password = "password";
		try {
			student = service.createStudent(name, email, password);
		} catch (IllegalArgumentException e) {
			error = e.getMessage();
		}

		assertEquals("Student name, email or password cannot be empty!", error);
		assertNull(student);
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
			s.setEmail(newEmail); studentDao.save(s); } catch (IllegalArgumentException e) { fail(); } List<Student> allStudents = service.getAllStudents(); assertEquals(newName, allStudents.get(0).getName()); assertEquals(newEmail, allStudents.get(0).getEmail()); }
	
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
		} catch (IllegalArgumentException e) {
			fail();
		}

		List<TimeSlot> t = service.getTimeSlot(date, time);
		assertEquals(newTutor.getUserId(), t.get(0).getTutor().getUserId());
	}
	
	/* 
	 * DAO Mock
	 */
	
	
}
