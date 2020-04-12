package com.udacity.baking;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.udacity.baking.database.RecipeIngredientDetails;
import com.udacity.baking.database.RecipeIngredientsEntity;
import com.udacity.baking.database.RecipeStepDetails;
import com.udacity.baking.database.RecipeStepsEntity;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.ui.RecipeViewAdapter;
import com.udacity.baking.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecipeViewAdapter mAdapter;
    private static final String TAG = "MAIN ACTIVITY";
    public static boolean sIsTablet;



    private MainViewModel mViewModel;
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set device orientation supported
        boolean allowRotation = getResources().getBoolean(R.bool.portrait_only);
        if(allowRotation){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            sIsTablet = false;
        }else{
            sIsTablet = true;
        }




        initViewModel();



    }




    private void initViewModel() {
        // set the reference for view model
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        // Get and observe recipelist from webservice
        mViewModel.getRecipeListObservable().observe(this, new Observer<List<Recipe>>() {
                    @Override
                    public void onChanged(List<Recipe> recipes) {
                        if (recipes != null){
                            initRecyclerView(recipes);
                            // Add recipe to DB
                            addRecipeList(recipes);
                        }
                    }
                }
        );

        //logDisplayRecipeSteps();
        //logDisplayRecipeIngredients();

    }


    // Display recipe steps in log
    private void logDisplayRecipeSteps() {
        // Get recipeStep from DB
        mViewModel.getRecipeStepDetails().observe(this, new Observer<List<RecipeStepDetails>>() {
            @Override
            public void onChanged(List<RecipeStepDetails> recipeStepDetails) {
                if(recipeStepDetails != null){
                    String recipeName;
                    recipeName = recipeStepDetails.get(1).getRecipeEntity().getName();
                    Log.d(TAG, recipeName);


                    List<RecipeStepsEntity> recipeSteps = recipeStepDetails.get(0).getSteps();

                    for(int i = 0; i < recipeSteps.size(); i++){
                        String description;
                        description = recipeSteps.get(i).getDescription();
                        Log.d(TAG, description);
                    }

                }else{
                    Log.d(TAG, "Recipe Step details null");
                }
            }
        });

    }

    // Display recipe ingredients in log
    private void logDisplayRecipeIngredients() {
        mViewModel.getRecipeIngredientDetails().observe(this, new Observer<List<RecipeIngredientDetails>>() {
            @Override
            public void onChanged(List<RecipeIngredientDetails> recipeIngredientDetails) {
                if(recipeIngredientDetails != null){

                   List<RecipeIngredientsEntity> recipeIngredients = recipeIngredientDetails.get(1).getIngredients();

                   for(int i = 0; i < recipeIngredients.size(); i++){
                       String ingredient;
                       ingredient = recipeIngredients.get(i).getIngredient();
                       Log.d(TAG, "Ingredient: " + ingredient);
                   }
                }else{
                    Log.d(TAG, "Recipe Ingredient details null");

                }
            }
        });
    }



    // Add recipe list to local storage
    private void addRecipeList(List<Recipe> recipes) {
        mViewModel.addRecipeData(recipes);
    }


    private void initRecyclerView(List<Recipe> recipeList) {
        mRecyclerView = findViewById(R.id.recycler_MainActivity);
        mAdapter = new RecipeViewAdapter(this, recipeList);

        int gridsize;

        if(sIsTablet){
            gridsize = 4;
        }else{
            gridsize = 2;
        }
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridsize));
        mRecyclerView.setAdapter(mAdapter);
    }


}

