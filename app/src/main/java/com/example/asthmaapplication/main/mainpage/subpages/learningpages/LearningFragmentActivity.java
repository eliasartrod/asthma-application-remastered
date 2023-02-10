package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.os.Bundle;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.main.common.BaseActivity;
import com.example.asthmaapplication.main.mainpage.subpages.LearningFragment;
import com.example.asthmaapplication.main.utils.ActivityUtils;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LearningFragmentActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LearningFragment fragment = new LearningFragment();
        ActivityUtils.addFragment(getSupportFragmentManager(), fragment, R.id.fragment_container);
    }
}
