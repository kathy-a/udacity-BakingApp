package com.udacity.baking.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.udacity.baking.R;
import com.udacity.baking.database.RecipeIngredientsEntity;
import com.udacity.baking.fragment.IngredientsFragment;

import java.util.List;

public class IngredientViewAdapter extends  RecyclerView.Adapter<IngredientViewAdapter.ViewHolder>{

    private static final String TAG = "IngredientViewAdapter";

    private IngredientsFragment mContext;
    private List<RecipeIngredientsEntity> mRecipeIngredients;

    public IngredientViewAdapter(IngredientsFragment mContext, List<RecipeIngredientsEntity> mRecipeIngredients) {
        this.mContext = mContext;
        this.mRecipeIngredients = mRecipeIngredients;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recipe_ingredients, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String ingredientName = mRecipeIngredients.get(position).getIngredient();
        String ingredientQuantity = String.valueOf(mRecipeIngredients.get(position).getQuantity());
        String ingredientMeasurement = mRecipeIngredients.get(position).getMeasure();
        String quantityMeasurement = ingredientQuantity + " " + ingredientMeasurement;

        holder.textIngredientName.setText(ingredientName);
        holder.textIngredientMeasurement.setText(quantityMeasurement);

    }

    @Override
    public int getItemCount() {
        return mRecipeIngredients != null ? mRecipeIngredients.size() : 0;
    }


    // Holds widget in memory for each individual entry
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textIngredientName;
        TextView textIngredientQuantity;
        TextView textIngredientMeasurement;
        RelativeLayout parentLayout;

        //Constructor required for Viewholder
        public ViewHolder(View itemView){
            super(itemView);
            textIngredientName = itemView.findViewById(R.id.text_ingredientName);
            //textIngredientQuantity = itemView.findViewById(R.id.text_ingredient_quantity);
            textIngredientMeasurement = itemView.findViewById(R.id.text_ingredient_quantity_measurement);
            parentLayout = itemView.findViewById(R.id.layout_ingredients);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Log.d(TAG, String.valueOf(position));

        }
    }

}
