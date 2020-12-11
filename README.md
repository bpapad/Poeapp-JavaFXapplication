# Poeapp-JavaFXapplication
An application including another personal web-parsing application(https://github.com/bpapad/Web_Scraping_App), which use:  Java 11, Jsoup (lib), JavaFX, Scenebuilder, SQL

After login this applications interface includes my other web-parsing application also included in my github profile (https://github.com/bpapad/Web_Scraping_App).

1- Each user user can create his own account using the Registration form (adds the user data inside the database provided).

2- Providing his credentials, a user logs in using the Login form (checks if the data provided refer to a user and returns the userID from the database).

3- After login the user enters his individual interface which: a) Loads a video from the internet(WebView).
                                                               b) Loads a table with the choices the user makes using the other app(Tableview + web-parsing app).
                                                               c) Displays the interface of the web-parsing app.

4- When chosen, the user logs out and the app waits for another user input to login.
