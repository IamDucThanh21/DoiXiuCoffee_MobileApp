package com.midterm.doixiucoffee_mobileapp.Model;

import java.util.ArrayList;

public class Drink {
    private String idDrink;
    private String drinkName;
    private String typeDrink;
    private ArrayList<SizeInfo> sizeInfos;
    private String story;

    public Drink(String idDrink, String drinkName, String typeDrink, ArrayList<SizeInfo> sizeInfos, String story) {
        this.idDrink = idDrink;
        this.drinkName = drinkName;
        this.typeDrink = typeDrink;
        this.sizeInfos = sizeInfos;
        this.story = story;
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
}
