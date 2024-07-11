package com.example.asthmaapplication.main.homepage

import android.os.Bundle
import com.example.asthmaapplication.main.common.BaseActivity
import com.example.asthmaapplication.main.utils.ActivityUtils
import com.example.inventory.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)

        val fragment = HomePageFragment()
        ActivityUtils.addFragment(supportFragmentManager, fragment, R.id.fragment_container)
    }

}