package com.example.asthmaapplication.main.common;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

public class AppPreferences {
    public static String USER_NAME = "user.name";
    public static String USER_PREFERENCES = "user.prefs";
    public static String PREF_FCM_TOKEN = "fcm.token";
    public SharedPreferences preferences;
    Context context;

    public AppPreferences(Context context) {
        preferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE);
    }
    public void setPreferences(String userPreference) {
        USER_PREFERENCES = userPreference;
    }

    public void setUserName(String userName) {
        USER_NAME = userName;
    }

    public void setFcmToken(String token) {
        preferences.edit().putString(PREF_FCM_TOKEN, token).apply();
    }

    public String getUserName() {
        return USER_NAME;
    }

    public SharedPreferences getPreferences() {
        return context.getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
    }

    public String getFcmToken() {
        return preferences.getString(PREF_FCM_TOKEN, "");
    }
}
