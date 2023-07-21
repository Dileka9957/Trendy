package com.example.shopx;

public class ItemModel {
    String title;
    String description;
    String prize;
    String contact;
    String image;

    public ItemModel(String title, String description, String prize, String contact, String image) {
        this.title = title;
        this.description = description;
        this.prize = prize;
        this.contact = contact;
        this.image = image;
    }
    public ItemModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
