package com.example.asthmaapplication.main.common;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class AppPreferences {
    public static String PREF_USER_NAME = "pref.user.name";
    public static String PREF_USER_TYPE = "pref.user.type";

    private final SharedPreferences preferences;
    private final Gson gson;

    public AppPreferences(SharedPreferences preferences, Gson gson) {
        this.preferences = preferences;
        this.gson = gson;
    }


    public void setUserName(String name) {
        preferences.edit().putString(PREF_USER_NAME, name).apply();
    }

    public void setUserType(String type) {
        preferences.edit().putString(PREF_USER_TYPE, type).apply();
    }

    public String getUserName() {
        return preferences.getString(PREF_USER_NAME, "");
    }

    public String getUserType() {
        return preferences.getString(PREF_USER_TYPE, "");
    }

}
