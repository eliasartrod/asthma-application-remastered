package com.example.asthmaapplication.main.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.asthmaapplication.databinding.FragmentHomePageBinding;
import com.example.asthmaapplication.main.main.MainActivity;
import com.example.asthmaapplication.main.main.UserViewModel;

public class HomePageActivity extends AppCompatActivity {

    FragmentHomePageBinding binding;
    UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentHomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        viewModel.getUserLiveData().observe(this, firebaseUser -> {
            if (firebaseUser != null) {
                binding.fragmentLoggedinLoggedInUser.setText("Logged In User: " + firebaseUser.getEmail());
                binding.fragmentLoggedinLogOut.setEnabled(true);
            } else {
                binding.fragmentLoggedinLogOut.setEnabled(false);
                binding.fragmentLoggedinLogOut.setVisibility(View.INVISIBLE);
            }
        });

        binding.fragmentLoggedinLogOut.setOnClickListener(logOut -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            viewModel.logOut();
        });

    }
}
