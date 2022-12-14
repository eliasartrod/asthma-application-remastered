package com.example.asthmaapplication.main.main;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.asthmaapplication.main.repository.AuthenticationRepository;
import com.google.firebase.auth.FirebaseUser;


import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.annotations.NonNull;

@HiltViewModel
public class UserViewModel extends AndroidViewModel {
    private AuthenticationRepository authenticationRepository;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<Boolean> loggedOutLiveData;

    public UserViewModel(@NonNull Application application) {
        super(application);
        authenticationRepository = new AuthenticationRepository(application);
        userLiveData = authenticationRepository.getUserLiveData();
    }

    public void login(String emailAddress, String password) {
        authenticationRepository.login(emailAddress, password);
    }

    public void logOut() {
        authenticationRepository.logOut();
    }

    public void register(String emailAddress, String password) {
        authenticationRepository.register(emailAddress, password);
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOutLiveData;
    }
}
