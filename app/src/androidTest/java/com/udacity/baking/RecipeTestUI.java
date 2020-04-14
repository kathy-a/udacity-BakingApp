package com.udacity.baking;


import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;


import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.rule.IntentsTestRule;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNot.not;


/**
 *  Test UI of Recipe details. Currently selecting the first recipe. Works on small screens
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeTestUI {

    @Rule
    public IntentsTestRule<MainActivity> mActivityRule = new IntentsTestRule<>(
            MainActivity.class);



    @Before
    public void stubAllExternalIntents() {
        // By default Espresso Intents does not stub any Intents. Stubbing needs to be setup before
        // every test run. In this case all external Intents will be blocked.
        intending(not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    }


    @Test
    public void recipeTestUI() {
        //Wait to Load screen
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Scroll to first recipe in the Recyclerview
        onView(ViewMatchers.withId(R.id.recycler_MainActivity)).perform(RecyclerViewActions.scrollToPosition(0));

        //Select first recipe in the Recyclerview
        onView(withId(R.id.recycler_MainActivity)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        //Check if intent (Main Activity to RecipeActivity) passed intent extra
        intended(hasExtraWithKey("recipeName"));
        intended(hasExtraWithKey("recipeId"));


        //Scroll to recipe steps in the Recyclerview
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(5));
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(6));
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(2));


        //Select ingredients tab
        onView(ViewMatchers.withId(R.id.tabs_recipe)).perform(ViewActions.click());
        onView(withContentDescription("INGREDIENTS")).perform(ViewActions.click());

        //Scroll to different ingredients in the Recyclerview
        onView(ViewMatchers.withId(R.id.recycler_fragment_ingredientSteps)).perform(RecyclerViewActions.scrollToPosition(8));
        onView(ViewMatchers.withId(R.id.recycler_fragment_ingredientSteps)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(ViewMatchers.withId(R.id.recycler_fragment_ingredientSteps)).perform(RecyclerViewActions.scrollToPosition(9));
        onView(ViewMatchers.withId(R.id.recycler_fragment_ingredientSteps)).perform(RecyclerViewActions.scrollToPosition(2));


        //Pause exoplayer
        onView(ViewMatchers.withId(R.id.exo_pause)).perform(ViewActions.click());



    }
}
