package com.midterm.doixiucoffee_mobileapp.Model;

public class SizeInfo {
    private String size;
    private int price;
    private String idDrink;

    public SizeInfo(String size, int price, String idDrink) {
        this.size = size;
        this.price = price;
        this.idDrink = idDrink;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }
}
