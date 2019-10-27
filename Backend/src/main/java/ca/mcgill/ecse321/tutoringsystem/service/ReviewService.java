package ca.mcgill.ecse321.tutoringsystem.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ca.mcgill.ecse321.tutoringsystem.dao.ReviewRepository;
import ca.mcgill.ecse321.tutoringsystem.model.Person;
import ca.mcgill.ecse321.tutoringsystem.model.Review;
import ca.mcgill.ecse321.tutoringsystem.model.Student;
import ca.mcgill.ecse321.tutoringsystem.model.Tutor;

@Service
public class ReviewService {
  @Autowired
  ReviewRepository reviewRepository;

  @Transactional
  public Review createReview(Integer rating, String comment, Person from, Person to) {
    if (rating == null) {
      throw new IllegalArgumentException("Rating cannot be null!");
    }
    if (from == null || to == null) {
      throw new IllegalArgumentException("Review needs to be from a user and to another user.");
    }
    if (comment == null) {
      comment = "";
    }
    Review r = new Review();
    r.setRating(rating);
    r.setComment(comment);
    if (from instanceof Tutor) {
      r.setTutor((Tutor) from);
      r.setStudent((Student) to);
    } else {
      r.setTutor((Tutor) to);
      r.setStudent((Student) from);
    }
    r.setFrom(from);
    r.setTo(to);
    reviewRepository.save(r);
    return r;
  }

  @Transactional
  public Review getReview(Integer reviewId) {
    Review r = reviewRepository.findReviewByReviewId(reviewId);
    return r;
  }

  @Transactional
  public List<Review> getAllReviews() {
    return toList(reviewRepository.findAll());
  }
  
  @Transactional
  public void deleteReview(Integer id) {
      Review r = reviewRepository.findReviewByReviewId(id);
      if (r == null) {
          throw new NullPointerException("No Review by this id.");
      }
      reviewRepository.delete(r);
      return;
  }
  
  private <T> List<T> toList(Iterable<T> iterable) {
    List<T> resultList = new ArrayList<T>();
    for (T t : iterable) {
      resultList.add(t);
    }
    return resultList;
  }
}
