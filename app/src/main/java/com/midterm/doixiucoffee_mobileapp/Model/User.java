package com.midterm.doixiucoffee_mobileapp.Model;

import java.util.ArrayList;

public class User extends Person{
    private ArrayList<Feedback> listMyFeedback;
    private ArrayList<Order> historyOrder;
    private int point;

    public User(String idPerson, String phoneNumber, String name, ArrayList<Feedback> listMyFeedback, ArrayList<Order> historyOrder, int point) {
        super(idPerson, phoneNumber, name);
        this.listMyFeedback = listMyFeedback;
        this.historyOrder = historyOrder;
        this.point = point;
    }
}
