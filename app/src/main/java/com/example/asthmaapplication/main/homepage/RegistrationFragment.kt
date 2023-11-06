package com.example.asthmaapplication.main.homepage

import dagger.hilt.android.AndroidEntryPoint
import com.example.asthmaapplication.main.common.BaseFragment
import android.text.TextWatcher
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.asthmaapplication.main.utils.UIUtils
import android.text.Editable
import android.text.TextUtils
import android.view.View
import androidx.fragment.app.viewModels
import com.example.asthmaapplication.R
import com.example.asthmaapplication.databinding.FragmentRegistrationBinding
import com.example.asthmaapplication.main.common.BaseActivity
import com.example.asthmaapplication.main.common.Event
import com.example.asthmaapplication.main.utils.ActivityUtils

@AndroidEntryPoint
class RegistrationFragment: BaseFragment() {
    private lateinit var _binding: FragmentRegistrationBinding
    private lateinit var watcher: TextWatcher

    private val _viewModel: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistrationBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setActionBarTitle()
        (activity as BaseActivity?)?.showBackButton(true)
        UIUtils.addUnderlineFlag(_binding.actionLogin)

        _viewModel.loading.observe(viewLifecycleOwner) { setupLoading(it) }
        _viewModel.snackBar.observe(viewLifecycleOwner) { showSnackBar(it) }
        _viewModel.registrationSuccess.observe(viewLifecycleOwner) { setupRegistrationListener(it) }

        _binding.actionRegister.isEnabled = false
        _binding.actionLogin.setOnClickListener { launchLoginPage() }

        setupListeners()

    }

    private fun setupLoading(event: Event<Boolean>) {
        val result = event.contentIfNotHandled
        result?.let {
            _binding.progressIndicator.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setupRegistrationListener(event: Event<Boolean>) {
        event.contentIfNotHandled?.let {
            if (it) {
                fragmentManager!!.popBackStackImmediate()
            }
        }
    }

    private fun setupListeners() {
        watcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                val allowRegister = !TextUtils.isEmpty(_binding.emailLogin.text.toString()) &&
                        !TextUtils.isEmpty(_binding.passwordLogin.text.toString())
                _binding.actionRegister.isEnabled = allowRegister
                _binding.actionRegister.setOnClickListener {
                    _viewModel.register(
                        _binding.emailLogin.text.toString(),
                        _binding.passwordLogin.text.toString()
                    )
                    resetRegistrationPage()
                }
            }
        }
        _binding.emailLogin.addTextChangedListener(watcher)
        _binding.passwordLogin.addTextChangedListener(watcher)
    }

    private fun resetRegistrationPage() {
        _binding.emailLogin.text = null
        _binding.passwordLogin.text = null
    }

    private fun launchLoginPage() {
        val fragment = LoginFragment()
        ActivityUtils.addFragmentWithBackStack(
            parentFragmentManager,
            fragment,
            R.id.fragment_container,
            null
        )
    }

    fun setActionBarTitle() {
        setActionBarTitle(getString(R.string.registration_page_title))
    }

    override fun getRoot(): View {
        return _binding.root
    }
}