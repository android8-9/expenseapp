package com.cheekupeeku.expensetrackerapp.model;

public class User {
  private int id;
  private String mobile;
  private String password;
  public User(){}
    public User(int id, String mobile, String password) {
        this.id = id;
        this.mobile = mobile;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
