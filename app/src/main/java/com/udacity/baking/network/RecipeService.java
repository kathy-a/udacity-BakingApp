package com.udacity.baking.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeService {
    private static Retrofit sRetrofit;
    private static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net";


    public static Retrofit getRetrofitInstance() {

        if (sRetrofit == null) {
            sRetrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }

}

