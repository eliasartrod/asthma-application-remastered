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
import com.example.asthmaapplication.databinding.FragmentSectionThreeBinding
import java.util.ArrayList

@AndroidEntryPoint
class SectionThreeFragment : BaseFragment() {
    private lateinit var _binding: FragmentSectionThreeBinding
    private var _adapter: AsthmaInfoAdapter? = null
    private var asthmaInfoModels = ArrayList<AsthmaInfoModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSectionThreeBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        setActionBarTitle()
        createSectionThreeInfo()
        setupAdapter()
    }

    private fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title))
    }

    private fun createSectionThreeInfo() {
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_three_title_one),
                getString(R.string.section_three_description_one),
                R.drawable.ic_pathology_two
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_three_title_two),
                getString(R.string.section_three_description_two),
                R.drawable.ic_lung_abnormal
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_three_title_three),
                getString(R.string.section_three_description_three),
                R.drawable.ic_obstruction
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_three_title_four),
                getString(R.string.section_three_description_four),
                R.drawable.ic_bronchial_hyperresponsiveness
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_three_reference),
                getString(R.string.section_three_reference_description),
                R.drawable.ic_references
            )
        )
    }

    private fun setupAdapter() {
        _binding.sectionTitle.text = getString(R.string.section_three)
        _adapter = AsthmaInfoAdapter(context, asthmaInfoModels)
        _binding.recyclerView.layoutManager = LinearLayoutManager(context)
        _binding.recyclerView.adapter = _adapter
    }

    override fun getRoot(): View {
        return _binding.root
    }
}