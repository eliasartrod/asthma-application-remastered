package com.example.asthmaapplication.main.mainpage.subpages.learningbasepages

import javax.inject.Inject
import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.mainpage.MainViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.inventory.R
import com.example.inventory.databinding.FragmentQuizzesExamsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizzesExamsFragment @Inject constructor() : BaseFragment() {
    var viewModel: MainViewModel? = null
    var binding: FragmentQuizzesExamsBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentQuizzesExamsBinding.inflate(inflater, container, false)
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
        setActionBarTitle(getString(R.string.quizzes_exam_page_title))
    }

    override fun getRoot(): View {
        return binding!!.root
    }
}