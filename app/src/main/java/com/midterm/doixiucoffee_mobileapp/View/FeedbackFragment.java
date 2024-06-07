package com.midterm.doixiucoffee_mobileapp.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataFeedback;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.Feedback;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.FeebackAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentFeedbackBinding;

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

        //Setup ava, back, title
        String idPersonLogin = DataPerson.getInstance().getIdPersonLogin();
        if (!idPersonLogin.equals("null")){
            binding.toolbar.imgAva.setVisibility(View.VISIBLE);
            String img = DataPerson.getInstance().getPersonById(idPersonLogin).getImage();
            binding.toolbar.imgAva.setImageBitmap(DataPerson.getInstance().base64toBitmap(img));
            binding.toolbar.btnLogin.setVisibility(View.GONE);
        }
        binding.includeHb.homeBackImg.setImageResource(R.drawable.feedback_back);
        binding.includeHb.homeBackTitle.setText(R.string.feedback);

        //Lấy danh sách feedback
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

        //Setup sự kiện cho nút thêm feedback, cần phải đăng nhập để viết feedback
        binding.addFeedbackBtn.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(v).navigate(R.id.searchMusicFragment);

                if(idPersonLogin.equals("null")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext()); // 'this' là context của Activity hoặc Fragment
                    builder.setTitle("Thông báo")
                            .setMessage("Bạn cần đăng nhập để tiếp tục!")
                            .setPositiveButton("Đăng nhập", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Navigation.findNavController(v).navigate(R.id.loginFragment);
                                }
                            })
                            .setNegativeButton("Thoát", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                //Nếu đã đăng nhập thêm fragment viết feedback vào fragment chính
                else{
                    AddFeedbackFragment addFeedbackFragment = AddFeedbackFragment.newInstance();

                    binding.frameLayout.setVisibility(View.VISIBLE);
                    binding.addFeedbackBtn.addddd.setVisibility(View.GONE);
                    binding.include.music.setVisibility(View.GONE);

                    getActivity().getSupportFragmentManager().beginTransaction()
                            .add(R.id.frameLayout, addFeedbackFragment)
                            .commit();
                }
            }
        });

        //Sự kiện nút xem feedback của mọi người -> tất cả
        binding.btnPublic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnPrivate.setTextColor(Color.parseColor("#6c6c6c"));
                binding.btnPublic.setTextColor(Color.parseColor("#1A2130"));
                feebackAdapter = new FeebackAdapter(listFeedback);
                binding.rvCategory.setAdapter(feebackAdapter);
            }
        });

        //Sự kiện nút xem feedback của mình
        binding.btnPrivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnPublic.setTextColor(Color.parseColor("#6c6c6c"));
                binding. btnPrivate.setTextColor(Color.parseColor("#1A2130"));
                ArrayList<Feedback> myFeedback = new ArrayList<>();
                for(Feedback fb : listFeedback){
                    if(fb.getUser().getIdPerson().equals(idPersonLogin)){
                        myFeedback.add(fb);
                    }
                }
                feebackAdapter = new FeebackAdapter(myFeedback);
                binding.rvCategory.setAdapter(feebackAdapter);
            }
        });

        //Xem thoong tin cá nhân khi nhấn vào ava
        binding.toolbar.imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.infoUserFragment);
            }
        });

        View viewRoot = binding.getRoot();
        return viewRoot;
    }

}