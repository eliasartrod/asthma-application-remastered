package com.example.asthmaapplication.main.mainpage.subpages.learningpages

import dagger.hilt.android.AndroidEntryPoint
import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.adapter.AsthmaInfoAdapter
import com.example.asthmaapplication.main.model.AsthmaInfoModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.example.asthmaapplication.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asthmaapplication.databinding.FragmentSectionTwoBinding
import java.util.ArrayList

@AndroidEntryPoint
class SectionTwoFragment : BaseFragment() {
    private lateinit var _binding: FragmentSectionTwoBinding
    private var _adapter: AsthmaInfoAdapter? = null
    private var asthmaInfoModels = ArrayList<AsthmaInfoModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSectionTwoBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        setActionBarTitle()
        createSectionTwoInfo()
        setupAdapter()
    }

    private fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title))
    }

    private fun createSectionTwoInfo() {
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_title_one),
                getString(R.string.section_two_description_one),
                R.drawable.ic_lung_normal
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_title_two),
                getString(R.string.section_two_description_two),
                R.drawable.ic_doctor
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_title_three),
                getString(R.string.section_two_description_three),
                R.drawable.ic_exam_male
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_title_four),
                getString(R.string.section_two_description_four),
                R.drawable.ic_heartbeat
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_title_five),
                getString(R.string.section_two_description_five),
                R.drawable.ic_allergy
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_title_six),
                getString(R.string.section_two_description_six),
                R.drawable.ic_exam_female
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_title_seven),
                getString(R.string.section_two_description_seven),
                R.drawable.ic_lung_misc
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_title_eight),
                getString(R.string.section_two_description_eight),
                R.drawable.ic_asthma_children
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_two_reference),
                getString(R.string.section_two_reference_description),
                R.drawable.ic_references
            )
        )
    }

    private fun setupAdapter() {
        _binding.sectionTitle.text = getString(R.string.section_two)
        _adapter = AsthmaInfoAdapter(asthmaInfoModels)
        _binding.recyclerView.layoutManager = LinearLayoutManager(context)
        _binding.recyclerView.adapter = _adapter
    }

    override fun getRoot(): View {
        return _binding.root
    }
}