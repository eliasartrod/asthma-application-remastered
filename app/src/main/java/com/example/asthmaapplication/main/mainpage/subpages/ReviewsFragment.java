package com.example.asthmaapplication.main.mainpage.subpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentReviewsBinding;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainViewModel;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class ReviewsFragment extends BaseFragment {
    MainViewModel viewModel;
    FragmentReviewsBinding binding;

    @Inject
    public ReviewsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReviewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferences();
        setActionBarTitle();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.reviews_page_title));
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}