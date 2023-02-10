package com.example.asthmaapplication.main.mainpage;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentMainBinding;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.homepage.HomePageFragment;
import com.example.asthmaapplication.main.mainpage.subpages.LearningBaseFragment;
import com.example.asthmaapplication.main.mainpage.subpages.PatientsFragment;
import com.example.asthmaapplication.main.mainpage.subpages.QuizzesExamsFragment;
import com.example.asthmaapplication.main.mainpage.subpages.ReviewsFragment;
import com.example.asthmaapplication.main.utils.UIUtils;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class MainFragment extends BaseFragment {
    MainViewModel viewModel;
    FragmentMainBinding binding;
    FragmentManager manager;
    FragmentTransaction transaction;
    SharedPreferences preferences;

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
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferences();
        setActionBarTitle();

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

        binding.learningCard.cardImage.setImageResource(R.drawable.ic_lung_icon);
        binding.learningCard.cardTitle.setText(R.string.learning_card);
        binding.learningCard.getRoot().setOnClickListener(v -> launchLearningPage());

        binding.patientsCard.cardImage.setImageResource(R.drawable.ic_patient_icon);
        binding.patientsCard.cardTitle.setText(R.string.patient_card);
        binding.patientsCard.getRoot().setOnClickListener(v -> launchPatientsCard());

        binding.quizCard.cardImage.setImageResource(R.drawable.ic_quiz_icon);
        binding.quizCard.cardTitle.setText(R.string.quiz_card);
        binding.quizCard.getRoot().setOnClickListener(v -> launchQuizPage());

        binding.reviewCard.cardImage.setImageResource(R.drawable.ic_review_icon);
        binding.reviewCard.cardTitle.setText(R.string.review_card);
        binding.reviewCard.getRoot().setOnClickListener(v -> launchReviewsPage());

        UIUtils.addUnderlineFlag(binding.actionLogout);

        binding.actionLogout.setOnClickListener(userLogout -> {
            viewModel.logOut();
            getActivity().finish();
            setUserNamePreferences();
            launchHomePage();
        });

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

    public void setActionBarTitle() {
        if (preferences.getString("user.name", "").isEmpty()) {
            binding.actionLogout.setText(getString(R.string.redirect_home));
            setActionBarTitle(getString(R.string.welcome_user, getString(R.string.guest_user)));
        } else {
            binding.actionLogout.setText(getString(R.string.log_out));
            setActionBarTitle(getString(R.string.welcome_user, preferences.getString("user.name", "")));
        }
    }

    public void setUserNamePreferences() {
        preferences.edit().putString("user.name", "").apply();
    }

    public void launchLearningPage() {
        LearningBaseFragment fragment = new LearningBaseFragment();
        transaction
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();

    }

    public void launchPatientsCard() {
        PatientsFragment fragment = new PatientsFragment();
        transaction
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void launchReviewsPage() {
        ReviewsFragment fragment = new ReviewsFragment();
        transaction
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void launchQuizPage() {
        QuizzesExamsFragment fragment = new QuizzesExamsFragment();
        transaction
                .addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    public void launchHomePage() {
        HomePageFragment fragment = new HomePageFragment();
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