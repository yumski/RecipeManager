package com.recipe.manager.recipemanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.recipe.manager.recipemanager.service.RecipeService;
import com.recipe.manager.recipemanager.models.Recipe;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeService.allRecipes(), HttpStatus.OK);
    }

    @GetMapping("/{recipeName}")
    public ResponseEntity<Optional<Recipe>> getSingleRecipe(@PathVariable String recipeName) {
        Optional<Recipe> recipe = recipeService.singleRecipe(recipeName);
        HttpStatus status = recipe.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(recipe, status);
    }

    @GetMapping("/search/")
    public ResponseEntity<List<Recipe>> getRecipeCategory(
            @RequestParam(value = "category", required = false) String recipeCategory,
            @RequestParam(value = "name", required = false) String recipeName) {
        if (recipeCategory == null && recipeName == null) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
        List<Recipe> recipes;

        if (recipeCategory != null) {
            recipes = recipeService.recipesByCategory(recipeCategory);
        } else {
            recipes = recipeService.recipesByName(recipeName);
        }
        HttpStatus status = recipes.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK;
        return new ResponseEntity<>(recipes, status);
    }

    @PostMapping
    public ResponseEntity<String> createRecipe(@RequestBody Recipe recipe) {
        Recipe newRecipe = recipeService.insertRecipe(recipe);
        return new ResponseEntity<>(newRecipe.getId().toString(), HttpStatus.OK);
    }

    @DeleteMapping("/{recipeId}")
    public ResponseEntity<String> deleteRecipe(@PathVariable String recipeId) {
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
