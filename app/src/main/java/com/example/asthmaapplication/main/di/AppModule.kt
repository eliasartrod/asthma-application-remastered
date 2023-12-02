package com.example.asthmaapplication.main.di;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

import com.example.asthmaapplication.main.common.AppPreferences;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {

    @Provides
    @Singleton
    public static AppPreferences providesPreferences(@ApplicationContext Context context, Gson gson) {
        return new AppPreferences(PreferenceManager.getDefaultSharedPreferences(context), gson);
    }

    @Provides
    @Singleton
    public Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    public Gson provideGson() {
        return new Gson();
    }
}
