package com.recipe.manager.recipemanager.controller;

import com.recipe.manager.recipemanager.models.Review;
import com.recipe.manager.recipemanager.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
       return new ResponseEntity<>(reviewService.createReview(payload.get("reviewBody"), payload.get("recipeId")), HttpStatus.CREATED);
    }

}
