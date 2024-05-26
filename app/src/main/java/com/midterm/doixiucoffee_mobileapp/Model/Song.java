package com.midterm.doixiucoffee_mobileapp.Model;

import java.net.URL;

public class Song {
    private String idSong;
    private String name;
    private String singer;
    private int votes;
    private String image;

    public Song(String idSong, String name, String singer, int votes, String image) {
        this.idSong = idSong;
        this.name = name;
        this.singer = singer;
        this.votes = votes;
        this.image = image;
    }

    public Song(){

    }

    public String getIdSong() {
        return idSong;
    }

    public void setIdSong(String idSong) {
        this.idSong = idSong;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
