package ca.mcgill.ecse321.tutoringsystem.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class Notification {
	private Request request;

	@ManyToOne(optional = false)
	public Request getRequest() {
		return this.request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	private Tutor tutor;

	@ManyToOne(optional = false)
	public Tutor getTutor() {
		return this.tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}

	private Integer notificationId;

	public void setNotificationId(Integer value) {
		this.notificationId = value;
	}

	@Id
	@GeneratedValue()
	public Integer getNotificationId() {
		return this.notificationId;
	}
	
	private NotificationType notificationType;

	public void setNotificationType(NotificationType value) {
		this.notificationType = value;
	}

	@Enumerated(EnumType.STRING)
	public NotificationType getNotificationType() {
		return this.notificationType;
	}
}
