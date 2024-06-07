package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.Admin;
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
                getActivity().getSupportFragmentManager().popBackStack();
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
                    }
                }
                for(Admin admin: DataPerson.getInstance().getAllAdmin()){
                    if(phone.equals(admin.getPhoneNumber())){
                        binding.layoutPwd.setVisibility(View.VISIBLE);
                        binding.layoutBtnLogin.setVisibility(View.GONE);
                        binding.layoutBtnLoginAdmin.setVisibility(View.VISIBLE);
                        return;
                    }
                }
                binding.error.setVisibility(View.VISIBLE);
            }
        });

        binding.loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.etPhone.getText().toString().trim();
                String pwd = binding.etPwd.getText().toString().trim();
                for(Admin admin: DataPerson.getInstance().getAllAdmin()){
                    if(phone.equals(admin.getPhoneNumber())){
                        if(pwd.equals(admin.getPassword())){
                            DataPerson.getInstance().setIdPersonLogin(admin.getIdPerson());
                            Navigation.findNavController(v).navigate(R.id.homeFragment);
                        }
                        else{
                            binding.error.setVisibility(View.VISIBLE);
                            binding.error.setText("Mật khẩu không chính xác!");
                            binding.etPwd.setText("");
                        }
                    }
                }
            }
        });

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.signupFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }
}