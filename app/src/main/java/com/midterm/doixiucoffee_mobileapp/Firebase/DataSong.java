package com.midterm.doixiucoffee_mobileapp.Firebase;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.midterm.doixiucoffee_mobileapp.Model.Song;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataSong {
    private ArrayList<Song> listSong;
    private static DataSong dataSong;
    private Song playingSong;

    public static DataSong getInstance(){
        if(dataSong == null){
            dataSong = new DataSong();
        }
        return dataSong;
    }

    public void setPlayingSong(Song playingSong) {
        this.playingSong = playingSong;
    }

    public Song getPlayingSong() {
        return playingSong;
    }

    public void getAllSong(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Song").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            listSong = new ArrayList<>();
                            for(QueryDocumentSnapshot document : task.getResult()){
                                listSong.add(getDataSong(document));
                            }
                        }
                    }
                });
    }

    public boolean checkSong(Song song){
        boolean var = false;
        for (Song checkSong : listSong){
            if (Objects.equals(song.getIdSong(), checkSong.getIdSong())){
                var = true;
                break;
            }
        }
        return var;
    }

    public Song getDataSong(QueryDocumentSnapshot document){
        Song song = new Song();
        song.setIdSong((String) document.get("id"));
        song.setVotes(((Long) document.get("vote")).intValue());
        song.setName((String) document.get("songName"));
        song.setImage((String) document.get("image"));
        song.setSinger((String) document.get("singer"));
        return song;
    }
    public void addNewSong(Song newSong){
        if(listSong == null){
            listSong = new ArrayList<>();
        }
        listSong.add(newSong);

        Map<String, Object> dataSong = new HashMap<>();
        dataSong.put("songName", newSong.getName());
        dataSong.put("id", newSong.getIdSong());
        dataSong.put("vote", newSong.getVotes());
        dataSong.put("image", newSong.getImage());
        dataSong.put("singer", newSong.getSinger());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Song").document(newSong.getIdSong()).set(dataSong)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("Debug", "Add success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Debug", "Add fail");
                    }
                });
    }

    public void deleteMusicById(String idMusic){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Song").document(idMusic).delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Delete music", "Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Delete music", "Fail");
                    }
                });
    }

    public void updateVote(String idSong){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> update = new HashMap<>();
        update.put("vote", FieldValue.increment(1));

        db.collection("Song").document(idSong).update(update)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Update vote", "Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Update vote", "Fail");
                    }
                });
    }

    public void getPlayingSongFromFirebase(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("SongPlaying").document("song").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            playingSong.setIdSong((String) document.get("id"));
                            playingSong.setImage((String) document.get("image"));
                            playingSong.setVotes((Integer) document.get("vote"));
                            playingSong.setSinger((String) document.get("singer"));
                            playingSong.setName((String) document.get("songName"));
                        }
                    }
                });
    }

    public void setPlayingSongToFirebase(Song song){
        Map<String, Object> dataPlayingSong = new HashMap<>();
        dataPlayingSong.put("id", song.getIdSong());
        dataPlayingSong.put("image", song.getImage());
        dataPlayingSong.put("vote", song.getVotes());
        dataPlayingSong.put("singer", song.getSinger());
        dataPlayingSong.put("songName", song.getName());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("SongPlaying").document("song").update(dataPlayingSong)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("Update Playing Song", "Success");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Update Playing Song", "Fail");
                    }
                });
    }

    public ArrayList<Song> getListSong(){ return listSong;}
}
