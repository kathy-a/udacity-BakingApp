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

    public RecipeEntity(RecipeStepDetails recipeStepDetails){
        this.id = recipeStepDetails.getRecipeEntity().getId();
        this.name = recipeStepDetails.getRecipeEntity().getName();
        this.servings = recipeStepDetails.getRecipeEntity().getServings();
        this.image = recipeStepDetails.getRecipeEntity().getImage();
        this.steps = this.getSteps();

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
