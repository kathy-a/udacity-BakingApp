package com.udacity.baking.database;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.udacity.baking.model.Ingredient;
import com.udacity.baking.model.Step;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "Recipe")
public class RecipeEntity {

    @PrimaryKey
    private Integer id;
    private String name;
    private Integer servings;
    private String image;

    @Ignore
    private List<RecipeStepsEntity> steps = null;

    @Ignore
    private List<RecipeIngredientsEntity> ingredients = null;


    public RecipeEntity(Integer id, String name, Integer servings, String image) {
        this.id = id;
        this.name = name;
        this.servings = servings;
        this.image = image;
    }

    public List<RecipeStepsEntity> getSteps() {
        return steps;
    }

    public void setSteps(RecipeStepsEntity steps) {
        if(this.steps == null){
            this.steps = new ArrayList<>();
        }
        this.steps.add(steps);
    }


    public List<RecipeIngredientsEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(RecipeIngredientsEntity ingredients) {
        if(this.ingredients == null){
            this.ingredients = new ArrayList<>();
        }
        this.ingredients.add(ingredients);
    }

    public RecipeEntity(RecipeStepDetails recipeStepDetails){
        this.id = recipeStepDetails.getRecipeEntity().getId();
        this.name = recipeStepDetails.getRecipeEntity().getName();
        this.servings = recipeStepDetails.getRecipeEntity().getServings();
        this.image = recipeStepDetails.getRecipeEntity().getImage();
        this.steps = recipeStepDetails.getSteps();
    }

    public RecipeEntity(RecipeIngredientDetails recipeIngredientDetails){
        this.id = recipeIngredientDetails.getRecipeEntity().getId();
        this.name = recipeIngredientDetails.getRecipeEntity().getName();
        this.servings = recipeIngredientDetails.getRecipeEntity().getServings();
        this.image = recipeIngredientDetails.getRecipeEntity().getImage();
        this.ingredients = recipeIngredientDetails.getIngredients();
    }





    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
