# uLesson
Task for Android developer role.

[![Android CI](https://github.com/Victor-El/uLesson/actions/workflows/android.yml/badge.svg)](https://github.com/Victor-El/uLesson/actions/workflows/android.yml)

## Architecture
This application was built using MVVM design patter with clean architecture

Presentation Layer -> Domain Layer -> Data Layer

Each of these layer ware modularized to gain the advantages of a modularized application such as faster builds and seperation of concern so that each layer is not
tightly coupled to another layer.

The Data layer contained database entities DAOs and the database class alongside the repository and Retrofit web service.
The caching of "My Lessons" is done in this layer in the repository class.

The Domain layer contains business logic such as use-cases and domain models. Use-cases are an abstraction layer over the repository, they are meant to perform a 
meaningful action a user can take and usually can integrate with multiple repository call in order to fufil a "Use case".

The presentation layer is where all the UI logic goes into. Classes and objects such as the ViewModel, Adapters, Activities and Fragment live in this layer.

This project was designed with clean architecture in mind, in order to make it easily possible for service to be easily swapable and overall codebase maintainable.

<img src="https://miro.medium.com/max/1400/1*SjczBI6N688JKSiBiYoTcA.png" width="70%" vspace="10" hspace="10">

## Continuous Integration
GitHub Actions is being used for continuous integration; build, run unit tests and generate artifact

## Features
* Kotlin
* Coroutines
* Clean Architecture with MVVM
* Jetpack Navigation Component
* Dagger Hilt,
* LiveData,
* Unit testing

## Libraries
*   [Retrofit 2](https://square.github.io/retrofit/) : A type-safe HTTP client for Android and Java
*   [OkHttp Logging Interceptor](https://github.com/square/okhttp/tree/master/okhttp-logging-interceptor) : An OkHttp interceptor which logs HTTP request and response data.
*   [Gson](https://github.com/google/gson) : An Object-to-JSON serialization/deserialization library
*   [Kotlin Coroutines](https://developer.android.com/kotlin/coroutines) : A coroutine is a concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
*   [Jetpack Navigation component](https://developer.android.com/guide/navigation/navigation-dynamic)
*   [Glide](https://github.com/bumptech/glide) : Glide is a fast and efficient open source media management and image loading framework for Android
*   [ShimmerLayout](https://github.com/facebook/shimmer-android) : Shimmer is an Android library that provides an easy way to add a shimmer effect to any view in your Android app.
*   [Paging 3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) : The Paging library helps you load and display pages of data from a larger dataset from local storage or over network
*   [Dagger Hilt](https://dagger.dev/hilt) : Hilt provides a standard way to incorporate Dagger dependency injection into an Android application.
*   [Material Design Components](https://material.io)
*   [Room](https://developer.android.com/training/data-storage/room) : The Room persistence library provides an abstraction layer over SQLite to allow fluent database access while harnessing the full power of SQLite.

<h2 align="left">Screenshots</h2>
<h4 align="center">
<img src="https://user-images.githubusercontent.com/34776187/133760658-bb2db435-0b42-4d84-ab8a-b01693384567.png" width="30%" vspace="10" hspace="10">
<img src="https://user-images.githubusercontent.com/34776187/133760667-fb308ced-ec23-4211-9778-36a8b0eb028b.png" width="30%" vspace="10" hspace="10">
<img src="https://user-images.githubusercontent.com/34776187/133760672-fbc38640-1c60-4ae9-a466-401c2f7a15bd.png" width="30%" vspace="10" hspace="10">

## Author
Elezua Victor

## License
This project is licensed under the Apache MIT License - See: https://github.com/Victor-El/Wally/blob/master/LICENSE


