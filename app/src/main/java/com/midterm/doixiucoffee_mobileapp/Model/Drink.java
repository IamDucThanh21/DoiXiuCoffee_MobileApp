package com.midterm.doixiucoffee_mobileapp.Model;

public class Drink {
    private String idDrink;
    private String drinkName;
    private String typeDrink;
    private char Size;
    private int price;
    private String story;

    public Drink(String idDrink, String drinkName, String typeDrink, char size, int price, String story) {
        this.idDrink = idDrink;
        this.drinkName = drinkName;
        this.typeDrink = typeDrink;
        Size = size;
        this.price = price;
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

    public char getSize() {
        return Size;
    }

    public void setSize(char size) {
        Size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
