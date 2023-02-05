package com.example.asthmaapplication.main.homepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentMainBinding;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.google.firebase.auth.FirebaseUser;

import javax.annotation.Nullable;
import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class HomePageFragment extends BaseFragment {
    FragmentMainBinding binding;
    HomePageViewModel viewModel;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Inject
    public HomePageFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomePageViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBarTitle(getString(R.string.welcome));

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        viewModel.getUserLiveData().observe(getViewLifecycleOwner(), this::checkUserSignedIn);

        binding.actionLogin.setOnClickListener(v -> launchLoginPage());
        binding.actionRegister.setOnClickListener(v -> launchRegistrationPage());
    }

    private void checkUserSignedIn(FirebaseUser firebaseUser) {
        if (viewModel.getUserLiveData() == null) {
            binding.actionGuestRedirect.setEnabled(true);
        } else {
            // Direct User to Home Page of the App
        }
    }

    private void launchLoginPage() {
        LoginFragment fragment = new LoginFragment();
        transaction
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void launchRegistrationPage() {
        RegistrationFragment fragment = new RegistrationFragment();
        transaction
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
