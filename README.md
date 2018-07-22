[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/jelic98/dashbug.svg)](https://jitpack.io/#jelic98/dashbug)
[![API](https://img.shields.io/badge/API-19%2B-orange.svg?style=flat)](https://android-arsenal.com/api?level=19)
[![Android Arsenal]( https://img.shields.io/badge/Android%20Arsenal-Dashbug-brightgreen.svg?style=flat)]( https://android-arsenal.com/details/1/6891)

# Dashbug

Android library that lets developers modify configuration class(es) at runtime. Useful in situations when app's behaviour changes depending on fields in configuration class like theme switcher, user credentials, various flags, etc. Developers can alter these fields without recompiling source code. 

![App icon](https://raw.githubusercontent.com/jelic98/dashbug/master/doc/icon.png)

This awesome icon was made by [@iqbalhood](https://github.com/iqbalhood).

## Demo

![Demo preview](https://raw.githubusercontent.com/jelic98/dashbug/master/doc/demo.gif)

## Installing

1. Add repository in root ```build.gradle```

```gradle
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

2. Add the dependency

```gradle
    dependencies {
        compile 'com.github.jelic98:dashbug:1.0.0'
    }
```

## Usage

```java
    Dashbug db = new Dashbug(AppConfig.class); // Provide configuration class
    db.start(this, getApplicationContext()); // Show notification
```
