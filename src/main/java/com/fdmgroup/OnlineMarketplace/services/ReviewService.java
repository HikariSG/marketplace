package com.fdmgroup.OnlineMarketplace.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.OnlineMarketplace.entities.Review;
import com.fdmgroup.OnlineMarketplace.exceptions.ReviewNotFoundException;
import com.fdmgroup.OnlineMarketplace.repositories.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepo;
	
	private Logger logger = LogManager.getLogger(ReviewService.class);
	
	public List<Review> getAllReviews() {
		List<Review> reviewList = reviewRepo.findAll();
		return reviewList;
	}
	
	public Review getReviewById(long reviewId) {
		Review existingReview = reviewRepo.findById(reviewId).orElseThrow(() -> {
								logger.error("Review with reviewId " + reviewId + " not found");
								return new ReviewNotFoundException("Review not found");
								});
		
		logger.info("Found review with review id " + reviewId);
		return existingReview;
	}
	
	public Review updateReview(Map<String, Object>updates, long reviewId) {
		Review existingReview = reviewRepo.findById(reviewId).orElseThrow(() -> {
								logger.error("Review with reviewId " + reviewId + " not found");
								return new ReviewNotFoundException("Review not found");
								});
		
		if (updates.containsKey("reviewContent")) {
			existingReview.setReviewContent((String)updates.get("reviewContent"));
			logger.info("Updating review content for review " + reviewId);
		}
		
		if (updates.containsKey("reviewRating")) {
			existingReview.setReviewRating((Double)updates.get("reviewRating"));
			logger.info("Updating review rating for review " + reviewId);
		}
		
		return existingReview;
	}
	
	public Map<String, Boolean> deleteReviewByReviewId(long reviewId){
		Review existingReview = reviewRepo.findById(reviewId).orElseThrow(() -> {
								logger.error("Review with reviewId " + reviewId + " not found");
								return new ReviewNotFoundException("Review not found");
								});
		
		reviewRepo.delete(existingReview);
		logger.info("Successfully deleted review id " + reviewId);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}

	public Review addReview(Review review) {
		return reviewRepo.save(review);
	}
}
