package com.udacity.baking.fragment;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.baking.R;
import com.udacity.baking.RecipeAppWidget;
import com.udacity.baking.database.RecipeIngredientDetails;
import com.udacity.baking.database.RecipeIngredientsEntity;

import com.udacity.baking.ui.IngredientViewAdapter;
import com.udacity.baking.viewmodel.DetailViewModel;

import java.io.Serializable;
import java.util.List;


public class IngredientsFragment extends Fragment {

    private TextView ingredientsView;
    private DetailViewModel mDetailViewModel;
    private static final String TAG = "INGREDIENTS FRAGMENT";

    private RecyclerView mRecyclerView;
    private IngredientViewAdapter mAdapter;

    private View rootView;

    public IngredientsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the fragment layout
        rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewMode();


    }

    private void initViewMode() {
        mDetailViewModel = ViewModelProviders.of(this)
                .get(DetailViewModel.class);

        mDetailViewModel.loadRecipeIngredients();

        mDetailViewModel.mLiveRecipeIngredients.observe(getViewLifecycleOwner(), new Observer<RecipeIngredientDetails>() {
            @Override
            public void onChanged(RecipeIngredientDetails recipeIngredientDetails) {
                if(recipeIngredientDetails != null){
                    Log.d(TAG, "recipeIngredientDetails: " + "recipe found");

                    List<RecipeIngredientsEntity> recipeIngredients = recipeIngredientDetails.getIngredients();
                    String recipeName = recipeIngredientDetails.getRecipeEntity().getName();

                    // Pass recipe ingredients to recyclerview
                    initRecyclerView(recipeIngredients);

                    // Update widget with the new ingredients
                    UpdateWidget(recipeIngredients, recipeName);

                }else{
                    Log.d(TAG, "RECIPE NOT FOUND");

                }
            }
        });

    }

    private void UpdateWidget( List<RecipeIngredientsEntity> recipeIngredients, String recipeName) {

        Class destinationActivity = RecipeAppWidget.class;
        Context context = getActivity();

        Intent intent = new Intent(context, destinationActivity);
        intent.putExtra("INGREDIENTS", (Serializable) recipeIngredients);
        intent.putExtra("recipeName", recipeName );
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        if (context != null) {
            context.sendBroadcast(intent);
        }



    }


    private void initRecyclerView(List<RecipeIngredientsEntity> recipeIngredients) {
        mRecyclerView = rootView.findViewById(R.id.recycler_fragment_ingredientSteps);
        mAdapter = new IngredientViewAdapter(this, recipeIngredients);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(RecipeStepsFragment.class));


        LinearLayoutManager verticalLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(verticalLayoutManager);

        // Add divider to recyclerview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                verticalLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

    }
}
