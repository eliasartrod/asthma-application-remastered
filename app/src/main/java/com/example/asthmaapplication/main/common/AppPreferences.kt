package com.example.asthmaapplication.main.common

import android.content.SharedPreferences
import com.google.gson.Gson

class AppPreferences(private val preferences: SharedPreferences, private val gson: Gson) {
    var userName: String?
        get() = preferences.getString(PREF_USER_NAME, "")
        set(name) {
            preferences.edit().putString(PREF_USER_NAME, name).apply()
        }
    var userType: String?
        get() = preferences.getString(PREF_USER_TYPE, "")
        set(type) {
            preferences.edit().putString(PREF_USER_TYPE, type).apply()
        }

    companion object {
        var PREF_USER_NAME = "pref.user.name"
        var PREF_USER_TYPE = "pref.user.type"
    }
}