package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentSectionOneBinding;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainViewModel;
import com.example.asthmaapplication.main.adapter.AsthmaInfoAdapter;
import com.example.asthmaapplication.main.model.AsthmaInfoModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class SectionOneFragment extends BaseFragment {
    FragmentSectionOneBinding binding;
    MainViewModel viewModel;
    AsthmaInfoAdapter adapter;
    ArrayList<AsthmaInfoModel> asthmaInfoModels = new ArrayList<>();

    @Inject
    public SectionOneFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @javax.annotation.Nullable ViewGroup container, @javax.annotation.Nullable Bundle savedInstanceState) {
        binding = FragmentSectionOneBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferences();
        setActionBarTitle();
        createSectionOneInfo();
        setUpAdapter();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title));
    }

    public void createSectionOneInfo() {
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_introduction), getString(R.string.section_one_introduction_description), R.drawable.ic_section_one));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pOne), getString(R.string.section_one_description_pOne), R.drawable.ic_lung_icon));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pTwo), getString(R.string.section_one_description_pTwo), R.drawable.ic_section_two));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pThree), getString(R.string.section_one_description_pThree), R.drawable.ic_section_three));

    }

    public void setUpAdapter() {
        binding.sectionTitle.setText(getString(R.string.section_one));
        adapter = new AsthmaInfoAdapter(getContext(), asthmaInfoModels);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
