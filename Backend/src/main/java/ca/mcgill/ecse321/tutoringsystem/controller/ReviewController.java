package ca.mcgill.ecse321.tutoringsystem.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ca.mcgill.ecse321.tutoringsystem.dto.ReviewDto;
import ca.mcgill.ecse321.tutoringsystem.model.Person;
import ca.mcgill.ecse321.tutoringsystem.model.Review;
import ca.mcgill.ecse321.tutoringsystem.service.ReviewService;

@CrossOrigin(origins = "*")
@RestController
public class ReviewController {

  @Autowired
  ReviewService reviewService;

  @PostMapping(value = {"/reviews/create", "/reviews/create/"})
  public ReviewDto createReview(@PathVariable("id") Integer reviewId,
      @RequestParam(name = "rating") Integer rating, @RequestParam(name = "comment") String comment,
      @RequestParam(name = "from") Person from, @RequestParam(name = "to") Person to)
      throws IllegalArgumentException {
    Review review = reviewService.createReview(rating, comment, from, to);
    return DtoConverter.toDto(review);
  }
  
  @GetMapping(value = {"/reviews/{id}", "/reviews/{id}/"})
  public ReviewDto getReview(@PathVariable("id") Integer reviewId) throws IllegalArgumentException {
    Review review = reviewService.getReview(reviewId);
    return DtoConverter.toDto(review);
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
