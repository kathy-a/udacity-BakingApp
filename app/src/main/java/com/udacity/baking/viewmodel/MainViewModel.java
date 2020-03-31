package com.udacity.baking.viewmodel;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.baking.database.AppRepository;
import com.udacity.baking.database.RecipeEntity;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.utilities.SampleData;

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
        mRepository = AppRepository.getInstance();
        mRecipeListObservable = mRepository.getInstance().getRecipeList();
        //mRecipe = mRepository.mRecipe;
        mRecipeList = mRepository.mRecipeList;
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
}
