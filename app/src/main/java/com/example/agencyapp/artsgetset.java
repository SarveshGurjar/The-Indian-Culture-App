package com.example.agencyapp;

public class artsgetset {
    String Title , Description , Image , Key;

    public artsgetset(String title, String description ,String image,String key) {
        Title = title;
        Description = description;
        Image = image;
        Key = key;
    }


    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public artsgetset() {
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

}
