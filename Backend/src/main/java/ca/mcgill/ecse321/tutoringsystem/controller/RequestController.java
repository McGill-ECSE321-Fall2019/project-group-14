package ca.mcgill.ecse321.tutoringsystem.controller;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.tutoringsystem.dto.*;
import ca.mcgill.ecse321.tutoringsystem.model.*;
import ca.mcgill.ecse321.tutoringsystem.service.*;

@CrossOrigin(origins = "*")
@RestController
public class RequestController {

	@Autowired
	RequestService requestService;
	@Autowired
	TutorService tutorService;
	@Autowired
	StudentService studentService;
	@Autowired
	CourseService courseService;

	@PostMapping(value = { "/requests/create", "/requests/create/"})
	public RequestDto createRequest(@RequestParam String time, @RequestParam String date, @RequestParam Integer tutorId, @RequestParam Integer studentId, @RequestParam String courseName) {
		Request request = requestService.createRequest(Time.valueOf(time), Date.valueOf(date), tutorService.getTutor(tutorId), studentService.getStudent(studentId), courseService.getCourse(courseName));
		return DtoConverter.toDto(request);
	}
	
	@GetMapping(value = { "/requests/{id}", "/requests/{id}/"})
	public RequestDto getRequestById(@PathVariable("id") Integer id) throws IllegalArgumentException {
		return DtoConverter.toDto(requestService.getRequest(id));
	}
	
	@GetMapping(value = {"/requests", "/requests/"})
	public List<RequestDto> getAllRequests() {
		List<RequestDto> requestDtos = new ArrayList<>();
		for (Request request : requestService.getAllRequests()) {
			requestDtos.add(DtoConverter.toDto(request));
		}
		return requestDtos;	
	}
	
	/**
	 * @param Tutor id
	 * @return All request from that Tutor
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/requests/tutor/{id}", "/requests/tutor/{id}/" })
	public Set<RequestDto> getRequestsByTutorId(@PathVariable("id") Integer id) throws IllegalArgumentException {
		Set<Request> requestSet = new HashSet<Request>(requestService.getTutorRequests(tutorService.getTutor(id)));
		return DtoConverter.requestSetToDto(requestSet);
	}
	
	/**
	 * @param Request id
	 * @return All the accepted requests
	 * @throws IllegalArgumentException
	 */
	@GetMapping(value = { "/sessions/accepted/{id}", "/sessions/accepted/{id}/" })
	public Set<RequestDto> getRequestsByAcceptedTutorId(@PathVariable("id") Integer id) throws IllegalArgumentException {
		Set<Request> requestSet = new HashSet<Request>(requestService.getAcceptedTutorRequests(tutorService.getTutor(id)));
		return DtoConverter.requestSetToDto(requestSet);
	}
	
	@DeleteMapping(value = { "/requests/{id}", "/requests/{id}/" })
	public boolean deleteRequestById(@PathVariable("id") Integer id) throws IllegalArgumentException {
		return requestService.rejectRequest(id);
	}

}
