package com.midterm.doixiucoffee_mobileapp.View;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataSong;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.Model.Order;
import com.midterm.doixiucoffee_mobileapp.Model.Song;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.CategoryAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentBookingBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class BookingFragment extends Fragment {
    private ArrayList<Category> listCategory;
    private CategoryAdapter categoryAdapter;
    private FragmentBookingBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_booking, null, false);

        Song firstSong = DataSong.getInstance().getPlayingSong();
        binding.include.tvNameMusic.setText(firstSong.getName());
        binding.include.tvNameArtist.setText(firstSong.getSinger());
        Picasso.get().load(firstSong.getImage()).into(binding.include.imgMusic);

        //Setup ava và order hiện tại nếu có
        String idPersonLogin = DataPerson.getInstance().getIdPersonLogin();

        if(!idPersonLogin.equals("null")) {
            binding.toolbar.imgAva.setVisibility(View.VISIBLE);
            binding.toolbar.btnLogin.setVisibility(View.GONE);
            String img = DataPerson.getInstance().getUserById(idPersonLogin).getImage();
            binding.toolbar.imgAva.setImageBitmap(DataPerson.getInstance().base64toBitmap(img));

            Order o = new Order();
            o = DataOrder.getInstance().findOrderByIdUser(idPersonLogin);
            if(o != null && (o.getStatus().equals("booking")||o.getStatus().equals("waiting"))){
                DataOrder.getInstance().setOrder(o);
            }
        }



        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listCategory = new ArrayList<>(DataDrink.getInstance().getMenu());
        if(listCategory.size()!=0){
            categoryAdapter = new CategoryAdapter(listCategory, 0);
            Log.d("Check","Size menu: "+categoryAdapter.getItemCount());
            binding.rvCategory.setAdapter(categoryAdapter);
        }

        binding.btnAdd.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Nếu như chưa đăng nhập thì hiện thông báo yêu cầu đăng nhập
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
                if(DataPerson.getInstance().getIdPersonLogin().equals("null")){
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else Navigation.findNavController(v).navigate(R.id.addDrinkFragment);
            }
        });

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });



        binding.toolbar.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

        //Xem thoong tin cá nhân khi nhấn vào ava
        binding.toolbar.imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.infoUserFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }



}