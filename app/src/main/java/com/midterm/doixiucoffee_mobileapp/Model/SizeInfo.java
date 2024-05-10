package com.midterm.doixiucoffee_mobileapp.Model;

public class SizeInfo {
    private String size;
    private int price;

    public SizeInfo(String size, int price) {
        this.size = size;
        this.price = price;
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
}
