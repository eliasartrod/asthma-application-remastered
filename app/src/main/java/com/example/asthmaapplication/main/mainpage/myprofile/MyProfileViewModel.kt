package com.example.asthmaapplication.main.mainpage.myprofile

import androidx.lifecycle.ViewModel
import com.example.asthmaapplication.main.common.AppPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyProfileViewModel @Inject constructor(
    private val _appPreferences: AppPreferences
): ViewModel(){

    val userName: String? = _appPreferences.userName
    val userType: String? = _appPreferences.userType

}