package com.example.asthmaapplication.main.main;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.asthmaapplication.databinding.FragmentMainBinding;
import com.example.asthmaapplication.main.homepage.HomePageActivity;
import com.example.asthmaapplication.main.utils.UIUtils;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    private FragmentMainBinding binding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.actionLogin.setOnClickListener(v -> loginRedirect());
        binding.actionRegister.setOnClickListener(v -> registrationRedirect());
        binding.actionGuestRedirect.setOnClickListener(v -> guestRedirect());
        UIUtils.addUnderlineFlag(binding.actionGuestRedirect);

    }

    public void onResume() {
        super.onResume();
    }

    public void loginRedirect() {
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
    }

    public void registrationRedirect() {
        Intent registrationIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(registrationIntent);
    }

    public void guestRedirect() {
        Intent guestIntent = new Intent(getApplicationContext(), HomePageActivity.class);
        startActivity(guestIntent);
    }

}