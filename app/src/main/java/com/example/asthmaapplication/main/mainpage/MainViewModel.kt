package com.example.asthmaapplication.main.mainpage

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import com.example.asthmaapplication.main.common.AppPreferences

@HiltViewModel
class MainViewModel @Inject constructor(
    private val _appPreferences: AppPreferences
) : ViewModel() {

    val userName: String? = _appPreferences.userName
    val userType: String? = _appPreferences.userType

    fun logOut() {
        _appPreferences.userName = null
        _appPreferences.userType = null
    }
}