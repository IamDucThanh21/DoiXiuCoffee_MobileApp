package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.midterm.doixiucoffee_mobileapp.Firebase.Firestore;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.SizeInfo;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.CategoryAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentBookingBinding;

import java.util.ArrayList;

public class BookingFragment extends Fragment {
    private ArrayList<Category> listCategory;
    private CategoryAdapter categoryAdapter;
    private FragmentBookingBinding binding;
//    private RecyclerView rvCategory;
//    private ImageView btnBack;

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

        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this.getContext()));
        listCategory = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(listCategory);
        binding.rvCategory.setAdapter(categoryAdapter);

        khoitaodulieumau();

        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }

    public void khoitaodulieumau(){
        ArrayList<SizeInfo> sizePhinden = new ArrayList<>(Firestore.getFullSize());


        ArrayList<SizeInfo> sizePhinsua = new ArrayList<>();
        sizePhinsua.add(new SizeInfo("M", 15, null));
        sizePhinsua.add(new SizeInfo("L", 18, null));

        ArrayList<Drink> listDrink = new ArrayList<>();
        listDrink.add(new Drink("dr01", "Phin đen", "ca01", sizePhinden, "Cau chuyen"));
        listDrink.add(new Drink("dr02", "Phin sữa", "ca01", sizePhinsua, "Cau chuyen"));

        listCategory.add(new Category("ca01", "Cà phê", listDrink, "Đây là thông tin"));
        listCategory.add(new Category("ca02", "Trà", listDrink, "Đây là thông tin"));
    }
}