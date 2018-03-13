[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

# Dashbug

Android library that lets developers modify configuration class(es) at runtime.

## Demo

![Alt Text](https://raw.githubusercontent.com/jelic98/dashbug/master/doc/demo.gif)

## Usage

Gradle dependency:

```java
    TODO: Publish to MavenCentral
```

Construction:

```java
    // provide configuration class
    Dashbug db = new Dashbug(AppConfig.class);
    // show notification
    db.start(this, getApplicationContext());
```

Getting all fields:

```java
    Map<String, String> fields = db.getFields();
    for(String name : fields.keySet()) {
        Log.i(TAG, "Name: " + name + ", Value: " + fields.get(name));
    }
```

Setting single field:

```java
    db.setField("COUNT", "123");
```
