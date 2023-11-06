package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentSectionFourBinding;
import com.example.asthmaapplication.main.adapter.AsthmaInfoAdapter;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainViewModel;
import com.example.asthmaapplication.main.model.AsthmaInfoModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class SectionFourFragment extends BaseFragment {
    FragmentSectionFourBinding binding;
    AsthmaInfoAdapter adapter;
    ArrayList<AsthmaInfoModel> asthmaInfoModels = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSectionFourBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setActionBarTitle();
        createSectionFourInfo();
        setupAdapter();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title));
    }

    public void createSectionFourInfo() {
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_four_title_one), getString(R.string.section_four_description_one), R.drawable.ic_pathology_two));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_four_title_two), getString(R.string.section_four_description_two), R.drawable.ic_asthma_children));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_four_title_three), getString(R.string.section_four_description_three), R.drawable.ic_lung_abnormal));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_four_reference), getString(R.string.section_four_reference_description), R.drawable.ic_references));

    }
    public void setupAdapter() {
        binding.sectionTitle.setText(getString(R.string.section_four));
        adapter = new AsthmaInfoAdapter(getContext(), asthmaInfoModels);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
