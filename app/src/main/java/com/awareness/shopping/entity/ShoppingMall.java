package com.awareness.shopping.entity;

public class ShoppingMall {
    private String shoppingMallLabel;
    private double latitude;
    private double longitude;
    private double radius;

    public ShoppingMall(String shoppingMallLabel, double latitude, double longitude,double radius) {
        this.shoppingMallLabel = shoppingMallLabel;
        this.latitude = latitude;
        this.longitude = longitude;
        this.radius = radius;
    }

    public String getShoppingMallLabel() {
        return shoppingMallLabel;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getRadius() {
        return radius;
    }
}
