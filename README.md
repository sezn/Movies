## The Goal
Display a list of Videos using themoviedb.org API

## Features
# Home
* Display lists of Movies
* API is paginated, so will use a DataSource

# Detail
Display Details of a Movie

# Account
## Create Account
* should be a feature, maybe a module independant from Home

## Architecture:
* ui layer, domain layer, data layer
* MVVM for UI
* DI by Hilt
* Jetpack Compose

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
## 3. Create a new  session id with the athorized request token


Automatically or using API this is not possible.
Each of them will have to create their own account here on the website.
