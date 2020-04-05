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
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.baking.R;
import com.udacity.baking.database.RecipeStepDetails;
import com.udacity.baking.database.RecipeStepsEntity;
import com.udacity.baking.ui.StepsViewAdapter;
import com.udacity.baking.viewmodel.DetailViewModel;

import java.util.List;


public class RecipeStepsFragment extends Fragment {

    private DetailViewModel mDetailViewModel;
    private TextView recipeStepsView;

    private RecyclerView mRecyclerView;
    private StepsViewAdapter mAdapter;
    View rootView;

    private static final String TAG = "STEPS FRAGMENT";
    public RecipeStepsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the fragment layout
        rootView = inflater.inflate(R.layout.fragment_recipe_steps, container, false);

        //recipeStepsView = rootView.findViewById(R.id.text_recipeSteps);


        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mDetailViewModel = ViewModelProviders.of(this)
                .get(DetailViewModel.class);



        mDetailViewModel.loadRecipeSteps();


        // Observer for main Recipe details e.g. name

        mDetailViewModel.mLiveRecipeSteps.observe(getViewLifecycleOwner(), new Observer<RecipeStepDetails>() {

            @Override
            public void onChanged(RecipeStepDetails recipeStepDetails) {
                if(recipeStepDetails != null){
                    Log.d(TAG, "recipeStepDetails: " + "recipe found");

                    List<RecipeStepsEntity> recipeSteps = recipeStepDetails.getSteps();

                    // Pass recipe steps to recyclerview
                    initRecyclerView(recipeSteps);

                }else{
                    Log.d(TAG, "RECIPE NOT FOUND");

                }
            }
        });








     }


    private void initRecyclerView(List<RecipeStepsEntity> recipeSteps) {
        mRecyclerView = rootView.findViewById(R.id.recycler_fragment_recipeSteps);
        mAdapter = new StepsViewAdapter(this, recipeSteps);
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

