package com.example.asthmaapplication.main.main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.asthmaapplication.databinding.FragmentRegistrationBinding;
import com.example.asthmaapplication.main.homepage.HomePageActivity;
import com.example.asthmaapplication.main.utils.UIUtils;

public class RegistrationActivity extends AppCompatActivity {

    FragmentRegistrationBinding binding;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.actionLogin.setOnClickListener(v -> loginRedirect());
        UIUtils.addUnderlineFlag(binding.actionLogin);

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        viewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
                startActivity(intent);
            }
        });

        binding.actionRegister.setOnClickListener(registration -> {

            if (!binding.emailLogin.getText().toString().isEmpty() && !binding.passwordLogin.getText().toString().isEmpty()) {
                binding.actionRegister.setEnabled(true);
                viewModel.register(
                        binding.emailLogin.getText().toString().trim(),
                        binding.passwordLogin.getText().toString().trim());
            } else {
                binding.actionRegister.setEnabled(false);
                Toast.makeText(getApplicationContext(),
                        "All Fields are Required!", Toast.LENGTH_LONG).show();
            }
        });

    }

    public void onResume() {
        super.onResume();
    }

    public void loginRedirect() {
        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(loginIntent);
    }

}
