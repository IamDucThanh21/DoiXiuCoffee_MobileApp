package com.midterm.doixiucoffee_mobileapp.Model;

import java.util.ArrayList;

public class User extends Person{
    private ArrayList<Feedback> listMyFeedback;
    private ArrayList<Order> historyOrder;
    private int point;

    public User(){

    }

    public User(String idPerson, String phoneNumber, String name, ArrayList<Feedback> listMyFeedback, ArrayList<Order> historyOrder, int point) {
        super(idPerson, phoneNumber, name);
        this.listMyFeedback = listMyFeedback;
        this.historyOrder = historyOrder;
        this.point = point;
    }

    public String getIdUser(){
        return getIdPerson();
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public ArrayList<Feedback> getListMyFeedback() {
        return listMyFeedback;
    }

    public void setListMyFeedback(ArrayList<Feedback> listMyFeedback) {
        this.listMyFeedback = listMyFeedback;
    }

    public ArrayList<Order> getHistoryOrder() {
        return historyOrder;
    }

    public void setHistoryOrder(ArrayList<Order> historyOrder) {
        this.historyOrder = historyOrder;
    }
}
