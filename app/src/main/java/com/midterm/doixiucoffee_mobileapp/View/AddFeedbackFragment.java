package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.Timestamp;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataFeedback;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.Feedback;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentAddFeedbackBinding;


import java.util.Date;
import java.time.Instant;

public class AddFeedbackFragment extends Fragment {
    FragmentAddFeedbackBinding binding;
    public AddFeedbackFragment(){}
    public static AddFeedbackFragment newInstance(){
        AddFeedbackFragment addFeedbackFragment = new AddFeedbackFragment();
        return addFeedbackFragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_add_feedback, null, false);

        binding.btnAddFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date = new Date();
                Timestamp timestamp = new Timestamp(date);
                Feedback newFeedback = new Feedback("null"
                        , DataPerson.getInstance().getUserById(DataPerson.getInstance().getIdPersonLogin())
                        , timestamp
                        , binding.etWriteFeedback.getText().toString()
                        , binding.cbPublic.isChecked()
                        , binding.cbIgconite.isChecked());
                DataFeedback.getInstance().addNewFeedback(DataPerson.getInstance().getIdPersonLogin()
                                                        , timestamp
                                                        , binding.etWriteFeedback.getText().toString()
                                                        , binding.cbIgconite.isChecked()
                                                        , binding.cbPublic.isChecked());
                DataFeedback.getInstance().addFeedBack(newFeedback);
                Log.d("contentttt", newFeedback.getContent());
                Navigation.findNavController(v).navigate(R.id.feedbackFragment);
            }
        });

        binding.etWriteFeedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.rvNoteSoChu.setText(binding.etWriteFeedback.length() + "/100");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // Inflate the layout for this fragment
        View view = binding.getRoot();
        return view;
    }
}