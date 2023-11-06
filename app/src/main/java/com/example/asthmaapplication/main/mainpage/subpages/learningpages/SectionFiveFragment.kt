package com.example.asthmaapplication.main.mainpage.subpages.learningpages

import dagger.hilt.android.AndroidEntryPoint
import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.adapter.AsthmaInfoAdapter
import com.example.asthmaapplication.main.model.AsthmaInfoModel
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import com.example.asthmaapplication.main.utils.UIUtils
import com.example.asthmaapplication.R
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.asthmaapplication.databinding.FragmentSectionFiveBinding
import java.util.ArrayList

@AndroidEntryPoint
class SectionFiveFragment : BaseFragment() {
    private lateinit var _binding: FragmentSectionFiveBinding
    private var _adapter: AsthmaInfoAdapter? = null
    private var asthmaInfoModels = ArrayList<AsthmaInfoModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSectionFiveBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle()
        createSectionFiveInfo()
        setupAdapter()
        UIUtils.addUnderlineFlag(_binding.actionFinish)
        _binding.actionFinish.setOnClickListener { activity!!.finish() }
    }

    private fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title))
    }

    private fun createSectionFiveInfo() {
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_one),
                getString(R.string.section_five_description_one),
                R.drawable.ic_exam_male
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_two),
                getString(R.string.section_five_description_two),
                R.drawable.ic_food_medicine
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_three),
                getString(R.string.section_five_description_three),
                R.drawable.ic_cigarette
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_four),
                getString(R.string.section_five_description_four),
                R.drawable.ic_weather
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_five),
                getString(R.string.section_five_description_five),
                R.drawable.ic_pets
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_six),
                getString(R.string.section_five_description_six),
                R.drawable.ic_pests
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_seven),
                getString(R.string.section_five_description_seven),
                R.drawable.ic_dirty
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_eight),
                getString(R.string.section_five_description_eight),
                R.drawable.ic_exercise
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_nine),
                getString(R.string.section_five_description_nine),
                R.drawable.ic_emotion
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_title_ten),
                getString(R.string.section_five_description_ten),
                R.drawable.ic_odor
            )
        )
        asthmaInfoModels.add(
            AsthmaInfoModel(
                getString(R.string.section_five_reference),
                getString(R.string.section_five_reference_description),
                R.drawable.ic_references
            )
        )
    }

    private fun setupAdapter() {
        _binding.sectionTitle.text = getString(R.string.section_five)
        _adapter = AsthmaInfoAdapter(context, asthmaInfoModels)
        _binding.recyclerView.layoutManager = LinearLayoutManager(context)
        _binding.recyclerView.adapter = _adapter
    }

    override fun getRoot(): View {
        return _binding.root
    }
}