package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataFeedback;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataSong;
import com.midterm.doixiucoffee_mobileapp.R;

public class HomeFragment extends Fragment {
    private RelativeLayout openBooking;
    private RelativeLayout openMusic;
    private RelativeLayout openFeedback;
    private ImageView imgAva;
    private TextView btnLogin;
    private TextView textview;
    private TextView tvBooking;
    private TextView tvMusic;
    private TextView tvFeedback;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        DataDrink.getInstance().getDataMenu();
        DataPerson.getInstance().getDataPerson();
        DataSong.getInstance().getAllSong();
        //DataFeedback.getInstance().getAllFeedback();
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
                if(DataPerson.getInstance().isAdminById(DataPerson.getInstance().getIdPersonLogin())){
                    Navigation.findNavController(v).navigate(R.id.adminBookingFragment);
                }
                else
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

        imgAva = view.findViewById(R.id.imgAva);
        btnLogin = view.findViewById(R.id.btnLogin);

        if(!DataPerson.getInstance().getIdPersonLogin().equals("null")){
            imgAva.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
            if(DataPerson.getInstance().isAdminById(DataPerson.getInstance().getIdPersonLogin())){
                textview = view.findViewById(R.id.textView);
                textview.setText("Admin");
                tvBooking = view.findViewById(R.id.tv_booking);
                tvMusic = view.findViewById(R.id.tv_music);
                tvFeedback = view.findViewById(R.id.tv_feedback);
                tvBooking.setText("Quản lí đặt món");
                tvMusic.setText("Quản lí nhạc");
                tvFeedback.setText("Quản lí feedback");
            }
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

    }
}