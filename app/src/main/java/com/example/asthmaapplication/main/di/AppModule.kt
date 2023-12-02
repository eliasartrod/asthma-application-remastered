package com.example.asthmaapplication.main.di

import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.app.Application
import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import com.example.asthmaapplication.main.common.AppPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesPreferences(@ApplicationContext context: Context?, gson: Gson?): AppPreferences {
        return AppPreferences(PreferenceManager.getDefaultSharedPreferences(context), gson!!)
    }

    @Provides
    @Singleton
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }


    @Provides
    fun provideGson(): Gson {
        return Gson()
    }
}