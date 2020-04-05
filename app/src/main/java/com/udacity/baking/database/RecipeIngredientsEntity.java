package com.udacity.baking.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "RecipeIngredients" , primaryKeys = {"recipeId", "ingredient"})
public class RecipeIngredientsEntity {

    @NonNull
    private Integer recipeId;

    private double quantity;
    private String measure;
    @NonNull
    private String ingredient;

    public RecipeIngredientsEntity(Integer recipeId, double quantity, String measure, String ingredient) {
        this.recipeId = recipeId;
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    @ForeignKey
            (entity = RecipeEntity.class,
                    parentColumns = "id",
                    childColumns = "recipeId",
                    onDelete = CASCADE)





    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
}
