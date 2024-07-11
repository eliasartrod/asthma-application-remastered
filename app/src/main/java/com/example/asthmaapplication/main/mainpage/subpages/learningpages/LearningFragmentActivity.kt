package com.example.asthmaapplication.main.mainpage.subpages.learningpages

import dagger.hilt.android.AndroidEntryPoint
import com.example.asthmaapplication.main.common.BaseActivity
import android.os.Bundle
import com.example.asthmaapplication.main.utils.ActivityUtils
import com.example.inventory.R

@AndroidEntryPoint
class LearningFragmentActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragment = LearningFragment()
        ActivityUtils.addFragment(supportFragmentManager, fragment, R.id.fragment_container)
    }
}