package com.example.asthmaapplication.main.homepage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.asthmaapplication.main.common.BaseActivity
import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.mainpage.MainActivity
import com.example.asthmaapplication.main.utils.ActivityUtils
import com.example.asthmaapplication.main.utils.UIUtils
import com.example.inventory.R
import com.example.inventory.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    private val _viewModel: HomePageViewModel by viewModels()

    private var _currentUser: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity?)?.showBackButton(false)
        UIUtils.addUnderlineFlag(binding.actionContinue)
        setActionBarTitle()
        setupListeners()

    }

    override fun onResume() {
        super.onResume()
        _currentUser = _viewModel.getCurrentUser()
        setupListeners()
    }

    private fun setupListeners() {
        binding.actionLogin.setOnClickListener { launchLoginPage() }
        binding.actionRegister.setOnClickListener { launchRegistrationPage() }

        if (_currentUser.isNullOrEmpty()) {
            binding.actionContinue.text = getString(R.string.continue_as_guest)
            binding.actionContinue.setOnClickListener { launchHomePage() }
        } else {
            binding.actionContinue.text = getString(R.string.you_are_signed_in)
            binding.actionContinue.setOnClickListener { launchHomePage() }
        }
    }

    private fun launchHomePage() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    private fun launchLoginPage() {
        val fragment = LoginFragment()
        ActivityUtils.addFragmentWithBackStack(
            parentFragmentManager,
            fragment,
            R.id.fragment_container,
            "login_page"
        )
    }

    private fun launchRegistrationPage() {
        val fragment = RegistrationFragment()
        ActivityUtils.addFragmentWithBackStack(
            parentFragmentManager,
            fragment,
            R.id.fragment_container,
            "registration_page"
        )

    }

    fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.welcome))
    }

    override fun getRoot(): View {
        return binding.root
    }
}