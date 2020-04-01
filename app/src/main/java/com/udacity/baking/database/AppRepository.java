package com.udacity.baking.database;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.baking.model.Recipe;
import com.udacity.baking.network.RecipeService;
import com.udacity.baking.network.TheRecipeDbService;
import com.udacity.baking.utilities.SampleData;

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


    public void addRecipeData(List<Recipe> recipes) {

        for(int i =0; i < recipes.size(); i++){
            String recipeName, recipeImage;
            final int recipeId, recipeServings;

            recipeName = recipes.get(i).getName();
            recipeImage = recipes.get(i).getImage();
            recipeId = recipes.get(i).getId();
            recipeServings = recipes.get(i).getServings();

            final RecipeEntity recipe = new RecipeEntity(recipeId, recipeName, recipeServings, recipeImage);

            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // mDb.recipeDao().insertAll(mLocalRecipeList);
                    mDb.recipeDao().insertRecipe(recipe);
                }
            });


        }

    }




    public RecipeEntity getRecipeById(int recipeId) {
        return mDb.recipeDao().getRecipeById(recipeId);

    }
}