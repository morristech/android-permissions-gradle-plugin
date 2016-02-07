# Android Permissions Gradle Plugin

## Introduction

Since Android Marshmallow, developers have to [request permissions](http://developer.android.com/guide/topics/security/permissions.html) the Android SDK considers dangerous, aka [Dangerous Permissions](http://developer.android.com/guide/topics/security/permissions.html#normal-dangerous).

## Goal

This plugin generates a class to help with that task. It parses the Android Manifest on build and generates a helper class with the list of the Dangerous Permissions plus a few utility methods.

## Example

If your manifest contains those three permissions:
```xml
    <uses-permission android:name="android.permission.GET_ACCOUNTS" />
    <uses-permission android:name="android.permission.INTERNET" />
    <uses-permission android:name="android.permission.READ_PHONE_STATE" />
```

Based on that list, this plugin will generate this helper class named PermissionsHelper:

```java
public class PermissionsHelper {
    public static final String GET_ACCOUNTS = Manifest.permission.GET_ACCOUNTS;
    public static final String READ_PHONE_STATE = Manifest.permission.READ_PHONE_STATE;
    public static final String [] REQUIRED_PERMISSIONS = {
        GET_ACCOUNTS,
        READ_PHONE_STATE
    };
    (...)
}
```
For each dangerous permission, a reference is generated with its name so you can forget where those permissions really are, and a static array containing all the dangerous permissions.
Note that you can see here only two declared permissions as the permissions android.permission.INTERNET is a normal permission.

