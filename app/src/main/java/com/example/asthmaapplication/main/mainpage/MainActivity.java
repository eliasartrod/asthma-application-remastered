package com.example.asthmaapplication.main.mainpage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.main.common.BaseActivity;
import com.example.asthmaapplication.main.utils.ActivityUtils;

import javax.annotation.Nullable;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainFragment fragment = new MainFragment();
        ActivityUtils.addFragment(getSupportFragmentManager(), fragment, R.id.fragment_container);

    }

}
