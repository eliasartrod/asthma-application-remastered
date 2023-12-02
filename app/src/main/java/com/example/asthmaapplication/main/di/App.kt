package com.example.asthmaapplication.main.di;

import android.app.Application;
import android.content.Context;

import com.example.asthmaapplication.main.common.AppPreferences;

import javax.inject.Inject;

import dagger.hilt.android.HiltAndroidApp;
import io.reactivex.disposables.CompositeDisposable;

@HiltAndroidApp
public class App extends Application {
    @Inject
    AppPreferences appPrefs;

    CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        disposable.dispose();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}
