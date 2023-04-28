package com.example.agencyapp;

public class Eventsgetset {
    public Eventsgetset(String artTitle, String artDescription, String imgurl, String uniqueKey) {
    }

    String Title , description, selectyear , name1 , name2 ,phn1 ,phn2,reglink , Image, Key;

    public Eventsgetset(String title, String description, String image, String date, String time, String key) {
        this.Title = title;
        this.description = description;
        this.Image = image;
        this.Key = key;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }


    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
