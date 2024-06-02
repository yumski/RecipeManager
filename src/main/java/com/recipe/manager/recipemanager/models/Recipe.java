package com.recipe.manager.recipemanager.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "recipes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe implements Comparable<Recipe> {
    @Id
    private ObjectId id;

    private String name;
    private String description;
    private String category;
    private LocalDateTime time = LocalDateTime.now();
    private List<String> ingredients;
    private List<String> directions;
    @DocumentReference
    private List<Review> reviewIds;

    @Override
    public int compareTo(Recipe otherRecipe) {
        return otherRecipe.getTime().compareTo(this.time);
    }
}
