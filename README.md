# Gradle for Android and Java Final Project

In this project, I created an app with multiple flavors that uses
multiple libraries and Google Cloud Endpoints. The finished app will consist
of four modules. A Java library that provides jokes, a Google Cloud Endpoints
(GCE) project that serves those jokes, an Android Library containing an
activity for displaying jokes, and an Android app that fetches jokes from the
GCE module and passes them to the Android Library for display.

## Project Components

* Project contains a Java library for supplying jokes
* Project contains an Android library with an activity that displays jokes passed to it as intent extras.
* Project contains a Google Cloud Endpoints module that supplies jokes from the Java library. Project loads jokes from GCE module via an async task.
* Project contains connected tests to verify that the async task is indeed loading jokes.
* Project contains paid/free flavors. The paid flavor has no ads, and no unnecessary dependencies.
* Make the free app variant display interstitial ads between the main activity and the joke-displaying activity.
* Have the app display a loading indicator while the joke is being fetched from the server.

### Main Page

<img src="https://user-images.githubusercontent.com/26672993/44983277-4311df00-af96-11e8-9cf7-42bff4ed48ea.png" width="300" height="550"/>   <img src="https://user-images.githubusercontent.com/26672993/44983292-4f963780-af96-11e8-8e85-ce7213b9b0bc.png" width="300" height="550"/>

### Loading indicator
<img src="https://user-images.githubusercontent.com/26672993/44983319-60df4400-af96-11e8-8310-ffe2aefb98c8.png" width="300" height="550"/>

### Jokes Page
<img src="https://user-images.githubusercontent.com/26672993/44983354-7f453f80-af96-11e8-9ac3-bbe5d1df82c0.png" width="300" height="550"/>  <img src="https://user-images.githubusercontent.com/26672993/44983363-8409f380-af96-11e8-9b16-824a9b9518fe.png" width="300" height="550"/>


### Backend

<img width="734" alt="backend" src="https://user-images.githubusercontent.com/26672993/44983684-a81a0480-af97-11e8-906b-b2a2c73fbdab.png">


### Credits

https://stackoverflow.com/questions/32655342/how-to-randomly-receive-a-material-design-color
https://www.flaticon.com/free-icon/joke_185034
https://www.uplabs.com/posts/design-quotes-app
https://romannurik.github.io/AndroidAssetStudio/index.html
https://stackoverflow.com/questions/9963691/android-asynctask-sending-callbacks-to-ui
https://stackoverflow.com/questions/2321829/android-asynctask-testing-with-android-test-framework
