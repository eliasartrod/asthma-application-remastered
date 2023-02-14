package com.example.asthmaapplication.main.mainpage.subpages;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentLearningBaseBinding;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainViewModel;
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.LearningFragment;
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.LearningFragmentActivity;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class LearningBaseFragment extends BaseFragment {
    MainViewModel viewModel;
    FragmentLearningBaseBinding binding;
    FragmentManager manager;
    FragmentTransaction transaction;

    @Inject
    public LearningBaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @javax.annotation.Nullable ViewGroup container, @javax.annotation.Nullable Bundle savedInstanceState) {
        binding = FragmentLearningBaseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferences();
        setActionBarTitle();
        setupUI();

        manager = getFragmentManager();
        transaction = manager.beginTransaction();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title));
    }

    public void setupUI() {
        binding.sectionOneCard.cardImage.setImageResource(R.drawable.ic_section_one);
        binding.sectionOneCard.cardTitle.setText(R.string.section_one);
        binding.sectionOneCard.getRoot().setOnClickListener(v -> launchSectionOnePage());

        binding.sectionTwoCard.cardImage.setImageResource(R.drawable.ic_section_two);
        binding.sectionTwoCard.cardTitle.setText(R.string.section_two);
        binding.sectionTwoCard.getRoot().setOnClickListener(v -> launchSectionTwoPage());

        binding.sectionThreeCard.cardImage.setImageResource(R.drawable.ic_section_three);
        binding.sectionThreeCard.cardTitle.setText(R.string.section_three);
        binding.sectionThreeCard.getRoot().setOnClickListener(v -> launchSectionThreePage());

        binding.sectionFourCard.cardImage.setImageResource(R.drawable.ic_section_four);
        binding.sectionFourCard.cardTitle.setText(R.string.section_four);
        binding.sectionFourCard.getRoot().setOnClickListener(v -> launchSectionFourPage());

        binding.sectionFiveCard.cardImage.setImageResource(R.drawable.ic_section_five);
        binding.sectionFiveCard.cardTitle.setText(R.string.section_five);
        binding.sectionFiveCard.getRoot().setOnClickListener(v -> launchSectionFivePage());

        binding.reviewCard.cardImage.setImageResource(R.drawable.ic_review_icon);
        binding.reviewCard.cardTitle.setText(R.string.review_card);
        binding.reviewCard.getRoot().setOnClickListener(v -> launchReviewsPage());
    }

    public void launchSectionOnePage() {
        Intent intent = new Intent(getContext(), LearningFragmentActivity.class);
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 0);
        startActivity(intent);
    }

    public void launchSectionTwoPage() {
        Intent intent = new Intent(getContext(), LearningFragmentActivity.class);
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 1);
        startActivity(intent);
    }

    public void launchSectionThreePage() {
        Intent intent = new Intent(getContext(), LearningFragmentActivity.class);
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 2);
        startActivity(intent);
    }

    public void launchSectionFourPage() {
        Intent intent = new Intent(getContext(), LearningFragmentActivity.class);
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 3);
        startActivity(intent);
    }

    public void launchSectionFivePage() {
        Intent intent = new Intent(getContext(), LearningFragmentActivity.class);
        intent.putExtra(LearningFragment.FRAGMENT_POSITION, 4);
        startActivity(intent);
    }

    public void launchReviewsPage() {
        ReviewsFragment fragment = new ReviewsFragment();
        transaction.addToBackStack(null)
                .replace(R.id.fragment_container, fragment)
                .commit();
    }


    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
