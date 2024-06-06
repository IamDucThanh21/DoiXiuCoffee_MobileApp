package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentSignupBinding;

public class SignupFragment extends Fragment {

    FragmentSignupBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_signup, null, false);

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.etPhone.getText().toString().trim();
                String name = binding.etName.getText().toString().trim();

                if (!DataPerson.getInstance().checkPhone(phone)){
                    binding.error.setText("Số điện thoại đã được đăng kí");
                    binding.error.setVisibility(View.VISIBLE);
                }else if (phone.equals("") || name.equals("")){
                    binding.error.setText("Vui lòng nhập đầy đủ thông tin");
                    binding.error.setVisibility(View.VISIBLE);
                }
                else {
                    DataPerson.getInstance().addNewUser(name, phone);
                    Navigation.findNavController(v).navigate(R.id.loginFragment);
                }
            }
        });

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }
}