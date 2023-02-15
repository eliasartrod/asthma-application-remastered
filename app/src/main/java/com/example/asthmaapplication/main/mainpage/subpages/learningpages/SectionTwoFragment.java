package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentSectionTwoBinding;
import com.example.asthmaapplication.main.adapter.AsthmaInfoAdapter;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainViewModel;
import com.example.asthmaapplication.main.model.AsthmaInfoModel;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class SectionTwoFragment extends BaseFragment {
    FragmentSectionTwoBinding binding;
    MainViewModel viewModel;
    AsthmaInfoAdapter adapter;
    ArrayList<AsthmaInfoModel> asthmaInfoModels = new ArrayList<>();

    @Inject
    public SectionTwoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSectionTwoBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferences();
        setActionBarTitle();
        createSectionTwoInfo();
        setupAdapter();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title));
    }

    public void createSectionTwoInfo() {
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_title_one), getString(R.string.section_two_description_one), R.drawable.ic_lung_normal));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_title_two), getString(R.string.section_two_description_two), R.drawable.ic_doctor));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_title_three), getString(R.string.section_two_description_three), R.drawable.ic_exam_male));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_title_four), getString(R.string.section_two_description_four), R.drawable.ic_heartbeat));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_title_five), getString(R.string.section_two_description_five), R.drawable.ic_allergy));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_title_six), getString(R.string.section_two_description_six), R.drawable.ic_exam_female));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_title_seven), getString(R.string.section_two_description_seven), R.drawable.ic_lung_misc));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_title_eight), getString(R.string.section_two_description_eight), R.drawable.ic_asthma_children));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_two_reference), getString(R.string.section_two_reference_description), R.drawable.ic_references));

    }

    public void setupAdapter() {
        binding.sectionTitle.setText(getString(R.string.section_two));
        adapter = new AsthmaInfoAdapter(getContext(), asthmaInfoModels);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
