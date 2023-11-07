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
import com.example.asthmaapplication.databinding.FragmentSectionOneBinding
import java.util.ArrayList

@AndroidEntryPoint
class SectionOneFragment : BaseFragment() {
    private lateinit var _binding: FragmentSectionOneBinding
    private var _adapter: AsthmaInfoAdapter? = null
    private var asthmaInfoModels = ArrayList<AsthmaInfoModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSectionOneBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        setActionBarTitle()
        createSectionOneInfo()
        setUpAdapter()
    }

    private fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title))
    }

    private fun createSectionOneInfo() {
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_one_introduction),
                getString(R.string.section_one_introduction_description),
                R.drawable.ic_lung_normal
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_one_title_One),
                getString(R.string.section_one_description_One),
                R.drawable.ic_lung_abnormal
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_one_title_Two),
                getString(R.string.section_one_description_Two),
                R.drawable.ic_cough
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_one_title_Three),
                getString(R.string.section_one_description_Three),
                R.drawable.ic_pathology
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_one_title_Five),
                getString(R.string.section_one_description_Five),
                R.drawable.ic_asthma_trigger
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_one_title_Six),
                getString(R.string.section_one_description_Six),
                R.drawable.ic_clinical_studies
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_one_title_Seven),
                getString(R.string.section_one_description_Seven),
                R.drawable.ic_facts
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_one_references),
                getString(R.string.section_one_references_description),
                R.drawable.ic_references
            )
        )
    }

    private fun setUpAdapter() {
        _binding.sectionTitle.text = getString(R.string.section_one)
        _adapter = AsthmaInfoAdapter(asthmaInfoModels)
        _binding.recyclerView.layoutManager = LinearLayoutManager(context)
        _binding.recyclerView.adapter = _adapter
    }

    override fun getRoot(): View {
        return _binding.root
    }
}