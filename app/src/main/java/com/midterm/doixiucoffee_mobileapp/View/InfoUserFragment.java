package com.midterm.doixiucoffee_mobileapp.View;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Model.User;
import com.midterm.doixiucoffee_mobileapp.R;
import com.midterm.doixiucoffee_mobileapp.databinding.FragmentInfoUserBinding;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InfoUserFragment extends Fragment {

    FragmentInfoUserBinding binding;
    private final int GALLERY_REQ_CODE = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_info_user, null, false);
        // Inflate the layout for this fragment

        binding.ava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });

        String idPersonLogin = DataPerson.getInstance().getIdPersonLogin();

        if(!idPersonLogin.equals("null")) {
            binding.ava.setVisibility(View.VISIBLE);

            String img = DataPerson.getInstance().getPersonById(idPersonLogin).getImage();
            if (!img.equals("")) {
                binding.ava.setImageBitmap(DataPerson.getInstance().base64toBitmap(img));
            }
        }

        return inflater.inflate(R.layout.fragment_info_user, container, false);
        User userLogin = new User();
        userLogin = DataPerson.getInstance().getUserById(DataPerson.getInstance().getIdPersonLogin());

        binding.tvName.setText(userLogin.getName());

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataPerson.getInstance().setIdPersonLogin("");
                Navigation.findNavController(v).navigate(R.id.homeFragment);
            }
        });

        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQ_CODE) {
            if (data != null) {
                Uri imageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                    binding.ava.setImageBitmap(bitmap);

                    // Chuyển đổi Bitmap thành chuỗi Base64
                    String base64String = bitmapToBase64(bitmap);

                    // Lưu chuỗi Base64 vào Firestore
                    DataPerson.getInstance().uploadImageToFirestore(base64String);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}