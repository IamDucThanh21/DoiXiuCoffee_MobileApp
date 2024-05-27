package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.User;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_login, null, false);
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.etPhone.getText().toString().trim();
                for(User user: DataPerson.getInstance().getAllUser()){
                    if(phone.equals(user.getPhoneNumber())){
                        DataPerson.getInstance().setIdPersonLogin(user.getIdPerson());
                        Navigation.findNavController(v).navigate(R.id.homeFragment);
//                        User u = new User();
//                        u = DataPerson.getInstance().getUserLogin();
//
//                        Log.d("test image", u.getImage());
                    }
                }
                binding.error.setVisibility(View.VISIBLE);
            }
        });

        View view = binding.getRoot();
        return view;
    }
}