package com.recipe.manager.recipemanager.service;

import com.recipe.manager.recipemanager.models.Recipe;
import com.recipe.manager.recipemanager.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.recipe.manager.recipemanager.models.Review;

@Service
public class ReviewService {
        @Autowired
        private ReviewRepository reviewRepository;
        @Autowired
        private MongoTemplate mongoTemplate;

        public Review createReview(String reviewBody, String recipeId) {
            Review review = reviewRepository.insert(new Review(reviewBody));

            mongoTemplate.update(Recipe.class)
                    .matching(Criteria.where("_id").is(recipeId))
                    .apply(new Update().push("reviewIds").value(review))
                    .first();

            return review;
        }
}
