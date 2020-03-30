package com.udacity.baking.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.udacity.baking.R;


public class IngredientsFragment extends Fragment {

    public IngredientsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_ingredients, container, false);

/*        TextView ingredients = rootView.findViewById(R.id.text_ingredients);

        ingredients.setText("HELLO");*/

        return rootView;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }
}
