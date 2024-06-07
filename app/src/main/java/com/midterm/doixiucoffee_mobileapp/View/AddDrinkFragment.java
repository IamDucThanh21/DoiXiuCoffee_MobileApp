package com.midterm.doixiucoffee_mobileapp.View;

import static com.midterm.doixiucoffee_mobileapp.R.id;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.Category;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.ViewModel.CategoryAdapter;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentAddDrinkBinding;

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
        
        //setup avatar
        String idPersonLogin = DataPerson.getInstance().getIdPersonLogin();
        String img = DataPerson.getInstance().getPersonById(idPersonLogin).getImage();
        binding.toolbar.imgAva.setVisibility(View.VISIBLE);
        binding.toolbar.imgAva.setImageBitmap(DataPerson.getInstance().base64toBitmap(img));
        binding.toolbar.btnLogin.setVisibility(View.GONE);

        //setup menu
        listCategory = new ArrayList<>(DataDrink.getInstance().getMenu());
        categoryAdapter = new CategoryAdapter(listCategory, 1);
        binding.rvCategory.setLayoutManager(new LinearLayoutManager(this.getContext()));
        binding.rvCategory.setAdapter(categoryAdapter);

        //Thêm fragment chọn món ở dưới
        ListOdersFragment listOdersFragment = ListOdersFragment.newInstance();

        getActivity().getSupportFragmentManager().beginTransaction()
                .add(id.sub_fragment, listOdersFragment )
                .commit();

        //Setup nút back
        binding.toolbar.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(id.bookingFragment);
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