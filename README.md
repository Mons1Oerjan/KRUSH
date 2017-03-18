# KRUSH
Krush is an android native application where students can book tutoring session based on the tutor's hourly rate, availability, and overall rating. Students can record their tutoring sessions and revisit their previous conversations. Tutors can also build their own profile and reputation through Krush. All payment transactions are taken care of by Krush, making it simple and easy to book a new tutor. 

## Table Of Contents
- Setup Development Environment
- Virtual Device Testing
- Code Contribution Guidelines
- 3rd Party References

## Setup Development Environment
1. Clone this github repository: `git clone https://github.com/ericdesj/KRUSH.git`
2. Open this project in Android Studios
3. We are using sdk21, and Gradle will most likely throw some build errors. Make sure that `build.gradle` (app: modules) looks like the code snippet below. On the errors displayed in the console, click on the `Sync repository, install dependencies` link, and you're all set!
build.gradle:
```
apply plugin: 'com.android.application'

android {
    compileSdkVersion 25
    buildToolsVersion "25.0.2"
    defaultConfig {
        applicationId "cs.dal.krush"
        minSdkVersion 21
        targetSdkVersion 25
        versionCode 1
        versionName "1.0"
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
    sourceSets { main { java.srcDirs = ['src/main/java', 'src/main/java/cs.dal.krush/helpers'] } }
}

dependencies {
    compile fileTree(include: ['*.jar'], dir: 'libs')
    androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
        exclude group: 'com.android.support', module: 'support-annotations'
    })
    compile 'com.android.support:appcompat-v7:25.1.0'
    compile 'com.prolificinteractive:material-calendarview:1.4.2'
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:design:25.2.0'
    compile 'com.android.support:support-core-utils:25.2.0'
}
```

## Virtual Device Testing
To test the mobile application, you have to run it on a phone VM that runs sdk21. Click on `create new virtual device`, select the phone you wish to run it on (e.g. Nexus 5), click next, select the `x86 images` tab, and select `Lollipop 21 x86-64 Android 5.0`. Click next and finish, and you're all set! 

## Code Contribution Guidelines
This project uses the following coding guidelines, format, and conventions:
- Curly brackets are placed on the same line as the function header.
```
public class foo() {
  if (bool) {
    //notice curly brackets on the same line.
  } else {
    //notice else statement on the same line as the curly bracket.
  }
}
```
- JavaDoc above every class and function header.
```
/**
 * Function that ___.
 */
```

## KRUSH Database 
We are currently using SQLite along with Adnroid's DBHelper class for data storage / retrieval. To view the ER Diagram of the KRUSH Database, [Click here](https://drive.google.com/open?id=0B4S0P8KE9IBoQVFrN2hRU0JILVE).

### DB Migrations and Seeding
We are currently only running the DB seeders on the first time the application gets opened on the emulator (we don't want to run seeders on every application launch, as this would duplicate the data each time the app was tested).

### DB Reset
We currently don't have a way to reset the DB. However, if you wish to reset the DB and rerun the seeders, you should follow these steps:
1. Go to Tools --> Android --> AVD Manager
2. In the "Actions" column, click on the dropdown arrow button.
3. Click on `Wipe Data`. This will wipe all cached data on your emulator, including the pre-seeded data for KRUSH. 
4. Rerun the app on the emulator. You will now reseed the DB.

## UML Diagram and Architectural Overview
[Click here](https://drive.google.com/open?id=0B_oCfPfOVbvWUV9GdHV5SXNPV1k) to take a look at KRUSH's UML Diagram. This will help you, as a developer, to get a full understanding of the architectural overview on how the activities and fragments are setup.

## 3rd Party References
- [1] Prolific Interactive, "Prolific Interactive material-calendarview," in GitHub, GitHub, 2016. [Online]. Available: https://github.com/prolificinteractive/material-calendarview. Accessed: Feb. 8, 2017.
- [2] M. Brandao, "Fredoka One," in Google fonts, Google. [Online]. Available: https://fonts.google.com/specimen/Fredoka+One?selection.family=Fredoka+One. Accessed: Mar. 4, 2017.
- [3] "How to get all possible combinations of a list’s elements?", Stackoverflow.com, 2017. [Online]. Available: http://stackoverflow.com/questions/464864/how-to-get-all-possible-combinations-of-a-list-s-elements. [Accessed: 08- Mar- 2017].
- [4] C. (n.d.). Codepath/android_guides. Retrieved March 12, 2017, from https://github.com/codepath/android_guides/wiki/Populating-a-ListView-with-a-CursorAdapter
- [5] List View. (n.d.). Retrieved March 12, 2017, from https://developer.android.com/guide/topics/ui/layout/listview.html
- [6] How to disable BottomNavigationView shift mode? (n.d.). Retrieved March 12, 2017, from http://stackoverflow.com/questions/40176244/how-to-disable-bottomnavigationview-shift-mode
- [7] Android column '_id' does not exist? (n.d.). Retrieved March 12, 2017, from http://stackoverflow.com/questions/3359414/android-column-id-does-not-exist
- [8]"CSSAuthor", Cssauthor.com, 2017. [Online]. Available: http://www.cssauthor.com/wp-content/uploads/2014/03/Credit-Card-Icons.jpg. Accessed: 18- Mar- 2017.
- [9]D. J, "android on Text Change Listener", Stackoverflow.com, 2017. [Online]. Available: http://stackoverflow.com/questions/20824634/android-on-text-change-listener. Accessed: 18- Mar- 2017.