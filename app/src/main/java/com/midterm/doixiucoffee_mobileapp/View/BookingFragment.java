package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
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

    private FirebaseFirestore database;

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
//        listCategory = new ArrayList<>();
//        categoryAdapter = new CategoryAdapter(listCategory);
//        binding.rvCategory.setAdapter(categoryAdapter);
//
//        khoitaodulieumau();

        getDataByStrDoc("si01");

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

        ArrayList<SizeInfo> sizePhinden = new ArrayList<>();
        sizePhinden.add(new SizeInfo("M", 15, null));
        sizePhinden.add(new SizeInfo("L", 20, null));

//        setData(sizePhinden, "si01");
//        setData(sizePhinden, "si02");
//        sizePhinden.add(getDataByStrDoc("si01"));
//        sizePhinden.add(getDataByStrDoc("si02"));


//        ArrayList<SizeInfo> sizePhinsua = new ArrayList<>();
//        sizePhinsua.add(new SizeInfo("M", 15, null));
//        sizePhinsua.add(new SizeInfo("L", 18, null));
//
//        ArrayList<Drink> listDrink = new ArrayList<>();
//        listDrink.add(new Drink("dr01", "Phin đen", "ca01", sizePhinden, "Cau chuyen"));
//        listDrink.add(new Drink("dr02", "Phin sữa", "ca01", sizePhinsua, "Cau chuyen"));
//
//        listCategory.add(new Category("ca01", "Cà phê", listDrink, "Đây là thông tin"));
//        listCategory.add(new Category("ca02", "Trà", listDrink, "Đây là thông tin"));
    }

//    public void setData(ArrayList<SizeInfo> sizeInfo, String doc){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("SizeInfo").document(doc).get().
//                addOnCompleteListener(task -> {
//                    DocumentSnapshot document = task.getResult();
//                    int price = Math.toIntExact(document.getLong("price"));
//                    String name = document.getString("name");
//                    String idDrink = document.getString("idDrink");
//
//                    Log.d("data", String.valueOf(price));
//                    Log.d("data", name);
//                    Log.d("data", idDrink);
//
//                    sizeInfo.add(new SizeInfo((String) document.getString("name"), Math.toIntExact(document.getLong("price")), (String) document.getString("idDrink")));
//                });
//    }
    public void getDataByStrDoc(String doc){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        SizeInfo sizeInfoNew = new SizeInfo();
        db.collection("SizeInfo").document(doc).get().
                addOnCompleteListener(task -> {
                    DocumentSnapshot document = task.getResult();
                    int price = Math.toIntExact(document.getLong("price"));
                    String name = document.getString("name");
                    String idDrink = document.getString("idDrink");

                    Log.d("data", String.valueOf(price));
                    Log.d("data", name);
                    Log.d("data", idDrink);

                    sizeInfoNew.setSize(name);
                    sizeInfoNew.setPrice(price);
                    sizeInfoNew.setIdDrink(idDrink);

                    ArrayList<SizeInfo> sizePhinsua = new ArrayList<>();
                    sizePhinsua.add(sizeInfoNew);
                    sizePhinsua.add(new SizeInfo("L", 18, null));

                    listCategory = new ArrayList<>();
                    categoryAdapter = new CategoryAdapter(listCategory);
                    binding.rvCategory.setAdapter(categoryAdapter);

                    ArrayList<Drink> listDrink = new ArrayList<>();
                    listDrink.add(new Drink("dr01", "Phin đen", "ca01", sizePhinsua, "Cau chuyen"));
//                    listDrink.add(new Drink("dr02", "Phin sữa", "ca01", sizePhinsua, "Cau chuyen"));

                    listCategory.add(new Category("ca01", "Cà phê", listDrink, "Đây là thông tin"));
                });
//        return sizeInfoNew;
    }

    
}