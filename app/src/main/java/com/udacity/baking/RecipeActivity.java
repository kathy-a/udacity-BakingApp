package com.udacity.baking;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.udacity.baking.fragment.IngredientsFragment;
import com.udacity.baking.fragment.RecipePageAdapter;
import com.udacity.baking.fragment.RecipeStepsFragment;
import com.google.android.material.tabs.TabLayout;
import com.udacity.baking.fragment.IngredientsFragment;
import com.udacity.baking.fragment.RecipeStepsFragment;

public class RecipeActivity extends AppCompatActivity {

    private RecipePageAdapter mRecipePageAdapter;
    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mRecipePageAdapter = new RecipePageAdapter(getSupportFragmentManager()) ;

        // Set up the ViewPager with the recipe adapter
        mViewPager = findViewById(R.id.view_pager_container);
        setupViewPage(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabs_recipe);
        tabLayout.setupWithViewPager(mViewPager);

    }

    private void setupViewPage(ViewPager viewPager){
        RecipePageAdapter adapter = new RecipePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecipeStepsFragment(), "STEPS");
        adapter.addFragment(new IngredientsFragment(), "INGREDIENTS");

        // TODO ADD Fragment if needed

        viewPager.setAdapter(adapter);
    }

}


