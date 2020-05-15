package com.awareness.shopping.entity;

public class Merchant {
    private String name;
    private int icon;
    private int backgroundImage;

    public Merchant(String name, int icon, int backgroundImage) {
        this.name = name;
        this.icon = icon;
        this.backgroundImage = backgroundImage;
    }

    public String getName() {
        return name;
    }

    public int getIcon() {
        return icon;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }
}
