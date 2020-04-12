package com.udacity.baking.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.udacity.baking.R;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.RecipeActivity;
import com.udacity.baking.utilities.RecipeImage;

import java.util.List;
import java.util.Random;

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
        holder.textRecipeName.setText(recipeName);

        String recipeImage = mRecipe.get(position).getImage();



        // Check if recipe image has URL resource. If there's none, add a placeholder image
        if(recipeImage.isEmpty()){
            List<String> imagePlaceholder = RecipeImage.getRecipePlaceholder();
            String recipeImagePlaceholder;

            // Randomize count if more than 10 recipes
            if(mRecipe.size() > 9){
                Random rand = new Random();
                // nextInt as provided by Random is exclusive of the top value so you need to add 1
                int randomNum = rand.nextInt(9) + 1;

                recipeImagePlaceholder = imagePlaceholder.get(randomNum);

            } else{
                recipeImagePlaceholder = imagePlaceholder.get(position);
            }

            Resources res = holder.itemView.getContext().getResources();
            holder.imageRecipe.setImageResource(res.getIdentifier(recipeImagePlaceholder, "drawable", mContext.getPackageName()));

        }else{
            Picasso.get()
                    .load(recipeImage)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(holder.imageRecipe);
        }
        
    }

    // Required for RecyclerView
    @Override
    public int getItemCount() { return mRecipe != null ? mRecipe.size() : 0; }


    // Holds widget in memory for each individual entry
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textRecipeName;
        ImageView imageRecipe;
        RelativeLayout parentLayout;

        //Constructor required for Viewholder
        public ViewHolder(View itemView){
            super(itemView);
            textRecipeName = itemView.findViewById(R.id.text_recipeName);
            parentLayout = itemView.findViewById(R.id.parent_layout);
            imageRecipe = itemView.findViewById(R.id.image_recipe);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            // TODO: Uncomment once the video fragment is implemented
            Class destinationActivity = RecipeActivity.class;

            Intent intent = new Intent(mContext, destinationActivity);

            intent.putExtra("recipeId", mRecipe.get(position).getId());
            intent.putExtra("recipeName", mRecipe.get(position).getName());

            // Set default step position to 0 / first item;
            StepsViewAdapter.index = 0;

            mContext.startActivity(intent);

        }
    }


}

