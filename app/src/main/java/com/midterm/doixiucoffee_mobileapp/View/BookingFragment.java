package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.midterm.doixiucoffee_mobileapp.Firebase.Firestore;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.SizeInfo;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.CategoryAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentBookingBinding;

import java.util.ArrayList;
import java.util.List;

public class BookingFragment extends Fragment {
    private ArrayList<Category> listCategory;
    private CategoryAdapter categoryAdapter;
    private FragmentBookingBinding binding;


//    private RecyclerView rvCategory;
//    private ImageView btnBack;

    private Firestore database;

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

        ArrayList<SizeInfo> sizePhinden = new ArrayList<>();
//        sizePhinden.add(new SizeInfo("M", 15, null));
//        sizePhinden.add(new SizeInfo("L", 20, null));

        database.getDataSizeInfoFromDocument("SizeInfo", "si01", new Firestore.FirestoreCallbackDataSizeInfo() {
            @Override
            public void onCallback(SizeInfo sizeInfo) {
                sizePhinden.add(new SizeInfo(sizeInfo.getSize(), sizeInfo.getPrice(), sizeInfo.getIdDrink()));
            }
        });

        database.getDataSizeInfoFromDocument("SizeInfo", "si02", new Firestore.FirestoreCallbackDataSizeInfo() {
            @Override
            public void onCallback(SizeInfo sizeInfo) {
                sizePhinden.add(new SizeInfo(sizeInfo.getSize(), sizeInfo.getPrice(), sizeInfo.getIdDrink()));
            }
        });

        ArrayList<SizeInfo> sizePhinsua = new ArrayList<>();
        sizePhinsua.add(new SizeInfo("M", 15, null));
        sizePhinsua.add(new SizeInfo("L", 18, null));

        ArrayList<Drink> listDrink = new ArrayList<>();
        listDrink.add(new Drink("dr01", "Phin đen", "ca01", sizePhinden, "Cau chuyen"));
        listDrink.add(new Drink("dr02", "Phin sữa", "ca01", sizePhinsua, "Cau chuyen"));

        listCategory.add(new Category("ca01", "Cà phê", listDrink, "Đây là thông tin"));
        listCategory.add(new Category("ca02", "Trà", listDrink, "Đây là thông tin"));
    }

    private void fetchDocumentName(String collection){
        ArrayList<String> arrayList = new ArrayList<>();
        database.getListDocumentFromCollection(collection, new Firestore.FirestoreCallbackString() {
            @Override
            public void onCallback(ArrayList<String> list) {
                if (list != null) {
                    arrayList.clear();
                    arrayList.addAll(list);

                    // In tất cả các giá trị ra Log.d
                    for (String documentName : arrayList) {
                        Log.d("Size Info", documentName);
                        fetchDataFromSizeInfo(collection, documentName);
                    }

                    // Gọi các hàm khác cần sử dụng documentNamesList ở đây
                } else {
                    // Xử lý lỗi ở đây
                    Log.d("FirestoreDocumentName", "Error getting documents.");
                }
            }
        });
    }

    private void fetchDataFromSizeInfo(String collection, String document){
        SizeInfo sizeInf = new SizeInfo("", 0, "");
        database.getDataSizeInfoFromDocument(collection, document, new Firestore.FirestoreCallbackDataSizeInfo() {
            @Override
            public void onCallback(SizeInfo sizeInfo) {
                 sizeInf.setSize(sizeInfo.getSize());
                 sizeInf.setPrice(sizeInfo.getPrice());
                 sizeInf.setIdDrink(sizeInfo.getIdDrink());
            }
        });
    }
}