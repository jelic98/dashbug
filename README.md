[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![](https://jitpack.io/v/jelic98/dashbug.svg)](https://jitpack.io/#jelic98/dashbug)

# Dashbug

Android library that lets developers modify configuration class(es) at runtime. Useful in situations when app's behaviour changes depending on fields in configuration class like theme switcher, user credentials, various flags, etc. Developers can alter these fields without recompiling source code. 

## Demo

![Alt Text](https://raw.githubusercontent.com/jelic98/dashbug/master/doc/demo.gif)

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

Construction

```java
    // provide configuration class
    Dashbug db = new Dashbug(AppConfig.class);
    // show notification
    db.start(this, getApplicationContext());
```

Getting single field

```java
    String value = db.getField("COUNT");
```

Getting all fields

```java
    Map<String, String> fields = db.getFields();
    for(String name : fields.keySet()) {
        Log.i(TAG, "Name: " + name + ", Value: " + fields.get(name));
    }
```

Setting single field

```java
    db.setField("COUNT", "123");
```
