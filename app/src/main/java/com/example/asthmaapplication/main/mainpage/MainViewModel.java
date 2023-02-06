package com.example.asthmaapplication.main.mainpage;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.asthmaapplication.main.common.ConfigurationHelper;
import com.example.asthmaapplication.main.repository.AuthenticationRepository;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.annotations.NonNull;

@HiltViewModel
public class MainViewModel extends AndroidViewModel {
    private AuthenticationRepository authenticationRepository;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<Boolean> loggedOutLiveData;
    private String userEmail;

    public MainViewModel(@NonNull Application application) {
        super(application);
        authenticationRepository = new AuthenticationRepository(application);
        userLiveData = authenticationRepository.getUserLiveData();
        loggedOutLiveData = authenticationRepository.getLoggedOutLiveData();
        userEmail = authenticationRepository.getUserEmail();
    }

    public void logOut() {
        userLiveData.setValue(null);
        loggedOutLiveData.setValue(true);
        authenticationRepository.logOut();
    }

    public MutableLiveData<Boolean> getLoggedOutLiveData() {
        return loggedOutLiveData;
    }


}