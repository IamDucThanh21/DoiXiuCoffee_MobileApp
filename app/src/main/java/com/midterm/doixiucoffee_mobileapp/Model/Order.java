package com.midterm.doixiucoffee_mobileapp.Model;

import java.util.ArrayList;

public class Order {
    private String idOrder;
    private int table;
    private ArrayList<Drink> listDrinks;
    private int totalPrice =0;
    private boolean status;
    private User user;
    private int discount;
    private String note;

    public Order(String idOrder, int table, ArrayList<Drink> listDrinks, int totalPrice, boolean status, User user, int discount, String note) {
        this.idOrder = idOrder;
        this.table = table;
        this.listDrinks = listDrinks;
        this.totalPrice = totalPrice;
        this.status = status;
        this.user = user;
        this.discount = discount;
        this.note = note;
    }

    public Order(){
        this.listDrinks = new ArrayList<>();
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public ArrayList<Drink> getListDrinks() {
        return listDrinks;
    }

    public void setListDrinks(ArrayList<Drink> listDrinks) {
        this.listDrinks = listDrinks;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void removeDrink(String idDrink, int size){
        int i =0;
        String s = "M";
        if(size == 1) s = "L";
        for(Drink dr : listDrinks){
            if(dr.getIdDrink().equals(idDrink)){
                if(dr.getSizeInfo().getSize().equals(s)){
                    listDrinks.remove(i);
                    return;
                }
            }
            i++;
        }
    }
}
