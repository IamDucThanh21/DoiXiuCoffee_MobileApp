package com.midterm.doixiucoffee_mobileapp.Model;

import android.util.Log;

import com.midterm.doixiucoffee_mobileapp.Firebase.DataDrink;

import java.util.ArrayList;

public class Drink {
    private String idDrink;
    private String drinkName;
    private String typeDrink;
    private ArrayList<SizeInfo> sizeInfos;
    private SizeInfo sizeInfo;
    private String story;
    private String note;

    public Drink(String idDrink, String drinkName, String typeDrink, ArrayList<SizeInfo> sizeInfos, String story) {
        this.idDrink = idDrink;
        this.drinkName = drinkName;
        this.typeDrink = typeDrink;
        this.sizeInfos = sizeInfos;
        this.story = story;
    }

    public Drink(String idDrink, String drinkName, String typeDrink, SizeInfo sizeInfo, String story) {
        this.idDrink = idDrink;
        this.drinkName = drinkName;
        this.typeDrink = typeDrink;
        this.sizeInfo = sizeInfo;
        this.story = story;
    }

    public Drink(String idDrink_, int size){
        Drink drink = new Drink();
        drink = DataDrink.getInstance().getDrinkById(idDrink_);
        this.idDrink = drink.getIdDrink();
        this.drinkName = drink.getDrinkName();
        this.typeDrink = drink.getTypeDrink();
        this.story = drink.getStory();
        this.sizeInfo = drink.getSizeInfos().get(size);
    }

    public Drink(){

    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getTypeDrink() {
        return typeDrink;
    }

    public void setTypeDrink(String typeDrink) {
        this.typeDrink = typeDrink;
    }

    public ArrayList<SizeInfo> getSizeInfos() {
        return sizeInfos;
    }

    public void setSizeInfos(ArrayList<SizeInfo> sizeInfos) {
        this.sizeInfos = sizeInfos;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public SizeInfo getSizeInfo() {
        return sizeInfo;
    }

    public void setSizeInfo(SizeInfo sizeInfo) {
        this.sizeInfo = sizeInfo;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
