package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.User;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentInfoUserBinding;

public class InfoUserFragment extends Fragment {

    FragmentInfoUserBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_info_user, null, false);
        // Inflate the layout for this fragment

        User userLogin = new User();
        userLogin = DataPerson.getInstance().getUserById(DataPerson.getInstance().getIdPersonLogin());

        binding.tvName.setText(userLogin.getName());

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPerson.getInstance().setIdPersonLogin("");
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }
}