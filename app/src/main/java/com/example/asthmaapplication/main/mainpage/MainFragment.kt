package com.example.asthmaapplication.main.mainpage

import dagger.hilt.android.AndroidEntryPoint
import com.example.asthmaapplication.main.common.BaseFragment
import android.os.Bundle
import android.view.*
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import com.example.asthmaapplication.R
import com.example.asthmaapplication.databinding.FragmentMainBinding
import com.example.asthmaapplication.main.common.Constants
import com.example.asthmaapplication.main.utils.UIUtils
import com.example.asthmaapplication.main.common.SnackBarMessage
import com.example.asthmaapplication.main.mainpage.myprofile.MyProfileFragment
import com.example.asthmaapplication.main.mainpage.subpages.learningbasepages.LearningBaseFragment
import com.example.asthmaapplication.main.mainpage.subpages.learningbasepages.PatientsFragment
import com.example.asthmaapplication.main.mainpage.subpages.learningbasepages.ReviewsFragment
import com.example.asthmaapplication.main.mainpage.subpages.learningbasepages.QuizzesExamsFragment
import com.example.asthmaapplication.main.utils.ActivityUtils

@AndroidEntryPoint
class MainFragment: BaseFragment() {
    private lateinit var _binding: FragmentMainBinding

    private val _viewModel: MainViewModel by viewModels()

    private var _loginOption: String? = null
    private var _userName: String? = null

    private val _myProfileFragment = MyProfileFragment()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUi()
        setupListeners()
        setupMenu()
    }

    override fun onResume() {
        super.onResume()
        _loginOption = _viewModel.userType
        setActionBarTitle()
        validateUserAccess()
    }

    private fun setupListeners() {
        _binding.actionLogout.setOnClickListener {
            _viewModel.logOut()
            activity?.finish()
        }
    }

    private fun setupUi() {
        UIUtils.addUnderlineFlag(_binding.actionLogout)
        _binding.learningCard.cardImage.setImageResource(R.drawable.ic_lung_normal)
        _binding.learningCard.cardTitle.setText(R.string.learning_card)
        _binding.patientsCard.cardImage.setImageResource(R.drawable.ic_patient_icon)
        _binding.patientsCard.cardTitle.setText(R.string.patient_card)
        _binding.quizCard.cardImage.setImageResource(R.drawable.ic_quiz_icon)
        _binding.quizCard.cardTitle.setText(R.string.quiz_card)
        _binding.reviewCard.cardImage.setImageResource(R.drawable.ic_review_icon)
        _binding.reviewCard.cardTitle.setText(R.string.review_card)
    }

    private fun setupMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.my_profile_menu, menu)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                if (item.itemId == R.id.action_my_profile) {
                    ActivityUtils.addFragmentWithBackStack(
                        parentFragmentManager,
                        _myProfileFragment,
                        R.id.fragment_container,
                        "my_profile"
                    )
                    return true
                }
                return false
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }

    private fun setActionBarTitle() {
        _userName = _viewModel.userName

        if (_userName.isNullOrEmpty()) {
            _binding.actionLogout.text = getString(R.string.redirect_home)
            _binding.mainTitleDescription.text = getString(R.string.guest_user_tip)
            setActionBarTitle(getString(R.string.welcome_user, getString(R.string.guest_user)))
        } else {
            _binding.mainTitleDescription.text = getText(R.string.main_page_title)
            _binding.actionLogout.text = getString(R.string.log_out)
            setActionBarTitle(getString(R.string.welcome_user, _userName))
        }
    }

    private fun validateUserAccess() {
        when (_loginOption) {
            Constants.STUDENT_OPTION -> {
                setStudentAccess()
            }
            Constants.PATIENT_OPTION -> {
                setPatientAccess()
            }
            else -> {
                setDefaultAccess()
            }
        }
    }

    private fun setStudentAccess() {
        _binding.learningCard.root.setOnClickListener { launchLearningPage() }
        _binding.patientsCard.root.setOnClickListener { launchPatientsCard() }
        _binding.quizCard.root.setOnClickListener { launchQuizPage() }
        _binding.reviewCard.root.setOnClickListener { launchReviewsPage() }
    }

    private fun setPatientAccess() {
        _binding.quizCard.cardHolder.setBackgroundColor(resources.getColor(R.color.gray))
        _binding.reviewCard.cardHolder.setBackgroundColor(resources.getColor(R.color.gray))
        _binding.learningCard.root.setOnClickListener { launchLearningPage() }
        _binding.patientsCard.root.setOnClickListener { launchPatientsCard() }
        _binding.reviewCard.root.setOnClickListener { setAccessWarnings(getString(R.string.patient_access_warning)) }
        _binding.quizCard.root.setOnClickListener { setAccessWarnings(getString(R.string.patient_access_warning)) }
    }

    private fun setDefaultAccess() {
        _binding.learningCard.root.setOnClickListener { launchLearningPage() }
        _binding.patientsCard.cardHolder.setBackgroundColor(resources.getColor(R.color.gray))
        _binding.quizCard.cardHolder.setBackgroundColor(resources.getColor(R.color.gray))
        _binding.reviewCard.cardHolder.setBackgroundColor(resources.getColor(R.color.gray))
        _binding.patientsCard.root.setOnClickListener { setAccessWarnings(getString(R.string.guest_user_access_warning)) }
        _binding.quizCard.root.setOnClickListener { setAccessWarnings(getString(R.string.guest_user_access_warning)) }
        _binding.reviewCard.root.setOnClickListener { setAccessWarnings(getString(R.string.guest_user_access_warning)) }
    }

    private fun setAccessWarnings(warningMessage: String) {
        showSnackBar(SnackBarMessage(warningMessage))
    }

    private fun launchLearningPage() {
        val fragment = LearningBaseFragment()
        ActivityUtils.addFragmentWithBackStack(
            parentFragmentManager,
            fragment,
            R.id.fragment_container,
            "learning_page"
        )
    }

    private fun launchPatientsCard() {
        val fragment = PatientsFragment()
        ActivityUtils.addFragmentWithBackStack(
            parentFragmentManager,
            fragment,
            R.id.fragment_container,
            "learning_page"
        )
    }

    private fun launchReviewsPage() {
        val fragment = ReviewsFragment()
        ActivityUtils.addFragmentWithBackStack(
            parentFragmentManager,
            fragment,
            R.id.fragment_container,
            "learning_page"
        )
    }

    private fun launchQuizPage() {
        val fragment = QuizzesExamsFragment()
        ActivityUtils.addFragmentWithBackStack(
            parentFragmentManager,
            fragment,
            R.id.fragment_container,
            "learning_page"
        )
    }

    override fun getRoot(): View {
        return _binding.root
    }
}