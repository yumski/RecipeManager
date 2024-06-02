package com.recipe.manager.recipemanager.service;

import com.recipe.manager.recipemanager.repository.RecipeRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.recipe.manager.recipemanager.models.Recipe;
import java.util.Collections;
import java.util.List;

import java.util.Optional;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    public List<Recipe> allRecipes() {
        return recipeRepository.findAll();
    }

    public Optional<Recipe> singleRecipe(String recipeName) {
        return recipeRepository.findRecipeByName(recipeName);
    }

    public List<Recipe> recipesByCategory(String categoryName) {
         List<Recipe> recipes = recipeRepository.findRecipeByCategory(categoryName);
         Collections.sort(recipes);
         return recipes;
    }

    public List<Recipe> recipesByName(String recipeName) {
        List<Recipe> recipes = recipeRepository.findByNameContaining(recipeName);
        Collections.sort(recipes);
        return recipes;
    }

    public Recipe insertRecipe(Recipe recipe) {
        return recipeRepository.insert(recipe);
    }

    public void deleteRecipe(String recipeId) {
        recipeRepository.deleteById(new ObjectId(recipeId));
    }
}
