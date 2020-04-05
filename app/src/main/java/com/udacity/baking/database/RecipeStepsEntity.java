package com.udacity.baking.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "RecipeSteps" , primaryKeys = {"recipeId", "step_number"})
public class RecipeStepsEntity {



    @NonNull
    private Integer recipeId;

    @NonNull
    @ColumnInfo(name = "step_number")
    private Integer stepId;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;


    @ForeignKey
            (entity = RecipeEntity.class,
                    parentColumns = "id",
                    childColumns = "recipeId",
                    onDelete = CASCADE)




    public RecipeStepsEntity(Integer recipeId, Integer stepId, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.recipeId = recipeId;
        this.stepId = stepId;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
