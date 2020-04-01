package com.udacity.baking.viewmodel;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.baking.database.AppRepository;
import com.udacity.baking.database.RecipeEntity;
import com.udacity.baking.model.Recipe;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Recipe> recipe = new MutableLiveData<>();
    public MutableLiveData<Integer> recipeId = new MutableLiveData<>();


    private final LiveData<List<Recipe>> mRecipeListObservable;
    private AppRepository mRepository;

    public List<Recipe> mRecipeList2;
    public List<RecipeEntity> mRecipeList;

    public MutableLiveData<RecipeEntity> mLiveRecipe =
            new MutableLiveData<>();


    private Executor executor = Executors.newSingleThreadExecutor();



    public MainViewModel(@NonNull Application application) {
        super(application);

        // Initalize app repository
        mRepository = AppRepository.getInstance(application.getApplicationContext());

        // TODO: CHECK IF THIS CAN BE USE INSTEAD
        //mRecipeListObservable = mRepository.getRecipeList();
        mRecipeListObservable = mRepository.getInstance(application.getApplicationContext()).getRecipeList();


        //mRecipe = mRepository.mRecipe;
        //mRecipeList = mRepository.mLocalRecipeList;
    }

    //Expose the LiveData Recipe query so the UI can observe it.
    public LiveData<List<Recipe>> getRecipeListObservable() {
        return mRecipeListObservable;
    }



    //Get the recipe selected
    public LiveData<Recipe> getRecipe() {
        return recipe;
    }

    //Set the recipe selected
    public void setRecipe(Recipe recipe) {
        this.recipe.setValue(recipe);
    }



    //Set recipe id
    public LiveData<Integer> getRecipeId() {
        return recipeId;
    }
    public void setRecipeId(int recipeId) {
        this.recipeId.setValue(recipeId);
    }




    public void addRecipeData(List<Recipe> recipes) {
        mRepository.addRecipeData(recipes);
    }


    // Get recipe from database
    public void loadRecipe(final int recipeId) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                RecipeEntity recipe = mRepository.getRecipeById(recipeId);

                if (recipe == null){
                    Log.d("load recipe runnable", "recipe null");

                }else{
                    Log.d("load recipe runnable", "recipe found");
                    mLiveRecipe.postValue(recipe);

                }

            }
        });

    }






}
