package com.example.asthmaapplication.main.mainpage.subpages.learningpages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentSectionFiveBinding;
import com.example.asthmaapplication.main.adapter.AsthmaInfoAdapter;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainViewModel;
import com.example.asthmaapplication.main.model.AsthmaInfoModel;
import com.example.asthmaapplication.main.utils.UIUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.annotations.NonNull;

@AndroidEntryPoint
public class SectionFiveFragment extends BaseFragment {
    FragmentSectionFiveBinding binding;
    MainViewModel viewModel;
    AsthmaInfoAdapter adapter;
    ArrayList<AsthmaInfoModel> asthmaInfoModels = new ArrayList<>();

    @Inject
    public SectionFiveFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSectionFiveBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferences();
        setActionBarTitle();
        createSectionFiveInfo();
        setupAdapter();
        UIUtils.addUnderlineFlag(binding.actionFinish);

        binding.actionFinish.setOnClickListener(v -> getActivity().finish());

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title));
    }

    public void createSectionFiveInfo() {
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_one), getString(R.string.section_five_description_one), R.drawable.ic_exam_male));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_two), getString(R.string.section_five_description_two), R.drawable.ic_food_medicine));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_three), getString(R.string.section_five_description_three), R.drawable.ic_cigarette));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_four), getString(R.string.section_five_description_four), R.drawable.ic_weather));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_five), getString(R.string.section_five_description_five), R.drawable.ic_pets));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_six), getString(R.string.section_five_description_six), R.drawable.ic_pests));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_seven), getString(R.string.section_five_description_seven), R.drawable.ic_dirty));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_eight), getString(R.string.section_five_description_eight), R.drawable.ic_exercise));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_nine), getString(R.string.section_five_description_nine), R.drawable.ic_emotion));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_title_ten), getString(R.string.section_five_description_ten), R.drawable.ic_odor));
        asthmaInfoModels.add(new AsthmaInfoModel(getString(R.string.section_five_reference), getString(R.string.section_five_reference_description), R.drawable.ic_references));

    }
    public void setupAdapter() {
        binding.sectionTitle.setText(getString(R.string.section_five));
        adapter = new AsthmaInfoAdapter(getContext(), asthmaInfoModels);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setAdapter(adapter);
    }


    @Override
    public View getRoot() {
        return binding.getRoot();
    }
}
