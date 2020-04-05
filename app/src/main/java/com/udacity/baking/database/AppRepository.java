package com.udacity.baking.database;


import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.baking.model.Ingredient;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.model.Step;
import com.udacity.baking.network.RecipeService;
import com.udacity.baking.network.TheRecipeDbService;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*Singleton patter*/
public class AppRepository {
    private static AppRepository instance;

    // For local storage
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    private static DatabaseHelper mDbHelper;


    private List<Recipe> mRecipeList;

    public static AppRepository getInstance(Context context) {
        if (instance == null) {
            instance = new AppRepository(context);
        }
        return instance;
    }

    private AppRepository(Context context){
       // mLocalRecipeList = SampleData.getSampleRecipeData();
        //Instantiate to allow db commands
        mDb = AppDatabase.getInstance(context);

        mDbHelper = new DatabaseHelper(mDb);
    }




    // Get Recipelist from webservice
    public LiveData<List<Recipe>> getRecipeList(){
        final MutableLiveData<List<Recipe>> recipeData = new MutableLiveData<>();

        final TheRecipeDbService service = RecipeService.getRetrofitInstance().create(TheRecipeDbService.class);

        Call <List<Recipe>> call = service.getData();


        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> recipeResponse) {
                if(recipeResponse.isSuccessful()){
                    Log.d("on Response", "Response Successful");
                    recipeData.setValue(recipeResponse.body());


                }else{
                    Log.d("on Response", "Response Fail");
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable retrofitError) {
                retrofitError.printStackTrace();
                Log.d("on Failure", "Response Fail");

            }
        });


        return recipeData;

    }

    // TODO: FUTURE maybe combine the retrofit pojo and database pojo
    public void addRecipeData(List<Recipe> recipes) {

        for(int i =0; i < recipes.size(); i++){
            String recipeName, recipeImage;
            final int recipeId, recipeServings;

            recipeName = recipes.get(i).getName();
            recipeImage = recipes.get(i).getImage();
            recipeId = recipes.get(i).getId();
            recipeServings = recipes.get(i).getServings();

            // Setting the main data for recipe
            final RecipeEntity recipe = new RecipeEntity(recipeId, recipeName, recipeServings, recipeImage);


            // Convert recipe steps to recipe steps entity
            List<Step> steps;
            steps = recipes.get(i).getSteps();

            int stepId;
            String shortDescription, description, videoURL, thumbnailURL;

            for(int j =0; j < steps.size(); j++) {
                stepId = steps.get(j).getId();
                shortDescription = steps.get(j).getShortDescription();
                description = steps.get(j).getDescription();
                videoURL = steps.get(j).getVideoURL();
                thumbnailURL = steps.get(j).getThumbnailURL();

/*                Log.d("Steps", String.valueOf(stepId));
                Log.d("Steps", shortDescription);
                Log.d("Steps", description);
                Log.d("Steps", videoURL);
                Log.d("Steps", thumbnailURL);*/

                // Setting Recipe steps
                RecipeStepsEntity currentStep ;
                currentStep =  new RecipeStepsEntity(recipeId, stepId, shortDescription, description, videoURL, thumbnailURL);
                recipe.setSteps(currentStep);
            }

            
            // Convert recipe ingredients to recipe ingredients entity
            List<Ingredient> ingredients;
            ingredients = recipes.get(i).getIngredients();

            double quantity;
            String measure, ingredient;

            for(int j =0; j < ingredients.size(); j++) {
                quantity = ingredients.get(j).getQuantity();
                measure = ingredients.get(j).getMeasure();
                ingredient = ingredients.get(j).getIngredient();

                RecipeIngredientsEntity currentIngredient;
                currentIngredient = new RecipeIngredientsEntity(recipeId, quantity, measure,  ingredient);
                recipe.setIngredients(currentIngredient);
            }





            // Insert Recipe in local db
            executor.execute(new Runnable(){
                @Override
                public void run() {
                    mDbHelper.saveRecipe(recipe);
                }
            });


        }

    }




    public RecipeStepDetails getRecipeById(int recipeId) {
        return mDb.recipeDao().getRecipeById(recipeId);

    }








    // Get all recipes
    public LiveData<List<RecipeStepDetails>> getRecipes() {
        return mDb.recipeDao().getRecipes();
    }
}