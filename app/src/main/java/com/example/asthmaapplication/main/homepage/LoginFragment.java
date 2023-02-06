package com.example.asthmaapplication.main.homepage;

import android.content.Intent;
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
import com.example.asthmaapplication.databinding.FragmentLoginBinding;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.common.SnackBarMessage;
import com.example.asthmaapplication.main.mainpage.MainActivity;
import com.example.asthmaapplication.main.mainpage.MainFragment;
import com.example.asthmaapplication.main.utils.UIUtils;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class LoginFragment extends BaseFragment {
    FragmentLoginBinding binding;
    HomePageViewModel viewModel;
    FragmentManager manager;
    FragmentTransaction transaction;
    TextWatcher watcher;

    @Inject
    public LoginFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBarTitle(getString(R.string.welcome));

        binding.actionLogin.setEnabled(false);
        UIUtils.addUnderlineFlag(binding.actionRegister);

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        binding.actionRegister.setOnClickListener(v -> {
            RegistrationFragment fragment = new RegistrationFragment();
            transaction
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
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
                boolean allowLogin = (!TextUtils.isEmpty(binding.emailLogin.getText().toString()) &&
                        !TextUtils.isEmpty(binding.passwordLogin.getText().toString()) &&
                        !TextUtils.isEmpty(binding.nameLogin.getText().toString()));
                binding.actionLogin.setEnabled(allowLogin);
                binding.actionLogin.setOnClickListener(v ->
                        viewModel.login(binding.emailLogin.getText().toString(),
                                binding.passwordLogin.getText().toString()));
            }
        };

        viewModel.getUserLiveData().observe(getViewLifecycleOwner(), value -> {
            if (value != null) {
                /*Intent intent = MainActivity.getIntent(getContext(), binding.nameLogin.getText().toString());
                startActivity(intent);*/
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
                binding.emailLogin.setText(null);
                binding.passwordLogin.setText(null);
                binding.nameLogin.setText(null);
                binding.emailLogin.requestFocus();
            } else {
                binding.actionLogin.setEnabled(false);
            }
        });

        binding.emailLogin.addTextChangedListener(watcher);
        binding.passwordLogin.addTextChangedListener(watcher);
        binding.nameLogin.addTextChangedListener(watcher);

    }

    public void onResume() {
        super.onResume();
    }


    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
