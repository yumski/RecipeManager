package com.recipe.manager.recipemanager.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.recipe.manager.recipemanager.models.Recipe;
import java.util.Optional;
import java.util.List;
@Repository
public interface RecipeRepository extends MongoRepository<Recipe, ObjectId> {
    Optional<Recipe> findRecipeByName(String recipeName);
    List<Recipe> findRecipeByCategory(String categoryName);
    List<Recipe> findByNameContaining(String recipeName);
}
