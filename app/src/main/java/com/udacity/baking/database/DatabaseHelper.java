package com.udacity.baking.database;

import android.util.Log;

public class DatabaseHelper {
    private static final String TAG = "DATABASE HELPER";
    private RecipeDao mRecipeDao;

    public DatabaseHelper(AppDatabase database){
        mRecipeDao = database.recipeDao();

    }

    public void saveRecipe(RecipeEntity recipeEntity){
        Log.d(TAG, "Save recipe");
        mRecipeDao.insertRecipe(recipeEntity);
        mRecipeDao.insertRecipeSteps(recipeEntity.getSteps());
        mRecipeDao.insertRecipeIngredients(recipeEntity.getIngredients());

    }

}
