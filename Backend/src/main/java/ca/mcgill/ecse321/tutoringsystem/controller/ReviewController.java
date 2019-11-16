package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.tutoringsystem.dto.ReviewDto;
import ca.mcgill.ecse321.tutoringsystem.model.Review;
import ca.mcgill.ecse321.tutoringsystem.service.PersonService;
import ca.mcgill.ecse321.tutoringsystem.service.ReviewService;
import ca.mcgill.ecse321.tutoringsystem.service.TutorService;

@CrossOrigin(origins = "*")
@RestController
public class ReviewController {

  @Autowired
  ReviewService reviewService;
  @Autowired
  PersonService personService;
  @Autowired
  TutorService tutorService;

  @PostMapping(value = {"/reviews/create", "/reviews/create/"})
  public ReviewDto createReview(@RequestParam(name = "rating") Integer rating,
		  @RequestParam(name = "comment") String comment,
		  @RequestParam(name = "from") Integer fromId, @RequestParam(name = "to") Integer toId)
      throws IllegalArgumentException {
    Review review = reviewService.createReview(rating, comment, personService.getPersonById(fromId), personService.getPersonById(toId));
    return DtoConverter.toDto(review);
  }
  
  @GetMapping(value = {"/reviews/{id}", "/reviews/{id}/"})
  public ReviewDto getReview(@PathVariable("id") Integer reviewId) throws IllegalArgumentException {
    Review review = reviewService.getReview(reviewId);
    return DtoConverter.toDto(review);
  }
  
  @GetMapping(value = {"/reviews/tutor/{id}", "/reviews/tutor/{id}/"})
  public List<ReviewDto> getReviewByTutor(@PathVariable("id") Integer tutorId) throws IllegalArgumentException {
    List<Review> tutorReviews = reviewService.getReviewByTutor(tutorService.getTutor(tutorId));
    List<ReviewDto> reviewDtos = new ArrayList<>();
    for (Review review : tutorReviews) {
      reviewDtos.add(DtoConverter.toDto(review));
    }
    return reviewDtos;
  }
  
  @DeleteMapping(value = {"/reviews/{id}", "/reviews/{id}/"})
  public boolean deleteReview(@PathVariable("id") Integer reviewId) throws IllegalArgumentException {
    return reviewService.deleteReview(reviewId);
  }

  @GetMapping(value = {"/reviews", "/reviews/"})
  public List<ReviewDto> getAllReviews() {
    List<ReviewDto> reviewDtos = new ArrayList<>();
    for (Review review : reviewService.getAllReviews()) {
      reviewDtos.add(DtoConverter.toDto(review));
    }
    return reviewDtos;
  }

}
