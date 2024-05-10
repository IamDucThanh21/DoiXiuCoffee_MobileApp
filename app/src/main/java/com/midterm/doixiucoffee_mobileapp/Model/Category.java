package com.midterm.doixiucoffee_mobileapp.Model;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private String idCategory;
    private String categoryName;
    private ArrayList<Drink> listDrink;
    private String categoryInfo;

    public Category(String idCategory, String categoryName, ArrayList<Drink> listDrink, String categoryInfo) {
        this.idCategory = idCategory;
        this.categoryName = categoryName;
        this.listDrink = listDrink;
        this.categoryInfo = categoryInfo;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<Drink> getListDrink() {
        return listDrink;
    }

    public void setListDrink(ArrayList<Drink> listDrink) {
        this.listDrink = listDrink;
    }

    public String getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(String categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
}
