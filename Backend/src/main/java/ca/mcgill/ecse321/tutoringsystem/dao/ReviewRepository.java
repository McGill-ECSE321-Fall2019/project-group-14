package ca.mcgill.ecse321.tutoringsystem.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import ca.mcgill.ecse321.tutoringsystem.model.Review;
import ca.mcgill.ecse321.tutoringsystem.model.Person;

public interface ReviewRepository extends CrudRepository<Review, Integer>{
  Review findReviewById(int reviewId);
  List<Review> findReviewByRating(int rating);
  List<Review> findReviewByTo(Person to);
  List<Review> findReviewByFrom(Person from);
}
