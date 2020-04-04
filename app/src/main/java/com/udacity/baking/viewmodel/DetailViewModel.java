package com.udacity.baking.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.udacity.baking.database.AppRepository;
import com.udacity.baking.database.RecipeStepDetails;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DetailViewModel extends AndroidViewModel {

    public static int sRecipeId;
    private AppRepository mRepository;
    private Executor executor = Executors.newSingleThreadExecutor();
    private static final String TAG = "Detail View Model";
    public MutableLiveData<RecipeStepDetails> mLiveRecipe =
            new MutableLiveData<RecipeStepDetails>();


    public DetailViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());

    }

    /**
     *  Set the recipe ID of recipe selected
     * @param sRecipeId
     */
    public static void setsRecipeId(int sRecipeId) {
        DetailViewModel.sRecipeId = sRecipeId;
    }

    public void loadRecipeSteps(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "Movie ID selected: " + String.valueOf(sRecipeId));

                RecipeStepDetails recipe = mRepository.getRecipeById(sRecipeId);

                if (recipe == null){
                    Log.d(TAG,"Load recipe Steps: recipe Null");
                }else{
                    Log.d(TAG,"Load recipe Steps: recipe found");
                    mLiveRecipe.postValue(recipe);

                }
            }
        });
    }



}
