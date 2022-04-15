package com.techelevator.model;

import org.springframework.data.relational.core.sql.In;

import java.sql.Timestamp;
import java.util.List;

public class Favorites {
    int favorite_id;
    int photo_id;
    int user_id;
    String text;
    Timestamp date_and_time;
    private List<Comments> comments;
    private int likeCount;
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Favorites() {}

    public Favorites(int favorite_id, int photo_id, int user_id, String text, Timestamp date_and_time) {
        this.favorite_id = favorite_id;
        this.photo_id = photo_id;
        this.user_id = user_id;
        this.text = text;
        this.date_and_time = date_and_time;
    }

    public int getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(int favorite_id) {
        this.favorite_id = favorite_id;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(Timestamp date_and_time) {
        this.date_and_time = date_and_time;
    }
}
