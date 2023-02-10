package com.example.asthmaapplication.main.mainpage.subpages;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.asthmaapplication.R;
import com.example.asthmaapplication.databinding.FragmentLearningBinding;
import com.example.asthmaapplication.main.common.BaseFragment;
import com.example.asthmaapplication.main.mainpage.MainViewModel;
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.SectionFiveFragment;
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.SectionFourFragment;
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.SectionOneFragment;
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.SectionThreeFragment;
import com.example.asthmaapplication.main.mainpage.subpages.learningpages.SectionTwoFragment;
import com.google.android.material.tabs.TabLayoutMediator;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

public class LearningFragment extends BaseFragment {
    public static final String FRAGMENT_POSITION = "fragment.position";
    MainViewModel viewModel;
    FragmentLearningBinding binding;
    SharedPreferences preferences;

    @Inject
    public LearningFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLearningBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@Nullable View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getPreferences();
        setActionBarTitle();
        setTabLayouts();
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

    public void setTabLayouts() {
        int startingPosition = this.getActivity().getIntent().getIntExtra(FRAGMENT_POSITION, 0);

        LearningManagementAdapter adapter = new LearningManagementAdapter(this);
        binding.fragmentPager.setAdapter(adapter);

        new TabLayoutMediator(binding.tabs, binding.fragmentPager, ((tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText(R.string.section_one);
                    break;
                case 1:
                    tab.setText(R.string.section_two);
                    break;
                case 2:
                    tab.setText(R.string.section_three);
                    break;
                case 3:
                    tab.setText(R.string.section_four);
                    break;
                case 4:
                    tab.setText(R.string.section_five);
                    break;
            }

        })).attach();

        binding.fragmentPager.setCurrentItem(startingPosition);

    }

    public void setActionBarTitle() {
        setActionBarTitle(getString(R.string.learning_page_title));
    }

    @Override
    public View getRoot() {
        return binding.getRoot();
    }

    static class LearningManagementAdapter extends FragmentStateAdapter {
        public LearningManagementAdapter(@NonNull @NotNull Fragment fragment) {
            super(fragment);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new SectionOneFragment();
                case 1:
                    return new SectionTwoFragment();
                case 2:
                    return new SectionThreeFragment();
                case 3:
                    return new SectionFourFragment();
                default:
                    return new SectionFiveFragment();
            }
        }

        @Override
        public int getItemCount() {
            return 5;
        }
    }
}
