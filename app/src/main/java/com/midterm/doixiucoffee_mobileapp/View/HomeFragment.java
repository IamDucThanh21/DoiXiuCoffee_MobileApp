package com.midterm.doixiucoffee_mobileapp.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.midterm.doixiucoffee_mobileapp.MainActivity;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.databinding.ActivityMainBinding;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private RelativeLayout openBooking;
    private RelativeLayout openMusic;
    private RelativeLayout openFeedback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openBooking = view.findViewById(R.id.open_booking);

        openBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bookingFragment);
            }
        });

        openMusic = view.findViewById(R.id.open_music);

        openMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.musicFragment);
            }
        });

        openFeedback = view.findViewById(R.id.open_feedback);

        openFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.feedbackFragment);
            }
        });
    }
}