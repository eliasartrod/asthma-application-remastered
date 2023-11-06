package com.example.asthmaapplication.main.homepage

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.asthmaapplication.R
import com.example.asthmaapplication.databinding.FragmentLoginBinding
import com.example.asthmaapplication.main.common.BaseActivity
import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.common.Constants
import com.example.asthmaapplication.main.common.Event
import com.example.asthmaapplication.main.mainpage.MainActivity
import com.example.asthmaapplication.main.utils.ActivityUtils
import com.example.asthmaapplication.main.utils.UIUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: BaseFragment() {
    private lateinit var _binding: FragmentLoginBinding
    private lateinit var watcher: TextWatcher

    private val _viewModel: HomePageViewModel by viewModels()
    private var _currentUser: String? = null
    private var _userType: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle()
        setupUi()

        _viewModel.snackBar.observe(viewLifecycleOwner) { showSnackBar(it) }
        _viewModel.loginSuccess.observe(viewLifecycleOwner) { launchMainPage(it) }
    }

    override fun onResume() {
        super.onResume()
        _currentUser = _viewModel.getCurrentUser()
        setupListeners()
    }

    private fun setupUi() {
        (activity as BaseActivity?)?.showBackButton(true)
        UIUtils.addUnderlineFlag(_binding.actionRegister)
        UIUtils.addUnderlineFlag(_binding.actionContinue)
    }

    private fun setupListeners() {
        _binding.actionLogin.isEnabled = false
        _binding.actionRegister.setOnClickListener { launchRegistrationPage() }

        if (!_currentUser.isNullOrEmpty()) {
            _binding.actionContinue.setOnClickListener { launchMainPage() }
        } else {
            _binding.actionContinue.text = getString(R.string.continue_as_guest)
            _binding.actionContinue.setOnClickListener {
                _viewModel.clearCurrentUser()
                launchMainPage()
            }
        }

        _binding.loginOptionGroup.setOnCheckedChangeListener{ _, checkedType ->
            _userType = when (checkedType) {
                R.id.student_login -> {
                    Constants.STUDENT_OPTION
                }
                R.id.patient_login -> {
                    Constants.PATIENT_OPTION
                }
                else -> {
                    null
                }
            }
        }

        watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val allowLogin = !TextUtils.isEmpty(_binding.emailLogin.text.toString()) &&
                        !TextUtils.isEmpty(_binding.passwordLogin.text.toString()) &&
                        !TextUtils.isEmpty(_binding.nameLogin.text.toString())
                _binding.actionLogin.isEnabled = allowLogin

                _binding.actionLogin.setOnClickListener {
                    _viewModel.login(
                        _binding.emailLogin.text.toString(),
                        _binding.passwordLogin.text.toString(),
                        _binding.nameLogin.text.toString(),
                        _userType
                    )
                }
            }
        }
        _binding.emailLogin.addTextChangedListener(watcher)
        _binding.passwordLogin.addTextChangedListener(watcher)
        _binding.nameLogin.addTextChangedListener(watcher)
    }

    private fun resetLoginPage() {
        _binding.emailLogin.text = null
        _binding.passwordLogin.text = null
        _binding.nameLogin.text = null
    }

    private fun launchMainPage(event: Event<Boolean>) {
        event.contentIfNotHandled?.let {
            if (it) {
                launchMainPage()
                resetLoginPage()
            }
        }
    }

    private fun launchMainPage() {
        val intent = Intent(context, MainActivity::class.java)
        startActivity(intent)
    }

    private fun launchRegistrationPage() {
        val fragment = RegistrationFragment()
        ActivityUtils.addFragmentWithBackStack(
            parentFragmentManager,
            fragment,
            R.id.fragment_container,
            null
        )
    }

    private fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.login_page_title))
    }

    override fun getRoot(): View {
        return _binding.root
    }
}