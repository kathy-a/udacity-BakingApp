package com.udacity.baking.utilities;

import com.udacity.baking.database.RecipeEntity;
import com.udacity.baking.model.Ingredient;
import com.udacity.baking.model.Step;

import java.util.ArrayList;
import java.util.List;

public class RecipeImage {


    public static List<String> getRecipePlaceholder() {
        List<String> imagePlaceholder = new ArrayList<>();

        for(int i = 0; i<10; i++){
            String currentImage = "baking_placeholder_" + String.valueOf(i);
            imagePlaceholder.add(currentImage);

        }


        return imagePlaceholder;
    }

}
