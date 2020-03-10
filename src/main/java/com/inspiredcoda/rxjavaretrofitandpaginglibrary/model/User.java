package com.inspiredcoda.rxjavaretrofitandpaginglibrary.model;

import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String body;

    public User(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public User() { }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
