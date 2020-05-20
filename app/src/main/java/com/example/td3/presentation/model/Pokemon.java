package com.example.td3.presentation.model;

public class Pokemon {
    private String name;
    private String url;
    private String size;


    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getNumber() {
        String[] urlPart = url.split("/");
        return Integer.parseInt(urlPart[urlPart.length - 1]);
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
