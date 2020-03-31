package com.udacity.baking.utilities;



import com.udacity.baking.database.RecipeEntity;
import com.udacity.baking.model.Ingredient;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.model.Step;


import java.util.ArrayList;
import java.util.List;

public class SampleData {
    private static final String SAMPLE_NAME_1 = "CAKE";
    private static final String SAMPLE_NAME_2 = "EGG TART";
    private static final String SAMPLE_NAME_3 = "BROWNIE";





    public static List<RecipeEntity> getSampleRecipeData() {
        List<RecipeEntity> recipe = new ArrayList<>();

        List<Ingredient> ingredient1 = new ArrayList<>();
        ingredient1.add(new Ingredient(1, "1/4 cup", "egg"));
        ingredient1.add(new Ingredient(1, "1/2 cup", "milk"));
        ingredient1.add(new Ingredient(1, "1 cup", "flour"));


        List<Step> step1= new ArrayList<>();
        step1.add(new Step(1,"Short description 1", "description1", "video URL", "thumbnail"));


        recipe.add(new RecipeEntity(110, SAMPLE_NAME_1,  5, "image string"));
        recipe.add(new RecipeEntity(200, SAMPLE_NAME_2,  5, "image string"));



/*        recipe.add(new Recipe(0, SAMPLE_NAME_1, ingredient1, step1, 5, "image string"));
        recipe.add(new Recipe(0, SAMPLE_NAME_2, ingredient1, step1, 5, "image string"));*/



        return recipe;
    }


}
