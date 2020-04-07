package com.udacity.baking.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.baking.R;
import com.udacity.baking.RecipeActivity;
import com.udacity.baking.database.RecipeStepsEntity;
import com.udacity.baking.fragment.RecipeStepsFragment;
import com.udacity.baking.model.Recipe;

import java.util.List;

public class StepsViewAdapter extends RecyclerView.Adapter<StepsViewAdapter.ViewHolder> {

    private RecipeStepsFragment mContext;
    private List<RecipeStepsEntity> mRecipeSteps;
    private static final String TAG = "StepViewAdapter";
    private int index = -1;


    public StepsViewAdapter(RecipeStepsFragment mContext, List<RecipeStepsEntity> mRecipeSteps) {
        this.mContext = mContext;
        this.mRecipeSteps = mRecipeSteps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_steps, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String recipeDescription = mRecipeSteps.get(position).getDescription();
        holder.textRecipeDescription.setText(recipeDescription);

        // Change item background color
        if(index==position){
            holder.textRecipeDescription.setBackgroundColor(Color.parseColor("#FFFFFF"));

        }else{
            holder.textRecipeDescription.setBackgroundColor(Color.parseColor("#FEEAE6"));

        }

    }

    @Override
    public int getItemCount() { return mRecipeSteps != null ? mRecipeSteps.size() : 0; }



    // Holds widget in memory for each individual entry
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textRecipeDescription;
        RelativeLayout parentLayout;

        //Constructor required for Viewholder
        public ViewHolder(View itemView){
            super(itemView);
            textRecipeDescription = itemView.findViewById(R.id.text_recipeStep);
            parentLayout = itemView.findViewById(R.id.layout_recipeSteps);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d(TAG, String.valueOf(position));

            index = position;
            notifyDataSetChanged();
        }
    }


}
