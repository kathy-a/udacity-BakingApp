package com.udacity.baking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.udacity.baking.fragment.IngredientsFragment;
import com.udacity.baking.fragment.RecipePageAdapter;
import com.udacity.baking.fragment.RecipeStepsFragment;
import com.google.android.material.tabs.TabLayout;
import com.udacity.baking.fragment.SelectedStepFragment;
import com.udacity.baking.fragment.VideoFragment;
import com.udacity.baking.model.Recipe;
import com.udacity.baking.viewmodel.DetailViewModel;

public class RecipeActivity extends AppCompatActivity {

    private RecipePageAdapter mRecipePageAdapter;
    private ViewPager mViewPager;
    private DetailViewModel mDetailViewModel;

    private static Recipe mRecipeSelected ;
    private static final String TAG = "RecipeActivity";
    private int recipeId;
    //public static boolean sIsTablet;
    private DrawerLayout mDrawer;




    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);




        // Initialize fragment details so it can be reused on small & big screens
        VideoFragment videoFragment = new VideoFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();



        /**
         *  Adjust layout of recipe details depending on screen size
         */
        // Set device orientation supported
        boolean allowRotation = getResources().getBoolean(R.bool.portrait_only);
        if(allowRotation){             // For smaller screens

            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


            /**
             * Setup the tab layout
             */
            mRecipePageAdapter = new RecipePageAdapter(getSupportFragmentManager()) ;

            // Set up the ViewPager with the recipe adapter
            mViewPager = findViewById(R.id.view_pager_container);
            setupViewPage(mViewPager);

            TabLayout tabLayout = findViewById(R.id.tabs_recipe);
            tabLayout.setupWithViewPager(mViewPager);


            if(savedInstanceState == null) {
                // Add the fragment to its container using a FragmentManager and a Transaction
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_video_container, videoFragment)
                        .commit();
            }


        }else{ // For larger screens

            //Drawer layout style
            mDrawer = findViewById(R.id.drawer_layout);

            // Only create new fragments when there is no previously saved state
            if(savedInstanceState == null) {
                RecipeStepsFragment stepsFragment = new RecipeStepsFragment();
                SelectedStepFragment selectedStepFragment = new SelectedStepFragment();
                IngredientsFragment ingredientsFragment = new IngredientsFragment();

                // Add the fragment to its container using a FragmentManager and a Transaction
                fragmentManager.beginTransaction()
                        .add(R.id.fragment_video_container, videoFragment)
                        .add(R.id.fragment_recipeSteps_container, stepsFragment)
                        .add(R.id.fragment_step_selected_container, selectedStepFragment)
                        .add(R.id.nav_view, ingredientsFragment)
                        .commit();

            }


        }

        initViewModel();


    }


    // Display Menu layout created
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(MainActivity.sIsTablet){
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_category, menu);
            return true;
        }else{
            return false;
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Open the drawer for ingredients for larger screens
        mDrawer.openDrawer(GravityCompat.END);
        return true;
    }



    private void getMovieId() {
        Intent intent = getIntent();

        if(intent != null){
            recipeId = intent.getIntExtra("recipeId",-1);
            Log.d(TAG, String.valueOf(recipeId));

            DetailViewModel.setsRecipeId(recipeId);
            String recipeName = intent.getStringExtra("recipeName");

            // Set title of activity
            setTitle(recipeName);

        }else{
            Log.d(TAG, "Intent null");
        }
    }


    private void initViewModel() {
        mDetailViewModel = ViewModelProviders.of(this)
                .get(DetailViewModel.class);

        getMovieId();


    }


    private void setupViewPage(ViewPager viewPager){
        RecipePageAdapter adapter = new RecipePageAdapter(getSupportFragmentManager());
        adapter.addFragment(new RecipeStepsFragment(), "STEPS");
        adapter.addFragment(new IngredientsFragment(), "INGREDIENTS");

        viewPager.setAdapter(adapter);
    }




}


