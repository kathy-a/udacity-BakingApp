package com.udacity.baking.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.baking.R;
import com.udacity.baking.StepsActivity;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.RecipeActivity;

import java.util.List;

public class RecipeViewAdapter extends RecyclerView.Adapter<RecipeViewAdapter.ViewHolder>{

    private Context mContext;
    private List<Recipe> mRecipe;

    public RecipeViewAdapter(Context mContext, List<Recipe> mRecipe) {
        this.mContext = mContext;
        this.mRecipe = mRecipe;
    }





    // Required for RecyclerView. Responsible for inflating the view / recycling the viewholder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    // Required for RecyclerView. Changes depends on what layouts are
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String recipeName = mRecipe.get(position).getName();
        holder.textFoodName.setText(recipeName);
    }

    // Required for RecyclerView
    @Override
    public int getItemCount() { return mRecipe != null ? mRecipe.size() : 0; }


    // Holds widget in memory for each individual entry
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textFoodName;
        RelativeLayout parentLayout;

        //Constructor required for Viewholder
        public ViewHolder(View itemView){
            super(itemView);
            textFoodName = itemView.findViewById(R.id.text_recipeName);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            // TODO: Uncomment once the video fragment is implemented
/*
            Class destinationActivity = RecipeActivity.class;

            Intent intent = new Intent(mContext, destinationActivity);

            intent.putExtra("recipeId", mRecipe.get(position).getId());
*/


            // TODO: remove temporary activity launch
            Class destinationActivity = StepsActivity.class;

            Intent intent = new Intent(mContext, destinationActivity);


            mContext.startActivity(intent);

        }
    }


}

