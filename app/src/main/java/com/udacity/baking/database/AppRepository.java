package com.udacity.baking.database;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.udacity.baking.model.Recipe;
import com.udacity.baking.network.RecipeService;
import com.udacity.baking.network.TheRecipeDbService;
import com.udacity.baking.utilities.SampleData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*Singleton patter*/
public class AppRepository {
    private static AppRepository instance;

    // For local storage
    public List<RecipeEntity> mRecipeList;


    //private List<Recipe> mRecipeList;

    public static AppRepository getInstance() {
        if (instance == null) {
            instance = new AppRepository();
        }
        return instance;
    }

    private AppRepository(){
        mRecipeList = SampleData.getSampleRecipeData();
    }

    // TODO: Retrieving data from a webservice
/*    public List<Recipe> getRecipe() {
        //return SampleData.getSampleRecipeData();

        //initRetrofit();
        // TODO add error handling if response fail
        return mRecipeList;
    }*/



    public LiveData<List<Recipe>> getRecipeList(){
        final MutableLiveData<List<Recipe>> recipeData = new MutableLiveData<>();



        TheRecipeDbService service = RecipeService.getRetrofitInstance().create(TheRecipeDbService.class);

        Call <List<Recipe>> call = service.getData();


        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if(response.isSuccessful()){
                    Log.d("on Response", "Response Successful");
                    recipeData.setValue(response.body());
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


}