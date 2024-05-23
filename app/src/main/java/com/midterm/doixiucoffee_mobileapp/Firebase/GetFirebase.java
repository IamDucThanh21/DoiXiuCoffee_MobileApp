package com.midterm.doixiucoffee_mobileapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.Model.Drink;
import com.midterm.doixiucoffee_mobileapp.Model.SizeInfo;
import com.midterm.doixiucoffee_mobileapp.ViewModel.CategoryAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentBookingBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GetFirebase {

    private ArrayList<Category> listCategory;
    private CategoryAdapter categoryAdapter;
    private static GetFirebase firesbase;
    public static GetFirebase getInstance(){
        if(firesbase == null){
            firesbase = new GetFirebase();
        }
        return firesbase;
    }

    public GetFirebase(){};

    public void getDataMenu(FragmentBookingBinding binding){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        db.collection("Category").get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            listCategory = new ArrayList<>();
                            for(QueryDocumentSnapshot document: task.getResult()){
                                ArrayList<Drink> listDrink = new ArrayList<>(setData(document));
                                listCategory.add(new Category(document.getId(), document.get("name").toString(),listDrink, document.get("info").toString()));
                            }

                            categoryAdapter = new CategoryAdapter(listCategory);

                            binding.rvCategory.setAdapter(categoryAdapter);
                        }
                    }
                });
    }
    public ArrayList<Drink> setData(QueryDocumentSnapshot document){
        List<Object> i = (List<Object>) document.get("drink");
        ArrayList<Drink> listDrink = new ArrayList<>();

        for(Object dr :i){
            Map<String, Object> dr1 = (Map<String, Object>) dr;
            Map<String, Long> size = (Map<String, Long>) dr1.get("size");

            ArrayList<SizeInfo> listSize = new ArrayList<>();
            listSize.add(new SizeInfo("M", size.get("M").intValue(), dr1.get("name").toString() ));
            listSize.add(new SizeInfo("L", size.get("L").intValue(), dr1.get("name").toString() ));

            Drink drink = new Drink("", dr1.get("name").toString(),document.getId().toString(), listSize, dr1.get("story").toString() );
            listDrink.add(drink);
        }

        return listDrink;

//        Map<String, Object> drink = (Map<String, Object>) i.get(0);
//
//        Map<String, Integer> size = (Map<String, Integer>) drink.get("size");
//
//        Log.d("ahah", String.valueOf(drink.get("story")));
    }
}