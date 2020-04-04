package com.udacity.baking.fragment;

import android.os.Bundle;
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
import com.udacity.baking.model.Recipe;
import com.udacity.baking.viewmodel.MainViewModel;


public class IngredientsFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView ingredientsView;


    public IngredientsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

        ingredientsView = rootView.findViewById(R.id.text_ingredients);

/*        TextView ingredients = rootView.findViewById(R.id.text_ingredients);

        ingredients.setText("HELLO");*/

        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

/*        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);


        mViewModel.mLiveRecipe.observe(getViewLifecycleOwner(), new Observer<RecipeEntity>() {
            @Override
            public void onChanged(RecipeEntity recipeEntity) {

            }
        });*/

       // ingredientsView.setText(mViewModel.getRecipe().getValue().getName());




    }
}
