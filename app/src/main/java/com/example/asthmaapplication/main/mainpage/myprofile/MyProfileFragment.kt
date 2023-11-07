package com.example.asthmaapplication.main.mainpage.myprofile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.asthmaapplication.R
import com.example.asthmaapplication.databinding.FragmentMyProfileBinding
import com.example.asthmaapplication.main.common.BaseFragment
import com.example.asthmaapplication.main.common.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyProfileFragment: BaseFragment(){
    private lateinit var _binding: FragmentMyProfileBinding

    private val _viewModel: MyProfileViewModel by viewModels()

    private var _userType: String? = null
    private var _userName: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _userName = _viewModel.userName
        _userType = _viewModel.userType

        setupUi()
    }

    private fun setupUi() {
        _binding.sectionTitle.text = getString(R.string.my_profile)
        when (_userType) {
            Constants.STUDENT_OPTION -> {
                _binding.name.text = getString(R.string.my_profile_name, _userName)
                _binding.userType.text = getString(R.string.my_profile_user_type, getString(R.string.my_profile_student))
                _binding.accessType.text = getString(R.string.my_profile_access_type, getString(R.string.my_profile_student_access))
            }
            Constants.PATIENT_OPTION -> {
                _binding.name.text = getString(R.string.my_profile_name, _userName)
                _binding.userType.text = getString(R.string.my_profile_user_type, getString(R.string.my_profile_patient))
                _binding.accessType.text = getString(R.string.my_profile_access_type, getString(R.string.my_profile_patient_access))
            }
            else -> {
                _binding.name.text = getString(R.string.my_profile_name, getString(R.string.my_profile_default))
                _binding.userType.text = getString(R.string.my_profile_user_type, getString(R.string.my_profile_default))
                _binding.accessType.text = getString(R.string.my_profile_access_type, getString(R.string.my_profile_default_access))
            }
        }

    }

    override fun getRoot(): View? {
        return _binding.root
    }
}