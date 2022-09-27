## The Goal
Display a list of Videos using themoviedb.org API

### 
if you want to test from Android Studio, you have just to create an api.properties file 
with api_key="YOUR_API_KEY" 
Or put it in local.properties and change file name in app.gradle.kt

A signed APK is available in the Folder app/release [Release](/app/release)

https://9c8cb97.online-server.cloud/gitlab/juszn/moviesdb/-/blob/main/app/release/Movies-1.0(1)-release.apk

# Features

# Home
* Display lists of Movies
* API is paginated, so will use a DataSource for the Header

# Detail
Display Details of a Movie

# Account
## Auth Account
* Feature module independant from Home

## Architecture:
* UI layer, domain layer, data layer
* MVVM
* DI by Hilt
* Jetpack Compose

## Dependencies:
Dependencies and versions are in buildSrc module. buildSrc is the first module compiled during a Gradle build, and its source artifacts are available throughout your entire build script.


## Libraries:
### DI:
    Hilt
### Network:
    OKhttp, Retrofit
### Images:
    Glide

### UI:
    Jetpack Compose

### Tests:
    JUnit, Espresso


# User Authentication
User authentication is controlled with a session_id query parameter. You can generate a session_id by following these steps:
## 1. Create a new request token
## 2. Get the user to authorize the request token
## 3. Create a new  session id with the authorized request token

Creation:
Automatically or using API this is not possible.
Each of them will have to create their own account here on the website.


# Notes:

Set two url for push
    git remote set-url --add --push origin git@github.com:sezn/Movies.git
    git remote set-url --add --push origin git@9xxxx.server.cloud:sezn/moviesdb.git