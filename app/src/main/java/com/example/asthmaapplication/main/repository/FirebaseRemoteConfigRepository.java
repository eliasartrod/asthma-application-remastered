package com.example.asthmaapplication.main.repository;

import android.app.Application;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class FirebaseRemoteConfigRepository extends Application {
    private Application application;
    private FirebaseRemoteConfig remoteConfig;

    public FirebaseRemoteConfigRepository(Application application) {
        this.application = application;
        this.remoteConfig = FirebaseRemoteConfig.getInstance();
    }

    public void getRemoteConfig() {
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(3600)
                .build();
        remoteConfig.setConfigSettingsAsync(configSettings);

        remoteConfig.fetchAndActivate().addOnCompleteListener(new OnCompleteListener<Boolean>() {
            @Override
            public void onComplete(@NonNull Task<Boolean> task) {
                if (task.isSuccessful()) {
                    // Code to retrieve JSON response config
                }
            }
        });
    }
}
