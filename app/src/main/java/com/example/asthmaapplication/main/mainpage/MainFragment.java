package com.example.asthmaapplication.main.mainpage;

import static com.example.asthmaapplication.main.mainpage.MainActivity.USER_EMAIL;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentMainBinding;
import com.example.asthmaapplication.main.common.BaseActivity;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.homepage.HomePageActivity;
import com.example.asthmaapplication.main.utils.UIUtils;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class MainFragment extends BaseFragment {

    MainViewModel viewModel;
    FragmentMainBinding binding;

    String currentUser;
    String userTrimmed;

    @Inject
    public MainFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @javax.annotation.Nullable ViewGroup container, @javax.annotation.Nullable Bundle savedInstanceState) {
        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@javax.annotation.Nullable View view, @javax.annotation.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setActionBarTitle(getString(R.string.welcome_user, getString(R.string.returned_user)));

        binding.learningCard.cardImage.setImageResource(R.drawable.ic_lung_icon);
        binding.learningCard.cardTitle.setText(R.string.learning_card);

        binding.patientsCard.cardImage.setImageResource(R.drawable.ic_patient_icon);
        binding.patientsCard.cardTitle.setText(R.string.patient_card);

        binding.quizCard.cardImage.setImageResource(R.drawable.ic_quiz_icon);
        binding.quizCard.cardTitle.setText(R.string.quiz_card);

        binding.reviewCard.cardImage.setImageResource(R.drawable.ic_review_icon);
        binding.reviewCard.cardTitle.setText(R.string.review_card);

        UIUtils.addUnderlineFlag(binding.actionLogout);

        binding.actionLogout.setOnClickListener(userLogout -> {
            viewModel.logOut();
            Intent intent = new Intent(getContext(), HomePageActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}