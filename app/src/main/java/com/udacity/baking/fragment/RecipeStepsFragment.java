package com.udacity.baking.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.udacity.baking.R;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.utilities.SampleData;
import com.udacity.baking.viewmodel.MainViewModel;


public class RecipeStepsFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView recipeStepsView;
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

       // recipeStepsView.setText(mViewModel.getRecipe().getValue().getName());


        // TODO: REMOVE TEMPORARY
       // mViewModel.setRecipe(SampleData.getSampleRecipeData().get(0));

/*        mViewModel.getRecipe().observe(getViewLifecycleOwner(), new Observer<Recipe>() {
            @Override
            public void onChanged(Recipe recipe) {
                recipeStepsView.setText(recipe.getName());


            }
        });*/


    }
}

