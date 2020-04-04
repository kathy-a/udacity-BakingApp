package com.udacity.baking.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.udacity.baking.R;
import com.udacity.baking.database.RecipeStepDetails;
import com.udacity.baking.database.RecipeStepsEntity;
import com.udacity.baking.viewmodel.DetailViewModel;

import java.util.List;


public class RecipeStepsFragment extends Fragment {

    private DetailViewModel mDetailViewModel;
    private TextView recipeStepsView;

    private static final String TAG = "STEPS FRAGMENT";
    public RecipeStepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        recipeStepsView = rootView.findViewById(R.id.text_recipeSteps);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDetailViewModel = ViewModelProviders.of(this)
                .get(DetailViewModel.class);



        mDetailViewModel.loadRecipeSteps();


        // Observer for main Recipe details e.g. name

        mDetailViewModel.mLiveRecipe.observe(getViewLifecycleOwner(), new Observer<RecipeStepDetails>() {

            @Override
            public void onChanged(RecipeStepDetails recipeStepDetails) {
                if(recipeStepDetails != null){
                    Log.d(TAG, "recipe found");

                    List<RecipeStepsEntity> recipeSteps = recipeStepDetails.getSteps();

                    String shortDescription;

                    for(int i = 0; i < recipeSteps.size(); i++){
                        shortDescription = recipeSteps.get(i).getShortDescription();
                        Log.d(TAG, shortDescription);

                    }


                }else{
                    Log.d(TAG, "RECIPE NOT FOUND");

                }
            }
        });






/*        mDetailViewModel.mLiveRecipe.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(RecipeEntity recipeEntity) {
                if (recipeEntity != null){
                    Log.d(TAG, "recipe found");
                    Log.d(TAG, recipeEntity.getName());
                    recipeStepsView.setText(recipeEntity.getName());

                    List<RecipeStepsEntity> recipeSteps = recipeEntity.getSteps();


                    String shortDescription;
                    shortDescription = recipeSteps.get(0).getShortDescription();
                    Log.d(TAG, shortDescription);

                    // TODO: display data , Possibly pass to recyclerview
                }else{
                    Log.d(TAG, "RECIPE NOT FOUND");

                }

            }
        });*/






     }
}

