package com.kevinpelgrims.pictureplayground.model;

public class Picture {
    public String url;
    public String name;
    public String description;

    public Picture() {
    }

    public Picture(String url, String name, String description) {
        this.url = url;
        this.name = name;
        this.description = description;
    }
}
