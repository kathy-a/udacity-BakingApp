package com.udacity.baking;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;

import com.udacity.baking.database.AppDatabase;
import com.udacity.baking.database.RecipeDao;
import com.udacity.baking.database.RecipeEntity;
import com.udacity.baking.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseTest {
    private static final String TAG = "Junit";
    private AppDatabase db;
    private RecipeDao dao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();

        // Create db in memory
        db = Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class).build();

        dao = db.recipeDao();
        Log.d(TAG, "Create database");
    }

    @After
    public void closeDb() {
        db.close();
        Log.d(TAG, "Close database");
    }

    @Test
    public void createAndRetrieveMovie() {
        dao.insertAll(SampleData.getSampleRecipeData());
        int count = dao.getCount();
        Log.d(TAG, "createAndRetrieveNotes: " + SampleData.getSampleRecipeData());
        Log.d(TAG, "createAndRetrieveNotes: count=" + count);
        Log.d(TAG, "createAndRetrieveNotes: count=" + SampleData.getSampleRecipeData().size());

        assertEquals(SampleData.getSampleRecipeData().size(), count);
    }

    @Test
    public void compareStrings() {
        dao.insertAll(SampleData.getSampleRecipeData());
        String original = SampleData.getSampleRecipeData().get(0).getName();

        RecipeEntity fromDb = dao.getRecipeById2(100);

        assertEquals(original, fromDb.getName());

    }
}
