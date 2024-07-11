package com.example.asthmaapplication.main.mainpage.subpages.learningpages

import dagger.hilt.android.AndroidEntryPoint
import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.adapter.AsthmaInfoAdapter
import com.example.asthmaapplication.main.model.AsthmaInfoModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inventory.R
import com.example.inventory.databinding.FragmentSectionFourBinding
import java.util.ArrayList

@AndroidEntryPoint
class SectionFourFragment : BaseFragment() {
    private lateinit var _binding: FragmentSectionFourBinding
    private var _adapter: AsthmaInfoAdapter? = null
    private var asthmaInfoModels = ArrayList<AsthmaInfoModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSectionFourBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        setActionBarTitle()
        createSectionFourInfo()
        setupAdapter()
    }

    private fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title))
    }

    private fun createSectionFourInfo() {
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_four_title_one),
                getString(R.string.section_four_description_one),
                R.drawable.ic_pathology_two
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_four_title_two),
                getString(R.string.section_four_description_two),
                R.drawable.ic_asthma_children
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_four_title_three),
                getString(R.string.section_four_description_three),
                R.drawable.ic_lung_abnormal
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_four_reference),
                getString(R.string.section_four_reference_description),
                R.drawable.ic_references
            )
        )
    }

    private fun setupAdapter() {
        _binding.sectionTitle.text = getString(R.string.section_four)
        _adapter = AsthmaInfoAdapter(asthmaInfoModels)
        _binding.recyclerView.layoutManager = LinearLayoutManager(context)
        _binding.recyclerView.adapter = _adapter
    }

    override fun getRoot(): View {
        return _binding.root
    }
}