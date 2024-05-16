package com.midterm.doixiucoffee_mobileapp.Model;

public class Person {
    private String idPerson;
    private String phoneNumber;
    private String name;

    public Person(String idPerson, String phoneNumber, String name) {
        this.idPerson = idPerson;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public Person(){

    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
