# KRUSH
Krush is an android native application where students can book tutoring session based on the tutor's hourly rate, availability, and overall rating. Students can record their tutoring sessions and revisit their previous conversations. Tutors can also build their own profile and reputation through Krush. All payment transactions are taken care of by Krush, making it simple and easy to book a new tutor. 

## Table Of Contents
- Code Contribution Guidelines
- Setup Development Environment
- Virtual Device Testing
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

## 3rd Party References
- [1] Prolific Interactive, "Prolific Interactive material-calendarview," in GitHub, GitHub, 2016. [Online]. Available: https://github.com/prolificinteractive/material-calendarview. Accessed: Feb. 8, 2017.
- [2]	M. Brandao, "Fredoka One," in Google fonts, Google. [Online]. Available: https://fonts.google.com/specimen/Fredoka+One?selection.family=Fredoka+One. Accessed: Mar. 4, 2017.