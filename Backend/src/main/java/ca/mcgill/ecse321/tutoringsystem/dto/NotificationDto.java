package ca.mcgill.ecse321.tutoringsystem.dto;

public class NotificationDto {
	private RequestDto request;
	private TutorDto tutor;
	private Integer notificationId;

	public NotificationDto(RequestDto request, TutorDto tutor, Integer notificationId) {
		super();
		this.request = request;
		this.tutor = tutor;
		this.notificationId = notificationId;
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
}
