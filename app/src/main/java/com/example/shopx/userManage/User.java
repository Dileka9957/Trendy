package com.example.shopx.userManage;

public class User {
    String username, email,address, phoneNo, password;
    public User() {
    }
    public User(String username, String email, String address, String phoneNo, String password) {
        this.username = username;
        this.email = email;
        this.address = address;
        this.phoneNo = phoneNo;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}