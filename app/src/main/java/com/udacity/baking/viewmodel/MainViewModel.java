package com.udacity.baking.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.baking.database.AppRepository;
import com.udacity.baking.database.RecipeEntity;
import com.udacity.baking.model.Recipe;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Recipe> recipe = new MutableLiveData<>();
    private MutableLiveData<Integer> recipeId = new MutableLiveData<>();


    private final LiveData<List<Recipe>> mRecipeListObservable;
    private AppRepository mRepository;

    public List<Recipe> mRecipeList2;
    public List<RecipeEntity> mRecipeList;




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


    public LiveData<Integer> getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(MutableLiveData<Integer> recipeId) {
        this.recipeId = recipeId;
    }

    public void addRecipeData(List<Recipe> recipes) {
        mRepository.addRecipeData(recipes);
    }

    public void convertRecipeObject(List<Recipe> recipes) {
        mRepository.convertRecipeObject(recipes);
    }
}
