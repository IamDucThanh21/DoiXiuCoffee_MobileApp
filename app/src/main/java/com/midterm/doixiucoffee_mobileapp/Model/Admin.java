package com.midterm.doixiucoffee_mobileapp.Model;

public class Admin extends Person{
    private String password;
    public Admin(String idPerson, String phoneNumber, String name) {
//        super(idPerson, phoneNumber, name);
    }

    public Admin(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
