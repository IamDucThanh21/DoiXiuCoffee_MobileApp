package com.midterm.doixiucoffee_mobileapp.View;

import static com.midterm.doixiucoffee_mobileapp.R.*;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.imageview.ShapeableImageView;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.CategoryAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentAddDrinkBinding;
import com.midterm.doixiucoffee_mobileapp.databinding.ItemCategoryBinding;

import java.util.ArrayList;

public class AddDrinkFragment extends Fragment {

    private FragmentAddDrinkBinding binding;
    private ArrayList<Category> listCategory;
    private CategoryAdapter categoryAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_add_drink, null, false);

        listCategory = new ArrayList<>(DataDrink.getInstance().getMenu());
        categoryAdapter = new CategoryAdapter(listCategory, 1);
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.rvCategory.setAdapter(categoryAdapter);

        ListOdersFragment listOdersFragment = ListOdersFragment.newInstance();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(id.sub_fragment, listOdersFragment )
                .commit();

        View view = binding.getRoot();
        return view;
    }
}