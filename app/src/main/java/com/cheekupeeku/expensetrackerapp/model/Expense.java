package com.cheekupeeku.expensetrackerapp.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Expense {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("categoryId")
    @Expose
    private int categoryId;
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("edate")
    @Expose
    private String edate;
    @SerializedName("amount")
    @Expose
    private int amount;
    @SerializedName("paymentMode")
    @Expose
    private String paymentMode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

}