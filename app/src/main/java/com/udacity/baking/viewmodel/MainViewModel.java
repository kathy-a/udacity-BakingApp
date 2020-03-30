package com.udacity.baking.viewmodel;


import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.udacity.baking.database.AppRepository;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.network.RecipeService;
import com.udacity.baking.network.TheRecipeDbService;
import com.udacity.baking.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Recipe>> mRecipeData;
    private final LiveData<List<Recipe>> mRecipeListObservable;
    private AppRepository mRepository;

    public List<Recipe> mRecipe;
    public List<Recipe> mRecipeList;



    public MainViewModel(@NonNull Application application) {
        super(application);

        // Initalize app repository
        mRepository = AppRepository.getInstance();
        mRecipeListObservable = mRepository.getInstance().getRecipeList();
        //mRecipe = mRepository.mRecipe;
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<List<Recipe>> getmRecipeListObservable() {
        return mRecipeListObservable;
    }
}
