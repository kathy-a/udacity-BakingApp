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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.udacity.baking.R;
import com.udacity.baking.database.RecipeEntity;
import com.udacity.baking.viewmodel.MainViewModel;


public class RecipeStepsFragment extends Fragment {

    private MainViewModel mViewModel;
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

        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);


        // TODO: replace with id of selected recipe

/*        mViewModel.recipeId.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer recipeId) {
                Log.d(TAG, "Movie id: " + recipeId.toString());

                mViewModel.loadRecipe(recipeId);

            }
        });*/


/*        int temp = mViewModel.tempRecipeId;
        Log.d(TAG, String.valueOf(temp));*/


    // TODO: Update the loading of recipe
/*
        mViewModel.loadRecipe();
*/




        // Observer for main Recipe details e.g. name
        mViewModel.mLiveRecipe.observe(getViewLifecycleOwner(), new Observer<RecipeEntity>() {
            @Override
            public void onChanged(RecipeEntity recipeEntity) {
                if (recipeEntity != null){
                    Log.d(TAG, "recipe found");
                    Log.d(TAG, recipeEntity.getName());
                    recipeStepsView.setText(recipeEntity.getName());
                    // TODO: display data
                }else{
                    Log.d(TAG, "RECIPE NOT FOUND");

                }

            }
        });






    }
}

