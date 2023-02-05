package com.example.asthmaapplication.main.common;

import android.view.View;

import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment {
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

    public abstract View getRoot();
}