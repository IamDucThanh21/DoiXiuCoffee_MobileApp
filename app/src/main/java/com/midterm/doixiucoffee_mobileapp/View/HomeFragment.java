package com.midterm.doixiucoffee_mobileapp.View;

import android.os.Bundle;
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
import com.midterm.doixiucoffee_mobileapp.Firebase.DataOrder;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataPerson;
import com.midterm.doixiucoffee_mobileapp.Firebase.DataSong;
import com.midterm.doixiucoffee_mobileapp.R;

public class HomeFragment extends Fragment {
    private RelativeLayout openBooking;
    private RelativeLayout openMusic;
    private RelativeLayout openFeedback;
    private ImageView imgAva;
    private TextView btnLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

        DataDrink.getInstance().getDataMenu();
        DataPerson.getInstance().getDataPerson();
        DataSong.getInstance().getAllSong();
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
        btnLogin = view.findViewById(R.id.btnLogin);

        if(!DataPerson.getInstance().getIdPersonLogin().equals("null")){
            imgAva.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.loginFragment);
            }
        });
    }

//    public void addNewUser(String id, String name, String phone, int point, String role){
//        Map<String, Object> dataUser = new HashMap<>();
//        dataUser.put("name", name);
//        dataUser.put("phone", phone);
//        dataUser.put("point", point);
//        dataUser.put("role", role);
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("User").document(id).set(dataUser)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//                        Log.d("Debug", "Add success");
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.d("Debug", "Add fail");
//                    }
//                });
//    }
//
//    public static String generateRandomId() {
//        Random random = new Random();
//        int number = random.nextInt(100); // Tạo số ngẫu nhiên từ 0 đến 99
//        String randomId = "us" + String.format("%02d", number); // Đảm bảo số có hai chữ số
//        ArrayList<User> listUser = new ArrayList<>();
//        listUser = DataPerson.getInstance().getAllUser();
//
//        for (User user : listUser){
//            while (randomId == user.getIdUser()){
//                randomId = "us" + String.format("%02d", random.nextInt(100));
//            }
//        }
//        return randomId;
//    }
}