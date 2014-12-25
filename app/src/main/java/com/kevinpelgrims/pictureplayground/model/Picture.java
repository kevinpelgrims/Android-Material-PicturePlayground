package com.kevinpelgrims.pictureplayground.model;

import java.io.Serializable;

public class Picture implements Serializable {
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
