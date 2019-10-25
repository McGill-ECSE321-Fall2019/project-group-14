package ca.mcgill.ecse321.tutoringsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import ca.mcgill.ecse321.tutoringsystem.dao.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;

@Service
public class TutoringSystemService {
	@Autowired
	TutorRepository tutorRepository; //
	@Autowired
	StudentRepository studentRepository; //
	@Autowired
	ManagerRepository managerRepository; //
	@Autowired
	RequestRepository requestRepository; //
	@Autowired
	CourseRepository courseRepository; //
	@Autowired
	RoomRepository roomRepository; //
	@Autowired
	NotificationRepository notificationRepository; //
	@Autowired
	ReviewRepository reviewRepository; //
	@Autowired
	ApplicationRepository applicationRepository; //
	@Autowired
	InstitutionRepository institutionRepository; //
	@Autowired
	WageRepository wageRepository;
	@Autowired
	TimeSlotRepository timeslotRepository; //

	/**
	 * TUTOR
	 */
	@Transactional
	public Tutor createTutor(String name, String email, String password) {
		if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0
				|| password.trim().length() == 0) {
			throw new IllegalArgumentException("Tutor name, email or password cannot be empty!");
		}
		Tutor t = new Tutor();
		t.setName(name);
		t.setEmail(email);
		t.setPassword(password);
		tutorRepository.save(t);
		return t;
	}

	@Transactional
	public Tutor getTutor(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		Tutor t = tutorRepository.findTutorByUserId(id);
		return t;
	}

	@Transactional
	public Tutor getTutor(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Tutor email cannot be empty!");
		}
		Tutor t = tutorRepository.findTutorByEmail(email);
		return t;
	}

	@Transactional
	public List<Tutor> getAllTutors() {
		return toList(tutorRepository.findAll());
	}

	/*
	 * STUDENT
	 */
	@Transactional
	public Student createStudent(String name, String email, String password) {
		if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0
				|| password.trim().length() == 0) {
			throw new IllegalArgumentException("Student name, email or password cannot be empty!");
		}
		Student s = new Student();
		s.setName(name);
		s.setEmail(email);
		s.setPassword(password);
		studentRepository.save(s);
		return s;
	}

	@Transactional
	public Student getStudent(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		Student s = studentRepository.findStudentByUserId(id);
		return s;
	}

	@Transactional
	public Student getStudent(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Tutor email cannot be empty!");
		}
		Student s = studentRepository.findStudentByEmail(email);
		return s;
	}

	@Transactional
	public List<Student> getAllStudents() {
		return toList(studentRepository.findAll());
	}

	/*
	 * MANAGER
	 */
	@Transactional
	public Manager createManager(String name, String email, String password) {
		if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0
				|| password.trim().length() == 0) {
			throw new IllegalArgumentException("Manager name, email or password cannot be empty!");
		}
		Manager m = new Manager();
		m.setName(name);
		m.setEmail(email);
		m.setPassword(password);
		managerRepository.save(m);
		return m;
	}

	@Transactional
	public Manager getManager(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Tutor Id cannot be empty!");
		}
		Manager m = managerRepository.findManagerByUserId(id);
		return m;
	}

	@Transactional
	public Manager getManager(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Tutor email cannot be empty!");
		}
		Manager m = managerRepository.findManagerByEmail(email);
		return m;
	}

	/*
	 * REQUEST
	 */
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
	public void acceptRequest(int requestId) {
		Request request = getRequest(requestId);

		if (getAllRooms().size() == 0) {
			throw new RuntimeException("There are no rooms created.");
		}

		List<Room> rooms = getAllRooms();
		for (Room room : rooms) {
			if (room.getRequest().size() == 0) {
				request.setRoom(room);
				Set<Request> requests = room.getRequest();
				requests.add(request);
				room.setRequest(requests);
				requestRepository.save(request);
				roomRepository.save(room);
				return;
			} else {
				Set<Request> requests = room.getRequest();
				for (Request checkRequest : requests) {
					if (checkRequest.getDate() != request.getDate() && checkRequest.getTime() != request.getTime()) {
						request.setRoom(room);
						requests.add(request);
						room.setRequest(requests);
						requestRepository.save(request);
						roomRepository.save(room);
						return;
					}
				}
			}
		}
		throw new RuntimeException("There are no rooms available for that time and date.");
	}

	/*
	 * COURSE
	 */
	@Transactional
	public Course createCourse(String name, Institution institution, String subjectName) {
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "Course name cannot be empty! ";
		}
		if (institution == null) {
			error = error + "Course email cannot be empty! ";
		}
		if (subjectName == null || subjectName.trim().length() == 0) {
			error = error + "Course course time cannot be empty! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Course c = new Course();
		c.setCourseName(name);
		c.setInstitution(institution);
		c.setSubjectName(subjectName);
		courseRepository.save(c);
		return c;
	}

	@Transactional
	public Course getCourse(String name) {
		if (name == null) {
			throw new IllegalArgumentException("Course name cannot be empty!");
		}
		Course p = courseRepository.findCourseByCourseName(name);
		return p;
	}

	@Transactional
	public List<Course> getAllCourses() {
		return toList(courseRepository.findAll());
	}

	@Transactional
	public List<Course> getAllCoursesWithSubject(String subjectName) {
		if (subjectName == null) {
			throw new IllegalArgumentException("Subject name cannot be null!");
		}
		List<Course> coursesWithSubject = new ArrayList<>();
		for (Course c : courseRepository.findCourseBySubjectName(subjectName)) {
			coursesWithSubject.add(c);
		}
		return coursesWithSubject;
	}

	/*
	 * ROOM
	 */
	@Transactional
	public Room createRoom(Integer roomNumber, Integer capacity) {
		String error = "";
		if (roomNumber == null) {
			error = error + "Room number cannot be empty! ";
		}
		if (capacity == null || capacity == 0) {
			error = error + "Room capacity cannot be empty! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Room r = new Room();
		r.setRoomNumber(roomNumber);
		r.setCapacity(capacity);
		roomRepository.save(r);
		return r;
	}

	@Transactional
	public Room getRoom(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Room id cannot be empty!");
		}
		Room p = roomRepository.findRoomByRoomNumber(id);
		return p;
	}

	@Transactional
	public List<Room> getAllRooms() {
		return toList(roomRepository.findAll());
	}

	/*
	 * NOTIFICATION
	 */

	@Transactional
	public Notification createNotification(Request request) {
		if (request == null) {
			throw new IllegalArgumentException("Notification ID cannot be null!");
		}
		Notification n = new Notification();
		n.setRequest(request);
		n.setTutor(request.getTutor());
		notificationRepository.save(n);
		return n;
	}

	@Transactional
	public Notification getNotification(Integer notificationId) {
		Notification w = notificationRepository.findNotificationByNotificationId(notificationId);
		return w;
	}

	@Transactional
	public List<Notification> getAllNotifications() {
		return toList(notificationRepository.findAll());
	}

	/*
	 * REVIEW
	 */
	@Transactional
	public Review createReview(Integer rating, String comment, Person from, Person to) {
		if (rating == null) {
			throw new IllegalArgumentException("Rating cannot be null!");
		}
		if (from == null || to == null) {
			throw new IllegalArgumentException("Review needs to be from a user and to another user.");
		}
		if (comment == null) {
			comment = "";
		}
		Review r = new Review();
		r.setRating(rating);
		r.setComment(comment);
		if (from instanceof Tutor) {
			r.setTutor((Tutor) from);
			r.setStudent((Student) to);
		} else {
			r.setTutor((Tutor) to);
			r.setStudent((Student) from);
		}
		r.setFrom(from);
		r.setTo(to);
		reviewRepository.save(r);
		return r;
	}

	@Transactional
	public Review getReview(Integer reviewId) {
		Review r = reviewRepository.findReviewByReviewId(reviewId);
		return r;
	}

	@Transactional
	public List<Review> getAllReviews() {
		return toList(reviewRepository.findAll());
	}

	/*
	 * APPLICATION
	 */
	@Transactional
	public Application createApplication(Boolean isExistingUser, String name, String email, String course) {
		String error = "";
		if (isExistingUser == null) {
			error = error + "User cannot be empty! ";
		}
		if (name == null || name.trim().length() == 0) {
			error = error + "Application name cannot be empty! ";
		}
		if (email == null || email.trim().length() == 0) {
			error = error + "Application email cannot be empty! ";
		}
		if (course == null) {
			error = error + "Course cannot be empty! ";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		Application t = new Application();
		t.setIsExistingUser(isExistingUser);
		t.setName(name);
		t.setEmail(email);
		t.setCourses(course);
		applicationRepository.save(t);
		return t;
	}

	@Transactional
	public Application getApplication(Integer id) {
		if (id == null) {
			throw new IllegalArgumentException("Application id cannot be empty!");
		}
		Application p = applicationRepository.findApplicationByApplicationId(id);
		return p;
	}

	@Transactional
	public Application getApplication(String email) {
		if (email == null) {
			throw new IllegalArgumentException("Application email cannot be empty!");
		}
		Application p = applicationRepository.findApplicationByEmail(email);
		return p;
	}

	/*
	 * INSTITUTION
	 */
	@Transactional
	public Institution createInstitution(String institutionName, SchoolLevel institutionLevel) {
		if (institutionName == null) {
			throw new IllegalArgumentException("Institution name cannot be null!");
		}
		Institution i = new Institution();
		i.setInstitutionName(institutionName);
		i.setInstitutionLevel(institutionLevel);
		institutionRepository.save(i);
		return i;
	}

	@Transactional
	public Institution getInstitution(String institutionName) {
		Institution i = institutionRepository.findInstitutionByInstitutionName(institutionName);
		return i;
	}

	@Transactional
	public List<Institution> getAllInstitutions() {
		return toList(institutionRepository.findAll());
	}

	/*
	 * WAGE
	 */
	@Transactional
	public Wage createWage(Tutor tutor, Course course, Integer wage) {
		if (tutor == null) {
			throw new IllegalArgumentException("A tutor needs to be specified!");
		}
		if (course == null) {
			throw new IllegalArgumentException("Course cannot be empty!");
		}
		if (wage == null || wage <= 0) {
			throw new IllegalArgumentException("Wage cannot be negative or zero!");
		}
		Wage w = new Wage();
		w.setTutor(tutor);
		w.setWage(wage);
		w.setCourse(course);
		wageRepository.save(w);
		return w;
	}

	@Transactional
	public Wage getWage(Integer wageId) {
		Wage w = wageRepository.findWageByWageId(wageId);
		return w;
	}

	@Transactional
	public List<Wage> getAllWages() {
		return toList(wageRepository.findAll());
	}

	// TODO Get all wages from Tutor and courses

	/*
	 * TIMESLOT
	 */

	@Transactional
	public TimeSlot createTimeSlot(Tutor tutor, Date date, Time time) {
		if (tutor == null) {
			throw new IllegalArgumentException("A tutor needs to be specified!");
		}
		if (date == null | time == null) {
			throw new IllegalArgumentException("Date and time cannot be empty!");
		}
		TimeSlot t = new TimeSlot();
		t.setTutor(tutor);
		t.setDate(date);
		t.setTime(time);
		timeslotRepository.save(t);
		return t;
	}

	@Transactional
	public TimeSlot getTimeSlot(Integer timeSlotId) {
		TimeSlot t = timeslotRepository.findTimeSlotByTimeSlotId(timeSlotId);
		return t;
	}

	@Transactional
	public List<TimeSlot> getTimeSlot(Date date, Time time) {
		if (date == null || time == null) {
			throw new IllegalArgumentException("Date and time cannot be null!");
		}
		return timeslotRepository.findTimeSlotByDateAndTime(date, time);
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}
