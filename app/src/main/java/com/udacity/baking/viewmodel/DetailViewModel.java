package com.udacity.baking.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.udacity.baking.database.AppRepository;
import com.udacity.baking.database.RecipeIngredientDetails;
import com.udacity.baking.database.RecipeStepDetails;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DetailViewModel extends AndroidViewModel {

    public static int sRecipeId;
    public static int sRecipeStep;
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();
    private static final String TAG = "Detail View Model";
    public MutableLiveData<RecipeStepDetails> mLiveRecipeSteps =
            new MutableLiveData<RecipeStepDetails>();

    public MutableLiveData<RecipeIngredientDetails> mLiveRecipeIngredients =
            new MutableLiveData<>();


    public DetailViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());

    }

    public static void setsRecipeStep(int sRecipeStep) {
        DetailViewModel.sRecipeStep = sRecipeStep;
    }

    /**
     *  Set the recipe ID of recipe selected
     * @param sRecipeId
     */
    public static void setsRecipeId(int sRecipeId) {
        DetailViewModel.sRecipeId = sRecipeId;
    }


    /**
     * Load recipe steps and pass to live data
     */
    public void loadRecipeSteps(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Movie ID selected: " + String.valueOf(sRecipeId));

                RecipeStepDetails recipe = mRepository.getRecipeStepsById(sRecipeId);
                if (recipe == null){
                    Log.d(TAG,"Load recipe Steps: recipe Null");
                }else{
                    Log.d(TAG,"Load recipe Steps: recipe found");
                    mLiveRecipeSteps.postValue(recipe);

                }
            }
        });
    }


    /**
     *  Load recipe ingredients and pass to live data
     */
    public void loadRecipeIngredients() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                RecipeIngredientDetails recipe = mRepository.getRecipeIngredientsById(sRecipeId);

                if (recipe == null){
                    Log.d(TAG,"Load recipe ingredients: recipe Null");
                }else{
                    Log.d(TAG,"Load recipe ingredients: recipe found");
                    mLiveRecipeIngredients.postValue(recipe);
                }

            }
        });


    }
}
