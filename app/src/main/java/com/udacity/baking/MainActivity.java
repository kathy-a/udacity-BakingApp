package com.udacity.baking;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

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


    private MainViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*        RecipeFragment ingredientFragment = new RecipeFragment();

        // Add the fragment to its container using the FragmentManager and a transaction
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.frame_ingredients_container,ingredientFragment)
                .commit();*/

        // TODO: remove temporary placeholder
/*        final Button button = findViewById(R.id.button_id);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button

                Intent intent = new Intent(MainActivity.this, RecipeActivity.class);
                startActivity(intent);


            }
        });*/

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

        //logDisplayRecipe();

    }

    private void logDisplayRecipe() {
        // TODO: check where to put the getting of data
        // Get recipeStep from DB
        mViewModel.getRecipes().observe(this, new Observer<List<RecipeStepDetails>>() {
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

    // Add recipe list to local storage
    private void addRecipeList(List<Recipe> recipes) {

        mViewModel.addRecipeData(recipes);

    }


    private void initRecyclerView(List<Recipe> recipeList) {
        mRecyclerView = findViewById(R.id.recycler_MainActivity);
        mAdapter = new RecipeViewAdapter(this, recipeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }


}

