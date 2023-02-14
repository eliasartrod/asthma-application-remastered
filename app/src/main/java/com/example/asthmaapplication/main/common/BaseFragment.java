package com.example.asthmaapplication.main.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment {
    public static final String USER_PREFS = "user.prefs";
    public static final String USER_NAME = "user.name";
    public static final String USER_RESULTS = "user.results";
    public static final String USER_READ = "user.read";

    SharedPreferences preferences;

    protected CompositeDisposable fragmentDisposable = new CompositeDisposable();

    public void setActionBarTitle(int resId){
        ((BaseActivity) getActivity()).setActionBarTitle(resId);
    }

    public void setActionBarTitle(String title){
        ((BaseActivity) getActivity()).setActionBarTitle(title);
    }


    public void showSnackBar(Event<SnackBarMessage> event) {
        SnackBarMessage message = event.getContentIfNotHandled();
        if (message != null) {
            showSnackBar(message);
        }
    }

    public void showSnackBar(SnackBarMessage message) {
        String snkMessage = null;
        if (message.getMessage() != null) {
            snkMessage = message.getMessage();
        } else {
            snkMessage = getString(message.getResId(), message.getFormattedMessages().toArray());
        }
        Snackbar snackbar = Snackbar.make(getRoot(), snkMessage, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public void getPreferences() {
        if (getContext() != null) {
            preferences = getContext().getSharedPreferences(USER_PREFS, Context.MODE_PRIVATE);
        }
    }

    public void setUserNamePreferences(String userName) {
        preferences.edit().putString(USER_NAME, userName).apply();
    }

    public void clearUserName() {
        preferences.edit().putString(USER_NAME, "").apply();
    }

    public String getUserName() {
        return preferences.getString(USER_NAME, "");
    }

    public boolean getHasUserRead() {
        return preferences.getBoolean(USER_READ, false);
    }

    public void setReadingPreferences(boolean hasFinished) {
        preferences.edit().putBoolean(USER_READ, hasFinished).apply();
    }

    public void setQuizResults(String quizResults) {
        preferences.edit().putString(USER_RESULTS, quizResults).apply();
    }

    public String getQuizResults() {
        return preferences.getString(USER_RESULTS, "");
    }

    public abstract View getRoot();
}