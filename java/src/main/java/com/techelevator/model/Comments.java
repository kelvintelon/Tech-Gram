package com.techelevator.model;

import java.sql.Timestamp;

public class Comments {
    int comment_id;
    int photo_id;
    int user_id;
    String text;
    Timestamp date_and_time;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Comments(){};

    public Comments(int comment_id, int photo_id, int user_id, String text, Timestamp date_and_time, String username) {
        this.comment_id = comment_id;
        this.photo_id = photo_id;
        this.user_id = user_id;
        this.text = text;
        this.date_and_time = date_and_time;
        this.username = username;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(Timestamp date_and_time) {
        this.date_and_time = date_and_time;
    }
}