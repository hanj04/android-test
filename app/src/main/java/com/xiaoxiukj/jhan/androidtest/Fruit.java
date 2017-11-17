package com.xiaoxiukj.jhan.androidtest;

/**
 * Created by jhan on 5/18/17.
 */

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String n, int im) {
        name = n;
        imageId = im;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
