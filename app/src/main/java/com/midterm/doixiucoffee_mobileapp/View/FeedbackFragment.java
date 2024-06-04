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

        listFeedback = DataFeedback.getInstance().getListFeedback();
        feebackAdapter = new FeebackAdapter(listFeedback);

        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.rvCategory.setAdapter(feebackAdapter);

//        listFeedback.add(new Feedback("fb01", new User("us01", "0", "Đức Thành", null, null, 8),new Timestamp(2024,5), "Tôi cảm thấy Việt Thanh rất giỏi và đáng mến, tôi rất ngưỡng mộ anh ấy.", false, false));
//        listFeedback.add(new Feedback("fb02", new User("us02", "0", "Hoàng Huy", null, null, 8),new Timestamp(2024,5), "Việt Thanh siêu cute, dễ thương, đẹp trai. Tôi rất ghen tỵ với Việt Thanh, và muốn được như anh ấy!", false, false));

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });
//        if(listFeedback.size() != 0){
//            for(Feedback fb : listFeedback){
//                Log.d("test id", fb.getIdFeedback());
//                Log.d("Test content", fb.getContent());
//                Log.d("Test date", String.valueOf(fb.getDate().toDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate()));
//                Log.d("Test user", fb.getUser().getPhoneNumber());
//                Log.d("Test isPublic", String.valueOf(fb.getPublic()));
//                Log.d("Test incognito", String.valueOf(fb.getIncognito()));
//            }
//        }
//        else Log.d("Lỗi" , "Không có dữ liệu feedback");

//        Long time = 16250L;
//        Timestamp timestamp = new Timestamp(Instant.ofEpochSecond(time));
//        DataFeedback.getInstance().addNewFeedback("us02", timestamp , "This is content", true, true);
        View viewRoot = binding.getRoot();
        // Inflate the layout for this fragment
        return viewRoot;
    }

}