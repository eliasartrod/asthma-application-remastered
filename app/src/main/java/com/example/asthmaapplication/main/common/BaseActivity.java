package com.example.asthmaapplication.main.common;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.asthmaapplication.databinding.ActivityToolbarBinding;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.disposables.CompositeDisposable;

@AndroidEntryPoint
public class BaseActivity extends AppCompatActivity {
    SharedPreferences preferences;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    ActivityToolbarBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityToolbarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getPreferences();

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        compositeDisposable.clear();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void getPreferences() {
        preferences = getApplicationContext().getSharedPreferences("my.prefs", MODE_PRIVATE);
    }

    public void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    public void setActionBarTitle(int resId) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(resId);
        }
    }

    public void showBackButton(boolean show) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(show);
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
