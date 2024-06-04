package com.midterm.doixiucoffee_mobileapp.View;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataFeedback;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataSong;
import com.midterm.doixiucoffee_mobileapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HomeFragment extends Fragment {
    private RelativeLayout openBooking;
    private RelativeLayout openMusic;
    private RelativeLayout openFeedback;
    private ImageView imgAva;
    private TextView btnLogin;
    private final int GALLERY_REQ_CODE = 1000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        DataDrink.getInstance().getDataMenu();
        DataPerson.getInstance().getDataPerson();
        DataSong.getInstance().getAllSong();
        DataFeedback.getInstance().getAllFeedback();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        openBooking = view.findViewById(R.id.open_booking);

        openBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.bookingFragment);
            }
        });

        openMusic = view.findViewById(R.id.open_music);

        openMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.musicFragment);
            }
        });

        openFeedback = view.findViewById(R.id.open_feedback);

        openFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.feedbackFragment);
            }
        });

        imgAva = view.findViewById(R.id.imgAva);

        imgAva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iGallery = new Intent(Intent.ACTION_PICK);
                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(iGallery, GALLERY_REQ_CODE);
            }
        });

        btnLogin = view.findViewById(R.id.btnLogin);

        if(!DataPerson.getInstance().getIdPersonLogin().equals("null")){
            imgAva.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        }

        
//            String id = DataPerson.getInstance().getIdPersonLogin();
//            String img = DataPerson.getInstance().getUserById(id).getImage();
//            imgAva.setImageBitmap(DataPerson.getInstance().base64toBitmap(img));





        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == GALLERY_REQ_CODE) {
            if (data != null) {
                Uri imageUri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), imageUri);
                    imgAva.setImageBitmap(bitmap);

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