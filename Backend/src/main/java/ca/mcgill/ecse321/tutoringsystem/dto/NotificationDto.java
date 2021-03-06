package ca.mcgill.ecse321.tutoringsystem.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class NotificationDto {
	private RequestDto request;
	private TutorDto tutor;
	private Integer notificationId;
	private NotificationTypeDto notificationType;

	public NotificationDto(RequestDto request, TutorDto tutor, Integer notificationId, NotificationTypeDto notificationType) {
		super();
		this.request = request;
		this.tutor = tutor;
		this.notificationId = notificationId;
		this.notificationType = notificationType;
	}

	public NotificationDto() {
		super();
	}

	public RequestDto getRequest() {
		return request;
	}

	public void setRequest(RequestDto request) {
		this.request = request;
	}

	public TutorDto getTutor() {
		return tutor;
	}

	public void setTutor(TutorDto tutor) {
		this.tutor = tutor;
	}
	
	public Integer getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}
	
	public void setNotificationType(NotificationTypeDto value) {
		this.notificationType = value;
	}

	@Enumerated(EnumType.STRING)
	public NotificationTypeDto getNotificationType() {
		return this.notificationType;
	}
}
