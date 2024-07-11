package com.example.asthmaapplication.main.mainpage.subpages.learningpages

import com.example.asthmaapplication.main.common.BaseFragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayout
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.inventory.R
import com.example.inventory.databinding.FragmentLearningBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearningFragment : BaseFragment() {

    private lateinit var _binding: FragmentLearningBinding

    companion object {
        const val FRAGMENT_POSITION = "fragment.position"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearningBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle()
        setTabLayouts()
    }

    private fun setTabLayouts() {
        val startingPosition = this.activity!!
            .intent.getIntExtra(FRAGMENT_POSITION, 0)
        val adapter = LearningManagementAdapter(this)
        _binding.fragmentPager.adapter = adapter
        TabLayoutMediator(
            _binding.tabs,
            _binding.fragmentPager
        ) { tab: TabLayout.Tab, position: Int ->
            when (position) {
                0 -> tab.setText(R.string.section_one)
                1 -> tab.setText(R.string.section_two)
                2 -> tab.setText(R.string.section_three)
                3 -> tab.setText(R.string.section_four)
                4 -> tab.setText(R.string.section_five)
            }
        }.attach()
        _binding.fragmentPager.currentItem = startingPosition
    }

    private fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title))
    }

    override fun getRoot(): View {
        return _binding.root
    }

    internal class LearningManagementAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> SectionOneFragment()
                1 -> SectionTwoFragment()
                2 -> SectionThreeFragment()
                3 -> SectionFourFragment()
                else -> SectionFiveFragment()
            }
        }

        override fun getItemCount(): Int {
            return 5
        }
    }

}