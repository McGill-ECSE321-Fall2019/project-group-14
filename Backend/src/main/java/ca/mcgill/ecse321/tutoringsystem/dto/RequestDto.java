package ca.mcgill.ecse321.tutoringsystem.dto;

import java.sql.Date;
import java.sql.Time;

public class RequestDto {
	private TutorDto tutor;
	private StudentDto student;
	private Time time;
	private Date date;
	private NotificationDto notification;
	private CourseDto course;
	private RoomDto room;
	private Integer requestId;

	public RequestDto(TutorDto tutor, StudentDto student, Time time, Date date, NotificationDto notification,
			CourseDto course, RoomDto room, Integer requestId) {
		super();
		this.tutor = tutor;
		this.student = student;
		this.time = time;
		this.date = date;
		this.notification = notification;
		this.course = course;
		this.room = room;
		this.requestId = requestId;
	}
	
	public RequestDto() {
		super();
	}

	public TutorDto getTutor() {
		return tutor;
	}

	public void setTutor(TutorDto tutor) {
		this.tutor = tutor;
	}

	public StudentDto getStudent() {
		return student;
	}

	public void setStudent(StudentDto student) {
		this.student = student;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public NotificationDto getNotification() {
		return notification;
	}

	public void setNotification(NotificationDto notification) {
		this.notification = notification;
	}

	public CourseDto getCourse() {
		return course;
	}

	public void setCourse(CourseDto course) {
		this.course = course;
	}

	public RoomDto getRoom() {
		return room;
	}

	public void setRoom(RoomDto room) {
		this.room = room;
	}
	
	public Integer getRequestId() {
		return requestId;
	}

	public void setRequestId(Integer requestId) {
		this.requestId = requestId;
	}
	
}
