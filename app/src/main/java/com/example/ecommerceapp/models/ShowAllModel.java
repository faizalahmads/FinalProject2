package com.example.ecommerceapp.models;

import java.io.Serializable;

public class ShowAllModel implements Serializable {

    String description;
    String name;
    String type;
    int price;
    String img_url;

    public ShowAllModel() {
    }

    public ShowAllModel(String description, String name, String type, int price, String img_url) {
        this.description = description;
        this.name = name;
        this.type = type;
        this.price = price;
        this.img_url = img_url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
