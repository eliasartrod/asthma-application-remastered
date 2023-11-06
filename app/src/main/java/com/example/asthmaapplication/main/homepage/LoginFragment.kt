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
import com.example.asthmaapplication.main.common.Event
import com.example.asthmaapplication.main.common.SnackBarMessage
import com.example.asthmaapplication.main.mainpage.MainActivity
import com.example.asthmaapplication.main.utils.ActivityUtils
import com.example.asthmaapplication.main.utils.UIUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment: BaseFragment() {
    private lateinit var _binding: FragmentLoginBinding
    private lateinit var watcher: TextWatcher

    private val _viewModel: HomePageViewModel by viewModels()

    private val student = "studentOption"
    private val patient = "patientOption"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)
        setActionBarTitle()
        (activity as BaseActivity?)?.showBackButton(true)
        UIUtils.addUnderlineFlag(_binding.actionRegister)

        _viewModel.snackBar.observe(viewLifecycleOwner) { showSnackBar(it) }

        _binding.actionLogin.isEnabled = false
        _binding.actionRegister.setOnClickListener { launchRegistrationPage() }

        setupListeners()

        _binding.emailLogin.addTextChangedListener(watcher)
        _binding.passwordLogin.addTextChangedListener(watcher)
        _binding.nameLogin.addTextChangedListener(watcher)
    }

    private fun setupListeners() {
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
                        _binding.nameLogin.text.toString()
                    )
                    launchUserLoginSuccessful()
                    resetLoginPage()
                }
            }
        }
    }

    private fun resetLoginPage() {
        _binding.emailLogin.text = null
        _binding.passwordLogin.text = null
        _binding.nameLogin.text = null
    }

    private fun launchUserLoginSuccessful() {
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
        return _binding!!.root
    }
}