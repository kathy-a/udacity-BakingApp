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


public class RecipeStepsFragment extends Fragment implements StepsViewAdapter.ViewHolder.OnStepListener {

    private DetailViewModel mDetailViewModel;
    private TextView recipeStepsView;

    private RecyclerView mRecyclerView;
    private StepsViewAdapter mAdapter;
    private List<RecipeStepsEntity> recipeSteps;
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

        initViewModel();

        // TODO: Possibly add the call to the select recipe
        //selectRecipeStep(0);
     }

    private void initViewModel() {
        mDetailViewModel = ViewModelProviders.of(this)
                .get(DetailViewModel.class);

        mDetailViewModel.loadRecipeSteps();


        // Observer for main Recipe details e.g. name
        mDetailViewModel.mLiveRecipeSteps.observe(getViewLifecycleOwner(), new Observer<RecipeStepDetails>() {
            @Override
            public void onChanged(RecipeStepDetails recipeStepDetails) {
                if(recipeStepDetails != null){
                    Log.d(TAG, "recipeStepDetails: " + "recipe found");


                     recipeSteps = recipeStepDetails.getSteps();

                    //Log.d(TAG, "onChanged: " + recipeSteps.get(0).getVideoURL());
                    // Pass recipe steps to recyclerview
                    initRecyclerView(recipeSteps);

                    // Set default video to be the first step
                    selectRecipeStep(0);

                }else{
                    Log.d(TAG, "RECIPE NOT FOUND");

                }
            }
        });

    }


    private void initRecyclerView(List<RecipeStepsEntity> recipeSteps) {
        mRecyclerView = rootView.findViewById(R.id.recycler_fragment_recipeSteps);
        mAdapter = new StepsViewAdapter(this, recipeSteps,this);
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


    @Override
    public void onStepClick(int position) {
        Log.d(TAG, "onStepClick: " + String.valueOf(position));
        mAdapter.notifyDataSetChanged();

        selectRecipeStep(position);
    }

    private void selectRecipeStep(final int position) {
        String videoUrl, step;
        videoUrl = recipeSteps.get(position).getVideoURL();
        step = recipeSteps.get(position).getDescription();

        // If video URL is empty, assign thumbnail url regardless if it's empty as well
        if(videoUrl.isEmpty()){
            videoUrl = recipeSteps.get(position).getThumbnailURL();
        }


        mDetailViewModel.setStepVideoURL(videoUrl);

        mDetailViewModel.setsStepSelected(step);


        mDetailViewModel.getsVideoURL().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String urlString) {
                Log.d(TAG, "onChanged of URL position:" + String.valueOf(position) + " " +  urlString);
            }
        });
    }


}

