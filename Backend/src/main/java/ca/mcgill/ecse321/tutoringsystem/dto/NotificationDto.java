package ca.mcgill.ecse321.tutoringsystem.dto;

public class NotificationDto {
	private RequestDto request;
	private TutorDto tutor;

	public NotificationDto(RequestDto request, TutorDto tutor) {
		super();
		this.request = request;
		this.tutor = tutor;
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
}
