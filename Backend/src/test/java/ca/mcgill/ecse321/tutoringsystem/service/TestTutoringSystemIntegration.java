package ca.mcgill.ecse321.tutoringsystem.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.Test;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import ca.mcgill.ecse321.tutoringsystem.dao.ApplicationRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.CourseRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.InstitutionRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.ManagerRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.NotificationRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RequestRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.RoomRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.StudentRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.TimeSlotRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.TutorRepository;
import ca.mcgill.ecse321.tutoringsystem.dao.WageRepository;
import ca.mcgill.ecse321.tutoringsystem.model.*;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTutoringSystemIntegration {

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
		assertEquals(user, null);
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
		Room room = roomService.createRoom(2, 20);
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
	public void testRejectRequest() {
		assertEquals(0, tutorService.getAllTutors().size());
		assertEquals(0, studentService.getAllStudents().size());
		Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
		Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
		Time time = Time.valueOf("08:00:01");
		Date date = Date.valueOf("2019-09-22");
		Room room = roomService.createRoom(2, 20);
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
		Set<TimeSlot> timeSlots = new HashSet<TimeSlot>();
		TimeSlot t = timeSlotService.createTimeSlot(tutor, Date.valueOf("2019-09-22"), Time.valueOf("08:00:01"));
		timeSlots.add(t);
		Set<Wage> wages = new HashSet<Wage>();
		tutor.setWage(wages);
		Wage w = wageService.createWage(tutor,
				courseService.createCourse("test",
						institutionService.createInstitution("institutionName", SchoolLevel.University), "subject"),
				40);
		wages.add(w);
		try {
			tutorService.changeTutorSettings(tutor.getUserId(), newName, newPassword, timeSlots, wages);
		} catch (IllegalArgumentException e) {
			fail();
		}
		List<Wage> wagesList = wageService.getTutorWages(tutor);	  
		
		assertEquals(tutorService.getAllTutors().get(0).getName(), newName);
	    assertEquals(tutorService.getAllTutors().get(0).getPassword(), newPassword);
	    assertEquals(1, wagesList.size());

	}
}
