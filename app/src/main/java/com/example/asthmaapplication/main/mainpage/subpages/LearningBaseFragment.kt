package com.example.asthmaapplication.main.mainpage.subpages

import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.mainpage.MainViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.asthmaapplication.R
import com.example.asthmaapplication.main.utils.UIUtils
import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import com.example.asthmaapplication.databinding.FragmentLearningBaseBinding
import com.example.asthmaapplication.main.common.Constants
import com.example.asthmaapplication.main.common.SnackBarMessage
import com.example.asthmaapplication.main.mainpage.MainFragment
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.LearningFragmentActivity
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.LearningFragment
import com.example.asthmaapplication.main.utils.ActivityUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LearningBaseFragment: BaseFragment() {
    private lateinit var _binding: FragmentLearningBaseBinding

    private val _viewModel: MainViewModel by viewModels()

    private var _loginOption: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLearningBaseBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _loginOption = _viewModel.userType

        setActionBarTitle()
        setupUI()
        validateReviewAccess()
    }

    private fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title))
    }

    private fun setupUI() {
        UIUtils.addUnderlineFlag(_binding.actionHomePageRedirect)
        _binding.actionHomePageRedirect.setOnClickListener {
            ActivityUtils.addFragmentWithBackStack(
                parentFragmentManager,
                MainFragment(),
                R.id.fragment_container,
                null
            )
        }
        _binding.sectionOneCard.cardImage.setImageResource(R.drawable.ic_inhaler)
        _binding.sectionOneCard.cardTitle.setText(R.string.section_one)
        _binding.sectionOneCard.root.setOnClickListener { launchSectionOnePage() }
        _binding.sectionTwoCard.cardImage.setImageResource(R.drawable.ic_lung_abnormal)
        _binding.sectionTwoCard.cardTitle.setText(R.string.section_two)
        _binding.sectionTwoCard.root.setOnClickListener { launchSectionTwoPage() }
        _binding.sectionThreeCard.cardImage.setImageResource(R.drawable.ic_board_charts)
        _binding.sectionThreeCard.cardTitle.setText(R.string.section_three)
        _binding.sectionThreeCard.root.setOnClickListener { launchSectionThreePage() }
        _binding.sectionFourCard.cardImage.setImageResource(R.drawable.ic_clinical_studies)
        _binding.sectionFourCard.cardTitle.setText(R.string.section_four)
        _binding.sectionFourCard.root.setOnClickListener { launchSectionFourPage() }
        _binding.sectionFiveCard.cardImage.setImageResource(R.drawable.ic_guidance)
        _binding.sectionFiveCard.cardTitle.setText(R.string.section_five)
        _binding.sectionFiveCard.root.setOnClickListener { launchSectionFivePage() }
        _binding.reviewCard.cardImage.setImageResource(R.drawable.ic_review_icon)
        _binding.reviewCard.cardTitle.setText(R.string.review_card)
    }

    private fun launchSectionOnePage() {
        val intent = Intent(context, LearningFragmentActivity::class.java)
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 0)
        startActivity(intent)
    }

    private fun launchSectionTwoPage() {
        val intent = Intent(context, LearningFragmentActivity::class.java)
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 1)
        startActivity(intent)
    }

    private fun launchSectionThreePage() {
        val intent = Intent(context, LearningFragmentActivity::class.java)
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 2)
        startActivity(intent)
    }

    private fun launchSectionFourPage() {
        val intent = Intent(context, LearningFragmentActivity::class.java)
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 3)
        startActivity(intent)
    }

    private fun launchSectionFivePage() {
        val intent = Intent(context, LearningFragmentActivity::class.java)
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 4)
        startActivity(intent)
    }

    private fun validateReviewAccess() {
        when (_loginOption) {
            Constants.STUDENT_OPTION -> {
                _binding.reviewCard.root.setOnClickListener {
                    val fragment = ReviewsFragment()
                    ActivityUtils.addFragmentWithBackStack(
                        parentFragmentManager,
                        fragment,
                        R.id.fragment_container,
                        "reviews_page"
                    )
                }
            }
            Constants.PATIENT_OPTION -> {
                _binding.reviewCard.cardHolder.setBackgroundColor(resources.getColor(R.color.gray))
                _binding.reviewCard.root.setOnClickListener { setAccessWarnings(getString(R.string.patient_access_warning)) }
            }
            else -> {
                _binding.reviewCard.cardHolder.setBackgroundColor(resources.getColor(R.color.gray))
                _binding.reviewCard.root.setOnClickListener { setAccessWarnings(getString(R.string.guest_user_access_warning)) }
            }
        }
    }

    private fun setAccessWarnings(warningMessage: String) {
        showSnackBar(SnackBarMessage(warningMessage))
    }

    override fun getRoot(): View {
        return _binding.root
    }
}