<img src="./graphics/icon.png" height="160">

# Dynamic Preferences

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Release](https://img.shields.io/maven-central/v/com.pranavpandey.android/dynamic-preferences)](https://search.maven.org/artifact/com.pranavpandey.android/dynamic-preferences)

**A library to manage shared preferences on Android 4.0 (API 14) and above.**

> [!IMPORTANT]
> It uses [AndroidX][androidx] so, first [migrate][androidx-migrate] your project to AndroidX.
<br/>Since v2.1.0, it is dependent on Java 8 due to the dependency on
[Dynamic Utils][dynamic-utils].
<br/>Since v2.3.1, it is targeting Java 17 to provide maximum compatibility.
<br/>Since v2.4.0, the minimum SDK is Android 4.4 (API 19) to comply with the latest policies.
<br/>Since v2.4.1, the minimum SDK is Android 5.0 (API 21) to comply with the latest policies.

---

## Contents

- [Installation](#installation)
- [Usage](#usage)
    - [Initialize](#initialize)
    - [Save](#save)
    - [Load](#load)
    - [Delete](#delete)
    - [Dependency](#dependency)
- [License](#license)

---

## Installation

It can be installed by adding the following dependency to your `build.gradle` file:

```groovy
dependencies {
    // For AndroidX enabled projects.
    implementation 'com.pranavpandey.android:dynamic-preferences:2.4.1'
}
```

---

## Usage

It provides various methods to `save`, `load` and `delete` keys and preferences. Please read 
below about the various supported operations.

> For a complete reference, please read the [documentation][documentation].

### Initialize

`DynamicPreferences` must be initialized once before accessing its methods.

```java
// Initialize with application context.
DynamicPreferences.initializeInstance(applicationContext);
```

After initializing, its various public methods can be accessed via getting the initialized 
instance.

### Save

It supports the saving of `boolean`, `int`, `String` and `Set<String>` value types into the 
shared preferences. 

```java
// Save a value in the default shared preferences.
DynamicPreferences.getInstance().save(key, value);

// Save a value in the supplied shared preferences.
DynamicPreferences.getInstance().save(preferences, key, value);
```

### Load

It supports the retrieval of `boolean`, `int`, `String` and `Set<String>` value types from the 
shared preferences. 

```java
// Retrieve a value from the default shared preferences.
DynamicPreferences.getInstance().load(key, defaultValue);

// Retrieve a value from the supplied shared preferences.
DynamicPreferences.getInstance().save(preferences, key, defaultValue);
```

### Delete

It supports the deletion of a particular `key` or a complete shared preferences.

```java
// Remove a key from the default shared preferences.
DynamicPreferences.getInstance().delete(key);

// Remove a key from the supplied shared preferences.
DynamicPreferences.getInstance().delete(preferences, key);

// Delete a shared preferences.
DynamicPreferences.getInstance().deleteSharedPreference(preferences);
```

### Dependency

It depends on the [dynamic-utils][dynamic-utils] to perform various internal operations. 
So, its functions can also be used to perform other useful operations.

---

## Author

Pranav Pandey

[![GitHub](https://img.shields.io/github/followers/pranavpandey?label=GitHub&style=social)](https://github.com/pranavpandey)
[![Follow on Twitter](https://img.shields.io/twitter/follow/pranavpandeydev?label=Follow&style=social)](https://twitter.com/intent/follow?screen_name=pranavpandeydev)
[![Donate via PayPal](https://img.shields.io/static/v1?label=Donate&message=PayPal&color=blue)](https://paypal.me/pranavpandeydev)

---

## License

    Copyright 2019-2025 Pranav Pandey

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[androidx]: https://developer.android.com/jetpack/androidx
[androidx-migrate]: https://developer.android.com/jetpack/androidx/migrate
[documentation]: https://pranavpandey.github.io/dynamic-preferences
[dynamic-utils]: https://github.com/pranavpandey/dynamic-utils
