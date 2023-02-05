package com.example.asthmaapplication.main.homepage;

import android.os.Bundle;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.main.common.BaseActivity;
import com.example.asthmaapplication.main.utils.ActivityUtils;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomePageActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        HomePageFragment fragment = new HomePageFragment();
        ActivityUtils.addFragment(getSupportFragmentManager(), fragment, R.id.fragment_container);

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
