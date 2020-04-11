package com.udacity.baking.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.udacity.baking.R;
import com.udacity.baking.viewmodel.DetailViewModel;

/**
 * Display the selected step description
 */
public class SelectedStepFragment extends Fragment {

    private View rootView;
    private DetailViewModel mDetailViewModel;
    private static final String TAG = "SELECTED STEP FRAGMENT";




    public SelectedStepFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_selected_step, container, false);

        return rootView;


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initViewModel();


    }

    private void initViewModel() {
        mDetailViewModel = ViewModelProviders.of(this)
                .get(DetailViewModel.class);

        mDetailViewModel.sStepSelected.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String recipeStep) {
                Log.d(TAG, "selected step: " + recipeStep);

                TextView textStepDescription = rootView.findViewById(R.id.text_selectedStep);
                textStepDescription.setText(recipeStep);
            }
        });
    }
}
