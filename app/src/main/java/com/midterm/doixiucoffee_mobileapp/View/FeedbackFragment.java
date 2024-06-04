package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataFeedback;
import com.midterm.doixiucoffee_mobileapp.Model.Feedback;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.FeebackAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentFeedbackBinding;

import java.time.ZoneId;
import java.util.ArrayList;

public class FeedbackFragment extends Fragment {
    private ArrayList<Feedback> listFeedback;
    private FeebackAdapter feebackAdapter;
    private FragmentFeedbackBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_feedback, null, false);

        binding.includeHb.homeBackImg.setImageResource(R.drawable.feedback_back);
        binding.includeHb.homeBackTitle.setText(R.string.feedback);

        listFeedback = new ArrayList<>();
        listFeedback = DataFeedback.getInstance().getListFeedback();

        feebackAdapter = new FeebackAdapter(listFeedback);

        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.rvCategory.setAdapter(feebackAdapter);

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

//        Long time = 16250L;
//        Timestamp timestamp = new Timestamp(Instant.ofEpochSecond(time));
//        DataFeedback.getInstance().addNewFeedback("us02", timestamp , "This is content", true, true);

        binding.addFeedbackBtn.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(v).navigate(R.id.searchMusicFragment);

                AddFeedbackFragment addFeedbackFragment = AddFeedbackFragment.newInstance();

                binding.frameLayout.setVisibility(View.VISIBLE);
                binding.addFeedbackBtn.addddd.setVisibility(View.GONE);
                binding.include.music.setVisibility(View.GONE);

                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.frameLayout, addFeedbackFragment)
                        .commit();
            }
        });


        View viewRoot = binding.getRoot();
        // Inflate the layout for this fragment
        return viewRoot;
    }

}