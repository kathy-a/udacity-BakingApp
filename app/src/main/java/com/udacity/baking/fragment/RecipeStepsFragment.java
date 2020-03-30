package com.udacity.baking.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.udacity.baking.R;


public class RecipeStepsFragment extends Fragment {

    public RecipeStepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

/*        TextView ingredients = rootView.findViewById(R.id.text_ingredients);

        ingredients.setText("HELLO");*/

        return rootView;
    }
}

