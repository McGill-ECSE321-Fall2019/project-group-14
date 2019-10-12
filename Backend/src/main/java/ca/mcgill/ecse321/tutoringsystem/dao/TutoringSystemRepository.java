package ca.mcgill.ecse321.tutoringsystem.dao;

import java.sql.Date;
import java.sql.Time;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.model.*;

@Repository
public class TutoringSystemRepository {
  @Autowired
  EntityManager entityManager;

  @Transactional
  public Person getPerson(String email) {
    if (email == null || email.trim().length() == 0) {
      throw new IllegalArgumentException("Tutor Id cannot be empty!");
    }
    Person p = entityManager.find(Person.class, email);
    return p;
  }

  @Transactional
  public Tutor createTutor(String name, String email) {
    if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0) {
      throw new IllegalArgumentException("Tutor name and email cannot be empty!");
    }
    Tutor p = new Tutor();
    p.setName(name);
    p.setEmail(email);
    entityManager.persist(p);
    return p;
  }
  
  @Transactional
  public Tutor getTutor(Integer Id) {
    if (Id == null) {
      throw new IllegalArgumentException("Tutor Id cannot be empty!");
    }
    Tutor p = entityManager.find(Tutor.class, Id);
    return p;
  }

  @Transactional
  public Tutor getTutor(String email) {
    if (email == null) {
      throw new IllegalArgumentException("Tutor email cannot be empty!");
    }
    Tutor p = entityManager.find(Tutor.class, email);
    return p;
  }

  @Transactional
  public Student createStudent(String name, String email) {
    if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0) {
      throw new IllegalArgumentException("Student name and email cannot be empty!");
    }
    Student p = new Student();
    p.setName(name);
    p.setEmail(email);
    entityManager.persist(p);
    return p;
  }

  @Transactional
  public Student getStudent(Integer Id) {
    if (Id == null) {
      throw new IllegalArgumentException("Student Id cannot be empty!");
    }
    Student p = entityManager.find(Student.class, Id);
    return p;
  }

  @Transactional
  public Student getStudent(String email) {
    if (email == null || email.trim().length() == 0) {
      throw new IllegalArgumentException("Student email cannot be empty!");
    }
    Student p = entityManager.find(Student.class, email);
    return p;
  }

  @Transactional
  public Manager createManager(String name, String email) {
    if (name == null || name.trim().length() == 0 || email == null || email.trim().length() == 0) {
      throw new IllegalArgumentException("Manager name and email cannot be empty!");
    }
    Manager p = new Manager();
    p.setName(name);
    p.setEmail(email);
    entityManager.persist(p);
    return p;
  }

  @Transactional
  public Manager getManager(Integer Id) {
    if (Id == null) {
      throw new IllegalArgumentException("Manager id cannot be empty!");
    }
    Manager p = entityManager.find(Manager.class, Id);
    return p;
  }

  @Transactional
  public Manager getManager(String email) {
    if (email == null || email.trim().length() == 0) {
      throw new IllegalArgumentException("Manager email cannot be empty!");
    }
    Manager p = entityManager.find(Manager.class, email);
    return p;
  }

  @Transactional
  public Application createApplication(Boolean isExistingPerson, String name, String email,
      String course) {
    String error = "";
    if (isExistingPerson == null) {
      error = error + "Person cannot be empty! ";
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
    Application p = new Application();
    p.setIsExistingUser(isExistingPerson);
    p.setName(name);
    p.setEmail(email);
    p.setCourses(course);
    entityManager.persist(p);
    return p;
  }

  @Transactional
  public Application getApplication(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Application id cannot be empty!");
    }
    Application p = entityManager.find(Application.class, id);
    return p;
  }

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
    Course p = new Course();
    p.setCourseName(name);
    p.setInstitution(institution);
    p.setSubjectName(subjectName);
    entityManager.persist(p);
    return p;
  }

  @Transactional
  public Course getCourse(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Course id cannot be empty!");
    }
    Course p = entityManager.find(Course.class, id);
    return p;
  }

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
    Request p = new Request();
    p.setTime(time);
    p.setDate(date);
    p.setTutor(tutor);
    p.setStudent(student);
    p.setCourse(course);
    return p;
  }

  @Transactional
  public Request getRequest(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Request id cannot be empty!");
    }
    Request p = entityManager.find(Request.class, id);
    return p;
  }

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
    Room p = new Room();
    p.setRoomNumber(roomNumber);
    p.setCapacity(capacity);
    entityManager.persist(p);
    return p;
  }

  @Transactional
  public Room getRoom(Integer id) {
    if (id == null) {
      throw new IllegalArgumentException("Room id cannot be empty!");
    }
    Room p = entityManager.find(Room.class, id);
    return p;
  }

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
    entityManager.persist(w);
    return w;
  }

  @Transactional
  public Wage getWage(Integer wageId) {
    Wage w = entityManager.find(Wage.class, wageId);
    return w;
  }

  @Transactional
  public Institution createInstitution(String institutionName, SchoolLevel institutionLevel) {
    if (institutionName == null) {
      throw new IllegalArgumentException("Institution name cannot be null!");
    }
    Institution i = new Institution();
    i.setInstitutionName(institutionName);
    i.setInstitutionLevel(institutionLevel);
    entityManager.persist(i);
    return i;
  }

  @Transactional
  public Institution getInstitution(String institutionName) {
    Institution i = entityManager.find(Institution.class, institutionName);
    return i;
  }

  @Transactional
  public TimeSlot createTimeSlot(Tutor tutor, Date date, Time time) {
    // error checks for time slot
    TimeSlot t = new TimeSlot();
    t.setTutor(tutor);
    t.setDate(date);
    t.setTime(time);
    entityManager.persist(t);
    return t;
  }

  @Transactional
  public TimeSlot getTimeSlot(Integer timeSlotId) {
    TimeSlot t = entityManager.find(TimeSlot.class, timeSlotId);
    return t;
  }

  @Transactional
  public Session createSession(Request request) {
    if (request == null) {
      throw new IllegalArgumentException("Request cannot be null!");
    }
    Session p = new Session();
    p.setRequest(request);
    entityManager.persist(p);
    return p;
  }


  @Transactional
  public Session getSession(Integer sessionId) {
    Session p = entityManager.find(Session.class, sessionId);
    return p;
  }

  @Transactional
  public Notification createNotification(Request request) {
    if (request == null) {
      throw new IllegalArgumentException("Notification ID cannot be null!");
    }
    Notification n = new Notification();
    n.setRequest(request);
    n.setTutor(request.getTutor());
    entityManager.persist(n);
    return n;
  }

  @Transactional
  public Notification getNotification(Integer notificationId) {
    Notification w = entityManager.find(Notification.class, notificationId);
    return w;
  }


  @Transactional
  public Review createReview(Integer rating, String comment, Person from, Person to) {
    if (rating == null) {
      throw new IllegalArgumentException("Rating cannot be null!");
    }
    if (from == null || to == null) {
      throw new IllegalArgumentException("Review needs to be from a Person and to another Person.");
    }
    if (comment == null) {
      comment = "";
    }
    Review r = new Review();
    r.setRating(rating);
    r.setComment(comment);
    r.setFrom(from);
    r.setTo(to);
    entityManager.persist(r);
    return r;
  }

  @Transactional
  public Review getReview(Integer reviewId) {
    Review r = entityManager.find(Review.class, reviewId);
    return r;
  }


}

