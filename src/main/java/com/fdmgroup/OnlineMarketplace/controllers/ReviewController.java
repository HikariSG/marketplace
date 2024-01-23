package com.fdmgroup.OnlineMarketplace.controllers;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.OnlineMarketplace.entities.Review;
import com.fdmgroup.OnlineMarketplace.services.ReviewService;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@GetMapping
	public List<Review> getAllReviews(){
		return reviewService.getAllReviews();
	}
	
	@GetMapping("/{id}")
	public Review getReviewById(@PathVariable(value = "id") long reviewId) {
		return reviewService.getReviewById(reviewId);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Review> addNewReview(@RequestBody Review review){
		Review newReview = reviewService.addReview(review);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
			       .buildAndExpand(newReview.getReviewId()).toUri();

		return ResponseEntity.created(location).body(newReview);
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<Review> updateReview(@RequestBody Map<String, Object> updates,
												@PathVariable(value = "id") long reviewId){
		Review updatedReview = reviewService.updateReview(updates, reviewId);
		
		return ResponseEntity.ok(updatedReview);
	}
	
	@DeleteMapping("/{id}")
	public Map<String, Boolean> deleteReview(@PathVariable (value = "id") long reviewId){
		return reviewService.deleteReviewByReviewId(reviewId); 
	}
}
