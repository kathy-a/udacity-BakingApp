package com.udacity.baking.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeIngredientDetails {

    @Embedded
    public RecipeEntity recipeEntity;

    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = RecipeIngredientsEntity.class)
    private List<RecipeIngredientsEntity> ingredients;

    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }

    public List<RecipeIngredientsEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredientsEntity> ingredients) {
        this.ingredients = ingredients;
    }
}
