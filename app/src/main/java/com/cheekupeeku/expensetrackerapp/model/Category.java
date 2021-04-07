package com.cheekupeeku.expensetrackerapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Category {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("categoryName")
    @Expose
    private String categoryName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
   public String toString(){
        return categoryName;
   }
}