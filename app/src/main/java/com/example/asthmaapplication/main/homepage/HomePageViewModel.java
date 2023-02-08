package com.example.asthmaapplication.main.homepage;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.asthmaapplication.main.repository.AuthenticationRepository;
import com.google.firebase.auth.FirebaseUser;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.annotations.NonNull;

@HiltViewModel
public class HomePageViewModel extends AndroidViewModel {
    private AuthenticationRepository authenticationRepository;
    private MutableLiveData<FirebaseUser> userLiveData;
    private MutableLiveData<Boolean> loggedOutLiveData;

    public HomePageViewModel(@NonNull Application application) {
        super(application);
        authenticationRepository = new AuthenticationRepository(application);
        userLiveData = authenticationRepository.getUserLiveData();
        loggedOutLiveData = authenticationRepository.getLoggedOutLiveData();
    }

    public void login(String emailAddress, String password) {
        authenticationRepository.login(emailAddress, password);
        loggedOutLiveData.setValue(false);
    }

    public void register(String emailAddress, String password) {
        authenticationRepository.register(emailAddress, password);
    }

    public void clearCache() {
        loggedOutLiveData.setValue(true);
        userLiveData.setValue(null);
        authenticationRepository.logOut();
    }

    public MutableLiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }
}
