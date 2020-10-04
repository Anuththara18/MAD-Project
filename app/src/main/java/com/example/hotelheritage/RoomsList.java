package com.example.hotelheritage;

public class RoomsList {

    int image1, image2;
    String name, price;

    public RoomsList(int image1, String name, int image2, String price) {
        this.image1 = image1;
        this.image2 = image2;
        this.name = name;
        this.price = price;
    }

    public int getImage1() {
        return image1;
    }

    public int getImage2() {
        return image2;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

}
