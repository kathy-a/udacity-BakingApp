# Baking Recipes App

## Project Overview
This app displays a recipe list from a network resource. Recipe ingredients, steps description and videos are displayed once a specific recipe is selected.

## Implementation details:
- Structured code in Model–view–viewmodel (MVVM) architectural pattern principle.
- Application uses Master Detail Flow to display recipe steps and navigation between them.
- Ingredients, step description and videos have individual fragments.
- Recipe videos are played via Exoplayer(MediaPlayer). 
- Homescreen widget is populated with ingredient list for desired recipe.
- Utilized Espresso to write concise, and reliable Android User Interface tests. 

## Libraries
- [Retrofit](https://github.com/square/retrofit)
- [Picasso](https://github.com/square/picasso)
- [Room](https://developer.android.com/topic/libraries/architecture/room)
- [Espresso](https://developer.android.com/training/testing/espresso/)
- [ExoPlayer](https://github.com/google/ExoPlayer)

## Others
- Free images from [Pixabay](https://pixabay.com/) & [Unsplash](https://unsplash.com/)


## Requirements
Stable release versions of all libraries, Gradle, and Android Studio.
