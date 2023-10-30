package com.example.shortURL.vo;

public class UrlID {
    private static final UrlID ID = new UrlID();
    private int id;

    private UrlID() {
        this.id = 100000;
    }

    public static UrlID getInstance() {
        return ID;
    }

    public int getId() {
        this.id++;
        return this.id;
    }
}
