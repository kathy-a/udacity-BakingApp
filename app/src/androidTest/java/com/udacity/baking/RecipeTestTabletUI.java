package com.udacity.baking;


import android.app.Activity;
import android.app.Instrumentation;

import androidx.test.espresso.ViewInteraction;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.Intents.intending;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static androidx.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;


/**
 *  Test UI of Recipe details. Works on bigger screens / tablet
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class RecipeTestTabletUI {

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
    public void recipeTestTabletUI() {
        //Wait to Load screen
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Scroll to 3rd recipe in the Recyclerview
        onView(ViewMatchers.withId(R.id.recycler_MainActivity)).perform(RecyclerViewActions.scrollToPosition(2));

        //Select 3rd recipe in the Recyclerview
        onView(withId(R.id.recycler_MainActivity)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));

        //Check if intent (Main Activity to RecipeActivity) passed intent extra
        intended(hasExtraWithKey("recipeName"));
        intended(hasExtraWithKey("recipeId"));


        //Scroll to recipe steps in the Recyclerview
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(11));
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(1));
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(6));
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(2));


        //Select ingredients menu
        onView(ViewMatchers.withId(R.id.item_ingredients)).perform(ViewActions.click());

        //Pause exoplayer
        onView(ViewMatchers.withId(R.id.exo_pause)).perform(ViewActions.click());


        //Scroll to 3rd Step for Yellow Cake recipe in the Recyclerview & verify description
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(2));
        onView(withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
        onView(ViewMatchers.withId(R.id.text_selectedStep)).check(matches(withText("2. Combine the cake flour, 400 grams (2 cups) of sugar, baking powder, and 1 teaspoon of salt in the bowl of a stand mixer. Using the paddle attachment, beat at low speed until the dry ingredients are mixed together, about one minute")));

        //Scroll to 13th Step for Yellow Cake recipe in the Recyclerview & verify description
        onView(ViewMatchers.withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.scrollToPosition(12));
        onView(withId(R.id.recycler_fragment_recipeSteps)).perform(RecyclerViewActions.actionOnItemAtPosition(12, click()));
        onView(ViewMatchers.withId(R.id.text_selectedStep)).check(matches(withText("13. Frost your cake! Use a serrated knife to cut each cooled cake layer in half (so that you have 4 cake layers). Frost in between the layers, the sides of the cake, and the top of the cake. Then eat it!")));






    }
}
