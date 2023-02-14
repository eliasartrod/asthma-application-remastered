package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_introduction), getString(R.string.section_one_introduction_description), R.drawable.ic_lung_normal));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pOne), getString(R.string.section_one_description_pOne), R.drawable.ic_lung_abnormal));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pTwo), getString(R.string.section_one_description_pTwo), R.drawable.ic_cough));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pThree), getString(R.string.section_one_description_pThree), R.drawable.ic_pathology));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pFive), getString(R.string.section_one_description_pFive), R.drawable.ic_asthma_trigger));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pSix), getString(R.string.section_one_description_pSix), R.drawable.ic_clinical_studies));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_title_pSeven), getString(R.string.section_one_description_pSeven), R.drawable.ic_facts));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_one_references), getString(R.string.section_one_references_description), R.drawable.ic_references));
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
