package com.example.shopx.feedback;

public class Rate {
    String name;
    String details;

    public Rate(String name, String note) {
        this.name = name;
        this.details = note;
    }
    public Rate(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
