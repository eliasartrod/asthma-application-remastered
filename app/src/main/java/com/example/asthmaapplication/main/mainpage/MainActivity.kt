package com.example.asthmaapplication.main.mainpage

import dagger.hilt.android.AndroidEntryPoint
import com.example.asthmaapplication.main.common.BaseActivity
import android.os.Bundle
import com.example.asthmaapplication.main.utils.ActivityUtils
import com.example.inventory.R

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val fragment = MainFragment()
        ActivityUtils.addFragment(supportFragmentManager, fragment, R.id.fragment_container)
    }
}