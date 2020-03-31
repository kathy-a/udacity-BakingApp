package com.udacity.baking.network;


// Interface for Retrofit Implementation


import com.udacity.baking.model.Recipe;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TheRecipeDbService {
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call <List<Recipe>> getData();

}

