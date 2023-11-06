package com.example.asthmaapplication.main.common;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class AppPreferences {
    public static String PREF_USER_NAME = "pref.user.name";

    private final SharedPreferences preferences;
    private final Gson gson;

    public AppPreferences(SharedPreferences preferences, Gson gson) {
        this.preferences = preferences;
        this.gson = gson;
    }


    public void setUserName(String name) {
        preferences.edit().putString(PREF_USER_NAME, name).apply();
    }

    public String getUserName() {
        return preferences.getString(PREF_USER_NAME, "");
    }

}
