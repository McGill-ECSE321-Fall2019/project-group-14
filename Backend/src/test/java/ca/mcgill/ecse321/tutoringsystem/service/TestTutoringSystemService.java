package ca.mcgill.ecse321.tutoringsystem.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.sql.Time;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
  ApplicationService applicationService;
  @InjectMocks
  CourseService courseService;
  @InjectMocks
  InstitutionService institutionService;
  @InjectMocks
  ManagerService managerService;
  @InjectMocks
  NotificationService notificationService;
  @InjectMocks
  RequestService requestService;
  @InjectMocks
  ReviewService reviewService;
  @InjectMocks
  RoomService roomService;
  @InjectMocks
  StudentService studentService;
  @InjectMocks
  TimeSlotService timeSlotService;
  @InjectMocks
  TutorService tutorService;
  @InjectMocks
  WageService wageService;


  @Mock
  private TutorRepository tutorDao;
  private Tutor tutor;
  private Tutor tutor1;

  @Mock
  private StudentRepository studentDao;
  private Student student;

  @Mock
  private ManagerRepository managerDao;
  private Manager manager;

  @Mock
  private RequestRepository requestDao;
  private Request request;

  @Mock
  private CourseRepository courseDao;
  private Course course;

  @Mock
  private RoomRepository roomDao;
  private Room room;

  @Mock
  private NotificationRepository notificationDao;

  @Mock
  private ReviewRepository reviewDao;
  private Review review;

  @Mock
  private ApplicationRepository applicationDao;
  private Application application;

  @Mock
  private InstitutionRepository institutionDao;
  private Institution institution;
  private Institution institution1;

  @Mock
  private WageRepository wageDao;
  private Wage wage;

  @Mock
  private TimeSlotRepository timeslotDao;
  private TimeSlot timeslot;

  /*
   * Use EXISTING and NONEXISTING if the class you're testing for has String as its @Id, Otherwise use EXISTING_INT and
   * NONEXISTING_INT (e.g. for Tutor, @Id is Integer: use INTs)
   */
  private static final String EXISTING = "ExistingThing";
  private static final Integer EXISTING_INT = 1;
  private static final String NONEXISTING = "NotExistingThing";
  private static final Integer NONEXISTING_INT = 2;

  @Before
  public void setMockOutput() {
    when(tutorDao.findTutorByUserId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Tutor ro = new Tutor();
        ro.setName(EXISTING);
        return ro;
      } else {
        return null;
      }
    });
    when(studentDao.findStudentByUserId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Student ro = new Student();
        ro.setName(EXISTING);
        return ro;
      } else {
        return null;
      }
    });
    when(managerDao.findManagerByUserId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Manager ro = new Manager();
        ro.setName(EXISTING);
        return ro;
      } else {
        return null;
      }
    });
    when(requestDao.findRequestByRequestId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Request ro = new Request();
        ro.setRequestId(EXISTING_INT);
        return ro;
      } else {
        return null;
      }
    });
    when(courseDao.findCourseByCourseName((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING)) {
        Course ro = new Course();
        ro.setCourseName(EXISTING);
        return ro;
      } else {
        return null;
      }
    });
    when(roomDao.findRoomByRoomNumber((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Room ro = new Room();
        ro.setRoomNumber(EXISTING_INT);
        return ro;
      } else {
        return null;
      }
    });
    when(notificationDao.findNotificationByNotificationId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Notification ro = new Notification();
        ro.setNotificationId(EXISTING_INT);
        return ro;
      } else {
        return null;
      }
    });
    when(reviewDao.findReviewByReviewId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Review ro = new Review();
        ro.setReviewId(EXISTING_INT);
        return ro;
      } else {
        return null;
      }
    });
    when(applicationDao.findApplicationByApplicationId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Application ro = new Application();
        ro.setApplicationId(EXISTING_INT);
        return ro;
      } else {
        return null;
      }
    });
    when(institutionDao.findInstitutionByInstitutionName((anyString()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING)) {
        Institution ro = new Institution();
        ro.setInstitutionName(EXISTING);
        return ro;
      } else {
        return null;
      }
    });
    when(wageDao.findWageByWageId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        Wage ro = new Wage();
        ro.setWageId(EXISTING_INT);
        return ro;
      } else {
        return null;
      }
    });
    when(timeslotDao.findTimeSlotByTimeSlotId((anyInt()))).thenAnswer((InvocationOnMock invocation) -> {
      if (invocation.getArgument(0).equals(EXISTING_INT)) {
        TimeSlot ro = new TimeSlot();
        ro.setTimeSlotId(EXISTING_INT);
        return ro;
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
    when(courseDao.save(any(Course.class))).thenAnswer(returnParameterAsAnswer);
    when(roomDao.save(any(Room.class))).thenAnswer(returnParameterAsAnswer);
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
    assertEquals(0, tutorService.getAllTutors().size());
    String name = "Martin";
    String email = "ecse321test+tutor@protonmail.com";
    String password = "password";
    try {
      tutor = tutorService.createTutor(name, email, password);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(name, tutor.getName());
    assertEquals(email, tutor.getEmail());
  }

  @Test
  public void testCreateNullTutor() { // Test condition checks
    assertEquals(0, tutorService.getAllTutors().size());
    String error = null;
    String name = null;
    String email = null;
    String password = "password";
    try {
      tutor = tutorService.createTutor(name, email, password);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Tutor name, email or password cannot be empty!", error);
    assertNull(tutor);
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
      tutor = tutorService.createTutor(name, email, password);
      tutor.setName(newName);
      tutor.setEmail(newEmail);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(newName, tutor.getName());
    assertEquals(newEmail, tutor.getEmail());
  }

  @Test
  public void testGetExistingTutor() {
    assertEquals(EXISTING, tutorService.getTutor(EXISTING_INT).getName());
  }

  @Test
  public void testGetNonExistingTutor() {
    assertNull(tutorService.getTutor(NONEXISTING_INT));
  }

  // Student class tests

  @Test
  public void testCreateStudent() { // Test create and getters
    assertEquals(0, studentService.getAllStudents().size());
    String name = "Jason";
    String email = "ecse321test+student@protonmail.com";
    String password = "password";
    try {
      student = studentService.createStudent(name, email, password);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(name, student.getName());
    assertEquals(email, student.getEmail());
  }

  @Test
  public void testCreateNullStudent() { // Test condition checks
    assertEquals(0, studentService.getAllStudents().size());
    String error = null;
    String name = null;
    String email = null;
    String password = "password";
    try {
      student = studentService.createStudent(name, email, password);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Student name, email or password cannot be empty!", error);
    assertNull(student);
  }

  @Test
  public void testSetStudentNewName() { // Test setters
    assertEquals(0, studentService.getAllStudents().size());
    String name = "Jason";
    String email = "ecse321test+student@protonmail.com";
    String password = "password";
    String newName = "George";
    String newEmail = "ecse321test+student1@protonmail.com";
    try {
      student = studentService.createStudent(name, email, password);
      student.setName(newName);
      student.setEmail(newEmail);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(newName, student.getName());
    assertEquals(newEmail, student.getEmail());
  }

  @Test
  public void testGetExistingStudent() {
    assertEquals(EXISTING, studentService.getStudent(EXISTING_INT).getName());
  }

  @Test
  public void testGetNonExistingStudent() {
    assertNull(studentService.getStudent(NONEXISTING_INT));
  }

  // Manager class tests

  @Test
  public void testCreateManager() { // Test create and getters
    String name = "Marwan";
    String email = "ecse321test+manager@protonmail.com";
    String password = "password";
    try {
      manager = managerService.createManager(name, email, password);
    } catch (IllegalArgumentException e) {
      fail();
    }
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
      manager = managerService.createManager(name, email, password);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Manager name, email or password cannot be empty!", error);
    assertNull(manager);
  }

  @Test
  public void testSetManagerNewName() { // Test setters
	String name = "Marwan";
	String email = "ecse321test+manager@protonmail.com";
	String password = "password";
	String newName = "Daniel";
	String newEmail = "ecse321test+manager1@protonmail.com";
    try {
      manager = managerService.createManager(name, email, password);
      manager.setName(newName);
      manager.setEmail(newEmail);
    } catch (IllegalArgumentException e) {
      fail();
    }
    assertEquals(newName, manager.getName());
    assertEquals(newEmail, manager.getEmail());
  }

  @Test
  public void testGetExistingManager() {
    assertEquals(EXISTING, managerService.getManager(EXISTING_INT).getName());
  }

  @Test
  public void testGetNonExistingManager() {
    assertNull(managerService.getManager(NONEXISTING_INT));
  }

  // Request class tests

  @Test
  public void testCreateRequestWithNullTime() { // Test condition checks
    assertEquals(0, requestService.getAllRequests().size());
    String error = null;
    Time time = null;
    Date date = new Date(0);
    Tutor tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
    Student student = studentService.createStudent("name", "ecse321test+student@protonmail.com", "password");
    course = courseService.createCourse("test",
        institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
    try {
      request = requestService.createRequest(time, date, tutor, student, course);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Time cannot be empty!", error);
    assertNull(request);

  }

  @Test
  public void testGetExistingRequest() {
    assertEquals(EXISTING_INT, requestService.getRequest(EXISTING_INT).getRequestId());
  }

  @Test
  public void testGetNonExistingRequest() {
    assertNull(requestService.getRequest(NONEXISTING_INT));
  }

  // Course class tests

  @Test
  public void testCreateCourse() { // Test create and getters
    assertEquals(0, courseService.getAllCourses().size());
    String name = "MATH 263";
    institution = institutionService.createInstitution("McGill University", SchoolLevel.University);
    String subjectName = "Mathematics";
    try {
      course = courseService.createCourse(name, institution, subjectName);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(name, course.getCourseName());
    assertEquals(institution.getInstitutionName(), course.getInstitution().getInstitutionName());
    assertEquals(subjectName, course.getSubjectName());
  }

  @Test
  public void testCreateCourseWithNullName() { // Test condition checks
    assertEquals(0, courseService.getAllCourses().size());
    String error = null;
    String name = null;
    institution = institutionService.createInstitution("McGill University", SchoolLevel.University);
    String subjectName = "Mathematics";
    try {
      course = courseService.createCourse(name, institution, subjectName);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Course name cannot be empty!", error);
    assertNull(course);
  }

  @Test
  public void testSetCourseNewInstitution() { // Test setters
    assertEquals(0, courseService.getAllCourses().size());
    String name = "MATH 263";
    institution = institutionService.createInstitution("McGill University", SchoolLevel.University);
    institution1 = institutionService.createInstitution("CEGEP Bois-de-Boulogne", SchoolLevel.CEGEP);
    String subjectName = "Mathematics";
    try {
      course = courseService.createCourse(name, institution, subjectName);
      course.setInstitution(institution1);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(institution1.getInstitutionName(), course.getInstitution().getInstitutionName());
  }

  @Test
  public void testGetExistingCourse() {
    assertEquals(EXISTING, courseService.getCourse(EXISTING).getCourseName());
  }

  @Test
  public void testGetNonExistingCourse() {
    assertNull(courseService.getCourse(NONEXISTING));
  }

  // Room class tests

  @Test
  public void testCreateRoom() { // Test create and getters
    assertEquals(0, roomService.getAllRooms().size());
    Integer roomNumber = 12;
    Integer capacity = 30;
    try {
      room = roomService.createRoom(roomNumber, capacity);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(roomNumber, room.getRoomNumber());
    assertEquals(capacity, room.getCapacity());
  }

  @Test
  public void testCreateNullRoom() { // Test condition checks
    assertEquals(0, roomService.getAllRooms().size());
    String error = null;
    Integer roomNumber = null;
    Integer capacity = 30;
    try {
      room = roomService.createRoom(roomNumber, capacity);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Room number cannot be empty!", error);
    assertNull(room);
  }

  @Test
  public void testSetRoomNewCapacity() { // Test setters
    Integer roomNumber = 12;
    Integer capacity = 30;
    Integer newCapacity = 60;
    try {
      room = roomService.createRoom(roomNumber, capacity);
      room.setCapacity(newCapacity);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(newCapacity, room.getCapacity());
  }

  @Test
  public void testGetExistingRoom() {
    assertEquals(EXISTING_INT, roomService.getRoom(EXISTING_INT).getRoomNumber());
  }

  @Test
  public void testGetNonExistingRoom() {
    assertNull(roomService.getRoom(NONEXISTING_INT));
  }

  // Notification class tests

  @Test
  public void testCreateNullNotification() { // Test condition checks
    assertEquals(0, notificationService.getAllNotifications().size());
    String error = null;
    request = null;
    try {
      notificationService.createNotification(request, NotificationType.Requested);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Request cannot be null!", error);
  }

  @Test
  public void testGetExistingNotification() {
    assertEquals(EXISTING_INT, notificationService.getNotification(EXISTING_INT).getNotificationId());
  }

  @Test
  public void testGetNonExistingNotification() {
    assertNull(notificationService.getNotification(NONEXISTING_INT));
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
      review = reviewService.createReview(rating, comment, from, to);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(rating, review.getRating());
    assertEquals(comment, review.getComment());
    assertEquals(from.getUserId(), review.getFrom().getUserId());
    assertEquals(to.getUserId(), review.getTo().getUserId());
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
      review = reviewService.createReview(rating, comment, from, to);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }
    assertEquals("Rating cannot be null!", error);
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
      review = reviewService.createReview(rating, comment, from, to);
      review.setComment(newComment);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(newComment, review.getComment());
  }

  @Test
  public void testGetExistingReview() {
    assertEquals(EXISTING_INT, reviewService.getReview(EXISTING_INT).getReviewId());
  }

  @Test
  public void testGetNonExistingReview() {
    assertNull(reviewService.getReview(NONEXISTING_INT));
  }

  // Application class tests

  @Test
  public void testCreateApplication() { // Test create and getters
    Boolean isExistingUser = true;
    String name = "Martin";
    String email = "ecse321test+applicant@protonmail.com";
    String course = "ECSE 321";
    try {
      application = applicationService.createApplication(isExistingUser, name, email, course);
    } catch (IllegalArgumentException e) {
      fail();
    }
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
    String email = "ecse321test+applicant@protonmail.com";
    String course = "ECSE 321";
    try {
      application = applicationService.createApplication(isExistingUser, name, email, course);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Application name cannot be empty!", error);
  }

  @Test
  public void testSetApplicationNewName() { // Test setters
    Boolean isExistingUser = true;
    String name = "Martin";
    String newName = "George";
    String email = "ecse321test+applicant@protonmail.com";
    String course = "ECSE 321";
    try {
      application = applicationService.createApplication(isExistingUser, name, email, course);
      application.setName(newName);
    } catch (IllegalArgumentException e) {
      fail();
    }
    assertEquals(newName, application.getName());
  }

  @Test
  public void testGetExistingApplication() {
    assertEquals(EXISTING_INT, applicationService.getApplication(EXISTING_INT).getApplicationId());
  }

  @Test
  public void testGetNonExistingApplication() {
    assertNull(applicationService.getApplication(NONEXISTING_INT));
  }

  // Institution class tests

  @Test
  public void testCreateInstitution() { // Test create and getters
    assertEquals(0, institutionService.getAllInstitutions().size());
    String institutionName = "McGill University";
    SchoolLevel institutionLevel = SchoolLevel.University;
    try {
      institution = institutionService.createInstitution(institutionName, institutionLevel);
    } catch (IllegalArgumentException e) {
      fail();
    }
    assertEquals(institutionName, institution.getInstitutionName());
    assertEquals(institutionLevel, institution.getInstitutionLevel());
  }

  @Test
  public void testCreateNullInstitution() { // Test condition checks
    assertEquals(0, institutionService.getAllInstitutions().size());
    String error = null;
    String institutionName = null;
    SchoolLevel institutionLevel = SchoolLevel.University;
    try {
      institution = institutionService.createInstitution(institutionName, institutionLevel);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("Institution name cannot be null!", error);
  }

  @Test
  public void testSetInstitutionNewName() { // Test setters
    assertEquals(0, institutionService.getAllInstitutions().size());
    String institutionName = "McGill University";
    String newInstitutionName = "Concordia University";
    SchoolLevel institutionLevel = SchoolLevel.University;
    try {
      institution = institutionService.createInstitution(institutionName, institutionLevel);
      institution.setInstitutionName(newInstitutionName);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(newInstitutionName, institution.getInstitutionName());
  }

  @Test
  public void testGetExistingInstitution() {
    assertEquals(EXISTING, institutionService.getInstitution(EXISTING).getInstitutionName());
  }

  @Test
  public void testGetNonExistingInstitution() {
    assertNull(institutionService.getInstitution(NONEXISTING));
  }

  // Wage class tests

  @Test
  public void testCreateWage() { // Test create and getters
    assertEquals(0, wageService.getAllWages().size());
    tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
    course =
        courseService.createCourse("test", institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
    Integer wage_price = 20;
    try {
      wage = wageService.createWage(tutor, course, wage_price);
    } catch (IllegalArgumentException e) {
      fail();
    }
    assertEquals(tutor.getUserId(), wage.getTutor().getUserId());
    assertEquals(course.getCourseName(), wage.getCourse().getCourseName());
    assertEquals(wage_price, wage.getWage());
  }

  @Test
  public void testCreateNullWage() { // Test condition checks
    assertEquals(0, wageService.getAllWages().size());
    String error = null;
    Tutor tutor = null;
    Course course = null;
    Integer wage_price = 20;
    try {
      wage = wageService.createWage(tutor, course, wage_price);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("A tutor needs to be specified!", error);
  }

  @Test
  public void testSetWageNewTutor() { // Test setters
    assertEquals(0, wageService.getAllWages().size());
    tutor = tutorService.createTutor("name1", "ecse321test+tutor@protonmail.com", "password1");
    tutor1 = tutorService.createTutor("name2", "ecse321test+tutor1@protonmail.com", "password2");
    course =
        courseService.createCourse("test", institutionService.createInstitution("institutionName", SchoolLevel.University), "subject");
    Integer wage_price = 20;
    try {
      wage = wageService.createWage(tutor, course, wage_price);
      wage.setTutor(tutor1);
    } catch (IllegalArgumentException e) {
      fail();
    }
    assertEquals(tutor1.getUserId(), wage.getTutor().getUserId());
  }

  @Test
  public void testGetExistingWage() {
    assertEquals(EXISTING_INT, wageService.getWage(EXISTING_INT).getWageId());
  }

  @Test
  public void testGetNonExistingWage() {
    assertNull(wageService.getWage(NONEXISTING_INT));
  }

  // TimeSlot class tests

  @Test
  public void testCreateTimeSlot() { // Test create and getters
    Time time = Time.valueOf("08:00:01");
    Date date = Date.valueOf("2019-09-22");
    tutor = tutorService.createTutor("name", "ecse321test+tutor@protonmail.com", "password");
    try {
      timeslot = timeSlotService.createTimeSlot(tutor, date, time);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(tutor.getUserId(), timeslot.getTutor().getUserId());
  }

  @Test
  public void testCreateNullTimeSlot() { // Test condition checks
    String error = null;
    Time time = Time.valueOf("08:00:01");
    Date date = Date.valueOf("2019-09-22");
    Tutor tutor = null;
    try {
      timeslot = timeSlotService.createTimeSlot(tutor, date, time);
    } catch (IllegalArgumentException e) {
      error = e.getMessage();
    }

    assertEquals("A tutor needs to be specified!", error);
  }

  @Test
  public void testSetTimeSlotNewTutor() { // Test setters
    Time time = Time.valueOf("08:00:01");
    Date date = Date.valueOf("2019-09-22");
    tutor = tutorService.createTutor("name1", "ecse321test+tutor@protonmail.com", "password1");
    tutor1 = tutorService.createTutor("name2", "ecse321test+tutor1@protonmail.com", "password2");
    try {
      timeslot = timeSlotService.createTimeSlot(tutor, date, time);
      timeslot.setTutor(tutor1);
    } catch (IllegalArgumentException e) {
      fail();
    }

    assertEquals(tutor1.getUserId(), timeslot.getTutor().getUserId());
  }

  @Test
  public void testGetExistingTimeSlot() {
    assertEquals(EXISTING_INT, timeSlotService.getTimeSlot(EXISTING_INT).getTimeSlotId());
  }

  @Test
  public void testGetNonExistingTimeSlot() {
    assertNull(timeSlotService.getTimeSlot(NONEXISTING_INT));
  }
}
