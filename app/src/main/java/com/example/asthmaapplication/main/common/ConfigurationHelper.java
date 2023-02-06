package com.example.asthmaapplication.main.common;

import android.content.SharedPreferences;

public class ConfigurationHelper {
    public static String USER_EMAIL;

    private final SharedPreferences preferences;

    public ConfigurationHelper(SharedPreferences preferences) {
        this.preferences = preferences;
    }

    public void setUserEmail(String userEmail) {
        USER_EMAIL = userEmail;
    }

    public String getUserEmail() {
        return USER_EMAIL;
    }
}
