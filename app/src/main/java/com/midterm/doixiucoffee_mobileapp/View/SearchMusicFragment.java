package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentMusicBinding;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentSearchMusicBinding;

public class SearchMusicFragment extends Fragment {

    FragmentSearchMusicBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_search_music, null, false);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_music, container, false);
    }
}