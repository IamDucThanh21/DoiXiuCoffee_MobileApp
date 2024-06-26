package com.midterm.doixiucoffee_mobileapp.Model;

import java.util.ArrayList;

public class Order {
    private String idOrder;
    private int table = -1;
    private ArrayList<Drink> listDrinks;
    private int totalPrice =0;
    private String status = "booking";
    private User user;
    private int discount;

    public Order(String idOrder, int table, ArrayList<Drink> listDrinks, int totalPrice, String status, User user, int discount) {
        this.idOrder = idOrder;
        this.table = table;
        this.listDrinks = listDrinks;
        this.totalPrice = totalPrice;
        this.status = status;
        this.user = user;
        this.discount = discount;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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
