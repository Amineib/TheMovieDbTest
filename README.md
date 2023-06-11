Hello!

This is a test assignment for Android developers.
Your task will be to build a small movie database application based on themoviedb.org's APIs.

This starting project is a barebone project that includes your API keys and a basic network call
meant to verify if the API key is working as expected. You are free to modify this project as much 
as you want as long as you keep using the API keys provided in the gradle build files. 

The API documentation is here:
https://developers.themoviedb.org/3/getting-started/authentication
https://developers.themoviedb.org/3/trending/get-trending
https://developers.themoviedb.org/3/movies/get-top-rated-movies
https://developers.themoviedb.org/3/movies/get-movie-details

Your app should feature:
* A view pager as the main screen
    * The first page featuring the list of the currently trending movies
    * The second page featuring the list of the currently top-rated movies of the week
    * Each movie entry should feature at least:
        * The movie title
        * The average user rating
        * The movie poster
* A movie details screen
    * You should redirect the user to this screen when the user clicks a movie entry in the previous 
      screen 
    * It should feature at least
        * The movie title
        * The average user rating with the vote count
        * The release date
        * The movie poster
        * The synopsis

We will mainly focus on the quality of your app's architecture. You are free to use any of the 
popular development patterns. You are not obligated to use the most up-to-date libraries 
like Compose or Coroutines if you are comfortable with them.  
The project should also be appropriately documented, in English.

The visual look of your app of your app will not be our priority, you don't need to spend too 
much time on it, it just needs to look reasonably clean.

We should be able to run your finished project as is.
You are not required to advertise errors in the UI, but your app should not crash when an error 
occurs. 

Pagination is not required, you can limit yourself to loading only the first page returned 
by the API.

Unit tests are not required but a nice to have.

As for dependencies, you are allowed (but not obligated) to use any of these: 
* Any Jetpack library from Google, including Jetpack Compose and accompanist
* Any Kotlin extension library from Jetbrains including coroutines
* Any dependency injection library 
* Any networking library 
* Any serialization library 
* Any image loading library
* RxJava
