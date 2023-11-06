package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentSectionThreeBinding;
import com.example.asthmaapplication.main.adapter.AsthmaInfoAdapter;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainViewModel;
import com.example.asthmaapplication.main.model.AsthmaInfoModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class SectionThreeFragment extends BaseFragment {
    FragmentSectionThreeBinding binding;
    AsthmaInfoAdapter adapter;
    ArrayList<AsthmaInfoModel> asthmaInfoModels = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSectionThreeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setActionBarTitle();
        createSectionThreeInfo();
        setupAdapter();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title));
    }

    public void createSectionThreeInfo() {
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_three_title_one), getString(R.string.section_three_description_one), R.drawable.ic_pathology_two));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_three_title_two), getString(R.string.section_three_description_two), R.drawable.ic_lung_abnormal));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_three_title_three), getString(R.string.section_three_description_three), R.drawable.ic_obstruction));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_three_title_four), getString(R.string.section_three_description_four), R.drawable.ic_bronchial_hyperresponsiveness));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_three_reference), getString(R.string.section_three_reference_description), R.drawable.ic_references));

    }
    public void setupAdapter() {
        binding.sectionTitle.setText(getString(R.string.section_three));
        adapter = new AsthmaInfoAdapter(getContext(), asthmaInfoModels);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
