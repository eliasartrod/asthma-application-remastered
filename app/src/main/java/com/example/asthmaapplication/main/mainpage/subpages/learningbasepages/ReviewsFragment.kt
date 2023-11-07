package com.example.asthmaapplication.main.mainpage.subpages.learningbasepages

import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.mainpage.MainViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.asthmaapplication.R
import com.example.asthmaapplication.databinding.FragmentReviewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsFragment: BaseFragment() {
    private val _viewModel: MainViewModel by viewModels()
    var binding: FragmentReviewsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReviewsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        setActionBarTitle()
    }

    override fun onResume() {
        super.onResume()
    }

    fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.reviews_page_title))
    }

    override fun getRoot(): View {
        return binding!!.root
    }
}