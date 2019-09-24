package com.example.foodhut.Model;

public class Offer {
    private String Name;
    private String Image;

    public Offer() {
    }

    public Offer(String name, String image) {
        Name = name;
        Image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
