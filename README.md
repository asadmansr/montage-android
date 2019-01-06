# Montage for Android
Montage helps designers and developers test out their ideas.

[![Build Status](https://travis-ci.com/asadmansr/montage-android.svg?branch=master)](https://travis-ci.com/asadmansr/montage-android)

### Introduction
Montage is an Android application that creates and saves random user generated information for designers and developers to test their ideas. This could be the user's contact information, specific application's functionality and images.

Montage is built with Kotlin along side with the Android SDK using the MVVM Android architecture. Currently, the application randomly generates:

```
- User Picture
- Full Name
```

### Demo
Check out the demo of what has been completed so far in the [repository's wiki](https://github.com/asadmansr/montage-android/wiki/Demo) :rocket:.

### Development
Montage is currently under development. Feel free to keep an eye out for any ongoing work under the [repository's issue](https://github.com/asadmansr/montage-android/issues).

### Testing
To run tests on Montage, you can execute the following commands from your command line.

###### Check Build
```bash
# Checks build and lint
$ ./gradlew build check

Test results: path_to_app/app/build/reports/lint-results.html
```

###### Unit Test
```bash
# Execute local unit tests
$ ./gradlew test

Test results: path_to_app/app/build/reports/tests/ directory
```

###### Instrumented Test
```bash
# Start Android emulator
$ ./emulator -avd <name_of_emulator>

# Execute instrumented tests
$ ./gradlew connectedAndroidTest

Test results: path_to_app/app/build/reports/androidTests/connected/ directory
```

### License
This software is licensed under [MIT](https://github.com/asadmansr/montage-android/blob/master/LICENSE.md) © 2019 Asad Mansoor
