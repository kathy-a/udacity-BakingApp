package com.udacity.baking.database;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class RecipeStepDetails {

    public RecipeStepDetails() {
    }

    @Embedded
    public RecipeEntity recipeEntity;

    @Relation(parentColumn = "id", entityColumn = "recipeId", entity = RecipeStepsEntity.class)
    private List<RecipeStepsEntity> steps;

    public RecipeEntity getRecipeEntity() {
        return recipeEntity;
    }

    public void setRecipeEntity(RecipeEntity recipeEntity) {
        this.recipeEntity = recipeEntity;
    }

    public List<RecipeStepsEntity> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeStepsEntity> steps) {
        this.steps = steps;
    }
}
