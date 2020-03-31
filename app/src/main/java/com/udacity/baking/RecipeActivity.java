package com.udacity.baking;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.udacity.baking.fragment.IngredientsFragment;
import com.udacity.baking.fragment.RecipePageAdapter;
import com.udacity.baking.fragment.RecipeStepsFragment;
import com.google.android.material.tabs.TabLayout;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.utilities.SampleData;
import com.udacity.baking.viewmodel.MainViewModel;

public class RecipeActivity extends AppCompatActivity {

    private RecipePageAdapter mRecipePageAdapter;
    private ViewPager mViewPager;
    private MainViewModel mViewModel;

    private static Recipe mRecipeSelected ;
    private static final String TAG = "RecipeActivity";
    private int movieId;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

       getMovieId();
       initViewModel();

        mRecipePageAdapter = new RecipePageAdapter(getSupportFragmentManager()) ;

        // Set up the ViewPager with the recipe adapter
        mViewPager = findViewById(R.id.view_pager_container);
        setupViewPage(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs_recipe);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void getMovieId() {
        Intent intent = getIntent();

        if(intent != null){
            //mRecipeSelected = (Recipe) intent.getSerializableExtra("recipe");


            movieId = intent.getIntExtra("recipeId",-1);
            Log.d(TAG, String.valueOf(movieId));

        }else{
            Log.d(TAG, "Intent null");
        }
    }


    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);
        mViewModel.setRecipe(SampleData.getSampleRecipeData().get(0));

        //mViewModel.setRecipeId(movieId);


    }


    private void setupViewPage(ViewPager viewPager){
        RecipePageAdapter adapter = new RecipePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecipeStepsFragment(), "STEPS");
        adapter.addFragment(new IngredientsFragment(), "INGREDIENTS");

        // TODO ADD Fragment if needed

        viewPager.setAdapter(adapter);
    }

}


