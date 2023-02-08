package com.example.asthmaapplication.main.homepage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentRegistrationBinding;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainActivity;
import com.example.asthmaapplication.main.utils.UIUtils;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class RegistrationFragment extends BaseFragment {
    FragmentRegistrationBinding binding;
    HomePageViewModel viewModel;
    FragmentManager manager;
    FragmentTransaction transaction;
    TextWatcher watcher;
    SharedPreferences preferences;

    @Inject
    public RegistrationFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferences();
        setActionBarTitle();

        binding.actionRegister.setEnabled(false);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        UIUtils.addUnderlineFlag(binding.actionLogin);
        binding.actionLogin.setOnClickListener(v -> {
            launchLoginPage();
        });

        watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean allowRegister = (
                        !TextUtils.isEmpty(binding.emailLogin.getText().toString()) &&
                                !TextUtils.isEmpty(binding.passwordLogin.getText().toString()));

                binding.actionRegister.setEnabled(allowRegister);
                binding.actionRegister.setOnClickListener(v -> {
                    viewModel.register(binding.emailLogin.getText().toString(),
                            binding.passwordLogin.getText().toString());
                    resetRegistrationPage();
                });
            }
        };


        binding.emailLogin.addTextChangedListener(watcher);
        binding.passwordLogin.addTextChangedListener(watcher);

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getPreferences() {
        if (getContext() != null) {
            preferences = getContext().getSharedPreferences("user.prefs", Context.MODE_PRIVATE);
        }
    }

    public void resetRegistrationPage() {
        binding.emailLogin.setText(null);
        binding.passwordLogin.setText(null);
        getFragmentManager().popBackStackImmediate();
    }

    public void launchLoginPage() {
        LoginFragment fragment = new LoginFragment();
        transaction
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.registration_page_title));
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
