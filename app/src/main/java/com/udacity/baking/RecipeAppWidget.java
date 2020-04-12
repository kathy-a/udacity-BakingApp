package com.udacity.baking;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.udacity.baking.database.RecipeIngredientsEntity;
import com.udacity.baking.viewmodel.DetailViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeAppWidget extends AppWidgetProvider {


    private static DetailViewModel mDetailViewModel;
    private static final String TAG = "WIDGET - INGREDIENTS";
    private static final String INGREDIENTS = "Ingredients";

    private String mRecipeName = "";
    private List<RecipeIngredientsEntity> mRecipeIngredients = new ArrayList<>();


     void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {






        String ingredientName, ingredientMeasurement;

        ingredientName = "Egg";
        ingredientMeasurement = "1 dozen";



        // Create a list for ingredients
         StringBuilder ingredientBuilder = new StringBuilder();
         StringBuilder measurementBuilder = new StringBuilder();

         for (int i = 0; i < mRecipeIngredients.size(); i++) {
             String currentIngredient = mRecipeIngredients.get(i).getIngredient();
             String currentMeasurement = String.valueOf(mRecipeIngredients.get(i).getQuantity());
             currentMeasurement = currentMeasurement + " " + mRecipeIngredients.get(i).getMeasure();

             ingredientBuilder.append(currentIngredient + "\n" + "\n");
             measurementBuilder.append(currentMeasurement + "\n" + "\n");
         }




         RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_app_widget);



         // Update widget details if there's data
         if(mRecipeIngredients != null){
            //views.setTextViewText(R.id.widget_text_ingredientName, mRecipe);
            views.setTextViewText(R.id.widget_text_ingredient_measurement, measurementBuilder.toString());
            views.setTextViewText(R.id.widget_text_ingredientName, ingredientBuilder.toString());

            mRecipeName = mRecipeName + " " + INGREDIENTS;
            views.setTextViewText(R.id.widget_text_header, mRecipeName);

        }





        // Launch the app when the widget is clicked
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.widget_text_header, pendingIntent);





        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }




    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {

            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if(intent != null) {
            mRecipeIngredients = (List<RecipeIngredientsEntity>) intent.getSerializableExtra("INGREDIENTS");
            mRecipeName = intent.getStringExtra("recipeName");

            Log.d(TAG, "onReceive: " + mRecipeIngredients.get(0).getIngredient());


            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            ComponentName componentName = new ComponentName(context, RecipeAppWidget.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName), R.id.widget_text_ingredientName);
            this.onUpdate(context,appWidgetManager,appWidgetManager.getAppWidgetIds(componentName));
        }

    }




    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

