package com.midterm.doixiucoffee_mobileapp.Model;

public class Song {
    private String name;
    private String singer;
    private int votes;

    public Song(String name, String singer, int votes) {
        this.name = name;
        this.singer = singer;
        this.votes = votes;
    }

    public Song(){

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
}
