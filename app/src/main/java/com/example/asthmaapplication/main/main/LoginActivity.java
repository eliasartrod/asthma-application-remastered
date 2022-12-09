package com.example.asthmaapplication.main.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.asthmaapplication.databinding.FragmentLoginBinding;
import com.example.asthmaapplication.main.homepage.HomePageActivity;
import com.example.asthmaapplication.main.utils.UIUtils;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    FragmentLoginBinding binding;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        viewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                startActivity(intent);
            }
        });

        binding.actionRegister.setOnClickListener(v -> registrationRedirect());
        UIUtils.addUnderlineFlag(binding.actionRegister);

        binding.actionLogin.setOnClickListener(userLogin -> {
            String email = binding.emailLogin.getText().toString();
            String password = binding.passwordLogin.getText().toString();
            if (email.length() > 0 && password.length() > 0 ) {
                viewModel.login(email, password);
            } else {
                Toast.makeText(
                        getApplicationContext(),
                        "Email Address and Password Required!",
                        Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onResume() {
        super.onResume();
    }

    public void registrationRedirect() {
        Intent registrationIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
        startActivity(registrationIntent);
    }

}
