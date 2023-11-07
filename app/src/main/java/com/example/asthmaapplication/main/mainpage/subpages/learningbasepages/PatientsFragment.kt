package com.example.asthmaapplication.main.mainpage.subpages.learningbasepages

import javax.inject.Inject
import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.mainpage.MainViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.asthmaapplication.databinding.FragmentPatientsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PatientsFragment @Inject constructor() : BaseFragment() {
    var viewModel: MainViewModel? = null
    var binding: FragmentPatientsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPatientsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        setActionBarTitle()
    }

    override fun onResume() {
        super.onResume()
    }

    fun setActionBarTitle() {}
    override fun getRoot(): View {
        return binding!!.root
    }
}