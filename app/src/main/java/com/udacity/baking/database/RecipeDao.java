package com.udacity.baking.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.udacity.baking.model.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipe(RecipeEntity recipeEntity);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<RecipeEntity> recipeEntityList);

    @Delete
    void deleteRecipe(RecipeEntity recipeEntity);


    @Query("SELECT * FROM Recipe WHERE id = :id")
    RecipeEntity getRecipeById(int id);

    @Query("SELECT * FROM Recipe")
    LiveData<List<RecipeEntity>> getAll();

    @Query("DELETE FROM Recipe")
    int deleteAll();

    @Query("SELECT COUNT(*) FROM Recipe")
    int getCount();





/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipeSteps(List<RecipeStepsEntity> recipeStepsEntityList);


    @Query("SELECT * FROM RecipeSteps WHERE recipeId = :id")
    List<RecipeStepsEntity> getRecipeStepsById(int id);*/

/*
    @Query("SELECT * FROM Recipe WHERE id = :id")
    RecipeEntity getRecipeById2(int id);

    @Query("SELECT * FROM RecipeSteps WHERE recipeId = :recipeId")
    List<RecipeStepsEntity> getSteps(int recipeId);


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveRecipes(List<RecipeEntity> states);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveRecipeSteps(List<RecipeStepsEntity>);
*/


    @Transaction
    @Query("SELECT * FROM Recipe")
    LiveData<List <RecipeStepDetails>> loadRecipes();

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRecipeSteps(List<RecipeStepsEntity> recipeStepsEntityList);





 /*   @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveRecipeSteps(RecipeStepsEntity recipeStepsEntity);
*/


}
