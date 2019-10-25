package ca.mcgill.ecse321.tutoringsystem.dto;

public class ReviewDto {
  private Integer rating;
  private String comment;
  private PersonDto from;
  private PersonDto to;
  private Integer reviewId;
  
  public ReviewDto() {
    
  }

  public ReviewDto(Integer reviewId) {
    this(null, null, null, null, reviewId);
  }
  
  public ReviewDto(Integer rating, String comment, PersonDto from, PersonDto to, Integer reviewId) {
    this.rating = rating;
    this.comment = comment;
    this.from = from;
    this.to = to;
    this.reviewId = reviewId;
  }
  
  public Integer getRating() {
    return rating;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public PersonDto getFrom() {
    return from;
  }

  public void setFrom(PersonDto from) {
    this.from = from;
  }

  public PersonDto getTo() {
    return to;
  }

  public void setTo(PersonDto to) {
    this.to = to;
  }

  public Integer getReviewId() {
    return reviewId;
  }

  public void setReviewId(Integer reviewId) {
    this.reviewId = reviewId;
  }
}
